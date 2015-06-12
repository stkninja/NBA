package ui.News;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	private JPanel pane;
	private String content = "";
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
		for (int i = 0; i < line.length; i++) {
			if (line[i].indexOf("<!-- 一般全文 start -->") != -1)
				start = true;
			if (start && line[i].indexOf("<p>") != -1 && line[i].indexOf("<img alt=\"\" src=\"") == -1)
				content = content + this.text(line[i]) +"\n\n";
			if (line[i].indexOf("<!-- 一般全文 end -->") != -1)
				break;
		}
		//显示
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		pane = new JPanel(new BorderLayout(20, 20));
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
		
		JLabel label = new JLabel(title, JLabel.CENTER);
		label.setFont(new Font("黑体", Font.BOLD, 30));
		label.setForeground(new Color(25, 25, 112));
		JTextArea text = new JTextArea(content);
		text.setOpaque(false);
		text.setLineWrap(true);
		text.setFont(new Font("华文新魏", Font.PLAIN, 20));
		pane.add(label, BorderLayout.NORTH);
		pane.add(text, BorderLayout.CENTER);
		
		sp = new JScrollPane(pane);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		sp.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.add(sp, BorderLayout.CENTER);
	}
	/**
	 * 处理内容
	 */
	private String text(String str) {
		str = str.replaceAll("&ldquo;", "“");
		str = str.replaceAll("&rdquo;", "”");
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
