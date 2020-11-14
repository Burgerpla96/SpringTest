<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
  <title>ViewResolver.jsp</title>

  <!-- 부트스트랩 -->
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

  <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
  <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  
  
  <style>
  	body{
  		padding-top: 70px;
  	}
  	
  </style>
</head>
<body>
	<!-- 상단메뉴 시작 -->
 	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
 		<div class="container-fluid">
 		
 			<!-- 모바일의 경우 화면 -->
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" data-toggle="collapse" 
				data-target="#collapse-menu">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href='<c:url value="/"/>'>
				<span class="glyphicon glyphicon-education"></span> KOSMO
			</a>
		</div>
		
		<!-- desktop 용 -->
		<div class="collapse navbar-collapse" id="collapse-menu">
			<!-- navigation bar에 form 추가 -->
			<form class="navbar-form navbar-right">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-info">Submit</button>
			</form>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/"/>">Home</a></li>
				<li><a href="#">로그인</a></li>
				<li><a href="#">자료실</a></li>
				<li><a href="#">공지사항</a></li>
			</ul>


		</div>
	</div>
 	</nav><!-- 상단 메뉴 -->




	<!-- 실제 내용시작 -->  
  	<div class="container">
  		<!-- 점보트론(Jumbotron) -->
  		<div class="jumbotron">
  			<h1>Spring<small>ViewResolver</small></h1>
  		</div>
  		<fieldset>
  			<legend>View Resolver<span style="color: red;font-size: 1.5em;">영역 속성: ${message}, 파라미터: ${param.message }</span></legend>
  			<a href='<c:url value="/ViewResolver/ViewResolver.do"/>'>InternalResourceViewResolver(default view resolver)</a>
  		</fieldset>
  		
  		
  		
  		
	</div>

	
	
	
	
	
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	
</body>
</html>
