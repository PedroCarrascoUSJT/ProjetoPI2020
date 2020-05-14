package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Service.PostService;
import Model.Post;

/**
 * Servlet implementation class Perguntas
 */
@WebServlet("/adicionarpergunta.do")
public class AdicionarPergunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionarPergunta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object userID = request.getSession().getAttribute("userID");
		
		if(userID != null) {
			RequestDispatcher view = request.getRequestDispatcher("adicionarpergunta.jsp");
			view.forward(request, response);
		}else {
			response.sendRedirect("session.do");
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int userID = (int) request.getSession().getAttribute("userID");
		String titulo = request.getParameter("titulo");
		String corpo = request.getParameter("corpo");
		
		Post post = new Post(userID,titulo,corpo);
		post.setPostID(new PostService().criar(post));
		
		response.sendRedirect("perguntas.do");
	}

}
