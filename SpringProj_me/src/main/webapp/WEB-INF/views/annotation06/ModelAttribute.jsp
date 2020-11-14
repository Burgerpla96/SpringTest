<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>ModelAttribute.jsp</title>

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
body {
	padding-top: 70px;
	padding-bottom: 70px;
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
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href='<c:url value="/"/>'> <span
					class="glyphicon glyphicon-education"></span> KOSMO
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
	</nav>
	<!-- 상단 메뉴 -->



	<!-- 실제 내용시작 -->
	<div class="container">
		<!-- 점보트론(Jumbotron) -->
		<div class="jumbotron">
			<h1>
				Spring<small>ModelAttribute-@ModelAttribute</small>
			</h1>
		</div>
		
		<fieldset>
			<legend>@ModelAttribute 어노테이션 결과 페이지</legend>
			<table bgcolor="gray" cellspacing="1" width="60%">
				<tr bgcolor="white">
					<td>이름</td>
					<td><input type="text" name="name" value="${name }${cmd.name}"/></td>
				</tr>
				<tr bgcolor="white">
					<td>나이</td>
					<td><input type="text" name="years" value="${years }${cmd.years}" /></td>
				</tr>
				<tr bgcolor="white">
					<td>성별</td>
					<td>
						<input type="radio" name="gender" value="남자" <c:if test="${gender=='남자' or cmd.gender=='남자' }">checked</c:if>/>남자
						<input type="radio" name="gender" value="여자" <c:if test="${gender=='여자' or cmd.gender=='여자' }">checked</c:if>/>여자
					</td>
				</tr>
				<tr bgcolor="white">
					<td>관심사항</td>
					<td>
						<input type="checkbox" name="inters" value="정치" <c:if test="${fn:contains(inters,'정치') or fn:contains(cmd.inters,'정치') }">checked</c:if>/>정치 
						<input type="checkbox" name="inters" value="경제" <c:if test="${fn:contains(inters,'경제') or fn:contains(cmd.inters,'경제') }">checked</c:if>/>경제 
						<input type="checkbox" name="inters" value="스포츠" <c:if test="${fn:contains(inters,'스포츠') or fn:contains(cmd.inters,'스포츠') }">checked</c:if>/>스포츠
					</td>
				</tr>
				<tr bgcolor="white">
					<td>학력</td>
					<td>
						<select name="grade">
							<option value="초등학교" <c:if test="${grade=='초등학교' or cmd.grade=='초등학교' }">selected</c:if>>초등학교</option>
							<option value="중학교" <c:if test="${grade=='중학교'  or cmd.grade=='중학교' }">selected</c:if>>중학교</option>
							<option value="고등학교" <c:if test="${grade=='고등학교' or cmd.grade=='고등학교' }">selected</c:if>>고등학교</option>
							<option value="대학교" <c:if test="${grade=='대학교' or cmd.grade=='대학교' }">selected</c:if>>대학교</option>
						</select>
					</td>
				</tr>
				<tr bgcolor="white">
					<td>자기소개</td>
					<td><textarea name="self" cols="30" rows="10">${self }${cmd.self }</textarea></td>
				</tr>
			</table>
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
