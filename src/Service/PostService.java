package Service;

import Model.Post;

import java.util.ArrayList;

import DAO.PostDAO;

public class PostService {
	PostDAO postDAO = new PostDAO();
	
	public int criar(Post post) {
		java.util.Date data = new java.util.Date();
		java.sql.Date dataSql = new java.sql.Date(data.getTime());
		post.setDataPost(dataSql);
		return postDAO.criar(post);
	}
	
	public ArrayList<Post> carregar(int pagina) {
		return postDAO.carregar(pagina);
	}
	
	public int countPosts() {
		return postDAO.countPost();
	}
	
	public int countPostPorParametro(String parametroBusca) {
		return postDAO.countPostPorParametro(parametroBusca);
	}
	
	public ArrayList<Post> carregarPorParametro(int pagina,String parametroBusca) {
		return postDAO.carregarPorParametro(pagina,parametroBusca);
	}
	
	public Post carregarPorID(int id) {
		return postDAO.carregarPorID(id);
	}
	
	public boolean excluirPorId(int id) {
		return postDAO.excluirPorId(id);
	}
	
	public boolean atualizar(Post post) {
		java.util.Date data = new java.util.Date();
		java.sql.Date dataSql = new java.sql.Date(data.getTime());
		post.setDataAtualizacao(dataSql);
		return postDAO.atualizar(post);
	}
}
