package ui.stat;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import businesslogic.TeamStatBL;
import businesslogicservice.TeamStatBLService;
import vo.TeamSeasonVO;

public class LineChart {
	private static TeamStatBLService team = new TeamStatBL();
	public LineChart(){
		try {
			chart1();
			chart2();
		    chart3();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void chart1() throws IOException {  
		DefaultCategoryDataset ds = getDataSet1();  
        JFreeChart chart = ChartFactory.createLineChart(  
                "��6����ʿ������������ı仯", //ͼ�����  
                "���", //Ŀ¼�����ʾ��ǩ  
                "������", //��ֵ�����ʾ��ǩ  
                ds, //���ݼ�  
                PlotOrientation.VERTICAL, //ͼ����  
                true, //�Ƿ���ʾͼ�������ڼ򵥵���״ͼ����Ϊfalse  
                false, //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
  
        CategoryPlot  plot = (CategoryPlot) chart.getPlot();  
        plot.setNoDataMessage("NO DATA");  
        // ͼƬ����ɫ  
        chart.setBackgroundPaint(Color.GRAY);  
          
        // x axis  
        CategoryAxis domainAxis = plot.getDomainAxis();  
          
        // Y axis  
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();  
        numberaxis.setAutoRange(true);  
        
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
      
  
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("pic5.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 300, 150, null);  
        } finally {  
            try {  
                out.close();  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
    }
	
	public void chart2() throws IOException {  
		DefaultCategoryDataset ds = getDataSet2();  
        JFreeChart chart = ChartFactory.createLineChart(  
                "��6����ʿ�����������ռͶ���������ı���", //ͼ�����  
                "���", //Ŀ¼�����ʾ��ǩ  
                "����", //��ֵ�����ʾ��ǩ  
                ds, //���ݼ�  
                PlotOrientation.VERTICAL, //ͼ����  
                true, //�Ƿ���ʾͼ�������ڼ򵥵���״ͼ����Ϊfalse  
                false, //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
  
        CategoryPlot  plot = (CategoryPlot) chart.getPlot();  
        plot.setNoDataMessage("NO DATA");  
          
        // x axis  
        CategoryAxis domainAxis = plot.getDomainAxis();  
          
        // Y axis  
        // ͼƬ����ɫ  
        chart.setBackgroundPaint(Color.GRAY);  
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();  
        numberaxis.setAutoRange(true);  
        
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
      
  
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("pic6.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 300, 150, null);  
        } finally {  
            try {  
                out.close();  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
    }
	
	public void chart3() throws IOException {  
		DefaultCategoryDataset ds = getDataSet3();  
        JFreeChart chart = ChartFactory.createLineChart(  
                "��6����ʿ�����������ռͶ���������ı���", //ͼ�����  
                "���", //Ŀ¼�����ʾ��ǩ  
                "����", //��ֵ�����ʾ��ǩ  
                ds, //���ݼ�  
                PlotOrientation.VERTICAL, //ͼ����  
                true, //�Ƿ���ʾͼ�������ڼ򵥵���״ͼ����Ϊfalse  
                false, //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
  
        CategoryPlot  plot = (CategoryPlot) chart.getPlot();  
        plot.setNoDataMessage("NO DATA");  
        
        // ͼƬ����ɫ  
        chart.setBackgroundPaint(Color.MAGENTA);  
          
        // x axis  
        CategoryAxis domainAxis = plot.getDomainAxis();  
          
        // Y axis  
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();  
        numberaxis.setAutoRange(true);  
        
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
      
  
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("pic7.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 300, 150, null);  
        } finally {  
            try {  
                out.close();  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
    }
	
	 private DefaultCategoryDataset getDataSet1() {
		 DefaultCategoryDataset ds = new  DefaultCategoryDataset();
		 ArrayList<TeamSeasonVO> list = team.getAllSeasonTeamData();
		 for(int i = 0;i < 6;i ++){
			 ds.addValue(list.get(i).avgthreepoint,"", String.valueOf(i + 2009));
		 }
	     return ds;  
	    }
	 private DefaultCategoryDataset getDataSet2() {
		 DefaultCategoryDataset ds = new  DefaultCategoryDataset();
		 ArrayList<TeamSeasonVO> list = team.getAllSeasonTeamData();
		 for(int i = 0;i < 6;i ++){
			 ds.addValue(list.get(i).avgthreepointOfpoint,"", String.valueOf(i + 2009));
		 }
	     return ds;  
	    }
	 
	 private DefaultCategoryDataset getDataSet3() {
		 DefaultCategoryDataset ds = new  DefaultCategoryDataset();
		 ArrayList<TeamSeasonVO> list = team.getAllSeasonTeamData();
		 for(int i = 0;i < 6;i ++){
			 ds.addValue(list.get(i).avgthreepointOfpoint,"", String.valueOf(i + 2009));
		 }
	     return ds;  
	    }

}
