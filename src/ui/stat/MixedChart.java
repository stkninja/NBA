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
		
		// ��������
		StandardChartTheme standardChartTheme = new StandardChartTheme("name");//�����"name"��������ʲô��˼��Ҳ��֪������������������
		standardChartTheme.setLargeFont(new Font("����",Font.BOLD, 12));//���Ըı����������
		standardChartTheme.setRegularFont(new Font("����",Font.BOLD, 12));//���Ըı�ͼ��������
		standardChartTheme.setExtraLargeFont(new Font("����",Font.BOLD, 12));//���Ըı�ͼ��ı�������
		ChartFactory.setChartTheme(standardChartTheme);//Theme.getTheme()
		DefaultCategoryDataset dataset = getDataSet1();
		DefaultCategoryDataset lineDataset = getDataSet2();
		JFreeChart chart = ChartFactory.createBarChart("��6����ʿ�����������ʷ���������������ʵı仯",
			     "���",// Ŀ¼�����ʾ��ǩ
			     "��ֵ", // ��ֵ�����ʾ��ǩ
			     dataset,// ���ݼ�
			     PlotOrientation.VERTICAL,// ͼ����ˮƽ����ֱ
			     true,// �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
			     true,//�Ƿ����ɹ���
			     false);// �Ƿ�����URL����
		chart.getTitle().setFont(new Font("����", Font.BOLD, 26));//����title����
		chart.setBackgroundPaint(new Color(238, 238, 255));//���ñ���ɫ
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 18));// �ײ�

		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();//ͼ����
		categoryplot.setDataset(1, lineDataset);//������ͼ����
		LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
		lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		categoryplot.setRenderer(1, lineandshaperenderer);
			       
		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("����", Font.ITALIC, 18)); // ������������(����)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("����", Font.ITALIC, 18)); // ����ʱ���壨���ᣩ
		categoryaxis.setLowerMargin(0.0); // ��״ͼ���������
			   
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);//����������ǰ����ʾ
		
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
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();//��״ͼ����
		ArrayList<TeamSeasonVO> list = team.getAllSeasonTeamData();
		for(int i = 0;i < 6;i ++){
			dataset.addValue(list.get(i).threepointrate * 100, "������������(%)", String.valueOf(i + 2009));
		}
		return dataset;
		
	}
	private DefaultCategoryDataset getDataSet2() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();//����ͼ����
		ArrayList<TeamSeasonVO> list = team.getAllSeasonTeamData();
		for(int i = 0;i < 6;i ++){
			lineDataset.addValue(list.get(i).varthreepoint, "����", String.valueOf(i + 2009));
		}
		return lineDataset;
	}

}
