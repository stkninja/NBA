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

import vo.PlayerVO;
import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;

@SuppressWarnings("serial")
public class CareerData extends JFrame{
	
	PlayerBLService bl; 
	private ArrayList<PlayerVO> list;
	PlayerVO vo;
	ImageIcon icon1;
	ImageIcon icon2;
	JPanel panel;
	JLabel label1;
	JLabel label2;
	ImageIcon bg;
	JLabel lab;
	public CareerData(String name,PlayerFrame dialog) throws IOException{
		bl = new PlayerBL();
		vo = bl.getPlayerPast(name);
		
		list = bl.getAllSeasonPlayer(name);
		
		int frameHeight = 450;
		int frameWidth = 400;
		this.setBounds(dialog.getX()-frameWidth , dialog.getY(), frameWidth, frameHeight);
		
		//����ͼƬ
		bg = new ImageIcon("data/pic/playerframe.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		this.chart1();
		this.chart2();
		icon1 = new ImageIcon("1.jpg");
		icon2 = new ImageIcon("2.jpg");
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
                "�÷����", //ͼ�����  
                "", //Ŀ¼�����ʾ��ǩ  
                "����", //��ֵ�����ʾ��ǩ  
                ds, //���ݼ�  
                PlotOrientation.VERTICAL, //ͼ����  
                true, //�Ƿ���ʾͼ�������ڼ򵥵���״ͼ����Ϊfalse  
                false, //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
  
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();  
  
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
  
        /*------����X�������ϵ�����-----------*/  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
  
        /*------����X��ı�������------------*/  
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
  
        /*------����Y�������ϵ�����-----------*/  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
  
        /*------����Y��ı�������------------*/  
        numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
  
        /*------���������˵ײ��������������-----------*/  
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));  
  
        /*******���������˱��⺺�����������********/  
        chart.getTitle().setFont(new Font("����", Font.PLAIN, 12));  
  
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("1.jpg");  
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
                "���������", //ͼ�����  
                "", //Ŀ¼�����ʾ��ǩ  
                "������", //��ֵ�����ʾ��ǩ  
                ds, //���ݼ�  
                PlotOrientation.VERTICAL, //ͼ����  
                true, //�Ƿ���ʾͼ�������ڼ򵥵���״ͼ����Ϊfalse  
                false, //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
  
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();  
  
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
  
        /*------����X�������ϵ�����-----------*/  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
  
        /*------����X��ı�������------------*/  
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
  
        /*------����Y�������ϵ�����-----------*/  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
  
        /*------����Y��ı�������------------*/  
        numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
  
        /*------���������˵ײ��������������-----------*/  
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));  
  
        /*******���������˱��⺺�����������********/  
        chart.getTitle().setFont(new Font("����", Font.PLAIN, 12));  
  
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("2.jpg");  
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
		    for(int n = 0;n < list.size();n++){
		    	ds.addValue(list.get(n).point, "�����������÷�", list.get(n).season);
		    }
		    ds.addValue(vo.point,"���ĳ����÷�","����");
	        return ds;  
	    }
	 private CategoryDataset getDataSet2() {
		    DefaultCategoryDataset ds = new DefaultCategoryDataset();  
		    for(int n = 0;n < list.size();n++){
		    	ds.addValue(list.get(n).fieldgoalpercent*100, "������������%", list.get(n).season);		    	
		    }
		    ds.addValue(vo.fieldgoalpercent*100,"����������%","����");
	        return ds;  
	    } 

}
