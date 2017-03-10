package www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import www.bean.User;

public class Load_Connect {
	//�������ݿ�
	// ���ݿ������ࡣ
		private String dbDriver = "com.mysql.jdbc.Driver";
		// �������ݿ�url��ip:�˿�:���ݿ���
		private String dbURL = "jdbc:mysql://localhost:3306/xiaoli";
		// �������ݿ��û�����
		private String dbUser = "root";
		// �������ݿ����롣
		private String dbPwd = "xiaoroot";
		// ��ȡ���ݿ����ӷ���, ����Connection����
		private Connection con = null;
		// ����ִ����䡣
		private Statement stat = null;
		// �����
		private ResultSet rs = null;

		/**
		 * �õ�oracle���ݿ�����ӡ�
		 * 
		 * @return ����
		 */
		@SuppressWarnings("finally")
		public Connection getConnect() {
			try {
				Class.forName(dbDriver);
				// ͨ��DriverManager�õ����ӡ�
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
			con = getConnect();// ������ӡ�
			// �������sql��䡣
			//System.out.println(sql);
			ArrayList<User> list_user = null;// ����һ��װ�ر�����������
			try {
				stat = con.createStatement();// ����ִ�С�
				rs = stat.executeQuery(sql);// ִ�С�
				list_user = new ArrayList<User>();// ����һ��װ�ر�����������
				while (rs.next()) {// ������
					User us = new User();//������Ӧ��ʵ�塣
				
					
					us.setName(rs.getString("user_name"));// ����������
					us.setPwd(rs.getString("user_pwd"));
					
					list_user.add(us);// װ��������
					
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
 * ����:�ر����ݿ������
 */
public void close() { // �ͷ���Դ
	try { // ��׽�쳣
		try {
			if (rs != null) { // ��ResultSet�����ʵ��rs��Ϊ��ʱ
				rs.close(); // �ر�ResultSet����
			}
		} finally {
			try {
				if (stat != null) { // ��Statement�����ʵ��stmt��Ϊ��ʱ
					stat.close(); // �ر�Statement����
				}
			} finally {
				if (con != null) { // ��Connection�����ʵ��conn��Ϊ��ʱ
					con.close(); // �ر�Connection����
				}
			}
		}
	} catch (Exception e) {
		e.printStackTrace(System.err); // ����쳣��Ϣ
	}
}

}
