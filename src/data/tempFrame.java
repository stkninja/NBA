package data;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class tempFrame extends JFrame {

	public tempFrame() {
		Toolkit tool = getToolkit();  
        Dimension dim = tool.getScreenSize();  
        setBounds(0, 0, dim.width/2, dim.height/2);  
        JButton jb = new JButton(new ImageIcon("D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\players\\action\\Aaron Brooks.png"));
        this.getContentPane().add(jb);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);  
	}

}
