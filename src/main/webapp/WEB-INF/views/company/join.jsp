<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<h2>기업 회원가입 페이지</h2>
		<div class="mb-3 mt-3">
			◆아이디 <input id="loginId" type="text" class="form-control" placeholder="아이디를 입력해주세요.">
			<button id="btnUsernameSameCheck" class="btn btn-warning" type="button">아이디 중복체크</button>
		</div>
		
		<div class="mb-3">
			◆비밀번호 
			<input id="loginPassword" type="password" class="form-control" placeholder="비밀번호를 입력해주세요">
		</div>
		<div class="mb-3">
			◆비밀번호 확인 
			<span id="passwordCheck" style="visibility: hidden; color: tomato;">
			-----비밀번호가 같지 않습니다!</span>
			<input id="passwordConfirm" type="password" class="form-control" placeholder="비밀번호를 입력해주세요">
		</div>
		
		<div class="mb-3"></div>
		
		<div class="mb-3">
			◆이름 <input id="companyName" type="text" class="form-control" placeholder="이름을 입력해주세요">
		</div>
		
		<div class="mb-3">
			◆전화번호<input id="companyPhoneNumber" type="text" class="form-control" placeholder="전화번호를 입력해주세요">
		</div>
		
		<div class="mb-3">
			◆이메일<input id="companyEmail" type="email" class="form-control" placeholder="이메일을 입력해주세요">
		</div>
		
		<div class="mb-3">
			◆사진<input id="companyPicture" type="text" class="form-control" placeholder="사진 입력 자리">
		</div>

		<div class="mb-3">◆주소</div>
		<input id="postcode" type="text" placeholder="우편번호" readonly onclick="findAddr()">
		<button id="btnAddress" type="button" class="btn btn-primary" onclick="findAddr()">우편번호찾기</button>
		<br> 
		<input id="addr" type="text" placeholder="주소" style="width: 620px;" readonly>
		<input id="detailAddress" type="text"  placeholder="상세주소" style="width: 620px;">

	</form>
	
	<div class="mb-5"></div>
	
	<div class="d-grid gap-1 col-2 mx-auto">
		<button id="btnJoin" type="button" class="btn btn-primary" disabled="disabled">작성완료</button>
	</div>

</div>

<script>
//회원가입 - 기업 ============================== //
	$("#btnJoin").click(() => {
		let data = {
			loginId: $("#loginId").val(),
			loginPassword: $("#loginPassword").val(),
			companyName: $("#companyName").val(),
			companyPhoneNumber: $("#companyPhoneNumber").val(),
			companyEmail: $("#companyEmail").val(),
			companyPicture: $("#companyPicture").val(),
			companyAddress: $("#postcode").val() + "," + $("#addr").val() + "," + $("#detailAddress").val()
		};
	
		$.ajax("/join/company", {
			type: "POST",
			dataType: "json",
			data: JSON.stringify(data),
			headers: {
				"Content-Type": "application/json"
			}
		}).done((res) => {
			alert("기업 회원가입 성공");
			location.href = "/loginForm";
		});
		
	});
	
	// 아이디 중복체크
	let UsernameSameCheck = {
		loginId : null,
		isCheck : 0
	}
	
	$("#btnUsernameSameCheck").click(() => {
		if ($("#loginId").val() == "") {
			alert("아이디를 입력하여 주세요");
			return;
		} else {
			let loginId = $("#loginId").val();
			$.ajax("/checkId/" + loginId, {
				type: "GET",
				dataType: "JSON",
			}).done((res) => {
				if (res.code == 1) {
					alert(res.message);
					UsernameSameCheck.loginId = $("#loginId").val();
					UsernameSameCheck.isCheck = true;
				} else {
					alert(res.message);
					UsernameSameCheck.isCheck = false;
				}
			});
		}
	});
	
	// 패스워드 일치 여부 체크
	$("#passwordConfirm").keyup((event) => {
		event.preventDefault();
		if ($("#loginPassword").val() != $("#passwordConfirm").val()) {
			$("#passwordCheck").css("visibility", "visible");
			$("#btnJoin").attr(`disabled`, true);
		} else {
			$("#passwordCheck").css("visibility", "hidden");
			$("#btnJoin").removeAttr(`disabled`);
		}
	});
	
	// 주소 불러오기
	function findAddr() {
		new daum.Postcode({
				oncomplete: function(data) {
					// 검색결과 항목을 선택했을때 실행할 코드
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가짐
					
					let roadAddr = data.roadAddress; // 도로명 주소 변수
					let jibunAddr = data.jibunAddress; // 지번 주소 변수
					
					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					document.getElementById('postcode').value = data.zonecode;
					if (roadAddr !== '') {
						document.getElementById("addr").value = roadAddr;
					} else if (jibunAddr !== '') {
						document.getElementById("addr").value = jibunAddr;
					}
				}
		}).open();
	}

</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<%@ include file="../layout/footer.jsp"%>