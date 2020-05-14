package Service;

import java.util.ArrayList;

import DAO.ComentarioDAO;
import Model.Comentario;

public class ComentarioService {
	ComentarioDAO comentarioDAO = new ComentarioDAO();
	
	public ArrayList<Comentario> carregar(int pagina,int id){
		return comentarioDAO.carregar(pagina, id);
	}
	
	public int countComentario(int postID){
		return comentarioDAO.countComentario(postID);
	}
	
	public Comentario criar(Comentario comentario) {
		java.util.Date data = new java.util.Date();
		java.sql.Date dataSql = new java.sql.Date(data.getTime());
		comentario.setDataComentario(dataSql);
		return comentarioDAO.criar(comentario);
	}
	
	public int atualizar(Comentario comentario) {
		java.util.Date data = new java.util.Date();
		java.sql.Date dataSql = new java.sql.Date(data.getTime());
		comentario.setDataAtualizacao(dataSql);
		return comentarioDAO.atualizar(comentario);
	}
	
	public boolean excluirPorId(int id, int userID) {
		return comentarioDAO.excluirPorId(id, userID);
	}

}
