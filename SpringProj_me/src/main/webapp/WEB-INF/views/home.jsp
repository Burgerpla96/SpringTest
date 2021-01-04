<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- home.jsp -->



<!-- 실제 내용시작 -->
<div class="container">
	<!-- 점보트론(Jumbotron) -->
	<div class="page-header">
		<h1>
			Spring<small>framework 익히기</small>
		</h1>
	</div>
	<p>The time on the server is ${serverTime}.</p>
	<fieldset>
		<legend>Test Space</legend>
			<c:url value="/tiles.do"/></br/><!--/springapp/tiles.do-->
			${pageContext.request.contextPath}<br/> <!-- /springapp -->
			
			
	
	
	
	
	
	
	
	<!-- 
		<legend>static resource(.img,.avi,.css,.js파일등) 표시방법</legend>
		<h2>servlet-context.xml 파일의 설정된 resources tag사용-webapp/resources 디렉토리 아래에 리소스 저장</h2>
	 -->
		<!-- bean 설정 파일 servlet-context.xml에 맵핑명으로 경로 설정-->
		<!--  
  			<img alt="resources mapping으로 static-안 나와야함" src="/springapp/static/Images/sumnail.png"/>
  			-->
		<!--  실제 디렉토리로 경로 지정 -->
		<!--  
		<img height="200" width="200" alt="실제 디렉토리로 경로"
			src="<c:url value="/resources/Images/sumnail.png"/>" /> <img
			height="200" width="200" alt="실제 디렉토리로 경로2"
			src="/springapp/resources/Images/sumnail.png" />
		-->


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

<!--  
		<h2>servlet-context.xml 파일의 설정된 resources tag사용- 폴더 생성후 맵핑이름
			지정(폴더명과 같지 않아도 된다.)</h2>
		<img id="img3" height="200" width="200"
			alt="/webapp/resources안에 귀여운 아이가 있어용~"
			src="<c:url value="/images/sumnail.png"/>" />



		<h2>resources tag 미사용-&lt;default-servlet-handler/&gt; 디렉토리명으로 접근</h2>
		<img height="200" width="200" alt="디렉토리 구조로 접근"
			src="<c:url value="/Images/sumnail.png"/>" />
-->
	</fieldset>



	<fieldset>
		<legend>spring 익히기</legend>
		<ul style="list-style: upper-roman;">
			<li><a href='<c:url value="/handlermapping.do"/>'>handler
					mapping</a></li>
			<li><a href='<c:url value="/controller.do"/>'>controller</a></li>
			<li><a href='<c:url value="/viewresolver.do"/>'>view
					resolver</a></li>
			<li><a href='<c:url value="/returntype.do"/>'>컨트롤러의 반환타입</a></li>
			<li><a href='<c:url value="/injection.do"/>'>Dependency
					Injection</a></li>
			<li><a href='<c:url value="/annotation.do"/>'>Annotation</a></li>
			<li><a href='<c:url value="/database.do"/>'>Database</a></li>
			<li><a href='<c:url value="/resource.do"/>'>Resource</a></li>
			<li><a href='<c:url value="/validation.do"/>'>Vaildation</a></li>
			<li><a href="<c:url value="/dynamicsql.do"/>">마이바티스 동적SQL</a></li>
			<li><a href="<c:url value="/ajax.do"/>">Ajax 요청</a></li>
			<li><a href="<c:url value="/exception.do"/>">예외처리</a></li>
			<li><a href="<c:url value="/fileupdown.do"/>">File UpDown</a></li>
			<li><a href="<c:url value="/aop.do"/>">AOP</a></li>
			<li><a href="<c:url value="/websocket.do"/>">Web Socket</a></li>
			<li><a href="<c:url value="/tiles.do"/>">Tiles Test</a></li>
			
		</ul>
	</fieldset>

	<!-- include libraries(jQuery, bootstrap) -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	<!-- include summernote css/js-->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
	<!-- include summernote-ko-KR -->
	<script src="/resources/js/summernote-ko-KR.js"></script>

	<script>
		$(document).ready(function() {
			$('#summernote').summernote({
				placeholder : 'content',
				minHeight : 370,
				maxHeight : null,
				focus : true,
				lang : 'ko-KR'
			});
		});
	</script>
	
	<textarea id="summernote" name="content"></textarea>




</div>



