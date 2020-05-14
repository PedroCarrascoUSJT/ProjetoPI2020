<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action='adicionarpergunta.do' method='POST'>
		Titulo <input type='text' name='titulo' id='nome'>
		Corpo <textarea name='corpo' rows='5' cols='33'></textarea>
		<button type = 'submit'>Enviar</button>
	</form>
</body>
</html>