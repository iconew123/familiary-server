<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="/resources/style/form.css">
</head>
<body>
	<section id="root">
		<h2>�α���</h2>
		<form method="POST" action="/loginPro">
			<div>
				<input type="text" id="id" name="id" placeholder="���̵�">
				<input type="password" id="password" name="password" placeholder="��й�ȣ">
			</div>
			<div class="error-container">
				<p class="error-msg" id="error-msg-id">* ���̵�: �ʼ� �����Դϴ�.</p>
				<p class="error-msg" id="error-msg-password">* ��й�ȣ: �ʼ� �����Դϴ�.</p>
			</div>
			
			<input type="submit" value="�α���">
		</form>
	</section>
</body>
</html>