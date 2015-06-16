package ui.stat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import vo.TeamSeasonVO;
import businesslogic.TeamStatBL;
import businesslogicservice.TeamStatBLService;

public class PieChart {
	private TeamStatBLService team = new TeamStatBL();
	private TeamSeasonVO vo;
	public PieChart(String season){
		vo = team.getAllTeamData(season);
		try {
			this.chart1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    @SuppressWarnings("deprecation")
	public void chart1() throws IOException {
    	DefaultPieDataset ds = this.getDataSet1();  
    	JFreeChart chart = ChartFactory.createPieChart3D("球队得分类型分布",ds, true, true, true);  
        // 图片背景色  
        chart.setBackgroundPaint(Color.GRAY);  
        // 设置标题文字  
        // PiePlot plot = (PiePlot) chart.getPlot();  
        // 取得3D饼图对象  
        PiePlot3D plot = (PiePlot3D) chart.getPlot();  
        // 图形边框颜色  
        plot.setBaseSectionOutlinePaint(Color.RED);  
        // plot.setBaseSectionPaint(Color.WHITE);  
        // 图形边框粗细  
        plot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));  
  
        // 指定图片的透明度(0.0-1.0)  
        plot.setForegroundAlpha(0.65f);  
        // 指定显示的饼图上圆形(false)还椭圆形(true)  
        plot.setCircular(true);  
  
        // 设置第一个 饼块section 的开始位置，默认是12点钟方向  
        plot.setStartAngle(360);  
        // 设置鼠标悬停提示  
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());  
  
        // 设置突出显示的数据块  
        plot.setExplodePercent("One", 0.1D);  
        // 设置饼图各部分标签字体  
        plot.setLabelFont(new Font("宋体", Font.ITALIC, 10));  
        // 设置分饼颜色  
        plot.setSectionPaint(0, new Color(244, 194, 144));  
        // plot.setSectionPaint("2", new Color(144, 233, 144));  
        // 设置图例说明Legend上的文字  
        chart.getLegend().setVisible(false);   
        // 定义字体格式  
        Font font = new java.awt.Font("黑体", java.awt.Font.CENTER_BASELINE,12);  
        TextTitle title = new TextTitle("球队得分类型分布");  
        title.setFont(font);  
        
        // 设置字体,非常关键不然会出现乱码的,下方的字体  
        chart.setTitle(title); 
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("pic2.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 300, 150, null);  
        } finally {  
            try {  
                out.close();  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
	}
    
	private DefaultPieDataset getDataSet1() {
		DefaultPieDataset ds = new DefaultPieDataset();
		ds.setValue("两分球", vo.avgtwopoint);
		ds.setValue("三分", vo.avgthreepointmade);
		ds.setValue("罚球", vo.avgfreethrowpoint);
		return ds;
	}

}
