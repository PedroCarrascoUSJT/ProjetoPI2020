<%@ page import = "java.util.*" %>
<%@ page import = "Model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.0.js"></script>
</head>
<body>
	<%int userID = session.getAttribute("userID") == null ? -1 : (int) session.getAttribute("userID"); %>
	<% ArrayList<Comentario> comentarios = (ArrayList<Comentario>) request.getAttribute("comentarios"); %>
	<%int qtdPaginas =  (int) request.getAttribute("qtdPaginasComentario"); %>
	<%int paginaAtual = (int) request.getAttribute("paginacomentario"); %>
	<%String[][] comentarioUsuario = (String[][]) request.getAttribute("comentarioUsuario"); %>
	<% Post post = (Post) request.getAttribute("Post"); %>
	<% Usuario usuario = (Usuario) request.getAttribute("Usuario"); %>
	<% session.setAttribute("postID", post.getPostID()); %>
	
	<!-- PERGUNTA -->  
	<div id='pergunta'>
		Titulo: <%= post.getTitulo()%><br>
		Corpo: <%= post.getCorpo() %><br>
		Data: <%= post.getDataPost() %><br>
		Feita por: <%= usuario.getNome() %><br>
		<%if(post.getDataAtualizacao() != null){ %>
		Atualizada em: <%= post.getDataAtualizacao() %><br>
		<% } %>
		<%if(session.getAttribute("userID") != null && usuario.getUserID() == (int) session.getAttribute("userID")) {%>
			<% session.setAttribute("PostAtual", post); %>
			<!-- Opções de alteração e de exclusão de perguntas! -->
			<div id='excluirOuAltrear'>
				<form method='POST' action='pergunta.do'>
			        <input type="hidden" name="method" value="DELETE">
					<button type='submit'>Excluir</button>
				</form>
				<form method='GET' action='atualizarpergunta.do'>
					<button type='submit'>Atualizar</button>
				</form>
			</div>
		<% } %>
	</div>
	
	<!-- COMENTARIOS -->  
	<div id='Comentarios'>
	<%for(Comentario comentario : comentarios){ %>
		<div id='comentario'>
			<div id='comentarioInfo<%= comentario.getComentarioID() %>'>
				<% String usuarioNome = new String(); %>
				<% for(int i = 0;i<comentarios.size();i++) {%>
					<% if(comentarioUsuario[i][0].equals(""+comentario.getComentarioID())){%>
						<% usuarioNome = comentarioUsuario[i][1]; %>
					<% } %>
				<% } %>
				Comentario: <%= comentario.getComentario() %>
				Data: <%= comentario.getDataComentario() %>
				Por: <%= usuarioNome %>
			</div>
			<%if(comentario.getUserID() == userID){ %>
			
			<!-- Exclusão e inclusão de comentarios -->
			<div id="formAtualizarComentario<%= comentario.getComentarioID() %>" style='display:none;'>
				<form action="atualizarcomentario.do" method="POST" >
					 <input type="hidden" name="comentarioID" value="<%= comentario.getComentarioID() %>">
					Comentario: <textarea name='comentario' rows='3' cols='50'><%= comentario.getComentario() %></textarea>
					<button type="submit" >Atualizar</button>
				</form>
			</div>
				<div>
					<form method='POST' action='excluircomentario.do'>
				        <input type="hidden" name="comentarioID" value="<%= comentario.getComentarioID() %>">
						<button type="submit">Excluir</button>
					</form>
					<button type="submit" class='btnAtualizar' name="<%= comentario.getComentarioID() %>">Atualizar</button>
				</div>
			<% } %>
		</div>
		<% } %>
		<%if(userID != -1){ %>
			<form action='adicionarcomentario.do' method='POST'>
				<input type='hidden' value='<%=post.getPostID()%>' name='postID'>
				Comentario: <textarea name='comentario' rows='3' cols='50'></textarea>
				<button type='submit'>Enviar</button>
			</form>
		<% } %>
	</div>
	
	<!-- PAGINAS --> 
	<div id='Paginas'>
	<% if(paginaAtual > 10){ %>
			<%int comeco = ((paginaAtual/10) * 10); %>
			<%boolean validacao = qtdPaginas/10 == paginaAtual/10; %>
			<%for(int i =  (validacao ? comeco-1 : comeco);i <= (validacao ? qtdPaginas : comeco + 10);i++){%>
			<a href="pergunta.do?id=<%=request.getParameter("id") %>&paginacomentario=<%=i %>"><%=i %></a>
			<% } %>
		<% }else{ %>
			<%for(int i = 1;i <= (qtdPaginas>11 ? 11 : qtdPaginas);i++){%>
				<%if(qtdPaginas > 1){ %>
					<a href="pergunta.do?id=<%=request.getParameter("id") %>&paginacomentario=<%=i %>"><%=i %></a>
				<% } %>
			<% } %>
		<% } %>
	</div>
	
	<script>
		$(".btnAtualizar").click(function(){
			var id = $(this).attr("name");
			console.log("teste");
			$("#comentarioInfo"+id).css("display","none");
			$("#formAtualizarComentario"+id).css("display","block");
		});
	</script>
	
</body>
</html>