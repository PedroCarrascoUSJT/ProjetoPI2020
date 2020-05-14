package Model;

import java.sql.Date;

public class Post {
	
	private int postID;
	private int userID;
	private Date dataPost;
	private String titulo;
	private String corpo;
	private Date dataAtualizacao;
	
	public Post() {}
	
	public Post(int userID, String titulo, String corpo) {
		setUserID(userID);
		setTitulo(titulo);
		setCorpo(corpo);
	}
	
	public Post(int postID, int userID, Date dataPost,String titulo, String corpo, Date dataAtualizacao) {
		setPostID(postID);
		setUserID(userID);
		setDataPost(dataPost);
		setTitulo(titulo);
		setCorpo(corpo);
		setDataAtualizacao(dataAtualizacao);
	}

	public int getPostID() {
		return postID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public Date getDataPost() {
		return dataPost;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getCorpo() {
		return corpo;
	}
	
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	
	public void setPostID(int postID) {
		this.postID = postID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setDataPost(Date dataPost) {
		this.dataPost = dataPost;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
}
