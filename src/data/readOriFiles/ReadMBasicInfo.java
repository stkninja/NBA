package data.readOriFiles;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadMBasicInfo {

	public static ArrayList<String> readMBasicInfo(String addr) {
		ArrayList<String> data = new ArrayList<String>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			fis = new FileInputStream(addr);
			isr = new InputStreamReader(fis,"UTF-8"); 
			br = new BufferedReader(isr);
			
			String line = new String();
			while((line = br.readLine()) != null){
				data.add(line);
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
}
