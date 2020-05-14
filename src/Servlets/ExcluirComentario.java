package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Post;
import Service.ComentarioService;
import Service.PostService;

/**
 * Servlet implementation class ExcluirComentario
 */
@WebServlet("/excluircomentario.do")
public class ExcluirComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluirComentario() {
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
		int comentarioID = request.getParameter("comentarioID") == null ? -1 : Integer.parseInt(request.getParameter("comentarioID"));
		int userID = request.getSession().getAttribute("userID") == null ? -1 : (int) request.getSession().getAttribute("userID");
		int postID = (int) request.getSession().getAttribute("postID");
		System.out.println("comentario: "+comentarioID);
		System.out.println("usuario: "+userID);
		System.out.println("post: "+postID);
		
		boolean isDeleted = new ComentarioService().excluirPorId(comentarioID,userID);
		
		response.sendRedirect("pergunta.do?id="+postID);
	}

}
