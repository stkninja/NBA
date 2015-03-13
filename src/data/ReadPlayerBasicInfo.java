package data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ReadPlayerBasicInfo {

	public static ArrayList<String> readPlayerBasicInfo(String addr) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			fis = new FileInputStream(addr);
			isr = new InputStreamReader(fis,"UTF-8"); 
			br = new BufferedReader(isr);
			
			int i = 0;
			String line = new String();
			while((line = br.readLine()) != null){
				if(i % 2 == 1)
					data.add(line);
				i++;
			}
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
	
	public static ImageIcon getPlayerPortrait(String addr){
		ImageIcon img = new ImageIcon(addr);
		return img;
	}
	
	public static ImageIcon getPlayerAction(String addr){
		ImageIcon img = new ImageIcon(addr);
		return img;
	}
}
