<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
@SessionAttributes("id") 와 컨트롤러 메서드의 인자로 
@ModelAttribute("id") 사용시 
아래 로그인 여부 체크를 위한 인클루드 불필요
-->
<%@ include file="/WEB-INF/views/common/IsLogin.jsp"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>Edit.jsp</title>

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
	<%@ include file="/WEB-INF/views/templates/Top.jsp"%>
	<!--  상단 메뉴 끝 -->


	<div class="container">
		<!-- 점보트론(Jumbotron) -->
		<div class="jumbotron">
			<h1>
				한줄 메모 게시판<small>수정 페이지</small>
			</h1>
		</div>

		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" method="post" action="<c:url value='/OneMemo/BBS/Edit.do'/>">
					<!-- 씨큐리티 적용:csrf취약점 방어용 -->
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="no" value="${record.no}"/><!-- no를 post방식으로 넘기기 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">제목</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="title" placeholder="제목을 입력하세요?" value="${record.title}">
						</div>
					</div>

					​ ​
					<div class="form-group">
						<label class="col-sm-2 control-label">내용</label>
						<!-- 중첩 컬럼 사용 -->
						<div class="col-sm-10">
							<div class="row">
								<div class="col-sm-8">
									<textarea class="form-control" name="content" rows="5" placeholder="내용 입력하세요">${record.content}</textarea>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">수정</button>
						</div>
					</div>

				</form>
			</div>
		</div>




	</div>
	<!-- container -->


	<!-- footer 시작-->
	<%@ include file="/WEB-INF/views/templates/Footer.jsp"%>
	<!-- fotter 끝 -->

</body>
</html>
