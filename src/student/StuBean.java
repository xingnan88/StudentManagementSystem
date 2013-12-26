package student;
import java.sql.*;

import javax.swing.*;
//有关学生信息数据库操作的类
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
	
	//添加学生信息
	 
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
			JOptionPane.showMessageDialog(null, "请输入学生学号！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		/*else if(sName == null||sName.equals("")){
			JOptionPane.showMessageDialog(null, "请输入学生姓名！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		else if(sHome == null||sHome.equals("")){
			JOptionPane.showMessageDialog(null, "请输入籍贯！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(sBirth == null||sBirth.equals("")){
			JOptionPane.showMessageDialog(null, "请输入学生生日！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else{
			sql = "insert into student(snum,sname,ssex,sminzu,shome,sbirth,sdept) values ('"+sNum+"','"+sName+"','"+sSex+"','"+sMinzu+"','"+sHome+"','"+sBirth+"','"+sDept+"')";
			try{
				DB.OpenConn();
				DB.executeUpdate(sql);
				JOptionPane.showMessageDialog(null,"成功添加一条新的纪录！");
				}
				catch(Exception e){
					System.out.println(e);
					JOptionPane.showMessageDialog(null, "保存失败", "错误", JOptionPane.ERROR_MESSAGE); 
				}
				finally {
					DB.closeStmt();
					DB.closeConn();
				}
		}
	}
	

	// 修改学生信息
	 
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
				JOptionPane.showMessageDialog(null,"成功修改一条新的纪录！");
			}
			catch(Exception e){
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "更新失败", "错误", JOptionPane.ERROR_MESSAGE); 
			}
			finally {
				DB.closeStmt();
				DB.closeConn();
			}
		
	}

	//删除学生信息
	 
	public void stuDel(String num){
		
		Database DB = new Database();
		this.sNum = num;
		sql = "delete from student where snum = "+Integer.parseInt(sNum)+"";
		try{
			DB.OpenConn();
			DB.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"成功删除一条新的纪录！");
		}
		catch(Exception e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE); 
		}
		finally {
			DB.closeStmt();
			DB.closeConn();
		}
	}

	// 根据学号查询学生信息
	 
	public String[] stuSearch(String num){
		
		Database DB=new Database();
		this.sNum = num;
		String[] s = new String[6];
		if(sNum == null||sNum.equals("")){
			JOptionPane.showMessageDialog(null, "请输入学生学号！", "错误", JOptionPane.ERROR_MESSAGE);
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