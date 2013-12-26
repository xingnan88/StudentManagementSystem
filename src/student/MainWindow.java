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
	 * �����ڵĳ�ʼ��
	 */
	InputStudent ins;
	ModifyStudent mos;
	QueryStudent qus;
	DeleteStudent des;
	
	// �����˵���
	JMenuBar bar;
	// ������ϵͳ�����˵���
	JMenu menuSystem;
	JMenuItem itemExit;
	JMenuItem itemWel;
	// ������ѧ�������˵���
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
		// ���ɡ�ϵͳ������
		menuSystem = new JMenu("ϵͳ����");		
		itemWel = new JMenuItem("��ӭ����");
		itemExit = new JMenuItem("�˳�");
		// ���ɡ�ѧ��������
		menuStu = new JMenu("ѧ������");
		itemAdd = new JMenuItem("¼��ѧ����Ϣ");
		itemSearch = new JMenuItem("��ѯѧ����Ϣ");
		itemEdit = new JMenuItem("�޸�ѧ����Ϣ");
		itemDelete = new JMenuItem("ɾ��ѧ����¼");
		
		// ������в˵���
		bar = new JMenuBar();
		bar.add(menuSystem);
		bar.add(menuStu);
	  
		// ��ӡ�ϵͳ�����˵���
		menuSystem.add(itemWel);
		menuSystem.add(itemExit);
		// ��ӡ�ѧ�������˵���
		menuStu.add(itemAdd);
		menuStu.add(itemSearch);
		menuStu.add(itemEdit);
		menuStu.add(itemDelete);
		
		setJMenuBar(bar);
		// ����������
		label = new JLabel("ѧ����Ϣ����ϵͳ", JLabel.CENTER);
		label.setIcon(new ImageIcon(getClass().getResource("��ӭͼƬ.jpg")));
		label.setFont(new Font("����", Font.BOLD, 40));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		// ����¼�����
		itemExit.addActionListener(this);
		itemWel.addActionListener(this);
		itemAdd.addActionListener(this);
		itemSearch.addActionListener(this);
		itemEdit.addActionListener(this);
		itemDelete.addActionListener(this);
	
		// ��Ƭ���ֹ���������
		card = new CardLayout();
		pCenter = new JPanel();
		pCenter.setLayout(card);
		ins = new InputStudent();
		mos = new ModifyStudent();
		qus = new QueryStudent();
		des = new DeleteStudent();
	
		pCenter.add("��ӭ����",label);
		pCenter.add("¼�����", ins);
		pCenter.add("��ѯ����",qus);
		pCenter.add("�޸Ľ���", mos);
		pCenter.add("ɾ������", des);	
	
		add(pCenter, BorderLayout.CENTER);
		setTitle("ѧ����Ϣ����ϵͳ");//���ô�����
		ImageIcon icon = new ImageIcon(getClass().getResource("��ӭͼƬ.jpg"));//������С��ͼ��
		setIconImage(icon.getImage());		
		validate();
		setVisible(true);
		
		setBounds(250, 200,600, 600);
		// �رմ���
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "ȷ�϶Ի���",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});		
	}

	// �¼�����
	public void actionPerformed(ActionEvent e) {		
		//���ػ�ӭ����
		if(e.getSource() == itemWel){
			int ok = JOptionPane.showConfirmDialog(this, "ȷ�Ϸ�����", "ȷ�϶Ի���",
					JOptionPane.YES_NO_OPTION);
			if (ok == JOptionPane.YES_OPTION)
					card.show(pCenter,"��ӭ����");
		}
		//�˳�
		else if (e.getSource() == itemExit) {
			int n = JOptionPane.showConfirmDialog(this, "ȷ���˳���", "ȷ�϶Ի���",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION)
				System.exit(0);			
		} 		
		//¼��
		else if (e.getSource() == itemAdd) {
			ins.clearMess();
			card.show(pCenter, "¼�����");			
		} 
		//�޸�
		else if (e.getSource() == itemEdit) {
			mos.clearMess();
			card.show(pCenter, "�޸Ľ���");
		}
		//��ѯ
		else if (e.getSource() == itemSearch) {
			qus.clearMess();
			card.show(pCenter,"��ѯ����");
		} 
		//ɾ��
		else if (e.getSource() == itemDelete) {
			card.show(pCenter, "ɾ������");
		}
	
		}
		
	public static void main(String args[]) {
		new MainWindow();
	}

}
