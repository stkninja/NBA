package ui.player;

import java.awt.Color;
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
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

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
		
		//����ͼƬ
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
	@SuppressWarnings("deprecation")
	public void chart1() throws IOException {  
        CategoryDataset ds = this.getDataSet1();  
        JFreeChart chart = ChartFactory.createBarChart3D(  
                "���峡�÷����", //ͼ�����  
                "", //Ŀ¼�����ʾ��ǩ  
                "����", //��ֵ�����ʾ��ǩ  
                ds, //���ݼ�  
                PlotOrientation.VERTICAL, //ͼ����  
                true, //�Ƿ���ʾͼ�������ڼ򵥵���״ͼ����Ϊfalse  
                false, //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();  
  
        CategoryAxis domainAxis = plot.getDomainAxis();  
  
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
        chart.getTitle().setFont(new Font("����", Font.BOLD, 13));  
        
        BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); 
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); //����
        numberaxis.setUpperMargin(0.14999999999999999D); //����
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); 
        customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//��ʾÿ��������ֵ 
        customBarRenderer.setBaseItemLabelsVisible(true); 
        //ע�⣺�˾�ܹؼ������޴˾䣬�����ֵ���ʾ�ᱻ���ǣ���������û����ʾ���������� 
        customBarRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
        customBarRenderer.setItemLabelAnchorOffset(10D);// ��������ͼ�ϵ�����ƫ��ֵ 
        customBarRenderer.setItemLabelsVisible(true); 
        
        customBarRenderer.setSeriesPaint(0, Color.BLUE); // ��series1 Bar 
        customBarRenderer.setSeriesOutlinePaint(0,Color.BLACK);//�߿�Ϊ��ɫ 
  
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
	@SuppressWarnings("deprecation")
	public void chart2() throws IOException {  
        CategoryDataset ds = this.getDataSet2();  
        JFreeChart chart = ChartFactory.createBarChart3D(  
                "���峡���������", //ͼ�����  
                "", //Ŀ¼�����ʾ��ǩ  
                "������", //��ֵ�����ʾ��ǩ  
                ds, //���ݼ�  
                PlotOrientation.VERTICAL, //ͼ����  
                true, //�Ƿ���ʾͼ�������ڼ򵥵���״ͼ����Ϊfalse  
                false, //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();  
  
        CategoryAxis domainAxis = plot.getDomainAxis();  
  
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
        chart.getTitle().setFont(new Font("����", Font.BOLD, 13)); 
        
        BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); 
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); //����
        numberaxis.setUpperMargin(0.14999999999999999D); //����
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); 
        customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//��ʾÿ��������ֵ 
        customBarRenderer.setBaseItemLabelsVisible(true); 
        //ע�⣺�˾�ܹؼ������޴˾䣬�����ֵ���ʾ�ᱻ���ǣ���������û����ʾ���������� 
        customBarRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
        customBarRenderer.setItemLabelAnchorOffset(10D);// ��������ͼ�ϵ�����ƫ��ֵ 
        customBarRenderer.setItemLabelsVisible(true); 
  
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
		    for (int i = 0; i < list.size(); i++) {
		    	for(int m = 0;m<list.get(i).team1.teamPlayers.size();m++){
		    		if(list.get(i).team1.teamPlayers.get(m).name.equals(name)){
		    			ds.addValue((int)list.get(i).team1.teamPlayers.get(m).point, "�÷�", "��"+(i+1)+"��");
		    		}
		    	}
		    	for(int m = 0;m<list.get(i).team2.teamPlayers.size();m++){
		    		if(list.get(i).team2.teamPlayers.get(m).name.equals(name)){
		    			ds.addValue((int)list.get(i).team2.teamPlayers.get(m).point, "�÷�", "��"+(i+1)+"��");
		    		}
		    	}
		    }
	        return ds;  
	    }
	 private CategoryDataset getDataSet2() {
		    DefaultCategoryDataset ds = new DefaultCategoryDataset();
		    for (int i = 0; i < list.size(); i++) {
		    	for(int m = 0;m<list.get(i).team1.teamPlayers.size();m++){
		    		if(list.get(i).team1.teamPlayers.get(m).name.equals(name)){
		    			ds.addValue((list.get(i).team1.teamPlayers.get(m).shootmade/list.get(i).team1.teamPlayers.get(m).shoot)*100, "������(%)", "��"+(i+1)+"��");
		    		}
		    	}
		    	for(int m = 0;m<list.get(i).team2.teamPlayers.size();m++){
		    		if(list.get(i).team2.teamPlayers.get(m).name.equals(name)){
		    			ds.addValue((list.get(i).team2.teamPlayers.get(m).shootmade/list.get(i).team2.teamPlayers.get(m).shoot)*100, "������(%)", "��"+(i+1)+"��");
		    		}
		    	}
		    }
	        return ds;  
	    } 

}
