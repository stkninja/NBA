package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import po.PBasicInfoPO;
import po.TBasicInfoPO;

/*======================================================================*
 * 将ResultSet转化为对应的PO
 *======================================================================*/
public class RSToBasicPO {

	/*=================================================*
	 * rs仅有一条记录或空记录
	 * return null 或 对应的team类
	 *=================================================*/
	public static ArrayList<TBasicInfoPO> toTeamBasic(ResultSet rs) {
		ArrayList<String[]> arrays = to2DStringArray(rs);
		
		if(arrays == null)
			return null;
		else{
			ArrayList<TBasicInfoPO> ret = new ArrayList<TBasicInfoPO>();
			for(String[] a : arrays){
				TBasicInfoPO tb = new TBasicInfoPO();
				tb.setFullName(a[0]);
				tb.setAbbName(a[1]);
				tb.setHistoryFullName(a[2]);
				tb.setHistoryAbblName(a[3]);
				tb.setLocation(a[4]);
				tb.setCompetionArea(a[5]);
				tb.setSubArea(a[6]);
				tb.setHomeGround(a[7]);
				tb.setSetupTime(a[8]);
				ret.add(tb);
			}
			return ret;
		}
	}
	
	/*=================================================*
	 * rs仅有一条记录或空记录
	 * return null 或 对应的player类
	 *=================================================*/
	public static PBasicInfoPO toPlayerBasic(ResultSet rs){
		ArrayList<String[]> arrays = to2DStringArray(rs);
		if(arrays == null)
			return null;
		else{
			PBasicInfoPO pb = new PBasicInfoPO();
			pb.setName(arrays.get(0)[0].replace('#', '\''));
			pb.setExp(String.valueOf((Integer.parseInt(arrays.get(0)[2]) - Integer.parseInt(arrays.get(0)[1]) + 1)));
			pb.setPosition(arrays.get(0)[3]);
			pb.setHeight(arrays.get(0)[4]);
			pb.setWeight(arrays.get(0)[5]);
			pb.setBirth(arrays.get(0)[6].substring(0, 4) + "-" + arrays.get(0)[6].substring(4, 6) + "-" + arrays.get(0)[6].substring(6, 8));
			pb.setAge(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt((arrays.get(0)[6].substring(0, 4)))));
			pb.setSchool(arrays.get(0)[7].replace('#', '\''));
			return pb;
		}
	}
	
	/*=================================================*
	 * 将ResultSet转化为二维String数组
	 *=================================================*/
	public static ArrayList<String[]> to2DStringArray(ResultSet rs){
		try {
			//获得ResultSet的行数列数
			rs.last();
			int rowCount = rs.getRow();
			int columnCount = rs.getMetaData().getColumnCount();
			ArrayList<String[]> toReturn = new ArrayList<String[]>();
			rs.beforeFirst();
			
			//转化
			if(rowCount == 0)
				return null;
			
			for(; rs.next();){
				String[] temp = new String[columnCount];		
				for(int j = 0; j < columnCount; j++){
					temp[j] = rs.getString(j + 1);
				}
				toReturn.add(temp);
			}
			return toReturn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
