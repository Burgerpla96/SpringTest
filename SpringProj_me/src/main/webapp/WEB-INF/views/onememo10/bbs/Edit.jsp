<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
@SessionAttributes("id") 와 컨트롤러 메서드의 인자로 
@ModelAttribute("id") 사용시 
아래 로그인 여부 체크를 위한 인클루드 불필요
-->
<%--@ include file="/WEB-INF/views/common/IsLogin.jsp"--%>
<!-- Edit.jsp -->

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
	