package www.serlvet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import www.bean.User;
import www.dao.Load_Connect;

@WebServlet("/serlve01")
public class serlve01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public serlve01() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����ҳ��ı��롣
		response.setContentType("text/html;charset='utf-8'");
		// ������Ӧ�ı��롣
		response.setCharacterEncoding("utf-8");
		// ��������ı��롣
		request.setCharacterEncoding("utf-8");
		// ����application
		ServletContext application = request.getServletContext();
		// ��ȡsession����
		HttpSession session = request.getSession();
		// �����û���id��
		String name = request.getParameter("name");
		System.out.println(name);
		String pwd = request.getParameter("pwd");
		System.out.println(pwd);
		Load_Connect load = new Load_Connect();
		ArrayList<User> list = (ArrayList<User>) load.search("select * from users where user_name = '" + name + "'");
		if (list.size() == 0) {
			response.sendRedirect("load1.jsp");
		} else {
			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).getName().equals(name) && list.get(i).getPwd().equals(pwd)) {
					response.sendRedirect("show.jsp");
				} 
			}
		}
		System.out.print(list);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡͼƬ����·��
		String path = this.getServletContext().getRealPath("/");
		File file = new File(path + "/2.JPG");
		// ����ͷ��Ϣ,���ݴ���ķ�ʽ,attachment�Ը�������ʽ��,���ǽ�������,�����������ļ�������
		response.setHeader("Content-Disposition","attachment;filename="+file.getName());
		// �����ļ�������
		FileInputStream is = new FileInputStream(file);
		// ��Ӧ�����
		ServletOutputStream out = response.getOutputStream();
		// ����������
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = is.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		is.close();
		out.flush();
		out.close();

	}
}
