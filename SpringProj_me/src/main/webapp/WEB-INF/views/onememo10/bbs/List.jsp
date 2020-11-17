<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
<%@ include file="/WEB-INF/views/common/IsLogin.jsp" %>
--%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>List.jsp</title>

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
				한줄 메모 게시판<small>목록페이지</small>
			</h1>
		</div>
		<!-- 작성하기 버튼 -->
		<div class="row">
			<div class="col-md-12 text-right">
				<a href="<c:url value="/OneMemo/BBS/Write.do"/>"
					class="btn btn-success">등록</a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table
					class="table table-bordered table-hover table-condensed text-center">
					<!-- 테이블 컬럼폭은 col-*-*계열로 설정 -->
					<tr>
						<th class="col-md-1 text-center">번호</th>
						<th class="text-center">제목</th>
						<th class="col-md-1 text-center">작성자</th>
						<th class="col-md-2 text-center">작성일</th>
					</tr>
					<c:if test="${empty list }" var="isEmpty">
						<tr>
							<td colspan="4">등록된 게시물이 없어요</td>
						</tr>
					</c:if>
					<c:if test="${!isEmpty}">
						<c:forEach var="item" items="${list }" varStatus="loop">
							<tr>
								<td>${totalRecordCount - (((nowPage - 1) * pageSize) + loop.index)}</td>
								<td class="text-left">
									<a href="<c:url value='/OneMemo/BBS/View.do?no=${item.no}&nowPage='/><c:out value='${param.nowPage}' default='1'/>">${item.title }</a>
									<span class="badge">${item.commentCount}</span>
								</td>
								<td>${item.name}</td>
								<td>${item.postDate}</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<!-- column -->
		</div>
		<!-- row -->


		<!-- 페이징 -->
		<div class="row">
			<div class="col-md-12 text-center">${pagingString }</div>
		</div>
		
		
		<!-- 검색용 UI -->
		<div class="row">
			<div class="text-center">
				<form class="form-inline" method="post" action="<c:url value='/OneMemo/BBS/List.do'/>">
					<div class="form-group">
						<select name="searchColumn" class="form-control">
							<option value="title">제목</option>
							<option value="name">작성자</option>
							<option value="content">내용</option>
						</select>
					</div>
					<div class="form-group">
						<input type="text" name="searchWord" class="form-control" />
					</div>
					<button type="submit" class="btn btn-primary">검색</button>
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
