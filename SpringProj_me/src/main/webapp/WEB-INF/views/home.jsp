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
  <title>home.jsp</title>

  <!-- 부트스트랩 -->
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

  <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
  <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  
  
  <!-- static resource 테스트용 -->
  <link rel="stylesheet" href="<c:url value="/styles/common.css"/>">
  <style>
  	body{
  		padding-top: 70px;
  	}
  	#img3{
  		border: 3px solid gold;
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
				<li><a href="<c:url value="/"/>">HOME</a></li>
				<c:if test="${empty sessionScope.id}" var="isNotlogin">
					<li><a href="<c:url value="/OneMemo/Auth/Login.do"/>">로그인</a></li>
				</c:if>
				<c:if test="${not isNotlogin }">
					<li><a href="javascript:logout()">로그아웃</a></li>
				</c:if>
				<li><a href="<c:url value="/OneMemo/BBS/List.do"/>">한줄 댓글 게시판</a></li>
				<li><a href="#">공지사항</a></li>
			</ul>
			

		</div>
	</div>
 	</nav><!-- 상단 메뉴 -->




	<!-- 실제 내용시작 -->  
  	<div class="container">
  		<!-- 점보트론(Jumbotron) -->
  		<div class="page-header">
  			<h1>Spring<small>framework 익히기</small></h1>
  		</div>
		<p>  The time on the server is ${serverTime}. </p>
  		<fieldset>
  			<legend>static resource(.img,.avi,.css,.js파일등) 표시방법</legend>
  			<h2>servlet-context.xml 파일의 설정된 resources tag사용-webapp/resources 디렉토리 아래에 리소스 저장</h2>
  			<!-- bean 설정 파일 servlet-context.xml에 맵핑명으로 경로 설정-->
  			<!--  
  			<img alt="resources mapping으로 static-안 나와야함" src="/springapp/static/Images/sumnail.png"/>
  			-->
  			<!--  실제 디렉토리로 경로 지정 -->
  			<img height="200" width="200" alt="실제 디렉토리로 경로" src="<c:url value="/resources/Images/sumnail.png"/>"/>
  			<img height="200" width="200" alt="실제 디렉토리로 경로2" src="/springapp/resources/Images/sumnail.png"/>
  			


  			<!-- bean 설정 파일 servlet-context.xml에 맵핑명으로 경로 설정-->
  			<!--
  			방법 1) 
  			wepapp/resources 디렉토리 안에만 스태틱 리소스를 넣는다.
  			-->
  			
  			<!-- 
  			방법 2)
  			/SpringProj/src/main/webapp/WEB-INF/spring/appServlet 안의
  			servlet-context.xml에서 설정한 대로 src경로를 지정하기 
  			
  			wepapp아래 리소스 종류별 디렉토리 생성후 
  			<resources mapping="/접근 URL/**" location="/디렉토리명/" />추가하기
  			 -->
  			
  			<!-- 
  			방법 3)
  			<default-servlet-handler/> 태그 추가
  			webapp 아무곳이나 폴더를 만들어 리소스를 두도록 설정 추가.
  			
  			-->
  			
  			
  			<h2>servlet-context.xml 파일의 설정된 resources tag사용- 폴더 생성후 맵핑이름 지정(폴더명과 같지 않아도 된다.)</h2>
  			<img id="img3" height="200" width="200" alt="/webapp/resources안에 귀여운 아이가 있어용~" src="<c:url value="/images/sumnail.png"/>"/>
  			
  			
  			
  			<h2> resources tag 미사용-&lt;default-servlet-handler/&gt; 디렉토리명으로 접근</h2>
  			<img height="200" width="200" alt="디렉토리 구조로 접근" src="<c:url value="/Images/sumnail.png"/>"/>
  			
  		</fieldset>
  		
  		
  		
  		<fieldset>
  			<legend>spring 익히기</legend>
  			<ul style="list-style: upper-roman;">
  				<li><a href='<c:url value="/handlermapping.do"/>'>handler mapping</a></li>
  				<li><a href='<c:url value="/controller.do"/>'>controller</a></li>
  				<li><a href='<c:url value="/viewresolver.do"/>'>view resolver</a></li>
  				<li><a href='<c:url value="/returntype.do"/>'>컨트롤러의 반환타입</a></li>
  				<li><a href='<c:url value="/injection.do"/>'>Dependency Injection</a></li>
  				<li><a href='<c:url value="/annotation.do"/>'>Annotation</a></li>
  				<li><a href='<c:url value="/database.do"/>'>Database</a></li>
  				<li><a href='<c:url value="/resource.do"/>'>Resource</a></li>
  				<li><a href='<c:url value="/validation.do"/>'>Vaildation</a></li>
  				<li><a href="<c:url value="/dynamicsql.do"/>">마이바티스 동적SQL</a></li>
  				<li><a href="<c:url value="/ajax.do"/>">Ajax 요청</a></li>
  				<li><a href="<c:url value="/exception.do"/>">예외처리</a></li>
  				<li><a href="<c:url value="/fileupdown.do"/>">File UpDown</a></li>
  				<li><a href="<c:url value="/aop.do"/>">AOP</a></li>
  				
  				
  			</ul>
  		</fieldset>
  		
  		
  		
  		
  		
  		
  		
  		
	</div>

	
	
	
	
	
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	
</body>
</html>
