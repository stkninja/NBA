package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import po.PBasicInfoPO;
import po.TBasicInfoPO;

/*======================================================================*
 * ��ResultSetת��Ϊ��Ӧ��PO
 *======================================================================*/
public class RSToBasicPO {

	/*=================================================*
	 * rs����һ����¼��ռ�¼
	 * return null �� ��Ӧ��team��
	 *=================================================*/
	public static TBasicInfoPO toTeamBasic(ResultSet rs) {
		ArrayList<String[]> arrays = to2DStringArray(rs);
		if(arrays == null)
			return null;
		else{
			TBasicInfoPO tb = new TBasicInfoPO();
			tb.setFullName(arrays.get(0)[0]);
			tb.setAbbName(arrays.get(0)[1]);
			tb.setHistoryFullName(arrays.get(0)[2]);
			tb.setHistoryAbblName(arrays.get(0)[3]);
			tb.setLocation(arrays.get(0)[4]);
			tb.setCompetionArea(arrays.get(0)[5]);
			tb.setSubArea(arrays.get(0)[6]);
			tb.setHomeGround(arrays.get(0)[7]);
			tb.setSetupTime(arrays.get(0)[8]);
			return tb;
		}
	}
	
	/*=================================================*
	 * rs����һ����¼��ռ�¼
	 * return null �� ��Ӧ��player��
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
	 * ��ResultSetת��Ϊ��άString����
	 *=================================================*/
	public static ArrayList<String[]> to2DStringArray(ResultSet rs){
		try {
			//���ResultSet����������
			rs.last();
			int rowCount = rs.getRow();
			int columnCount = rs.getMetaData().getColumnCount();
			ArrayList<String[]> toReturn = new ArrayList<String[]>();
			rs.beforeFirst();
			
			//ת��
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
