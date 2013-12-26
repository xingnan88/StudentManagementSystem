package student;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class DeleteStudent extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 删除界面
	 */
	StuBean queryStu =new StuBean();
	StuBean delStu = new StuBean();
	JTextField Snum,Sname,Sminzu,Shome,Sbirth,Sdept;
	JRadioButton boy,girl;
	JButton del;
	ButtonGroup group = null;
	public DeleteStudent(){
		Snum = new JTextField(20);
		del = new JButton("删除");
		Snum.addActionListener(this);
		del.addActionListener(this);
		Sname = new JTextField(20);
		Sname.setEditable(false);
		Sminzu = new JTextField(20);
		Sminzu.setEditable(false);
		Shome = new JTextField(20);
		Shome.setEditable(false);
		Sbirth = new JTextField(20);
		Sbirth.setEditable(false);
		Sdept = new JTextField(20);
		Sdept.setEditable(false);
		group = new ButtonGroup();
		boy = new JRadioButton("男",false);
		girl = new JRadioButton("女",false);
		group.add(boy);
		group.add(girl);
		Box box0 = Box.createHorizontalBox();
		JLabel label = new JLabel("学生信息删除",JLabel.CENTER);
		label.setFont(new Font("黑体", Font.BOLD, 25));
		box0.add(label);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("学号：",JLabel.CENTER));
		box1.add(Snum);
		box1.add(del);
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
		JPanel messPanel = new JPanel();
		messPanel.add(boxH);
		messPanel.setBackground(Color.pink);
		setLayout(new BorderLayout());
		add(messPanel,BorderLayout.CENTER);
		validate();
		setVisible(true);	
	}
	//处理事件
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == del||e.getSource() == Snum){
			String number = "";
			number = Snum.getText();
			String rs[] = new String[4];
			rs = queryStu.stuSearch(number);
				if(rs != null){
					String q = "该生信息已存在，您想删除该生的基本信息吗？";				
					int yes = JOptionPane.showConfirmDialog(this,q,"确认",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(yes == JOptionPane.YES_OPTION){
						Sname.setText(rs[0]);
					    Sminzu.setText(rs[2]);
					    Shome.setText(rs[3]);
					    Sbirth.setText(rs[4]);
					    Sdept.setText(rs[3]);
					    if(rs[1].equals("男"))
					    	boy.setSelected(true);
					    else
					    	girl.setSelected(true);
					    String m = "确定要删除该学号及全部信息吗？";
						int ok = JOptionPane.showConfirmDialog(this,m, "确认",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if(ok == JOptionPane.YES_OPTION){
							delStu.stuDel(number);
						}
						else if(ok == JOptionPane.NO_OPTION){
							Snum.setText(null);
							Sname.setText(null);
							Sminzu.setText(null);
							Shome.setText(null);
							Sbirth.setText(null);
							Sdept.setText(null);
						}
					}
					else if(yes == JOptionPane.NO_OPTION){
						Snum.setText(null);
						Sname.setText(null);
						Sminzu.setText(null);
						Shome.setText(null);
						Sbirth.setText(null);
						Sdept.setText(null);
					}				    
				}
				else{					
					JOptionPane.showMessageDialog(this,"该学号不存在！","警告", JOptionPane.WARNING_MESSAGE);
				}			
		}		
	}
}