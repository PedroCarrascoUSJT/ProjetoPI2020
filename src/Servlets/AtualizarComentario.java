package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Comentario;
import Service.ComentarioService;

/**
 * Servlet implementation class AtualizarComentario
 */
@WebServlet("/atualizarcomentario.do")
public class AtualizarComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizarComentario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int userID = request.getSession().getAttribute("userID") == null ? -1 : (int) request.getSession().getAttribute("userID");
		int comentarioID = request.getParameter("comentarioID") == null ? -1 : Integer.parseInt(request.getParameter("comentarioID"));
		String comentario = request.getParameter("comentario");
		int postID = (int) request.getSession().getAttribute("postID");
		
		if(comentario != null) {
			Comentario comentarioUpdate = new Comentario();
			comentarioUpdate.setUserID(userID);
			comentarioUpdate.setComentarioID(comentarioID);
			comentarioUpdate.setComentario(comentario);
			
			int isUpdated = new ComentarioService().atualizar(comentarioUpdate);
			
			response.sendRedirect("pergunta.do?id="+postID);
		}
		
	}

}
