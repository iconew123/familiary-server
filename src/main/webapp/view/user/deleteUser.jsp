<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<section id="root">
		<h2>ȸ�� Ż��</h2>
		<form method="POST" action="/deleteUserFormPro">
			<div>
				<input type="password" id="password" name="password" placeholder="��й�ȣ">
			</div>
			<div class="error-container">
				<p class="error-msg" id="error-msg-password">* ��й�ȣ: �ʼ� �����Դϴ�.</p>
			</div>
			
			<input type="submit" value="ȸ��Ż��">
		</form>
	</section>
</body>
</html>