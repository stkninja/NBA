package ui.team;

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
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import vo.TeamVO;
import businesslogic.TeamBL;
import businesslogicservice.TeamBLService;

public class RecentData {
	TeamBLService bl;
	ArrayList<TeamVO> list;
	public RecentData(String name) throws IOException{
		bl = new TeamBL();
		list = bl.getLastFiveMatches(name);
		this.chart1();
		this.chart2();
	}
	
	@SuppressWarnings("deprecation")
	public void chart1() throws IOException {  
        CategoryDataset ds = this.getDataSet1();
        JFreeChart chart = ChartFactory.createBarChart3D(  
                "近五场全队得分情况", //图表标题  
                "", //目录轴的显示标签  
                "分数", //数值轴的显示标签  
                ds, //数据集  
                PlotOrientation.VERTICAL, //图表方向  
                true, //是否显示图例，对于简单的柱状图必须为false  
                false, //是否生成提示工具  
                false);         //是否生成url链接  
  
        CategoryPlot plot = chart.getCategoryPlot();  
  
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();  
  
        CategoryAxis domainAxis = plot.getDomainAxis();  
  
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
        
        BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); 
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); //横线
        numberaxis.setUpperMargin(0.14999999999999999D); //顶端
        customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//显示每个柱的数值 
        customBarRenderer.setBaseItemLabelsVisible(true); 
        //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题 
        customBarRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
        customBarRenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值 
        customBarRenderer.setItemLabelsVisible(true); 
        
        customBarRenderer.setSeriesPaint(0, Color.GREEN); // 给series1 Bar 
        customBarRenderer.setSeriesOutlinePaint(0,Color.BLACK);//边框为黑色 
        chart.getLegend().setVisible(false);
        
         
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("data/pic/1.jpg");  
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
                "近五场全队命中率情况(%)", //图表标题  
                "", //目录轴的显示标签  
                "命中率", //数值轴的显示标签  
                ds, //数据集  
                PlotOrientation.VERTICAL, //图表方向  
                true, //是否显示图例，对于简单的柱状图必须为false  
                false, //是否生成提示工具  
                false);         //是否生成url链接  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();  
  
        CategoryAxis domainAxis = plot.getDomainAxis();  
  
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
        
        BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); 
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); //横线
        numberaxis.setUpperMargin(0.14999999999999999D); //顶端
        customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//显示每个柱的数值 
        customBarRenderer.setBaseItemLabelsVisible(true); 
        //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题 
        customBarRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
        customBarRenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值 
        customBarRenderer.setItemLabelsVisible(true); 
        chart.getLegend().setVisible(false);
        customBarRenderer.setSeriesPaint(0, Color.ORANGE); // 给series1 Bar 
  
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("data/pic/2.jpg");  
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
		    for(int i = 0; i < list.size(); i++){
		    	ds.addValue((int)list.get(i).scores, "", "第"+(i+1)+"场");
		    }
	        return ds;  
	    }
	 private CategoryDataset getDataSet2() {
		    DefaultCategoryDataset ds = new DefaultCategoryDataset();
		    for(int i = 0; i < list.size(); i++){
		    	if(list.get(i).shooting == 0){
		    		ds.addValue(0, "", "第"+(i+1)+"场");
		    	}
		    	else{
		    		ds.addValue((list.get(i).shootingHit / list.get(i).shooting) * 100, "命中率(%)", "第"+(i+1)+"场");
		    	}
		    	
		    }
	        return ds;  
	    } 


}
