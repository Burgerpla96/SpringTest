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
<title>Websocket.jsp</title>

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
				Spring<small>Websocket</small>
			</h1>
		</div>

		<fieldset>
			<legend>웹소켓 클라이언트</legend>
			<form>
				<div class="form-group">
					<label for="nickname" class="col-sm-1">닉네임</label>
					<div class="col-sm-4">
						<input class="form-control " type="text" id="nickname">
					</div>
				</div>
				<input class="btn btn-info" type="button" id="enterBtn" value="입장">
				<input class="btn btn-danger" type="button" id="exitBtn" value="퇴장">
				<div class="form-group">
					<h4>대화내용</h4>
					<div id="chatArea">
						<div id="chatMessage" style="height: 300px; border: 1px gray solid; overflow: auto"></div>
						<!-- overflow 내용이 많으면 자동으로 스크롤바가 생긴다. -->
					</div>
				</div>​
				<div class="form-group">
					<label for="message" class="col-sm-1">메시지</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" id="message" />
					</div>
				</div>
				<input class="btn btn-success" type="button" id="sendBtn" value="전송">
			</form>​
		</fieldset>




	</div>






	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

	<!-- pure speech bubble로 검색 -->
	<!-- codepen 사이트에서 가져온다. -->
		
	<script>
		/*
		채팅 테스트 localhost를 아이피로 변경 소스 및 브라우저 URL도 변경
		그리고 인바운드 규칙 추가 403,8080
		*/
		//WEB socket의 client를 만드는 과정...
		//웹 소켓 저장용
		var wsocket;
		//닉네임 저장용
		var nickname;
		//입장버튼 클릭시, 서버와 연결된 소켓 클라이언트 생성
		$('#enterBtn').one('click',function(){
			//cmd 에서 ipconfig로 확인-IPv4 주소의 번호를 가져온다. 
			wsocket = new WebSocket("ws://192.168.0.25:8080<c:url value='/chat-ws.do'/>");
			console.log('websocket: '+wsocket);
			//서버와 연결된 소켓에 이벤트 등록(open,close,message,error)
			wsocket.onopen = open;
			wsocket.onclose=function(){
				appendMessage('연결이 끊어졌어요.');
			};
			wsocket.addEventListener('message',receiveMessage);
			wsocket.onerror=function(e){
				console.log('에러발생: '+e);
			};
		});
		
		
		//퇴장 버튼 클릭시
		$('#exitBtn').one('click',function(){
			//서버로 메세지를 보내는 메서드 send()
			wsocket.send('msg: '+nickname+'가(이) 퇴장했어요.');
			wsocket.close();
		});
		
		
		//전송버튼 이벤트 처리-클릭시
		$('#sendBtn').click(function(){
			sendMessage();
		});
		//전송버튼 이벤트 처리-enter입력시
		$('#message').on('keypress',function(e){
			//브라우저마다 다른 엔터 입력값 처리를 위해 e.keyCode,e.which 모두 처리해준다.
			console.log('e.keyCode: %s, e.which: %s',e.keyCode, e.which);
			var keyValue = e.keyCode ? e.keyCode : e.which;
			if(keyValue == 13) {//enter 입력시
				sendMessage();
			}
		});
		
		
		
		/////////////////////////////////////////////////함수 정의
		
		//서버에 연결되었을 때 호출되는 함수
		var open = function(){
			//연결시 닉네임 전송
			//사용자가 입력한 닉네임 저장
			nickname = $('#nickname').val();
			wsocket.send('msg: '+nickname+"가(이) 입장했어요.");
			appendMessage('연결이 되었어요.');
			
		};
		
		//메세지를 div에 뿌려주기 위한 함수
		var appendMessage = function(msg){
			$('#chatMessage').append(msg+"<br/>");
		};
		
		//서버에서 메세지를 받을 때의 함수
		var receiveMessage = function(e){//e는 message 이벤트 객체
			//서버로 부터 받은 데이터는 이벤트 객체(e).data속성에 저장되어 있다.
			var receiveData = e.data;
			if(receiveData.substring(0,4)=='msg:')
				appendMessage(receiveData.substring(4));
		};
		//서버로 메세지를 전송하는 함수
		function sendMessage(){
			//서버로 메세지 전송
			wsocket.send("msg: "+nickname+':'+$('#message').val()); //msg:Superman:안녕
			//div(대화영역)에 메세지 출력
			appendMessage($('#message').val());
			//기존 메세지 클리어
			$('#message').empty();
			$('#message').focus();
		};
		
		
	</script>	
	
	
	
	
</body>
</html>
