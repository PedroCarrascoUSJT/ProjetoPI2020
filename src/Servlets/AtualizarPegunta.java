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
 * Servlet implementation class AtualizarPegunta
 */
@WebServlet("/atualizarpergunta.do")
public class AtualizarPegunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizarPegunta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("atualizarPergunta.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = ((Post) request.getSession().getAttribute("PostAtual")).getPostID();
		String titulo = request.getParameter("titulo");
		String corpo = request.getParameter("corpo");
		Post post = new Post();
		post.setPostID(id);
		post.setTitulo(titulo);
		post.setCorpo(corpo);
		
		boolean isUpdated = new PostService().atualizar(post);
		
		System.out.print(isUpdated);
		
		response.sendRedirect("pergunta.do?id="+id);
	}

}
