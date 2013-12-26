package student;


import java.sql.*;

/**
 * 连接数据库的类
 */
public class Database{
	
	private Statement stmt=null;
	ResultSet rs=null;
	private Connection conn=null;
	String sql;
	String strurl="jdbc:odbc:student";
//	String strurl="jdbc:sqlserver://localhost:1433;DatabaseName=student";
	
	public Database(){
	}
	
	/**
	 * 打开数据库连接
	 */
	public void OpenConn()throws Exception{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection(strurl, "sa", "123456");
		}
		catch(Exception e){ 
			System.err.println("OpenConn:"+e.getMessage());
		}
	}

	/**
	 * 执行SQL查询语句，返回结果集RS
	 */
	public ResultSet executeQuery(String sql){
		stmt = null;
		rs=null;
		try{
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
		}
		catch(SQLException e){
			System.err.println("executeQuery:"+e.getMessage());
		}
		return rs;
	}

	/**
	 * 执行SQL更新语句
	 */
	public void executeUpdate(String sql){
		stmt=null;
		rs=null;
		try{
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);		
			conn.commit();
		}
		catch(SQLException e){
			System.err.println("executeUpdate:"+e.getMessage()); 
		}
	}
	
	public void closeStmt(){
		try{
			stmt.close();
		}
		catch(SQLException e){
			System.err.println("closeStmt:"+e.getMessage()); 
		}
	}

	/**
	 * 关闭数据库连接
	 */
	public void closeConn(){
		try{
			conn.close();
		}
		catch(SQLException ex){
			System.err.println("aq.closeConn:"+ex.getMessage()); 
		}
	}
	
	/*
	 *转换编码
	 */
	public static String toGBK(String str){
		try {
			if(str==null)
				str = "";
			else
				str=new String(str.getBytes("ISO-8859-1"),"GBK"); 
		}
		catch (Exception e) {System.out.println(e);}
		
		return str;
	}
}


