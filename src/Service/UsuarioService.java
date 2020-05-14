package Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import DAO.UsuarioDAO;
import Model.Usuario;

public class UsuarioService {
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	
	public int criar(Usuario usuario) {
		usuario.setSenha(convertMD5(usuario.getSenha()));
		return usuarioDao.criar(usuario);
	}
	
	public Usuario carregar(int id) {
		return usuarioDao.carregar(id);
	}
	
	public Usuario carregarPorEmailSenha(String email, String senha) {
		return usuarioDao.carregarPorEmailSenha(email, senha);
	}
	
	public String convertMD5(String senha) {
		try {
		    MessageDigest m = MessageDigest.getInstance("MD5");
		  
		    m.update( senha.getBytes(), 0 , senha.length() );
		              
		    byte[] digest = m.digest();
		          
		    String hexa = new BigInteger(1,digest).toString(16);
		              
		    return hexa;
		} catch (NoSuchAlgorithmException e) {
		    return null;
		}
		
	}
}	
