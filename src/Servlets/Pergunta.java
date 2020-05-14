package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import Service.*;
/**
 * Servlet implementation class Pergunta
 */
@WebServlet("/pergunta.do")
public class Pergunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pergunta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id = request.getParameter("id") == null ? -1 : Integer.parseInt(request.getParameter("id"));
		
		if(id != -1) {
			Post post = new PostService().carregarPorID(id);
			
			Usuario usuario = new UsuarioService().carregar(post.getUserID());
			
			usuario.setSenha(null);
			
			
			String paginaParam = request.getParameter("paginacomentario");
			int pagina = paginaParam == null ? 1 : Integer.parseInt(paginaParam);
						
			int todasPaginas;
			
			ArrayList<Comentario> comentarios = new ComentarioService().carregar(pagina,id);
			todasPaginas = new ComentarioService().countComentario(id);
	
			String[][] comentarioUsuario = new String[todasPaginas][2];
			System.out.println(todasPaginas);
			if(todasPaginas % 10 != 0)
				todasPaginas += 10;
			todasPaginas /= 10;
			
			int i = 0;
			
			for(Comentario comentario : comentarios) {
				comentarioUsuario[i][0] =""+comentario.getComentarioID();
				String usuarioNome = new UsuarioService().carregar(comentario.getUserID()).getNome();
				comentarioUsuario[i][1] = usuarioNome;
				i++;
			}
			
			request.setAttribute("comentarioUsuario", comentarioUsuario);
			
			request.setAttribute("paginacomentario",pagina);
			
			request.setAttribute("comentarios", comentarios);

			request.setAttribute("qtdPaginasComentario", todasPaginas);
						
			request.setAttribute("Post", post);
			
			request.setAttribute("Usuario", usuario);
			
			RequestDispatcher view = request.getRequestDispatcher("pergunta.jsp");
			view.forward(request, response);
			
			return;
		}
		
		response.sendRedirect("perguntas.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = ((Post) request.getSession().getAttribute("PostAtual")).getPostID();	
		
		boolean isDeleted = new PostService().excluirPorId(id);
		
		request.getSession().setAttribute("PostAtual", null);
		response.sendRedirect("perguntas.do");
		
	}

}
