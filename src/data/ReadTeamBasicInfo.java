package data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ReadTeamBasicInfo {
	public static ArrayList<String> readTeamBasicInfo(String addr) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			fis = new FileInputStream(addr);
			isr = new InputStreamReader(fis,"UTF-8"); 
			br = new BufferedReader(isr);
			
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
				fis.close();
				isr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public static ImageIcon readLogo(String addr){
		ImageIcon img = new ImageIcon(addr);
		return img;
	}
}
