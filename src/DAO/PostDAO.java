package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Post;

public class PostDAO {
	
	public int criar(Post post) {
		String sqlInsert = "INSERT INTO post VALUES(null, ?, ?, ?, ?, null)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, post.getUserID());
			stm.setDate(2, post.getDataPost());
			stm.setString(3, post.getTitulo());
			stm.setString(4, post.getCorpo());
			stm.execute();
			String sqlQuery  = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					post.setPostID(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return post.getPostID();
	}
	
	public ArrayList<Post> carregar(int pagina) {
		int comeco = (pagina * 10) - 10;
		ArrayList<Post> posts = new ArrayList<Post>();
		String sqlRead = "SELECT * FROM post LIMIT ?,?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlRead);) {
			stm.setInt(1, comeco);
			stm.setInt(2, 10);
			stm.execute();
			try(ResultSet rs = stm.executeQuery()) {
				while(rs.next()){
					Post post = new Post(
							rs.getInt("postID"),
							rs.getInt("userID"),
							rs.getDate("dataPost"),
							rs.getString("titulo"),
							rs.getString("corpo"),
							rs.getDate("dataAtualizacao")
					);
					posts.add(post);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	
	public int countPost() {
		String sqlCount = "SELECT COUNT(*) AS Contagem FROM post";
		try(Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlCount);
				ResultSet rs = stm.executeQuery();){
			if(rs.next()) {
				return rs.getInt("Contagem");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public ArrayList<Post> carregarPorParametro(int pagina, String parametroBusca) {
		String[] parametros = parametroBusca.split(" ");
		int comeco = (pagina * 10) - 10;
		ArrayList<Post> posts = new ArrayList<Post>();
		String sqlQuery ="SELECT * FROM post WHERE titulo LIKE ? LIMIT ?,?";
		try(Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery)){
			stm.setString(1, "%"+parametroBusca+"%");
			stm.setInt(2, comeco);
			stm.setInt(3, 10);
			stm.execute();
			try(ResultSet rs = stm.executeQuery()){
				while(rs.next()) {
					Post post = new Post(
							rs.getInt("postID"),
							rs.getInt("userID"),
							rs.getDate("dataPost"),
							rs.getString("titulo"),
							rs.getString("corpo"),
							rs.getDate("dataAtualizacao")
					);
					posts.add(post);
				}
				if(posts.size()<1){
					String sqlRead = "SELECT * FROM post WHERE ";
					String condicao = new String();
					for(int i = 1;i<=parametros.length;i++) {
						condicao +="titulo LIKE ?"+ (i==parametros.length ? " LIMIT ?,?" : " OR ");
					}
					sqlRead += condicao;
					try (PreparedStatement stm2 = conn.prepareStatement(sqlRead);) {
						int i = 1;
						for(String parametro : parametros) {
							stm.setString(i, "%"+parametro.toUpperCase()+"%");
							i++;
						}
						stm.setInt(i, comeco);
						stm.setInt(++i, 10);
						stm.execute();
						try(ResultSet rs2 = stm.executeQuery()) {
							while(rs.next()){
								Post post = new Post(
										rs.getInt("postID"),
										rs.getInt("userID"),
										rs.getDate("dataPost"),
										rs.getString("titulo"),
										rs.getString("corpo"),
										rs.getDate("dataAtualizacao")
								);
								posts.add(post);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	
	
	public int countPostPorParametro(String parametroBusca) {
		String sqlCountOneCondition = "SELECT COUNT(*) as Contagem FROM post WHERE titulo LIKE ?";
		try(Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlCountOneCondition);){
			stm.setString(1, "%"+parametroBusca+"%");
			stm.execute();
			try(ResultSet rs  = stm.executeQuery();){
				int count = -1;
				if(rs.next()) {
					count = rs.getInt("Contagem");
				}
				if(count > 0) {
					return count;
				}else {
					String[] parametros = parametroBusca.split(" ");
					String sqlCount = "SELECT COUNT(*) AS Contagem FROM post WHERE ";
					String condicao = new String();
					for(int i = 1;i<=parametros.length;i++) {
						condicao +="titulo LIKE ?"+ (i==parametros.length ? "" : " OR ");
					}
					sqlCount += condicao;
					try(PreparedStatement stm2 = conn.prepareStatement(sqlCount);){
						int i = 1;
						for(String parametro : parametros) {
							stm.setString(i, "%"+parametro.toUpperCase()+"%");
							i++;
						}
						stm.execute();
						try(ResultSet rs2 = stm.executeQuery();){
							if(rs.next()) {
								return rs.getInt("Contagem");
							}
						}catch (SQLException e) {
							e.printStackTrace();
						}
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Post carregarPorID(int id) {
		String sqlRead = "SELECT * FROM post WHERE postID = ?";
		Post post = new Post();
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlRead);) {
			stm.setInt(1, id);
			stm.execute();
			try(ResultSet rs = stm.executeQuery()) {
				if(rs.next()){
					post = new Post(
							rs.getInt("postID"),
							rs.getInt("userID"),
							rs.getDate("dataPost"),
							rs.getString("titulo"),
							rs.getString("corpo"),
							rs.getDate("dataAtualizacao")
					);
					return post;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return post;
	}
	
	public boolean excluirPorId(int id) {
		String sqlRead = "DELETE FROM post WHERE postID = ?";
		Post post = new Post();
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlRead);) {
			stm.setInt(1, id);
			stm.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean atualizar(Post post) {
		String sqlUpdate = "UPDATE post SET titulo = ?, corpo = ?, dataAtualizacao = ? WHERE postID = ? ";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, post.getTitulo());
			stm.setString(2, post.getCorpo());
			stm.setDate(3, post.getDataAtualizacao());
			stm.setInt(4, post.getPostID());
			System.out.println(post.getDataAtualizacao());
			stm.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
