<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>Ajax.jsp</title>

<!-- 부트스트랩 -->

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->



<style>
body {
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
				Spring<small> jQuery Ajax(Spring에 Ajax로 비동기 요청)</small>
			</h1>
		</div>
		<!-- 
		AJAX 할시 form을 사용하는 것은 의미없다.
		xml ..request가 요청하는 것이고 submit하는게 아니기떄문...
		
		 -->
		<form id="frm">
			아이디 <input type="text" name="id" /> 
			비빌번호 <input type="password" name="pwd" />
		</form>
		<ul style="list-style-type: upper-roman;">
			<li><input type="button" id="btnNoAjax" value="회원여부(AJAX미사용)" /></li>
			<!-- 
			Spring:반환타입을 void로 하거나
			반환타입을 String으로하고 @ResponseBody어노테이션 사용
			-->
			<li><a href="#" id="btnAjaxText">회원여부(AJAX사용-TEXT로 응답받기)</a></li>
			<li><input type="button" id="btnAjaxJson" value="회원여부(AJAX사용-JSON으로 응답받기)" /></li> ​
		</ul>

		<hr />
		<span id="lblDisplay" style="color: red; font-size: 2em; font-weight: bold">${isLogin}</span>
		<h3>JSON형식(배열타입)</h3>
		<a href="#">목록가져오기</a><br /> <span id="list"></span>
		<h3>Ajax폴링을 이용한 실시간 업데이트 웹 구현</h3>
		<button id="ajaxPolling">ajaxPolling</button>
		<span id="polling"></span>
		<!--  
		문]
		닷넷과정 선택시 커리큘럼에
			<option value="d01">C#</option>
			<option value="d02">ASP.NET</option>
			<option value="d03">WPF4</option>
		사물인터넷과정 선택시 커리큘럼에
			<option value="i01">라즈베리 파이</option>
			<option value="i02">파이썬</option>
		이 보이도록 AJAX로구현하여라, 단, 서버에서 데이타를 JSON타입으로 받아라. -->

		<h3>AJAX 실습하기</h3>
		과정 <select name="course" id="course">
			<option value="java">자바과정</option>
			<option value="dotnet">닷넷과정</option>
			<option value="iot">사물인터넷과정</option>
		</select> 
		커리큘럼 <select name="curriculum" id="curriculum">
			<option value="j01">자바</option>
			<option value="j02">JSP</option>
			<option value="j03">스프링</option>
		</select>
		
		
	</div><!-- container -->






	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
		//로드가 다 되어서 로드함수 미사용
		//1) ajax 미사용
		$('#btnNoAjax').click(function(){
			//form tag의 action 속성 및 mathod 속성 설정
			$('#frm').prop({action:'<c:url value="/Ajax/NoAjax.do"/>',method:'post'});
			//폼 객체의 submit() 함수로 서버로 전송
			$('#frm').submit();
		});

		//2) Ajax 사용
		/*
		post 방식으로 전송시
		type:"post" 그리고  contentType 디폴트로 즉 설정 불필요
		
		get 방식은
		type:"get"으로 contentType은 전송하는 타입따라(생략가능)
		
		전송할 데이터가 여러개인 경우 <form>태그로 감싸주고
		
		$("form선택자").serialize() 함수 사용.
		쿼리스트링으로 반환해줌
		*/
		$('#btnAjaxText').click(function(){
			console.log("serialize() 함수: ",$('#frm').serialize());
			//쿼리스트링으로 나온다.- id=KIM&pwd=1234
			//a tag클릭시 Ajax 요청
			$.ajax({
				url:"<c:url value="/Ajax/AjaxText.do"/>",//요청할 서버의 URL
				type: 'post',//데이터 전송 방식
				dataType:'text',//서버로 부터 응답 받을 데이타의 형식 설정
				data:
					//1)쿼리스트링 문자열로 전송 - data가 적을 떄
					//"id="+$(':input[name=id]').val()+'&pwd='+$('input[type=password]').val(),
					//2) JSON형태로 전송
					//{id:$(':input[name=id]').val(),pwd:$('input[type=password]').val()},
					//3) $('form 선택자').serialize() 데이터가 많을떄 사용
					$('#frm').serialize(),
				success:function(data){//서버로 부터 정상적인 응답을 받을 때 호출되는 함수
					//여기안에서 서버로 부터 받은 데이터를 원하는 위치에 뿌려주면 된다.
					//서버로 부터 받은 데이터는 매개변수로 전달된다.
					console.log("서버로 부터 받은 데이터: ",data);
					console.log("서버로 부터 받은 데이터의 타입: ",typeof data);
					//서버에서 Y 혹은 N으로 응답할 떄
					//$('#lblDisplay').html(data=="Y"?$('input[name=id]').val()+'님 방가방가':'회원이 아니에요');
					//서버에서 메시지로 응답할때,
					$('#lblDisplay').html(data);
				},
				error:function(error){//서버로 부터 비정상적인 응답을 받았을 때 호출되는 콜백함수
					console.log("에러: ",error.responseText);
				}
				
			});
			
		});		
		
		
		
		
		//3) ajax사용 json 으로 받기
		$('#btnAjaxJson').click(function(){
			$.ajax({
				url:"<c:url value="/Ajax/AjaxJson.do"/>",
				data:$('#frm').serialize(),
				dataType:'json',//text면 json으로 보내도 키값으로 꺼내올 수 없다.
				success:successCallBack,//함수명만 쓰고 ()는 제외
				error:function(request,status,error){
					console.log('응답코드:%s,에러메시지:%s,error:%s,status:%s',
						     request.status,
							 request.responseText,
							 error,
							 status
							);
				}
			});
			
		});
		
		
		
		//4) json 배열로 받기 // data를 설정하지 않음
		$('a:last').click(function(){
			$.ajax({
				url:"<c:url value="/Ajax/AjaxJsonArray.do"/>",
				dataType:'json',
				success:function(data){successAjax(data,'list')},
				error:function(request, error){
					console.log('응답코드(상태코드): ',request.status);
					console.log('서버로 부터 받은 HTML: ',request.responseText);
					console.log('에러: ',error);
				}
			});
			
			
		});
		
		
		//5) Ajax polling 구현
		$('#ajaxPolling').one('click',function(){
			//아래 요청을 특정 간격으로 서버에 보내자
			window.setInterval(function() {
				//ajax 로 요청
				$.ajax({
					url:"<c:url value="/Ajax/AjaxJsonArray.do"/>",
					dataType:'json',
					success:function(data){successAjax(data,'polling');},
					error:function(request,error){
						console.log("상태코드:",request.status);
						console.log("서버로부터 받은 HTML:",request.responseText);
						console.log("에러:",error);
					}
				});
			}, 50000)//ms시간 단위  
			
			
		});
		
		
		
		//위의 문제) ..
		$('#course').change(function() {
			$.ajax({
				url:"<c:url value="/Ajax/AjaxCourse.do"/>",
				data: {course:$('#course').val()},
				dataType:'json',
				success:function(data){
					//{'i01':'라즈베리 파이','i02':'파이썬'} 로 넘어온다.
					var options = "";
					$.each(data,function(key,value){
						options+= "<option value='"+key+"'>"+value+"</option>"
					});
					$('#curriculum').html(options);
				},
				error:function(request,error){
					console.log("상태코드:",request.status);
					console.log("서버로부터 받은 HTML:",request.responseText);
					console.log("에러:",error);
				}
			});
			
		});
		
		
		
		
		
		
		function successCallBack(data){
			/*data는 서버측에서 전송한 데이타(JSON형식)
			data는 dataType:"json"로 지정했기때문에
			JSON데이타 타입(object)임.
			만약 dataType:"text"로 설정하면 data는 string객체 타입임.
			string타입을 JSON타입으로 변환하려면
			JSON.parse(string객체)
			즉 data.키값 으로 value값을 꺼내온다.]
			{isMember:"메시지"}형태로 서버에서 응답 
			data=JSON.parse(data);//dataType:"text"일때 
			
			※string JSON.stringify(JSON객체 즉 {}):
			 {}타입의 객체를 string타입으로 변환하는 메소드
			*/	
			console.log('서버로 부터 받은 데이터: %s, 타입: %s',data, typeof data);
			console.log('서버로 부터 받은 데이터: %s',JSON.stringify(data));
			$('#lblDisplay').html(data.isLogin);
		}
		
		
		var successAjax = function(data, id){
			console.log('서버로 부터 받은 데이터: ',JSON.stringify(data));
			/*JSON배열을 출력할때는 $.each(data,function(index,index에 따른 요소값){}); 
			사용]
			data:서버로부터 전송받은 데이타(JSON배열타입)
			index:JSON배열의 인덱스(0부터 시작)	
			index에 따른 요소값:JSON배열에서 하나씩 꺼내온거를 담은 인자		
		    */
			var tableString = "<table class='table table-bordered'>";
			tableString += "<tr >";
			tableString += "<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th>";
			tableString += "</tr>";
			$.each(data, function(index,element){
				tableString += "<tr>";
				tableString += "<td>"+(index+1)+"</td><td>"+element["title"]+"</td><td>"+element["name"]+"</td><td>"+element["postDate"]+"</td>";
				tableString += "</tr>";
			});
			tableString	+= "</table>";
			$("#"+id).html(tableString);
		};
		
		
		
		
		
		
		
		
		
		
		
	</script>

</body>
</html>
