package student;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class InputStudent extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 录入界面
	 */
	StuBean addStu = new StuBean();
	StuBean queryStu = new StuBean();
	//设置“录入界面”窗口
	JTextField Snum,Sname,S,Shome,Sbirth,Sdept;
	JComboBox Sminzu;
	JRadioButton boy,girl;
	ButtonGroup group = null;
	JButton entry,reset;
	public InputStudent(){
		Snum = new JTextField(20);
		Sname = new JTextField(20);
		Shome=new JTextField(20);
		String minzus[]={"汉族","其他"};
		Sminzu = new JComboBox(minzus);
		Sbirth = new JTextField(20);
		Sdept=new JTextField(20);
		group = new ButtonGroup();
		boy = new JRadioButton("男",true);
		girl = new JRadioButton("女",false);
		group.add(boy);
		group.add(girl);
		entry = new JButton("录入");
		reset = new JButton("重置");
		entry.addActionListener(this);
		reset.addActionListener(this);
		Box box0 = Box.createHorizontalBox();
		JLabel label = new JLabel("学生信息录入",JLabel.CENTER);
		label.setFont(new Font("黑体", Font.BOLD, 25));
		box0.add(label);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("学号：",JLabel.CENTER));
		box1.add(Snum);
		Box box2 = Box.createHorizontalBox();
		box2.add(new JLabel("姓名：",JLabel.CENTER));
		box2.add(Sname);
		Box box3 = Box.createHorizontalBox();
		box3.add(new JLabel("性别：",JLabel.CENTER));
		box3.add(boy);
		box3.add(girl);
		Box box4 = Box.createHorizontalBox();
		box4.add(new JLabel("名族：",JLabel.CENTER));
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
		messPanel.setBackground(Color.LIGHT_GRAY);
		putButton.setBackground(Color.cyan);
		setLayout(new BorderLayout());
		add(messPanel,BorderLayout.CENTER);
		add(putButton,BorderLayout.SOUTH);
		validate();
		setVisible(true);
	}
	//事件处理
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == entry){
			String number = "";
			number = Snum.getText();
			String rs[] = new String[4];
			rs = queryStu.stuSearch(number);
			if(rs != null){
				String w = "该生基本信息已存在，请到修改页面修改！";
				JOptionPane.showMessageDialog(this, w, "警告", JOptionPane.WARNING_MESSAGE);
			}
			else{
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
				addStu.stuAdd(number, name, sex,minzu, home, birth,dept);
			}
		}
		else if(e.getSource() == reset){
			clearMess();
		}
	}
	public void clearMess(){
		Snum.setText(null);
		Sname.setText(null);
		Shome.setText(null);
		Sbirth.setText(null);	
		Sdept.setText(null);
		
	}
}
