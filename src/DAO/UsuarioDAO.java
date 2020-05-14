package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ConnectionFactory;
import Model.Usuario;

public class UsuarioDAO {
	
	public int criar(Usuario Usuario) {
		String sqlInsert = "INSERT INTO usuario VALUES(null, ?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, Usuario.getNome());
			stm.setString(2, Usuario.getEmail());
			stm.setString(3, Usuario.getSenha());
			stm.execute();
			String sqlQuery  = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					Usuario.setUserID(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Usuario.getUserID();
	}
	
	public Usuario carregarPorEmailSenha(String email, String senha) {
		Usuario usuario = null;
		String sqlSelect = "SELECT userID,nome FROM Usuario WHERE email = ? AND senha = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, email);
			stm.setString(2, senha);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario = new Usuario(rs.getInt("userID"), rs.getString("nome"), email, senha);
				} else {
					usuario = new Usuario(-1,null,null,null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return usuario;
	}
	
	
	public Usuario carregar(int id) {
		Usuario Usuario = null;
		String sqlSelect = "SELECT nome,email,senha FROM Usuario WHERE Usuario.userID = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					Usuario = new Usuario(id, rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
				} else {
					Usuario = new Usuario(-1,null,null,null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return Usuario;
	}
	
}
