<%@ page import = "java.util.*" %>
<%@ page import = "Model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%Post postAtual = (Post) session.getAttribute("PostAtual"); %>
	<form action='atualizarpergunta.do' method='POST'>
		Titulo <input type='text' name='titulo' id='nome' value='<%= postAtual.getTitulo() %>'>
		Corpo <textarea name='corpo' rows='5' cols='33'><%= postAtual.getCorpo() %></textarea>
		<button type = 'submit'>Atualizar</button>
	</form>
	
</body>
</html>