package www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import www.bean.User;

public class Load_Connect {
	//连接数据库
	// 数据库驱动类。
		private String dbDriver = "com.mysql.jdbc.Driver";
		// 连接数据库url。ip:端口:数据库名
		private String dbURL = "jdbc:mysql://localhost:3306/xiaoli";
		// 连接数据库用户名。
		private String dbUser = "root";
		// 连接数据库密码。
		private String dbPwd = "xiaoroot";
		// 获取数据库连接方法, 返回Connection对象。
		private Connection con = null;
		// 数据执行语句。
		private Statement stat = null;
		// 结果。
		private ResultSet rs = null;

		/**
		 * 得到oracle数据库的链接。
		 * 
		 * @return 链接
		 */
		@SuppressWarnings("finally")
		public Connection getConnect() {
			try {
				Class.forName(dbDriver);
				// 通过DriverManager得到链接。
				con = DriverManager.getConnection(dbURL, dbUser, dbPwd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				return con;
			}
		}

		@SuppressWarnings("finally")
		public List<User> search(String sql) {
			con = getConnect();// 获得链接。
			// 创建表的sql语句。
			//System.out.println(sql);
			ArrayList<User> list_user = null;// 创建一个装载表对象的容器。
			try {
				stat = con.createStatement();// 建立执行。
				rs = stat.executeQuery(sql);// 执行。
				list_user = new ArrayList<User>();// 创建一个装载表对象的容器。
				while (rs.next()) {// 迭代。
					User us = new User();//表对象对应的实体。
				
					
					us.setName(rs.getString("user_name"));// 设置姓名。
					us.setPwd(rs.getString("user_pwd"));
					
					list_user.add(us);// 装入容器。
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close();
				return list_user;
			}
		}
/**
 * 功能:关闭数据库的连接
 */
public void close() { // 释放资源
	try { // 捕捉异常
		try {
			if (rs != null) { // 当ResultSet对象的实例rs不为空时
				rs.close(); // 关闭ResultSet对象
			}
		} finally {
			try {
				if (stat != null) { // 当Statement对象的实例stmt不为空时
					stat.close(); // 关闭Statement对象
				}
			} finally {
				if (con != null) { // 当Connection对象的实例conn不为空时
					con.close(); // 关闭Connection对象
				}
			}
		}
	} catch (Exception e) {
		e.printStackTrace(System.err); // 输出异常信息
	}
}

}
