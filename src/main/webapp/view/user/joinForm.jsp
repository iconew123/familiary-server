<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="/resources/style/user.css">tyle/form.css">
</head>
<script src="/resources/script/validation-join.js"></script>
<body>
	<section id="root">
		<h2>ȸ������</h2>
		<form method="POST" action="/joinFormPro">
			<div>
				<input type="text" id="id" name="id" placeholder="���̵�">
				<input type="password" id="password" name="password" placeholder="��й�ȣ">
				<input type="text" id="nickname" name="nickname" placeholder="�г���">
				
			</div>
			<div class="error-container">
				<p class="error-msg" id="error-msg-id">* ���̵�: �ʼ� �����Դϴ�.</p>
				<p class="error-msg" id="error-msg-password">* ��й�ȣ: �ʼ� �����Դϴ�.</p>
				<p class="error-msg" id="error-msg-nickname">* �г���: �ʼ� �����Դϴ�.</p>

			</div>
			<div>
				<input type="text" id="name" name="name" placeholder="�̸�">
				<input type="text" id="security_number" name="security_number" placeholder="�ֹε�Ϲ�ȣ">
				<select id="telecom" name="telecom">
					<option selected disabled>��Ż� ����</option>
					<option value="skt">SKT</option>
					<option value="kt">KT</option>
					<option value="lgt">LGU+</option>
				</select>
				<input type="text" id="phone" name="phone" placeholder="�޴���ȭ��ȣ">
				<input type="text" id="email" name="email" placeholder="[����] �̸����ּ� (��й�ȣ ã�� �� ���� Ȯ�ο�)">
				<input type="text" id="adress" name="adress" placeholder="[����] �ּ�">
			</div>
			<div class="error-container">
				<p class="error-msg" id="error-msg-name">* �̸�: �ʼ� �����Դϴ�.</p>
				<p class="error-msg" id="error-msg-security_number">* �ֹε�Ϲ�ȣ: �ʼ� �����Դϴ�.</p>
				<p class="error-msg" id="error-msg-telecom">* ��Ż�: �̿��ϴ� ��Ż縦 ������ �ּ���.</p>
				<p class="error-msg" id="error-msg-phone">* �޴���ȭ��ȣ: �ʼ� �����Դϴ�.</p>
				<p class="error-msg" id="error-msg-phone-pattern">* �޴���ȭ��ȣ: �޴���ȭ��ȣ�� ��Ȯ���� Ȯ���� �ּ���.</p>
				<p class="error-msg" id="error-msg-email">* �̸���: �̸��� �ּҰ� ��Ȯ���� Ȯ���� �ּ���.</p>
			</div>
			<input type="submit" value="ȸ������">
		</form>
		</section>
</body>
</html>