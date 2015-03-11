package datalogic;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadTeam {
	public static ArrayList<String> readTeamInfo(String addr) {
		FileInputStream f = null;
		BufferedReader br = null;
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			f = new FileInputStream(addr);
			br = new BufferedReader(new InputStreamReader(f));
			
			String line = new String();
			while((line = br.readLine()) != null){
				data.add(line);
			}
				
			/**É¾³ýÊ×Î²Á½ÐÐ*/
			data.remove(0);
			data.remove(data.size() - 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				f.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public static Image readLogo(String addr){
		
	}
}
