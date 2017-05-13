

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import mywebsite.User;
import mywebsite.UserDao;
import mywebsite.UserDaoImpl;

/**
 * Servlet implementation class SetMessageServlet
 */
@WebServlet("/SetMessageServlet")
public class SetMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		UserDao userdao = new UserDaoImpl();
		PrintWriter out = response.getWriter();
//		OutputStream out = response.getOutputStream();
		
		User user = new User();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
		String time = sdf.format(date);  
		
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		
		user.setName(name);
		user.setTime(time);
		user.setMessage(message);
		
		try{
			userdao.add_message(user);
			response.sendRedirect("./message/message_first.html");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
