package ui.player;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import vo.MatchVO;
import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;
 

@SuppressWarnings("serial")
public class RecentMatch extends JFrame{
	PlayerBLService bl; 
	private ArrayList<MatchVO> list;
	ImageIcon icon1;
	ImageIcon icon2;
	JPanel panel;
	JLabel label1;
	JLabel label2;
	ImageIcon bg;
	JLabel lab;
	String name;
	public RecentMatch(String name,PlayerFrame dialog) throws IOException{
		this.name = name;
		bl = new PlayerBL();
		list = bl.getLastFiveMatches(name);
		
		int frameHeight = 450;
		int frameWidth = 400;
		this.setBounds(dialog.getX()-frameWidth , dialog.getY(), frameWidth, frameHeight);
		
		//背景图片
		bg = new ImageIcon("data/pic/playerframe.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		this.chart1();
		this.chart2();
		icon1 = new ImageIcon("3.jpg");
		icon2 = new ImageIcon("4.jpg");
		label1 = new JLabel();
		label2 = new JLabel();
		label2.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		label1.setIcon(icon1);
		label2.setIcon(icon2);
		
		this.setLayout(new GridLayout(2,1));
		this.add(label1);
		this.add(label2);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	public void chart1() throws IOException {  
        CategoryDataset ds = this.getDataSet1();  
        JFreeChart chart = ChartFactory.createBarChart3D(  
                "近五场得分情况", //图表标题  
                "", //目录轴的显示标签  
                "分数", //数值轴的显示标签  
                ds, //数据集  
                PlotOrientation.VERTICAL, //图表方向  
                true, //是否显示图例，对于简单的柱状图必须为false  
                false, //是否生成提示工具  
                false);         //是否生成url链接  
  
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();  
  
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
  
        /*------设置X轴坐标上的文字-----------*/  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
  
        /*------设置X轴的标题文字------------*/  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
  
        /*------设置Y轴坐标上的文字-----------*/  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
  
        /*------设置Y轴的标题文字------------*/  
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
  
        /*------这句代码解决了底部汉字乱码的问题-----------*/  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  
  
        /*******这句代码解决了标题汉字乱码的问题********/  
        chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 12));  
  
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("3.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 400, 200, null);  
        } finally {  
            try {  
                out.close();  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
    }
	public void chart2() throws IOException {  
        CategoryDataset ds = this.getDataSet2();  
        JFreeChart chart = ChartFactory.createBarChart3D(  
                "近五场命中率情况", //图表标题  
                "", //目录轴的显示标签  
                "命中率", //数值轴的显示标签  
                ds, //数据集  
                PlotOrientation.VERTICAL, //图表方向  
                true, //是否显示图例，对于简单的柱状图必须为false  
                false, //是否生成提示工具  
                false);         //是否生成url链接  
  
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();  
  
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
  
        /*------设置X轴坐标上的文字-----------*/  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
  
        /*------设置X轴的标题文字------------*/  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
  
        /*------设置Y轴坐标上的文字-----------*/  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
  
        /*------设置Y轴的标题文字------------*/  
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
  
        /*------这句代码解决了底部汉字乱码的问题-----------*/  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  
  
        /*******这句代码解决了标题汉字乱码的问题********/  
        chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 12));  
  
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("4.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 400, 200, null);  
        } finally {  
            try {  
                out.close();  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
    }
	 private CategoryDataset getDataSet1() {
		    DefaultCategoryDataset ds = new DefaultCategoryDataset();  
		    for (int i = 0; i < 5; i++) {
		    	for(int m = 0;m<list.get(i).team1.teamPlayers.size();m++){
		    		if(list.get(i).team1.teamPlayers.get(m).name.equals(name)){
		    			ds.addValue((int)list.get(i).team1.teamPlayers.get(m).point, "得分", "第"+(i+1)+"场");
		    		}
		    	}
		    	for(int m = 0;m<list.get(i).team2.teamPlayers.size();m++){
		    		if(list.get(i).team2.teamPlayers.get(m).name.equals(name)){
		    			ds.addValue((int)list.get(i).team1.teamPlayers.get(m).point, "得分", "第"+(i+1)+"场");
		    		}
		    	}
		    }
	        return ds;  
	    }
	 private CategoryDataset getDataSet2() {
		    DefaultCategoryDataset ds = new DefaultCategoryDataset();
		    for (int i = 0; i < 5; i++) {
		    	for(int m = 0;m<list.get(i).team1.teamPlayers.size();m++){
		    		if(list.get(i).team1.teamPlayers.get(m).name.equals(name)){
		    			ds.addValue((list.get(i).team1.teamPlayers.get(m).shootmade/list.get(i).team1.teamPlayers.get(m).shoot)*100, "命中率", "第"+(i+1)+"场");
		    		}
		    	}
		    	for(int m = 0;m<list.get(i).team2.teamPlayers.size();m++){
		    		if(list.get(i).team2.teamPlayers.get(m).name.equals(name)){
		    			ds.addValue((list.get(i).team2.teamPlayers.get(m).shootmade/list.get(i).team1.teamPlayers.get(m).shoot)*100, "命中率", "第"+(i+1)+"场");
		    		}
		    	}
		    }
	        return ds;  
	    } 

}
