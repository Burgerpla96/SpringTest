<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	a = 10;
	console.log(a);
	$(function(){
		//POST method: json으로 하나의 데이터를 받아서 받은 결과 그대로 json으로 응답 보내기
		$('#btn1').on('click',function(){
			//var data = $('#form1').serialize();//쿼리스트링으로 연결된다.//name=1&age=2&addr=3
			var data =  $('#form1').serializeArray();//[{…}, {…}, {…}] 형태
			/*
			[{name: "name", value: "1"},
			{name: "age", value: "2"},
			{name: "addr", value: "3"}]
			폼 하위요소의 속성명이 키가 되고, 파라미터 명이나 사용자가 입력한 값이 속성명에 따른 벨류값이 돤다.
			*/
			console.log(data);
			obj = {};
			$.each(data,function(index,element){
				obj[element.name]=element.value;
			});
			console.log(obj);
			$.ajax({
				type:'post',
				url:"<c:url value='/json/users/one'/>",
				dataType:'json',
				data:JSON.stringify(obj),
				contentType:'application/json',
				success:function(data){
					console.log('서버로 부터 받은 데이터: ',data);
				}
			});
			
		});
		
		
		//POST method: json으로 여러개 데이터를 받아서 받은 결과 그대로 json으로 응답 보내기
		$('#btn2').click(function(){
			var obj = [
				{"name":"가길동","age":20,"addr":"가산동"},
				{"name":"나길동","age":30,"addr":"나산동"},
				{"name":"다길동","age":40,"addr":"다산동"}];
			$.ajax({
				type:'post',
				url:"<c:url value='/json/users'/>",
				dataType:'json',
				data:JSON.stringify(obj),
				contentType:'application/json',
				success:function(data){
					console.log('서버로 부터 받은 데이터: ',data);
				}
			});
			
		});
		
	
		//PUT메소드:아이디 받아서 수정후 수정 데이타 보내기
		$('#btn3').click(function(){
			var data =  $('#form3').serializeArray();
			var obj = {};
			$.each(data,function(index,element){
				obj[element.name]=element.value;
			});
			//   /json/users/kim 식으로 요청
			$.ajax({
				type:'PUT',//method형식에 따라서 type바꾸기
				url:"<c:url value='/json/users/'/>"+$('#idput').val(),
				dataType:'json',
				data:JSON.stringify(obj),
				contentType:'application/json',
				success:function(data){
					console.log('서버로 부터 받은 데이터: ',data);
				}
			});
			
		});
		
		
		//DELETE method: 아이디 받아서 삭제후 삭제된 데이터 보기
		$('#btn4').click(function(){
			var obj = {"name":"가길동","age":20,"addr":"가산동"};
			//DELETE   /rest/json/users/kim 식으로 요천
			$.ajax({
				type:'DELETE',
				url:"<c:url value='/json/users/'/>"+$('#iddelete').val(),
				dataType:'json',
				data:JSON.stringify(obj),
				contentType:'application/json',
				success:function(data){
					console.log('서버로 부터 받은 데이터: ',data);
				}
			});
			
		});
	
	
		
		
	});
</script>
</head>
<body>
	<h1>Spring REST API 서비스!</h1>
	<P>The time on the server is ${serverTime}.</P>
	<h2>form태그 사용하기</h2>
	<fieldset>
		<legend>GET메소드:쿼리 파라미터 사용</legend>
		<form action="<c:url value='/users'/>">
			<input type="text" name="name" /> <input type="submit" value="확인" />
		</form>
	</fieldset>
	<fieldset>
		<legend>GET메소드:URI 파라미터 사용</legend>
		<form action="<c:url value='/users/김길동'/>">
			<input type="submit" value="확인" />
		</form>
	</fieldset>
	<fieldset>
		<legend>POST메소드:쿼리 파라미터 사용</legend>
		<form method="post" action="<c:url value='/users?name=박길동'/>">
			<!-- post방식일때도 query string으로 보내면 url에 보인다. -->
			<input type="submit" value="확인" />
		</form>
	</fieldset>
	<fieldset>
		<legend>POST메소드:URI 파라미터 사용</legend>
		<form method="post" action="<c:url value='/users/홍길동'/>">
			<input type="submit" value="확인" />
		</form>
	</fieldset>
	<fieldset>
		<legend>GET메소드:JSON 배열 리턴하기</legend>
		<form action="<c:url value='/json/users'/>">
			<input type="submit" value="확인" />
		</form>
	</fieldset>
	<fieldset>
		<legend>GET메소드:JSON 하나 리턴하기</legend>
		<form action="<c:url value='/json/users/kim'/>">
			<input type="submit" value="확인" />
		</form>
	</fieldset>
	<fieldset>
		<legend>POST메소드:JSON으로 하나의 데이타 받아서 받은 결과 그대로 JSON으로 응답 보내기</legend>
		<form id="form1">
			이름 <input type="text" name="name" /> 
			나이 <input type="text" name="age" /> 
			주소 <input type="text" name="addr" /> 
			<input type="button" value="확인" id="btn1" />
		</form>
	</fieldset>
	<fieldset>
		<legend>POST메소드:JSON으로 여러개의 데이타 받아서 받은 결과 그대로 JSON배열로 응답 보내기</legend>
		<input type="button" value="확인" id="btn2" />
	</fieldset>
	<fieldset>
		<legend>PUT메소드:아이디 받아서 수정후 수정 데이타 보내기</legend>
		<form id="form3">
			​아이디 <input type="text" id="idput" value="kim" /> 
			이름 <input type="text" name="name" /> 
			나이 <input type="text" name="age" /> 
			주소 <input type="text" name="addr" /> 
			<input type="button" value="확인" id="btn3" />
		</form>
	</fieldset>
	<fieldset>
		<legend>DELETE메소드:아이디 받아서 삭제후 삭제된 데이타 보내기</legend>
		아이디 <input type="text" id="iddelete" value="park" /> 
		<input type="button" value="확인" id="btn4" />
	</fieldset>
	<fieldset>
		<legend>POST메소드:파일 업로드</legend>
		<form method="POST" action="<c:url value='/users/upload'/>"
			enctype="multipart/form-data">
			이름 <input type="text" name="name" /> 
			나이 <input type="text" name="age" /> 
			주소 <input type="text" name="addr" /> 
			파일 첨부<input type="file" name="upload" /> 
			<input type="submit" value="확인" />
		</form>
	</fieldset>
</body>
</html>
