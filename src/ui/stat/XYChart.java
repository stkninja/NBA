package ui.stat;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import businesslogic.TeamStatBL;
import businesslogicservice.TeamStatBLService;

public class XYChart {
	private double[][] data;
	private TeamStatBLService team = new TeamStatBL();
	private ArrayList<Double> list;
	public XYChart(String season){
		list = team.getThreepointPerYear(season);
		try {
			chart1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void chart1() throws IOException {  
		DefaultXYDataset ds = getDataSet1();  
        JFreeChart chart = ChartFactory.createScatterPlot(  
                "��������ÿ�����ֳ���������", //ͼ�����  
                "����", //Ŀ¼�����ʾ��ǩ  
                "���ֳ���", //��ֵ�����ʾ��ǩ  
                ds, //���ݼ�  
                PlotOrientation.VERTICAL, //ͼ����  
                true, //�Ƿ���ʾͼ�������ڼ򵥵���״ͼ����Ϊfalse  
                false, //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
        chart.setBackgroundPaint(Color.GRAY);
  
        XYPlot plot = (XYPlot) chart.getPlot();  
        plot.setNoDataMessage("NO DATA");  
        plot.setDomainZeroBaselineVisible(true);  
        plot.setRangeZeroBaselineVisible(true); 
        plot.setBackgroundPaint(ChartColor.LIGHT_GRAY);
       
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();  
        renderer.setSeriesOutlinePaint(0, Color.black);  
        renderer.setUseOutlinePaint(true);  
          
        // x axis  
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();  
        domainAxis.setAutoRange(true);  
          
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
            out = new FileOutputStream("pic1.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 300, 150, null);  
        } finally {  
            try {  
                out.close();  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
    }
	
	 private DefaultXYDataset getDataSet1() {
		 DefaultXYDataset ds = new DefaultXYDataset();
		 
		 data = new double[2][list.size()];
		 for(int i = 0;i < list.size();i ++){
			 data[0][i] = i;
			 data[1][i] = list.get(i);
		 }	          
	     ds.addSeries("��ʿ��������ÿ�����ֳ�����", data);  
		 
	     return ds;  
	    }

}
