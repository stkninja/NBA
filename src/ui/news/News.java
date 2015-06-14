package ui.news;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import spider.htmlParser.HtmlParser;

/**
 * 新闻
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class News extends JPanel {
	private JScrollPane sp;
	private String content = "\n";
	private String image = "";
	/**
	 * 
	 * @param url 链接
	 */
	public News(String url) {
		//获得数据
		String[] line = HtmlParser.getHtmlContent(url, "utf-8").split("\n");
		String title = "";
		for (int i = 0; i < 20; i++) {
			if (line[i].indexOf("og:title\" content=\"") != -1) {
				title = line[i].replaceAll("<meta property=\"og:title\" content=\"", "");
				title = title.replaceAll("\" />", "");
				break;
			}
		}
		boolean start = false;
		int count = 0;
		for (int i = 0; i < line.length; i++) {
			if (line[i].indexOf("<!-- 一般全文 start -->") != -1)
				start = true;
			if (start && line[i].indexOf("<img src=\"") != -1)
				image = image + count + line[i].substring(line[i].indexOf("<img src=\"") + 10, line[i].indexOf("\" alt=\"")) +";";
			if (start && line[i].indexOf("<img alt=\"\" src=\"") != -1) {
				if (line[i].indexOf(".jpg") != -1)
					image = image + count + line[i].substring(line[i].indexOf("<img alt=\"\" src=\"") + 17, line[i].indexOf(".jpg") + 4);
				else if (line[i].indexOf(".png") != -1)
					image = image + count + line[i].substring(line[i].indexOf("<img alt=\"\" src=\"") + 17, line[i].indexOf(".png") + 4);
			}
			if (start && line[i].indexOf("<p>") != -1 && line[i].indexOf("<img alt=\"\" src=\"") == -1) {
				content = content + this.text(line[i]) +"\n\n";
				count++;
			}
			if (line[i].indexOf("<!-- 一般全文 end -->") != -1)
				break;
		}
		//显示
		this.setOpaque(false);
		this.setLayout(new BorderLayout(20, 20));
		
		JLabel label = new JLabel(title, JLabel.CENTER);
		label.setFont(new Font("黑体", Font.BOLD, 27));
		label.setForeground(new Color(25, 25, 112));
		JTextPane text = new JTextPane();
		text.setOpaque(false);
		text.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
		text.setFont(new Font("华文新魏", Font.PLAIN, 20));
		text.setText(content);
		
		String[] img = image.split(";");
		int[] position = new int[img.length];
		for (int i = 0; i < position.length; i++) {
			int temp = 0;
			for (int j = 0; j < img[i].charAt(0) - '0'; j++) {
				temp = content.indexOf("\n\n", temp + 1);
			}
			position[i] = temp;
		}
		for (int i = 0; i < img.length; i++) {
			try {
				if (position[i] == 0)
					text.setCaretPosition(0);
				else
					text.setCaretPosition(position[i] + i + 1);
				text.insertIcon(new ImageIcon(new URL(img[i].substring(1))));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		text.setCaretPosition(0);
		
		sp = new JScrollPane(text);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		sp.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		this.add(label, BorderLayout.NORTH);
		this.add(sp, BorderLayout.CENTER);
	}
	/**
	 * 处理内容
	 */
	private String text(String str) {
		str = str.replaceAll("&ldquo;", "“");
		str = str.replaceAll("&rdquo;", "”");
		str = str.replaceAll("&lsquo;", "‘");
		str = str.replaceAll("&rsquo;", "’");
		str = str.replaceAll("&hellip;", "…");
		str = str.replaceAll("&#39;", "'");
		
		str = str.replaceAll("<p>", "");
		str = str.replaceAll("</p>", "");
		str = str.replaceAll("<strong>", "");
		str = str.replaceAll("</strong>", "");
		str = str.replaceAll("</span>", "");
		str = str.replaceAll("<span style=\"line-height: 1.6em;\">", "");
		str = str.replaceAll("<span id=\"editor_baidu\">", "");
		str = str.replaceAll("<span style=\"line-height: 20.7999992370605px;\">", "");
		str = str.replaceAll(" ", "");
		return str;
	}
}
