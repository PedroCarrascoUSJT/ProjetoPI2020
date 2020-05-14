package Model;

import java.sql.*;

public class Comentario {
	private int comentarioID;
	private int postID;
	private int userID;
	private Date dataComentario;
	private String comentario;
	private Date dataAtualizacao;
	
	public Comentario() {}
	
	public Comentario(int comentarioID, int postID, int userID, Date dataComentario, String comentario, Date dataAtualizacao) {
		setComentarioID(comentarioID);
		setPostID(postID);
		setUserID(userID);
		setDataComentario(dataComentario);
		setComentario(comentario);
		setDataAtualizacao(dataAtualizacao);
	}
	
	public Comentario( int postID, int userID, String comentario) {
		setPostID(postID);
		setUserID(userID);
		setComentario(comentario);
	}
	
	public int getComentarioID() {
		return comentarioID;
	}
	
	public int getPostID() {
		return postID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public Date getDataComentario() {
		return dataComentario;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	
	public void setComentarioID(int comentarioID) {
		this.comentarioID = comentarioID;
	}
	
	public void setPostID(int postID) {
		this.postID = postID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setDataComentario(Date dataPost) {
		this.dataComentario = dataPost;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
}
