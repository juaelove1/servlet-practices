package jstlel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/03")
public class _03Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Uservo> list = new ArrayList<>();
		
		Uservo vo1 = new Uservo();
		vo1.setNo(10L);
		vo1.setName("김지수1");
		list.add(vo1);
		
		Uservo vo2 = new Uservo();
		vo2.setNo(20L);
		vo2.setName("김지수2");
		list.add(vo2);

		Uservo vo3 = new Uservo();
		vo3.setNo(30L);
		vo3.setName("김지수3");
		list.add(vo3);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/03.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}