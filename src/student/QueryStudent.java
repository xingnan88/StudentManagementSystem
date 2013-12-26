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
	 * ��ѯ����
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
		//��������		
		Snum = new JTextField(5);
		query = new JButton("��ѯ");
		//print = new JButton("��ӡ");
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
		boy = new JRadioButton("��",false);
		girl = new JRadioButton("Ů",false);
		group = new ButtonGroup();
		group.add(boy);
		group.add(girl);
		Box box0 = Box.createHorizontalBox();
		JLabel label = new JLabel("ѧ����Ϣ��ѯ",JLabel.CENTER);
		label.setFont(new Font("����", Font.BOLD, 25));
		box0.add(label);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("ѧ�ţ�",JLabel.CENTER));
		box1.add(Snum);
		box1.add(query);
		Box box2 = Box.createHorizontalBox();
		box2.add(new JLabel("������",JLabel.CENTER));
		box2.add(Sname);
		Box box3 = Box.createHorizontalBox();
		box3.add(new JLabel("�Ա�",JLabel.CENTER));
		box3.add(boy);
		box3.add(girl);
		Box box4 = Box.createHorizontalBox();
		box4.add(new JLabel("רҵ��",JLabel.CENTER));
		box4.add(Smajor);
		Box box5 = Box.createHorizontalBox();
		box5.add(new JLabel("�꼶��",JLabel.CENTER));
		box5.add(Sgrade);
		Box box6 = Box.createHorizontalBox();
		box6.add(new JLabel("������",JLabel.CENTER));
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
	//�¼�����
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
				    if(rs[1].equals("��"))
				    	boy.setSelected(true);
				    else
				    	girl.setSelected(true);
				    //print.setEnabled(true);
				}
				else{
					JOptionPane.showMessageDialog(this, "��ѧ����Ϣ�����ڣ�", "����", JOptionPane.ERROR_MESSAGE);
					Snum.setText(null);
					}								 
		}
		/*else if(e.getSource() == print){
			try{
				pri = getToolkit().getPrintJob(new JFrame(), "��ӡ", new Properties());
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
