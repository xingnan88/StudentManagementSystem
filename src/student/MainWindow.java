package student;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class MainWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主窗口的初始化
	 */
	InputStudent ins;
	ModifyStudent mos;
	QueryStudent qus;
	DeleteStudent des;
	
	// 建立菜单栏
	JMenuBar bar;
	// 建立“系统管理”菜单组
	JMenu menuSystem;
	JMenuItem itemExit;
	JMenuItem itemWel;
	// 建立“学生管理”菜单组
	JMenu menuStu;
	JMenuItem itemSearch;
	JMenuItem itemAdd;
	JMenuItem itemEdit;
	JMenuItem itemDelete;
	
	
	File file = null;
	CardLayout card;
	JLabel label = null;
	JPanel pCenter;

	public MainWindow() {
		// 生成“系统管理”组
		menuSystem = new JMenu("系统管理");		
		itemWel = new JMenuItem("欢迎界面");
		itemExit = new JMenuItem("退出");
		// 生成“学生管理”组
		menuStu = new JMenu("学生管理");
		itemAdd = new JMenuItem("录入学生信息");
		itemSearch = new JMenuItem("查询学生信息");
		itemEdit = new JMenuItem("修改学生信息");
		itemDelete = new JMenuItem("删除学生记录");
		
		// 添加所有菜单组
		bar = new JMenuBar();
		bar.add(menuSystem);
		bar.add(menuStu);
	  
		// 添加“系统管理”菜单项
		menuSystem.add(itemWel);
		menuSystem.add(itemExit);
		// 添加“学生管理”菜单项
		menuStu.add(itemAdd);
		menuStu.add(itemSearch);
		menuStu.add(itemEdit);
		menuStu.add(itemDelete);
		
		setJMenuBar(bar);
		// 设置主窗口
		label = new JLabel("学生信息管理系统", JLabel.CENTER);
		label.setIcon(new ImageIcon(getClass().getResource("欢迎图片.jpg")));
		label.setFont(new Font("黑体", Font.BOLD, 40));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		// 添加事件监听
		itemExit.addActionListener(this);
		itemWel.addActionListener(this);
		itemAdd.addActionListener(this);
		itemSearch.addActionListener(this);
		itemEdit.addActionListener(this);
		itemDelete.addActionListener(this);
	
		// 卡片布局管理器设置
		card = new CardLayout();
		pCenter = new JPanel();
		pCenter.setLayout(card);
		ins = new InputStudent();
		mos = new ModifyStudent();
		qus = new QueryStudent();
		des = new DeleteStudent();
	
		pCenter.add("欢迎界面",label);
		pCenter.add("录入界面", ins);
		pCenter.add("查询界面",qus);
		pCenter.add("修改界面", mos);
		pCenter.add("删除界面", des);	
	
		add(pCenter, BorderLayout.CENTER);
		setTitle("学生信息管理系统");//设置窗口名
		ImageIcon icon = new ImageIcon(getClass().getResource("欢迎图片.jpg"));//设置最小化图标
		setIconImage(icon.getImage());		
		validate();
		setVisible(true);
		
		setBounds(250, 200,600, 600);
		// 关闭窗口
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确认退出吗？", "确认对话框",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});		
	}

	// 事件处理
	public void actionPerformed(ActionEvent e) {		
		//返回欢迎界面
		if(e.getSource() == itemWel){
			int ok = JOptionPane.showConfirmDialog(this, "确认返回吗？", "确认对话框",
					JOptionPane.YES_NO_OPTION);
			if (ok == JOptionPane.YES_OPTION)
					card.show(pCenter,"欢迎界面");
		}
		//退出
		else if (e.getSource() == itemExit) {
			int n = JOptionPane.showConfirmDialog(this, "确认退出吗？", "确认对话框",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION)
				System.exit(0);			
		} 		
		//录入
		else if (e.getSource() == itemAdd) {
			ins.clearMess();
			card.show(pCenter, "录入界面");			
		} 
		//修改
		else if (e.getSource() == itemEdit) {
			mos.clearMess();
			card.show(pCenter, "修改界面");
		}
		//查询
		else if (e.getSource() == itemSearch) {
			qus.clearMess();
			card.show(pCenter,"查询界面");
		} 
		//删除
		else if (e.getSource() == itemDelete) {
			card.show(pCenter, "删除界面");
		}
	
		}
		
	public static void main(String args[]) {
		new MainWindow();
	}

}
