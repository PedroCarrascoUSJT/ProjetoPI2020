package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Comentario;
import Model.Post;

public class ComentarioDAO {
	
	public Comentario criar(Comentario comentario) {
		String sqlInsert = "INSERT INTO comentarios VALUES(null, ?, ?, ?, ?, null)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, comentario.getPostID());
			stm.setInt(2, comentario.getUserID());
			stm.setDate(3, comentario.getDataComentario());
			stm.setString(4, comentario.getComentario());
			stm.execute();
			String sqlQuery  = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					comentario.setComentarioID(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comentario;
	}
	
	public ArrayList<Comentario> carregar(int pagina, int postID){
		int comeco = (pagina * 10) - 10;
		ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
		String sqlRead = "SELECT * FROM comentarios WHERE postID = ? LIMIT ?,?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlRead);) {
			stm.setInt(1, postID);
			stm.setInt(2, comeco);
			stm.setInt(3, 10);
			stm.execute();
			try(ResultSet rs = stm.executeQuery()) {
				while(rs.next()){
					Comentario comentario = new Comentario(
							rs.getInt("comentarioID"),
							rs.getInt("postID"),
							rs.getInt("userID"),
							rs.getDate("dataComentario"),
							rs.getString("comentario"),
							rs.getDate("dataAtualizacao")
					);
					comentarios.add(comentario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comentarios;
	}
	
	public int countComentario(int postID) {
		String sqlCount = "SELECT COUNT(*) AS Contagem FROM comentarios WHERE postID = ?";
		try(Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlCount);){
			stm.setInt(1, postID);
			stm.execute();
			try(ResultSet rs = stm.executeQuery();){
				if(rs.next())
					return rs.getInt("Contagem");
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
		return -1;
	}

	public int atualizar(Comentario comentario) {
		String sqlUpdate = "UPDATE comentarios SET comentario = ?, dataAtualizacao = ? WHERE comentarioID = ? AND userID = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, comentario.getComentario());
			stm.setDate(2, comentario.getDataAtualizacao());
			stm.setInt(3, comentario.getComentarioID());
			stm.setInt(4,comentario.getUserID());
			stm.execute();
			System.out.println("Comentario id:" + comentario.getComentarioID());
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public boolean excluirPorId(int id, int userID) {
		String sqlDelete = "DELETE FROM comentarios WHERE comentarioID = ? AND userID = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.setInt(2, userID);
			stm.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
