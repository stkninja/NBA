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
		ArrayList<TBasicInfoPO> ret = new ArrayList<TBasicInfoPO>();

		try {
			while(true){
				if(rs.next()){
					TBasicInfoPO tb = new TBasicInfoPO();
					tb.setFullName(rs.getString(1));
					tb.setAbbName(rs.getString(2));
					tb.setHistoryFullName(rs.getString(3));
					tb.setHistoryAbblName(rs.getString(4));
					tb.setLocation(rs.getString(5));
					tb.setCompetionArea(rs.getString(6));
					tb.setSubArea(rs.getString(7));
					tb.setHomeGround(rs.getString(8));
					tb.setSetupTime(rs.getString(9));
					ret.add(tb);
				}
				else
					break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/*=================================================*
	 * rs仅有一条记录或空记录
	 * return null 或 对应的player类
	 *=================================================*/
	public static ArrayList<PBasicInfoPO> toPlayerBasic(ResultSet rs){
		ArrayList<PBasicInfoPO> pbs = new ArrayList<PBasicInfoPO>();
		try {
			while(true){
				if(rs.next()){
					PBasicInfoPO pb = new PBasicInfoPO();
					pb.setName(rs.getString(1).replace('#', '\''));
					pb.setExp(String.valueOf((Integer.parseInt(rs.getString(3)) - Integer.parseInt(rs.getString(2)) + 1)));
					pb.setPosition(rs.getString(4));
					pb.setHeight(rs.getString(5));
					pb.setWeight(rs.getString(6));
					if(!rs.getString(7).equals("")){
						pb.setBirth(rs.getString(7).substring(0, 4) + "-" + rs.getString(7).substring(4, 6) + "-" + rs.getString(7).substring(6, 8));
						pb.setAge(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt((rs.getString(7).substring(0, 4)))));					
					}
					pb.setSchool(rs.getString(8).replace('#', '\''));
					pbs.add(pb);
				}
				else
					break;
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return pbs;
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
