package ui.player;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
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

import vo.PlayerVO;
import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;

public class CareerData{
	
	PlayerBLService bl; 
	private ArrayList<PlayerVO> list;
	PlayerVO vo;
	public CareerData(String name) throws IOException{
		bl = new PlayerBL();
		vo = bl.getPlayerPast(name);
		
		list = bl.getAllSeasonPlayer(name);
		
		this.chart1();
		this.chart2();		
	}
	@SuppressWarnings("deprecation")
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
        customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//��ʾÿ��������ֵ 
        customBarRenderer.setBaseItemLabelsVisible(true); 
        //ע�⣺�˾�ܹؼ������޴˾䣬�����ֵ���ʾ�ᱻ���ǣ���������û����ʾ���������� 
        customBarRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
        customBarRenderer.setItemLabelAnchorOffset(10D);// ��������ͼ�ϵ�����ƫ��ֵ 
        customBarRenderer.setItemLabelsVisible(true); 
        chart.getLegend().setVisible(false);
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("1.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 320, 150, null);  
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
                "���������(%)", //ͼ�����  
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
        customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//��ʾÿ��������ֵ 
        customBarRenderer.setBaseItemLabelsVisible(true); 
        //ע�⣺�˾�ܹؼ������޴˾䣬�����ֵ���ʾ�ᱻ���ǣ���������û����ʾ���������� 
        customBarRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
        customBarRenderer.setItemLabelAnchorOffset(10D);// ��������ͼ�ϵ�����ƫ��ֵ 
        customBarRenderer.setItemLabelsVisible(true);
        chart.getLegend().setVisible(false);
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("2.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 320, 150, null);  
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
