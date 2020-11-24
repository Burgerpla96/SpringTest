<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Top.jsp -->

<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<script>
	function isLogin(){
		//ajax 요청 - 로그인 여부 판단
		$.ajax({
			url:"<c:url value='/OneMemo/Auth/IsLogin.do'/>",
			dataType:'json',
			success:function(data){//data를 json 형태로 받는다.
				if(data.isLogin == 'YES'){//로그인이 되었다면 
					//목록으로 이동 시키기
					location.replace("<c:url value='/OneMemo/BBS/List.do'/>");
				}
				else{//로그인이 안되었다면
					alert("로그인후 이용하세요.");
					location.replace("<c:url value='/OneMemo/Auth/Login.do'/>");
				}
			},
			error:function(e){console.log(e);}
		});
	}

	
	function logout(){
		//location.replace("<c:url value="/OneMemo/Auth/Logout.do"/>");
		$('#logoutForm').submit();//spring security 에 csrf 공격 사용시
	}
</script>

<!-- 로그아웃 get방식을 post방식으로 변경하기 위한 form 추가 -->
<form id="logoutForm" method="post" action="<c:url value="/OneMemo/Auth/Logout.do"/>">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


<style>
 	body{
 		padding-top: 70px;
 		padding-bottom: 70px;
 	}
</style>
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
			<a class="navbar-brand" href='<c:url value="/"/>'><span
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
				<!-- spring security 사용 안할시 -->
				<%-- 
				<c:if test="${empty sessionScope.id}" var="isNotlogin">
					<li><a href="<c:url value="/OneMemo/Auth/Login.do"/>">로그인</a></li>
				</c:if>
				<c:if test="${not isNotlogin }">
					<li><a href="javascript:logout()">로그아웃</a></li>
				</c:if>
				--%>
				<!-- security 사용 -->
				<!-- 단, security:csrf disabled ='false'시 로그아웃을 GET 방식이 아닌 POST방식으로 해야함. -->
				<sec:authorize access="isAnonymous()">
					<li><a href="<c:url value="/OneMemo/Auth/Login.do"/>">로그인</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li><a href="javascript:logout()">로그아웃</a></li>
				</sec:authorize>
				<!-- 
				<li><a href="<c:url value="/OneMemo/BBS/List.do"/>">한줄 댓글 게시판</a></li>
				-->
				<!-- AJAX로 처리해서 하얀 화면에 alert뜨는것 바꾸기 -->
				<!-- ajax로 로그인 여부 판단후 이동 -->
				<li><a href="javascript:isLogin()">한줄 댓글 게시판</a></li>
				 
				<li><a href="#">공지사항</a></li>
			</ul>
		</div>
	</div>
</nav>
<!--  상단 메뉴 끝 -->