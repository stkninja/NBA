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
                "单赛季的每场三分出手数汇总", //图表标题  
                "场次", //目录轴的显示标签  
                "三分出手", //数值轴的显示标签  
                ds, //数据集  
                PlotOrientation.VERTICAL, //图表方向  
                true, //是否显示图例，对于简单的柱状图必须为false  
                false, //是否生成提示工具  
                false);         //是否生成url链接  
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
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 13)); 
      
  
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
	     ds.addSeries("勇士单赛季的每场三分出手数", data);  
		 
	     return ds;  
	    }

}
