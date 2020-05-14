<%@ page import = "java.util.*" %>
<%@ page import = "Model.Post" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("Posts"); %>
	<%int qtdPaginas =  (int) request.getAttribute("qtdPaginas"); %>
	<%int paginaAtual = (int) request.getAttribute("pagina"); %>
	<%String busca = request.getParameter("busca"); %>
	
	<!-- BUSCA DE PERGUNTAS -->
	<div>
		<form action='perguntas.do' method='GET'>
			<input type ='text' autocomplete="off" name="busca" value="<%= busca == null ? "" : busca %>">
			<button type='submit'>Buscar</button>
		</form>
	</div>
	
	<% if(posts.size() > 0) { %>
	
	<!-- TODAS AS PERGUNTAS -->
	<div id='perguntas'>
		<% for(Post post : posts){ %>
			<div>
				<a href='pergunta.do?id=<%= post.getPostID() %>'><%=post.getTitulo()%> <br>
					<% String[] palavras = post.getCorpo().split(" "); %>
					<% String corpo = ""; %>
					<% if(palavras.length > 10){ %>
						<%for(int i = 0; i < 10;i++){ %>
							<% corpo += " "+palavras[i]; %>
						<% } %>
						<%= corpo %>...
					<% }else{ %>
						<% corpo = post.getCorpo(); %>
						<%= corpo %>
					<% } %>
					<%= post.getDataPost() %>
				</a>
				<hr/>
			</div>
		<% } %>
	</div>
	
	<!-- PAGINAS -->
	<div id = 'paginas'>
		<% if(paginaAtual > 10){ %>
			<%int comeco = ((paginaAtual/10) * 10); %>
			<%boolean validacao = qtdPaginas/10 == paginaAtual/10; %>
			<%for(int i =  (validacao ? comeco-1 : comeco);i <= (validacao ? qtdPaginas : comeco + 10);i++){%>
			<a href="perguntas.do?pagina=<%=i %><%= busca == null ? "" : "&busca="+busca %>"><%=i %></a>
			<% } %>
		<% }else{ %>
			<%for(int i = 1;i <= (qtdPaginas>11 ? 11 : qtdPaginas);i++){%>
				<%if(qtdPaginas > 1){ %>
					<a href="perguntas.do?pagina=<%=i %><%= busca == null ? "" : "&busca="+busca %>"><%=i %></a>
				<% } %>
			<% } %>
		<% } %>
	</div>
	<% }else{ %>
		Não exite pergunta relacionado a "<%= busca %>"
	<%} %>
</body>
</html>