<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Recruit Site</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">Recruit Site</a>
		</div>
	</nav>

<div class="container">
	<h2>로그인 페이지</h2>
	<form>
		<div class="mb-3 mt-3">
			<h3>◆아이디</h3>
			<input id="login_id" type="text" class="form-control"
				placeholder="Enter id">
		</div>
		<div class="mb-3 mt-3">
			<h3>◆비밀번호</h3>
			<input id="login_password" type="text" class="form-control"
				placeholder="Enter password">
		</div>

		<div class="d-flex justify-content-around">
			<button id="btnLogin" type="button" class="btn btn-primary">로그인</button>
			<button id="btnJoin" type="button" class="btn btn-primary">회원가입</button>
		</div>
	</form>
</div>

<script>
	$("#btnLogin").click(() => {
		let login_dto = {
			loginId: $("#login_id").val(),
			loginPassword: $("#login_password").val()
		}
		login(login_dto);
	});

	function login(login_dto) {
		$.ajax("/login", {
			type: "POST",
			dataType: "JSON",
			data: JSON.stringify(login_dto),
			headers: {
				"Content-Type": "application/JSON; charset=utf-8"
			}
		}).done((res) => {
			if (res.code == 1) {
				location.href = "/";
			}
		});
	}
</script>

</body>

</html>