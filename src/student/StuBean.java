package student;
import java.sql.*;

import javax.swing.*;
//�й�ѧ����Ϣ���ݿ��������
public class StuBean{
	String sql;
	ResultSet rs = null;	
	String sNum;
	String sName;
	String sSex;
	String sMinzu;
	String sHome;
	String sBirth;
	String sDept;
	
	//���ѧ����Ϣ
	 
	public void stuAdd(String num, String name, String sex,String minzu, String home, String birth,String dept){
		
		Database DB = new Database();
		this.sNum = num;
		this.sName = name;
		this.sSex = sex;		                       
		this.sMinzu = minzu;
		this.sHome = home;
		this.sBirth = birth;
		this.sDept=dept;
		if(sNum == null||sNum.equals("")){
			JOptionPane.showMessageDialog(null, "������ѧ��ѧ�ţ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		/*else if(sName == null||sName.equals("")){
			JOptionPane.showMessageDialog(null, "������ѧ��������", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		else if(sHome == null||sHome.equals("")){
			JOptionPane.showMessageDialog(null, "�����뼮�ᣡ", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(sBirth == null||sBirth.equals("")){
			JOptionPane.showMessageDialog(null, "������ѧ�����գ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else{
			sql = "insert into student(snum,sname,ssex,sminzu,shome,sbirth,sdept) values ('"+sNum+"','"+sName+"','"+sSex+"','"+sMinzu+"','"+sHome+"','"+sBirth+"','"+sDept+"')";
			try{
				DB.OpenConn();
				DB.executeUpdate(sql);
				JOptionPane.showMessageDialog(null,"�ɹ����һ���µļ�¼��");
				}
				catch(Exception e){
					System.out.println(e);
					JOptionPane.showMessageDialog(null, "����ʧ��", "����", JOptionPane.ERROR_MESSAGE); 
				}
				finally {
					DB.closeStmt();
					DB.closeConn();
				}
		}
	}
	

	// �޸�ѧ����Ϣ
	 
	public void stuModify(String num, String name, String sex,String minzu, String home, String birth,String dept){
		
		Database DB =new Database();		
		this.sNum = num;
		this.sName = name;
		this.sSex = sex;		
		this.sMinzu =minzu;
		this.sHome = home;
		this.sBirth = birth;
		this.sDept=dept;
		sql = "update student set snum = '"+sNum+"',sname = '"+sName+"', ssex = '"+sSex+"', sminzu = '"+sMinzu+"', shome = '"+sHome+"',sbirth = '"+sBirth+"',sdept='"+sDept+"' where snum = "+Integer.parseInt(sNum)+"";
			try{
				DB.OpenConn();
				DB.executeUpdate(sql);
				JOptionPane.showMessageDialog(null,"�ɹ��޸�һ���µļ�¼��");
			}
			catch(Exception e){
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "����ʧ��", "����", JOptionPane.ERROR_MESSAGE); 
			}
			finally {
				DB.closeStmt();
				DB.closeConn();
			}
		
	}

	//ɾ��ѧ����Ϣ
	 
	public void stuDel(String num){
		
		Database DB = new Database();
		this.sNum = num;
		sql = "delete from student where snum = "+Integer.parseInt(sNum)+"";
		try{
			DB.OpenConn();
			DB.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"�ɹ�ɾ��һ���µļ�¼��");
		}
		catch(Exception e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "����", JOptionPane.ERROR_MESSAGE); 
		}
		finally {
			DB.closeStmt();
			DB.closeConn();
		}
	}

	// ����ѧ�Ų�ѯѧ����Ϣ
	 
	public String[] stuSearch(String num){
		
		Database DB=new Database();
		this.sNum = num;
		String[] s = new String[6];
		if(sNum == null||sNum.equals("")){
			JOptionPane.showMessageDialog(null, "������ѧ��ѧ�ţ�", "����", JOptionPane.ERROR_MESSAGE);
		}
		sql = "select * from student where snum = "+Integer.parseInt(sNum)+"";
		try{
			DB.OpenConn();
			rs = DB.executeQuery(sql);
			if(rs.next()){
				s[0] = rs.getString("sname");
				s[1] = rs.getString("ssex");
				s[2] = rs.getString("sminzu");
				s[3] = rs.getString("shome");
				s[4] = rs.getString("sbirth");
				s[5]=rs.getString("sdept");
			}
			else{
				s = null;
			}				
		}
		catch(Exception e){}
		finally {
			DB.closeStmt();
			DB.closeConn();
		}
		return s;
	}
}