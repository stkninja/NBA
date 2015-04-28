package ui.team;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import vo.PlayerVO;
import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;

public class PlayerData {
	PlayerBLService bl;
	ArrayList<PlayerVO> list;
	public PlayerData(String name) throws IOException{
		bl = new PlayerBL();
		list = bl.getPlayers(bl.getLastSeason(), "All", "All", name);
		this.chart1();
		this.chart2();
		this.chart3();
	}
    @SuppressWarnings("deprecation")
	public void chart1() throws IOException {
    	DefaultPieDataset ds = this.getDataSet1();  
    	JFreeChart chart = ChartFactory.createPieChart3D("球队球员得分分布",ds, true, true, true);  
        // 图片背景色  
        chart.setBackgroundPaint(Color.WHITE);  
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
        TextTitle title = new TextTitle("球队球员得分分布");  
        title.setFont(font);  
        
        // 设置字体,非常关键不然会出现乱码的,下方的字体  
        chart.setTitle(title); 
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("5.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 200, 150, null);  
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
		DefaultPieDataset ds = this.getDataSet2();  
		JFreeChart chart = ChartFactory.createPieChart3D("球队球员助攻分布",ds, true, true, true);  
        // 图片背景色  
        chart.setBackgroundPaint(Color.GREEN);  
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
        TextTitle title = new TextTitle("球队球员助攻分布");  
        title.setFont(font);  
        // 设置字体,非常关键不然会出现乱码的,下方的字体  
        chart.setTitle(title); 
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("6.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 200, 150, null);  
        } finally {  
            try {  
                out.close();  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
		  
	}
	
	@SuppressWarnings("deprecation")
	public void chart3() throws IOException {
		DefaultPieDataset ds = this.getDataSet3();  
		JFreeChart chart = ChartFactory.createPieChart3D("球队球员篮板分布",ds, true, true, true);  
        // 图片背景色  
        chart.setBackgroundPaint(Color.ORANGE);  
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
        TextTitle title = new TextTitle("球队球员篮板分布");  
        title.setFont(font);  
        // 设置字体,非常关键不然会出现乱码的,下方的字体  
        chart.setTitle(title); 
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream("7.jpg");  
            ChartUtilities.writeChartAsJPEG(out, 0.5f, chart, 200, 150, null);  
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
		double temp = 0;
		for(int i = 0; i<list.size() ; i++){
			if(list.get(i).point != 0 && list.get(i).point > 10){
				ds.setValue(list.get(i).name, list.get(i).point);
			}
			else{
				temp = temp + list.get(i).point;
			}
		}
		ds.setValue("others", temp);
		return ds;
	}
    private DefaultPieDataset getDataSet2() {
    	DefaultPieDataset ds = new DefaultPieDataset();
    	double temp = 0; 
    	for(int i = 0; i<list.size() ; i++){
			if(list.get(i).assist != 0 && list.get(i).assist > 2){
				ds.setValue(list.get(i).name, list.get(i).assist);
			}
			else{
				temp = temp + list.get(i).assist;
			}
		}
    	ds.setValue("others", temp);
		return ds;
	}
    private DefaultPieDataset getDataSet3() {
    	DefaultPieDataset ds = new DefaultPieDataset();
    	double temp = 0;
    	for(int i = 0; i<list.size() ; i++){
			if(list.get(i).rebound != 0 && list.get(i).assist > 2){
				ds.setValue(list.get(i).name, list.get(i).rebound);
			}
			else{
				temp = temp + list.get(i).rebound;
			}
		}
    	ds.setValue("others", temp);
		return ds;
	}


}
