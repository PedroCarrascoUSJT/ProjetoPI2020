<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<title>COVID FÓRUM</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:700italic,400,300,700' rel='stylesheet' type='text/css'>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-panels.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel-noscript.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
	</head>
	<body class="homepage">

	<!-- Header -->
		<div id="header">
			<div class="container">
				<% int userID = session.getAttribute("userID") == null ? -1 : (int) session.getAttribute("userID");%>
				<%String nome = (String) session.getAttribute("nome"); %>
				<!-- Logo -->
					<div id="logo">
						<h1><a href="#">COVID Fórum</a></h1>
					</div>

				<!-- Nav -->
					<nav id="nav">
						<ul>
							<li class="active"><a href="index.html">Home</a></li>
							<li><a href="perguntas.do">Faça sua pergunta</a></li>
							<li><a href="faq.html">FAQ</a></li>
							<%if(userID ==-1){ %>
								<li><a href="session.do">Entrar</a></li>
								<li><a href="cadastro.do">Cadastrar</a></li>
							<% }else{ %>
								<li><a href="session.do">Acesse o perfil</a></li>
								<li>Olá, <%= nome %></li>
							<% } %>
						</ul>
					</nav>

			</div>
		</div>
	<!-- Header -->

	<!-- Banner -->
		<div id="banner">
			<div class="container">
				<h2 class="display-3 font-weight-bold text-left" style="color: #000; font-size: 36px;">Saiba como está a situação do COVID-19</h2>
				<a class="btn btn-danger botao" href="https://covid19.who.int">Acompanhe aqui</a>
			</div>
		</div>
	<!-- /Banner -->

	<!-- Main -->
		<div id="page">

			<!-- Extra -->
			<div id="marketing" class="container tabela">
				<div class="row">
					<div class="3u">
						<section>
							<header>
								<h2>Doria recomenda ao comércio que Dias das Mães seja comemorado em agosto</h2>
							</header>
							<p class="subtitle">Governador pede avaliação da situação por causa da pandemia do coronavírus. Para ele, data pode vir a ser comemorada no último domingo do mês de agosto.</p>
							<p><img width="282px" height="150px" src="images/pic1.jpg" alt=""></p>
							<a href="https://g1.globo.com/sp/sao-paulo/noticia/2020/04/24/doria-recomenda-ao-comercio-que-dias-das-maes-seja-comemorado-em-agosto.ghtml" class="button">Saiba mais.</a>
						</section>
					</div>
					<div class="3u">
						<section>
							<header>
								<h2>Governo de SP quer rodízio de estudantes no retorno presencial às aulas</h2>
							</header>
							<p class="subtitle">Medida permitiria distanciamento entre carteiras nas salas. Aulas foram suspensas em 23 de março por causa da pandemia do coronavírus e podem ser retomadas em julho.</p>
							<p><img width="282px" height="150px" src="images/pic2.jpg" alt="Movimentação nos trens durante a quarentena em São Paulo nesta segunda feira (20) — Foto: WILLIAN MOREIRA/FUTURA PRESS/ESTADÃO CONTEÚDO"></p>
							<a href="https://g1.globo.com/sp/sao-paulo/noticia/2020/04/24/governo-de-sp-quer-rodizio-de-estudantes-no-retorno-presencial-as-aulas.ghtml" class="button">Saiba mais.</a>
						</section>
					</div>
					<div class="3u">
						<section>
							<header>
								<h2>Prefeitura do Rio promete inaugurar dois hospitais de campanha até a semana que vem</h2>
							</header>
							<p class="subtitle">Unidade do Leblon começa a receber pacientes neste sábado (25); o do Riocentro será aberto na próxima sexta-feira (1).</p>
							<p><img width="282px" height="150px" src="images/hospital.jpg" alt="hospital de campanha"></p>
							<a href="https://g1.globo.com/rj/rio-de-janeiro/noticia/2020/04/24/prefeitura-do-rio-e-hospitais-de-campanha.ghtml" class="button">Saiba mais.</a>
						</section>
					</div>
					<div class="3u">
						<section>
							<header>
								<h2>Ministério da Agricultura lança cartilha com medidas de proteção contra o coronavírus para o período de colheita</h2>
							</header>
							<p class="subtitle">Ministério da Agricultura lança cartilha com medidas de proteção contra o coronavírus para o período de colheita</p>
							<p><img width="282px" height="150px" src="images/pic3.jpeg" alt="Colheita de café no Espírito Santo — Foto: Reprodução/TV Gazeta"></p>
							<a href="https://g1.globo.com/economia/agronegocios/noticia/2020/04/24/ministerio-da-agricultura-lanca-cartilha-com-medidas-de-protecao-contra-o-coronavirus-para-o-periodo-de-colheita.ghtml" class="button">Saiba mais.</a>
						</section>
					</div>
				</div>
			</div>
			<!-- /Extra -->


		</div>
	<!-- /Main -->
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	</body>
</html>