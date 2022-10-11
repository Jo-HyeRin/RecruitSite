<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h2>개인 회원가입 페이지</h2>
	<form>
		<div class="mb-3 mt-3">
			◆아이디 <input id="loginId" type="text" class="form-control"
				placeholder="아이디를 입력해주세요.">
			<button id="btnUsernameSameCheck" class="btn btn-warning"
				type="button">아이디 중복체크</button>
		</div>
		<div class="mb-3">
			◆비밀번호 <input id="loginPassword" type="text" class="form-control"
				placeholder="비밀번호를 입력해주세요">
		</div>
		
		<div class="mb-3">
			◆비밀번호 확인
				<input type="text" class="form-control"
				placeholder="비밀번호를 한 번 더 입력해주세요">
		</div>

		<div class="mb-3">
			◆이름 <input id="username" type="text" class="form-control"
				placeholder="이름을 입력해주세요">
		</div>
		
		<div class="mb-3">
			◆전화번호<input id="phoneNumber" type="text" class="form-control"
				placeholder="전화번호 양식 : 000-000-0000">
		</div>
		
		<div class="mb-3">
			◆이메일<input id="email" type="text" class="form-control"
				placeholder="이메일을 입력해주세요">
		</div>
			
		<div class="mb-3"></div>
		
		<div class="mb-3">◆관심분야</div>
		<div>
			<label><input type="checkbox" value="categoryFrontend">frontend</label><br />
			<label><input type="checkbox" value="categoryBackend">backend</label><br />
			<label><input type="checkbox" value="categoryDevops">devops</label><br />
			<label><input id="etc" type="checkbox" value="etc">etc</label>
		</div>
	</form>
	
	<div class="mb-5"></div>
	
	<div class="d-grid gap-1 col-2 mx-auto">
		<button id="btnJoin" type="button" class="btn btn-primary">회원가입하기</button>
	</div>
	
</div>

<script>
	$('#etc').change(function() {
		var checked = $('#etc').is(':checked');
		if (checked)
			$('input:checkbox').prop('checked', true);
		else{
			$('input:checkbox').prop('checked', false);
		}
	});
	
	$("#btnJoin").click(() => {
		let data = {
			loginId: $("#loginId").val(),
			loginPassword: $("#loginPassword").val(),
			personalName: $("#username").val(),
			personalEmail: $("#email").val(),
			personalPhoneNumber: $("#phoneNumber").val(),
			categoryFrontend: $("input:checkbox[value='categoryFrontend']").is(":checked"),
			categoryBackend: $("input:checkbox[value='categoryBackend']").is(":checked"),
			categoryDevops: $("input:checkbox[value='categoryDevops']").is(":checked")
		};
	
		$.ajax("/join/personal", {
			type: "POST",
			dataType: "json",
			data: JSON.stringify(data),
			headers: {
				"Content-Type": "application/json"
			}
		}).done((res) => {
			alert("개인 회원가입 성공");
			// location.href = "/loginForm";
		});
	});
</script>

<%@ include file="../layout/footer.jsp"%>