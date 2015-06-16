package ui.stat;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import vo.TeamSeasonVO;
import businesslogic.TeamStatBL;
import businesslogicservice.TeamStatBLService;

public class MixedChart {
	private static TeamStatBLService team = new TeamStatBL();
	public MixedChart(){
		try {
			chart1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void chart1() throws IOException { 
		
		// 设置主题
		StandardChartTheme standardChartTheme = new StandardChartTheme("name");//这里的"name"参数；是什么意思我也不知道，反正这样可以用
		standardChartTheme.setLargeFont(new Font("宋体",Font.BOLD, 12));//可以改变轴向的字体
		standardChartTheme.setRegularFont(new Font("宋体",Font.BOLD, 12));//可以改变图例的字体
		standardChartTheme.setExtraLargeFont(new Font("宋体",Font.BOLD, 12));//可以改变图标的标题字体
		ChartFactory.setChartTheme(standardChartTheme);//Theme.getTheme()
		DefaultCategoryDataset dataset = getDataSet1();
		DefaultCategoryDataset lineDataset = getDataSet2();
		JFreeChart chart = ChartFactory.createBarChart("近6年勇士三分球命中率方差和三分球命中率的变化",
			     "年份",// 目录轴的显示标签
			     "数值", // 数值轴的显示标签
			     dataset,// 数据集
			     PlotOrientation.VERTICAL,// 图表方向：水平、垂直
			     true,// 是否显示图例(对于简单的柱状图必须是false)
			     true,//是否生成工具
			     false);// 是否生成URL链接
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 26));//设置title标题
		chart.setBackgroundPaint(new Color(238, 238, 255));//设置背景色
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 18));// 底部

		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();//图本身
		categoryplot.setDataset(1, lineDataset);//放折线图数据
		LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
		lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		categoryplot.setRenderer(1, lineandshaperenderer);
			       
		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("黑体", Font.ITALIC, 18)); // 设置数据字体(纵轴)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("黑体", Font.ITALIC, 18)); // 设置时字体（横轴）
		categoryaxis.setLowerMargin(0.0); // 柱状图和纵轴紧靠
			   
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);//折线在柱面前面显示
		
		FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("pic4.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 500, 500, null);  
        } finally {  
            try {  
                out.close();  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
	}
	private DefaultCategoryDataset getDataSet1() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();//柱状图数据
		ArrayList<TeamSeasonVO> list = team.getAllSeasonTeamData();
		for(int i = 0;i < 6;i ++){
			dataset.addValue(list.get(i).threepointrate * 100, "三分球命中率(%)", String.valueOf(i + 2009));
		}
		return dataset;
		
	}
	private DefaultCategoryDataset getDataSet2() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();//折线图数据
		ArrayList<TeamSeasonVO> list = team.getAllSeasonTeamData();
		for(int i = 0;i < 6;i ++){
			lineDataset.addValue(list.get(i).varthreepoint, "方差", String.valueOf(i + 2009));
		}
		return lineDataset;
	}

}
