package spider.htmlParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*================================================================================*
 HtmlParser��ʵ�ֶ�Ŀ����ַ��Դ��������� 
 *================================================================================*/
public class HtmlParser {  
    public static String getHtmlContent(URL url, String encode) {  
        StringBuffer contentBuffer = new StringBuffer();  
  
        int responseCode = -1;  
        HttpURLConnection con = null;  
        try {  
        	//��������
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");// IE������������  
            con.setConnectTimeout(60000);  
            con.setReadTimeout(60000); 
            
            // �����ҳ������Ϣ��  
            responseCode = con.getResponseCode();  
            if (responseCode == -1) {  
                System.out.println(url.toString() + " : connection is failure...");  
                con.disconnect();  
                return null;  
            }  
            if (responseCode >= 400) // ����ʧ��  
            {  
                System.out.println("����ʧ��:get response code: " + responseCode);  
                con.disconnect();  
                return null;  
            }  
            
            //ͨ��buffStr����ȡ��ҳԴ��
            InputStream inStr = con.getInputStream();  
            InputStreamReader istreamReader = new InputStreamReader(inStr, encode);  
            BufferedReader buffStr = new BufferedReader(istreamReader);  
  
            String str = null;  
            while ((str = buffStr.readLine()) != null)  
                contentBuffer.append(str + "\n");  
            inStr.close();  
            
        } catch (IOException e) {  
            e.printStackTrace();  
            contentBuffer = null;  
            System.out.println("error: " + url.toString());  
        } finally {  
            con.disconnect();  
        } 
        
        return contentBuffer.toString();  
    }  
  
    public static String getHtmlContent(String url, String encode) {  
    	//�淶������URL
        if (!url.toLowerCase().startsWith("http://")) {  
            url = "http://" + url;  
        }  
        try {  
            URL rUrl = new URL(url);  
            return getHtmlContent(rUrl, encode);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    
    //������main����
    public static void main(String argsp[]){  
        System.out.println(getHtmlContent("www.basketball-reference.com/boxscores/201410280LAL.html","utf-8")) ; 
    }  
}