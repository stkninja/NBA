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
    	JFreeChart chart = ChartFactory.createPieChart3D("�����Ա�÷ֲַ�",ds, true, true, true);  
        // ͼƬ����ɫ  
        chart.setBackgroundPaint(Color.WHITE);  
        // ���ñ�������  
        // PiePlot plot = (PiePlot) chart.getPlot();  
        // ȡ��3D��ͼ����  
        PiePlot3D plot = (PiePlot3D) chart.getPlot();  
        // ͼ�α߿���ɫ  
        plot.setBaseSectionOutlinePaint(Color.RED);  
        // plot.setBaseSectionPaint(Color.WHITE);  
        // ͼ�α߿��ϸ  
        plot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));  
  
        // ָ��ͼƬ��͸����(0.0-1.0)  
        plot.setForegroundAlpha(0.65f);  
        // ָ����ʾ�ı�ͼ��Բ��(false)����Բ��(true)  
        plot.setCircular(true);  
  
        // ���õ�һ�� ����section �Ŀ�ʼλ�ã�Ĭ����12���ӷ���  
        plot.setStartAngle(360);  
        // ���������ͣ��ʾ  
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());  
  
        // ����ͻ����ʾ�����ݿ�  
        plot.setExplodePercent("One", 0.1D);  
        // ���ñ�ͼ�����ֱ�ǩ����  
        plot.setLabelFont(new Font("����", Font.ITALIC, 10));  
        // ���÷ֱ���ɫ  
        plot.setSectionPaint(0, new Color(244, 194, 144));  
        // plot.setSectionPaint("2", new Color(144, 233, 144));  
        // ����ͼ��˵��Legend�ϵ�����  
        chart.getLegend().setVisible(false);   
        // ���������ʽ  
        Font font = new java.awt.Font("����", java.awt.Font.CENTER_BASELINE,12);  
        TextTitle title = new TextTitle("�����Ա�÷ֲַ�");  
        title.setFont(font);  
        
        // ��������,�ǳ��ؼ���Ȼ����������,�·�������  
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
		JFreeChart chart = ChartFactory.createPieChart3D("�����Ա�����ֲ�",ds, true, true, true);  
        // ͼƬ����ɫ  
        chart.setBackgroundPaint(Color.GREEN);  
        // ���ñ�������  
        // PiePlot plot = (PiePlot) chart.getPlot();  
        // ȡ��3D��ͼ����  
        PiePlot3D plot = (PiePlot3D) chart.getPlot();  
        // ͼ�α߿���ɫ  
        plot.setBaseSectionOutlinePaint(Color.RED);  
        // plot.setBaseSectionPaint(Color.WHITE);  
        // ͼ�α߿��ϸ  
        plot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));  
  
        // ָ��ͼƬ��͸����(0.0-1.0)  
        plot.setForegroundAlpha(0.65f);  
        // ָ����ʾ�ı�ͼ��Բ��(false)����Բ��(true)  
        plot.setCircular(true);  
  
        // ���õ�һ�� ����section �Ŀ�ʼλ�ã�Ĭ����12���ӷ���  
        plot.setStartAngle(360);  
        // ���������ͣ��ʾ  
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());  
  
        // ����ͻ����ʾ�����ݿ�  
        plot.setExplodePercent("One", 0.1D);  
        // ���ñ�ͼ�����ֱ�ǩ����  
        plot.setLabelFont(new Font("����", Font.ITALIC, 10));  
        // ���÷ֱ���ɫ  
        plot.setSectionPaint(0, new Color(244, 194, 144));  
        // plot.setSectionPaint("2", new Color(144, 233, 144));  
        // ����ͼ��˵��Legend�ϵ�����  
        chart.getLegend().setVisible(false);  
        // ���������ʽ  
        Font font = new java.awt.Font("����", java.awt.Font.CENTER_BASELINE,12);  
        TextTitle title = new TextTitle("�����Ա�����ֲ�");  
        title.setFont(font);  
        // ��������,�ǳ��ؼ���Ȼ����������,�·�������  
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
		JFreeChart chart = ChartFactory.createPieChart3D("�����Ա����ֲ�",ds, true, true, true);  
        // ͼƬ����ɫ  
        chart.setBackgroundPaint(Color.ORANGE);  
        // ���ñ�������  
        // PiePlot plot = (PiePlot) chart.getPlot();  
        // ȡ��3D��ͼ����  
        PiePlot3D plot = (PiePlot3D) chart.getPlot();  
        // ͼ�α߿���ɫ  
        plot.setBaseSectionOutlinePaint(Color.RED);  
        // plot.setBaseSectionPaint(Color.WHITE);  
        // ͼ�α߿��ϸ  
        plot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));  
  
        // ָ��ͼƬ��͸����(0.0-1.0)  
        plot.setForegroundAlpha(0.65f);  
        // ָ����ʾ�ı�ͼ��Բ��(false)����Բ��(true)  
        plot.setCircular(true);  
  
        // ���õ�һ�� ����section �Ŀ�ʼλ�ã�Ĭ����12���ӷ���  
        plot.setStartAngle(360);  
        // ���������ͣ��ʾ  
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());  
  
        // ����ͻ����ʾ�����ݿ�  
        plot.setExplodePercent("One", 0.1D);  
        // ���ñ�ͼ�����ֱ�ǩ����  
        plot.setLabelFont(new Font("����", Font.ITALIC, 10));  
        // ���÷ֱ���ɫ  
        plot.setSectionPaint(0, new Color(244, 194, 144));  
        // plot.setSectionPaint("2", new Color(144, 233, 144));  
        // ����ͼ��˵��Legend�ϵ�����  
        chart.getLegend().setVisible(false);  
        // ���������ʽ  
        Font font = new java.awt.Font("����", java.awt.Font.CENTER_BASELINE,12);  
        TextTitle title = new TextTitle("�����Ա����ֲ�");  
        title.setFont(font);  
        // ��������,�ǳ��ؼ���Ȼ����������,�·�������  
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
