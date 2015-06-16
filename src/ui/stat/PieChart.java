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
    	JFreeChart chart = ChartFactory.createPieChart3D("��ӵ÷����ͷֲ�",ds, true, true, true);  
        // ͼƬ����ɫ  
        chart.setBackgroundPaint(Color.GRAY);  
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
        TextTitle title = new TextTitle("��ӵ÷����ͷֲ�");  
        title.setFont(font);  
        
        // ��������,�ǳ��ؼ���Ȼ����������,�·�������  
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
		ds.setValue("������", vo.avgtwopoint);
		ds.setValue("����", vo.avgthreepointmade);
		ds.setValue("����", vo.avgfreethrowpoint);
		return ds;
	}

}
