package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Comentario;
import Model.Post;
import Service.ComentarioService;

/**
 * Servlet implementation class AdicionarComentario
 */
@WebServlet("/adicionarcomentario.do")
public class AdicionarComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionarComentario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int post = Integer.parseInt(request.getParameter("postID") == null ? "-1" : request.getParameter("postID"));
		int userID = request.getSession().getAttribute("userID") == null ? -1 : (int) request.getSession().getAttribute("userID");
		String comentario = request.getParameter("comentario");
		
		Comentario comentarioAdd = new Comentario(post,userID, comentario);
		
		if(userID == -1 || post == -1) {
			response.sendRedirect("pergunta.di?id="+post);
			return;
		}
		
		comentarioAdd = new ComentarioService().criar(comentarioAdd);
		
		response.sendRedirect("pergunta.do?id="+post);
		return;
	}

}
