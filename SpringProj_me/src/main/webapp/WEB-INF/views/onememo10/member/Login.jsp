<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>Login.jsp</title>

<!-- 부트스트랩 -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
	
	<!--상단메뉴 시작-->
	<%@ include file="/WEB-INF/views/templates/Top.jsp" %>
	<!--  상단 메뉴 끝 -->
	
	<div class="container">
		<!-- 점보트론(Jumbotron) -->
		<div class="jumbotron">
			<h1>
				한줄 메모 게시판<small>로그인 페이지</small>
			</h1>
		</div>
		<c:if test="${! empty NotMember }">
			<div class="row">
				<div class="col-sm-5 alert alert-warning fade in">
					<button class="close" data-dismiss="alert">
						<span>&times;</span>
					</button>
					 ${NotMember }
				</div>
			</div>
		</c:if>
		<div class="row">
			<c:if test="${not empty sessionScope.id }" var="isLogin">

				<div class="col-sm-5 alert alert-success">${sessionScope.id }님
					즐감하세요</div>

			</c:if>
			<c:if test="${not isLogin }">
				<div class="col-sm-12">
					<form class="form-horizontal" method="post"
						action="<c:url value='/OneMemo/Auth/LoginProcess.do'/>">
						<div class="form-group">
							<label for="id" class="col-sm-2  control-label">아이디</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" name="id" id="id"
									placeholder="아이디를 입력하세요">
							</div>
						</div>
						<div class="form-group">
							<label for="pwd" class="col-sm-2  control-label">비밀번호</label>
							<div class="col-sm-3">
								<input type="password" class="form-control" id="pwd" name="pwd"
									placeholder="비밀번호를 입력하세요">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-danger">로그인</button>
							</div>
						</div>
					</form>
				</div>
			</c:if>
		</div>



	</div>
	<!-- container -->


	<!-- footer 시작-->
	<%@ include file="/WEB-INF/views/templates/Footer.jsp" %>
	<!-- fotter 끝 -->	

	

</body>
</html>
