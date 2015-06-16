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
                "近6年勇士三分球出手数的变化", //图表标题  
                "年份", //目录轴的显示标签  
                "出手数", //数值轴的显示标签  
                ds, //数据集  
                PlotOrientation.VERTICAL, //图表方向  
                true, //是否显示图例，对于简单的柱状图必须为false  
                false, //是否生成提示工具  
                false);         //是否生成url链接  
  
        CategoryPlot  plot = (CategoryPlot) chart.getPlot();  
        plot.setNoDataMessage("NO DATA");  
        // 图片背景色  
        chart.setBackgroundPaint(Color.GRAY);  
          
        // x axis  
        CategoryAxis domainAxis = plot.getDomainAxis();  
          
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
                "近6年勇士三分球出手数占投篮出手数的比重", //图表标题  
                "年份", //目录轴的显示标签  
                "比重", //数值轴的显示标签  
                ds, //数据集  
                PlotOrientation.VERTICAL, //图表方向  
                true, //是否显示图例，对于简单的柱状图必须为false  
                false, //是否生成提示工具  
                false);         //是否生成url链接  
  
        CategoryPlot  plot = (CategoryPlot) chart.getPlot();  
        plot.setNoDataMessage("NO DATA");  
          
        // x axis  
        CategoryAxis domainAxis = plot.getDomainAxis();  
          
        // Y axis  
        // 图片背景色  
        chart.setBackgroundPaint(Color.GRAY);  
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
                "近6年勇士三分球出手数占投篮出手数的比重", //图表标题  
                "年份", //目录轴的显示标签  
                "比重", //数值轴的显示标签  
                ds, //数据集  
                PlotOrientation.VERTICAL, //图表方向  
                true, //是否显示图例，对于简单的柱状图必须为false  
                false, //是否生成提示工具  
                false);         //是否生成url链接  
  
        CategoryPlot  plot = (CategoryPlot) chart.getPlot();  
        plot.setNoDataMessage("NO DATA");  
        
        // 图片背景色  
        chart.setBackgroundPaint(Color.MAGENTA);  
          
        // x axis  
        CategoryAxis domainAxis = plot.getDomainAxis();  
          
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
