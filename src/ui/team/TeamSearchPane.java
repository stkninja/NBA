package ui.team;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.batik.transcoder.TranscoderException;

import ui.Region;
import vo.TeamBasicInfoVO;
import vo.TeamVO;
import businesslogic.MatchBL;
import businesslogic.TeamBL;
import businesslogicservice.MatchBLService;
import businesslogicservice.TeamBLService;

/**
 * ����������
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class TeamSearchPane extends JInternalFrame implements ActionListener {
	private TeamPane father;
	private TeamBLService teamBL;
	private MatchBLService matchBL;
	private ArrayList<TeamVO> list;
	private JPanel contentPane;//��panel
	private ImageIcon background;//����ͼƬ
	private JPanel pane;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JComboBox<String> mode;
	private JComboBox<String> region;
	private JComboBox<String> season;
	private JTextField text;
	//�϶�
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	//--------------------------------------------------------
	public TeamSearchPane(TeamPane father) {
		this.father = father;
		teamBL = new TeamBL();
		matchBL = new MatchBL();
		this.setPlace();
		//����
		background = new ImageIcon("data/pic/background.jpg");
		contentPane = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		//��������
		pane = new JPanel();
		pane.setOpaque(false);
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBorder(BorderFactory.createEmptyBorder(20, 30, 50, 30));
		
		label1 = new JLabel("�������ͣ�");
		label1.setFont(new Font("����", Font.PLAIN, 14));
		mode = new JComboBox<String>(new String[]{"����", "����"});
		mode.setFont(new Font("����", Font.PLAIN, 14));
		label2 = new JLabel("������");
		label2.setFont(new Font("����", Font.PLAIN, 14));
		region = new JComboBox<String>(Region.getRegion());
		label3 = new JLabel("������");
		label3.setFont(new Font("����", Font.PLAIN, 14));
		season = new JComboBox<String>((String[])matchBL.getAllSeasons().toArray(new String[matchBL.getAllSeasons().size()]));
		label4 = new JLabel("�����д��");
		label4.setFont(new Font("����", Font.PLAIN, 14));
		text = new JTextField();
		
		pane.add(label1);
		pane.add(Box.createVerticalStrut(10));
		pane.add(mode);
		pane.add(Box.createVerticalStrut(10));
		pane.add(label2);
		pane.add(Box.createVerticalStrut(10));
		pane.add(region);
		pane.add(Box.createVerticalStrut(10));
		pane.add(label3);
		pane.add(Box.createVerticalStrut(10));
		pane.add(season);
		pane.add(Box.createVerticalStrut(10));
		pane.add(label4);
		pane.add(Box.createVerticalStrut(10));
		pane.add(text);
		contentPane.add(pane, BorderLayout.CENTER);
		//����
		mode.addActionListener(this);
		season.addActionListener(this);
		region.addActionListener(this);
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					TeamBasicInfoVO vo = teamBL.getOneTeam(text.getText());
					if (vo != null) {
						try {
							JFrame.setDefaultLookAndFeelDecorated(true);
							TeamFrame frame = new TeamFrame(vo);
							frame.setOpacity(0.9f);
							frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
						} catch (IOException | TranscoderException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "���޴˶ӣ�", "��ʾ", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		//-----------------------------------------------------------------
		this.setDragable();
		this.setBorder(BorderFactory.createEmptyBorder());
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.setVisible(true);
	}
	/**
	 * ����λ�ô�С
	 */
	public void setPlace() {
		this.setBounds(father.getX(), father.getY(), father.getWidth() / 5, father.getHeight() / 2);
	}
	/**
	 * ����
	 */
	public void actionPerformed(ActionEvent e) {
		this.setData(teamBL.getTeams((String)season.getSelectedItem(), (String)region.getSelectedItem()));
	}
	/**
	 * �����������
	 */
	public void getAll() {
		this.setData(teamBL.getTeams((String)season.getSelectedItem(), (String)region.getSelectedItem()));
	}
	/**
	 * ���ñ������
	 */
	public void setData(ArrayList<TeamVO> list) {
		this.list = list;
		Object[][] data = new Object[list.size()][31];
		if (mode.getSelectedItem() == "����") {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).fullName;
				data[i][2] = list.get(i).abbName;
				data[i][3] = list.get(i).winsNum;
				data[i][4] = list.get(i).gamesNum;
				data[i][5] = list.get(i).winsRate;
				
				data[i][6] = list.get(i).allshootingHit;
				data[i][7] = list.get(i).allshooting;
				data[i][8] = list.get(i).allshootingHitRate;
				
				data[i][9] = list.get(i).allthreePointHits;
				data[i][10] = list.get(i).allthreePoint;
				data[i][11] = list.get(i).allthreePointHitRate;
				
				data[i][12] = list.get(i).allfreeThrowHit;
				data[i][13] = list.get(i).allfreeThrow;
				data[i][14] = list.get(i).allfreeThrowHitRate;
				
				data[i][15] = list.get(i).alloffensiveRebounds;
				data[i][16] = list.get(i).alldefensiveRebounds;
				data[i][17] = list.get(i).allrebounds;
				
				data[i][18] = list.get(i).allassists;
				data[i][19] = list.get(i).allsteal;
				data[i][20] = list.get(i).allcaps;
				data[i][21] = list.get(i).allturnovers;
				data[i][22] = list.get(i).allfouls;
				data[i][23] = list.get(i).allscores;
				data[i][24] = list.get(i).allattackRound;
				
				data[i][25] = list.get(i).allattackEfficiency;
				data[i][26] = list.get(i).alldefenceEfficiency;
				data[i][27] = list.get(i).alloffensivereboundsEfficiency;
				data[i][28] = list.get(i).alldefensivereboundsEfficiency;
				data[i][29] = list.get(i).allstealEfficiency;
				data[i][30] = list.get(i).allassistEfficiency;
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).fullName;
				data[i][2] = list.get(i).abbName;
				data[i][3] = list.get(i).winsNum;
				data[i][4] = list.get(i).gamesNum;
				data[i][5] = list.get(i).winsRate;
				
				data[i][6] = list.get(i).shootingHit;
				data[i][7] = list.get(i).shooting;
				data[i][8] = list.get(i).shootingHitRate;
				
				data[i][9] = list.get(i).threePointHits;
				data[i][10] = list.get(i).threePoint;
				data[i][11] = list.get(i).threePointHitRate;
				
				data[i][12] = list.get(i).freeThrowHit;
				data[i][13] = list.get(i).freeThrow;
				data[i][14] = list.get(i).threePointHitRate;
				
				data[i][15] = list.get(i).offensiveRebounds;
				data[i][16] = list.get(i).defensiveRebounds;
				data[i][17] = list.get(i).rebounds;
				
				data[i][18] = list.get(i).assists;
				data[i][19] = list.get(i).steal;
				data[i][20] = list.get(i).caps;
				data[i][21] = list.get(i).turnovers;
				data[i][22] = list.get(i).fouls;
				data[i][23] = list.get(i).scores;
				data[i][24] = list.get(i).attackRound;
				
				data[i][25] = list.get(i).attackEfficiency;
				data[i][26] = list.get(i).defenceEfficiency;
				data[i][27] = list.get(i).offensivereboundsEfficiency;
				data[i][28] = list.get(i).defensivereboundsEfficiency;
				data[i][29] = list.get(i).stealEfficiency;
				data[i][30] = list.get(i).assistEfficiency;
			}
			
		}
		father.showTable(data);
	}
	/**
	 * ����������
	 * @return �������
	 */
	public ArrayList<TeamVO> getList() {
		return list;
	}
	/**
	 * ���ý�����϶�
	 */
	private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               TeamSearchPane.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               TeamSearchPane.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(TeamSearchPane.this.getLocation().x + e.getX() - tmp.x,
                		   TeamSearchPane.this.getLocation().y + e.getY() - tmp.y);
                   TeamSearchPane.this.setLocation(loc);
               }
            }
        });
	}
}
