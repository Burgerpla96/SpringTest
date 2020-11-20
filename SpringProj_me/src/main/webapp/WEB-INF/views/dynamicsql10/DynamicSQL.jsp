<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>DynamicSQL.jsp</title>

<!-- 부트스트랩 -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<style>
	body{
		padding-top:70px;
	}
</style>
</head>
<body>
	<!--상단메뉴 시작-->
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
		<div class="container-fluid">
			<!--화면 크기가 작을때 보여지는 네비게이션바(모바일용)  -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#collapse-menu">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href='<c:url value="/"/>'><span
					class="glyphicon glyphicon-education"></span> KOSMO</a>
			</div>
			<!-- 화면 크기가 클때 상단에 보여지는 메뉴(데스크탑용) -->
			<div class="collapse navbar-collapse" id="collapse-menu">
				<!-- 네비게이션바에 폼 추가 -->
				<form class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="검색">
					</div>
					<button type="submit" class="btn btn-info">확인</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value="/"/>">HOME</a></li>
					<li><a href="#">로그인</a></li>
					<li><a href="#">자료실</a></li>
					<li><a href="#">공지사항</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!--  상단 메뉴 끝 -->
	<div class="container">
		<!-- 점보트론(Jumbotron) -->
		<div class="jumbotron">
			<h1>
				마이바티스<small>동적 SQL</small>
			</h1>
		</div>		
		<fieldset>
			<legend>동적 SQL<span style="color:red;font-size:1.5em">${message}</span></legend>
			<h2>if문</h2>
			<a href="<c:url value="/MyBatis/If1.do?title=제목"/>">WHERE절 일부에 사용 첫번째</a><br/>
			
			<a href="<c:url value="/MyBatis/If2.do?title=제목&name=이길동&content=내용"/>">WHERE절 일부에 사용 두번째</a>
			<h2>choose~when~otherwise</h2>
			<a href="<c:url value="/MyBatis/choose.do?title=제목&name=이길동&content=내용"/>">WHERE절 일부에 사용</a>
			<!-- 아래 3개의 파라미터가 전달 안되도 에러 안남 -->
			<h2>&lt;where&gt;</h2>
			<a href="<c:url value="/MyBatis/where.do?title=제목&name=이길동&content=내용"/>">&lt;where&gt;</a>
			<h2>&lt;trim&gt;</h2>
			<!-- 아래 3개의 파라미터가 전달 안되도 에러 안남 -->
			<a href="<c:url value="/MyBatis/trim1.do?title=제목&name=이길동&content=내용"/>">&lt;trim&gt;검색문</a><br/>
			<a href="<c:url value="/MyBatis/trim2.do?no=7&title=제목&name=이길동&content=내용"/>">&lt;trim&gt;수정문</a>
			<h2>&lt;set&gt;</h2>
			<!-- update 하고자 하는 칼럼을 동적으로 포함시키기 위해 사용 -->
			<a href="<c:url value="/MyBatis/set.do?no=6&title=제목&name=이길동&content=내용"/>">&lt;set&gt;수정문</a>
			<h2>&lt;foreach&gt;</h2>
			<!-- update 하고자 하는 칼럼을 동적으로 포함시키기 위해 사용 -->
			<a href="<c:url value="/MyBatis/foreach.do"/>">&lt;foreach&gt;</a>
		</fieldset>
		
	</div>

	
	
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
</body>
</html>
