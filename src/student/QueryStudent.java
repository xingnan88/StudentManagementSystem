package student;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import student.StuBean;

import java.util.*;
public class QueryStudent extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 查询界面
	 */
	PrintJob pri;
	Graphics g = null;
	StuBean queryStu=new StuBean();
	JTextField Snum,Sname,Smajor,Sgrade,Sbirth;
	JRadioButton boy,girl;
	JButton query;
	ButtonGroup group = null;
	JPanel messPanel;
	public QueryStudent(){
		//窗口设置		
		Snum = new JTextField(5);
		query = new JButton("查询");
		//print = new JButton("打印");
		Snum.addActionListener(this);
		query.addActionListener(this);
		//print.addActionListener(this);
		Sname = new JTextField(20);
		Sname.setEditable(false);
		Smajor = new JTextField(20);
		Smajor.setEditable(false);
		Sgrade = new JTextField(20);
		Sgrade.setEditable(false);
		Sbirth = new JTextField(20);
		Sbirth.setEditable(false);
		boy = new JRadioButton("男",false);
		girl = new JRadioButton("女",false);
		group = new ButtonGroup();
		group.add(boy);
		group.add(girl);
		Box box0 = Box.createHorizontalBox();
		JLabel label = new JLabel("学生信息查询",JLabel.CENTER);
		label.setFont(new Font("黑体", Font.BOLD, 25));
		box0.add(label);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("学号：",JLabel.CENTER));
		box1.add(Snum);
		box1.add(query);
		Box box2 = Box.createHorizontalBox();
		box2.add(new JLabel("姓名：",JLabel.CENTER));
		box2.add(Sname);
		Box box3 = Box.createHorizontalBox();
		box3.add(new JLabel("性别：",JLabel.CENTER));
		box3.add(boy);
		box3.add(girl);
		Box box4 = Box.createHorizontalBox();
		box4.add(new JLabel("专业：",JLabel.CENTER));
		box4.add(Smajor);
		Box box5 = Box.createHorizontalBox();
		box5.add(new JLabel("年级：",JLabel.CENTER));
		box5.add(Sgrade);
		Box box6 = Box.createHorizontalBox();
		box6.add(new JLabel("出生：",JLabel.CENTER));
		box6.add(Sbirth);
		Box boxH = Box.createVerticalBox();
		boxH.add(box0);
		boxH.add(box1);
		boxH.add(box2);
		boxH.add(box3);
		boxH.add(box4);
		boxH.add(box5);
		boxH.add(box6);
		boxH.add(Box.createVerticalGlue());
		messPanel = new JPanel();
		messPanel.add(boxH);
		JPanel pSouth = new JPanel();
		//pSouth.add(print);
		messPanel.setBackground(Color.white);
		pSouth.setBackground(Color.CYAN);
		setLayout(new BorderLayout());
		add(messPanel,BorderLayout.CENTER);
		add(pSouth,BorderLayout.SOUTH);
		validate();
		setVisible(false);
	}
	//事件处理
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == query||e.getSource() == Snum){
			String number = "";
			number = Snum.getText();
			String rs[] = new String[4];
			rs = queryStu.stuSearch(number);
				if(rs != null){
					Sname.setText(rs[0]);
				    Smajor.setText(rs[2]);
				    Sgrade.setText(rs[3]);
				    Sbirth.setText(rs[4]);
				    if(rs[1].equals("男"))
				    	boy.setSelected(true);
				    else
				    	girl.setSelected(true);
				    //print.setEnabled(true);
				}
				else{
					JOptionPane.showMessageDialog(this, "该学生信息不存在！", "错误", JOptionPane.ERROR_MESSAGE);
					Snum.setText(null);
					}								 
		}
		/*else if(e.getSource() == print){
			try{
				pri = getToolkit().getPrintJob(new JFrame(), "打印", new Properties());
				g = pri.getGraphics();
				g.translate(120, 120);
				messPanel.printAll(g);
				g.dispose();
				pri.end();
			}catch(Exception ee){}
		}*/		
	}
	public void clearMess(){
		Snum.setText(null);
		Sname.setText(null);
		Smajor.setText(null);
		Sgrade.setText(null);
		Sbirth.setText(null);
	}

}
