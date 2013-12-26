package student;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
public class ModifyStudent extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 修改界面
	 */
	StuBean modStu = new StuBean();
	StuBean queryStu = new StuBean();
	JTextField Snum,Sname,Shome,Sbirth,Sdept;
	JComboBox Sminzu;
	JRadioButton boy,girl;
	ButtonGroup group = null;
	JButton start,entry,reset;
	JComboBox oldMess;
	public ModifyStudent(){
		Snum = new JTextField(20);
		Sname = new JTextField(20);
		Sminzu = new JComboBox();
		String minzus[]={"汉族","其他"};
		Sminzu = new JComboBox(minzus);
		Shome = new JTextField(20);
		Sbirth = new JTextField(20);
		Sdept=new JTextField(20);
		group = new ButtonGroup();
		boy = new JRadioButton("男",true);
		girl = new JRadioButton("女",false);
		group.add(boy);
		group.add(girl);
		oldMess = new JComboBox();
		start = new JButton("开始修改");
		entry = new JButton("录入修改");
		entry.setEnabled(false);
		reset = new JButton("重置");
		Snum.addActionListener(this);
		start.addActionListener(this);
		entry.addActionListener(this);
		reset.addActionListener(this);
		Box box0 = Box.createHorizontalBox();
		JLabel label = new JLabel("学生信息修改",JLabel.CENTER);
		label.setFont(new Font("黑体", Font.BOLD, 25));
		box0.add(label);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("学号：",JLabel.CENTER));
		box1.add(Snum);
		box1.add(start);
		Box box2 = Box.createHorizontalBox();
		box2.add(new JLabel("姓名：",JLabel.CENTER));
		box2.add(Sname);
		Box box3 = Box.createHorizontalBox();
		box3.add(new JLabel("性别：",JLabel.CENTER));
		box3.add(boy);
		box3.add(girl);
		Box box4 = Box.createHorizontalBox();
		box4.add(new JLabel("民族：",JLabel.CENTER));
		box4.add(Sminzu);
		Box box5 = Box.createHorizontalBox();
		box5.add(new JLabel("籍贯：",JLabel.CENTER));
		box5.add(Shome);
		Box box6 = Box.createHorizontalBox();
		box6.add(new JLabel("出生：",JLabel.CENTER));
		box6.add(Sbirth);
		Box box7 = Box.createHorizontalBox();
		box7.add(new JLabel("所属系：",JLabel.CENTER));
		box7.add(Sdept);
		Box boxH = Box.createVerticalBox();
		boxH.add(box0);
		boxH.add(box1);
		boxH.add(box2);
		boxH.add(box3);
		boxH.add(box4);
		boxH.add(box5);
		boxH.add(box6);
		boxH.add(box7);
		boxH.add(Box.createVerticalGlue());
		JPanel putButton = new JPanel();
		putButton.add(entry);
		putButton.add(reset);
		JPanel messPanel = new JPanel();
		messPanel.add(boxH);
		messPanel.setBackground(Color.ORANGE);
		putButton.setBackground(Color.CYAN);
		setLayout(new BorderLayout());
		add(messPanel,BorderLayout.CENTER);
		add(putButton,BorderLayout.SOUTH);
		validate();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		String number = Snum.getText();
		String rs[] = new String[5];
		rs = queryStu.stuSearch(number);
		if(e.getSource() == start||e.getSource() == Snum){
			oldMess.removeAllItems();			
				if(rs != null){
					String q = "该生信息已存在，您想修改他（她）的基本信息吗？";
					JOptionPane.showMessageDialog(this,q,"警告" ,JOptionPane.QUESTION_MESSAGE);
					entry.setEnabled(true);
					oldMess.addItem("姓名："+rs[0]);
					oldMess.addItem("名族："+rs[2]);
					oldMess.addItem("籍贯："+rs[3]);
					oldMess.addItem("出生："+rs[4]);	
					oldMess.addItem("所属系："+rs[5]);	
				    if(rs[1].equals("男"))
				    	boy.setSelected(true);
				    else
				    	girl.setSelected(true);				
				}
				else{
					entry.setEnabled(false);
					JOptionPane.showMessageDialog(this,"该学号不存在无法修改！","警告", JOptionPane.WARNING_MESSAGE);
					clearMess();
				}
			
		}
		else if(e.getSource() == entry){
				if(rs != null){					
					String m = "基本信息将被修改！";
					int ok = JOptionPane.showConfirmDialog(this,m,"确认",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(ok == JOptionPane.YES_OPTION){
						String name = Sname.getText();
						String minzu = (String)Sminzu.getSelectedItem();
						String home = Shome.getText();
						String birth = Sbirth.getText();
						String dept=Sdept.getText();
						String sex = null;
						if(boy.isSelected())
							sex = boy.getText();
						if(girl.isSelected())
							sex = girl.getText();
						modStu.stuModify(number, name, sex, minzu, home, birth,dept);
						}
					else if(ok == JOptionPane.NO_OPTION){
						entry.setEnabled(true);
					}
				}
				else{
					String w = "该学号没有基本信息，不能修改！";
					JOptionPane.showMessageDialog(this, w, "警告",JOptionPane.WARNING_MESSAGE);
					entry.setEnabled(false);
					clearMess();
				}
		}
		else if(e.getSource() == reset){
			clearMess();
			entry.setEnabled(false);
		}
	}
	public void clearMess(){
		Snum.setText(null);
		Sname.setText(null);
		Shome.setText(null);
		Sbirth.setText(null);
		Sdept.setText(null);
		oldMess.removeAllItems();
	}

}