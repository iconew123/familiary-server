$(document).ready(() => {
	$('#id').focusout(e => {
		if ($('#id').val() === "") {
			$('#error-msg-id').show();
			$('#id').css('border', 'solid 1px tomato');
		} else {
			$('#error-msg-id').hide();
			$('#id').css('border', 'solid 1px lightgrey');
		}
	});

	$('#password').focusout(e => {
		if ($('#password').val() === "") {
			$('error-msg-password').show();
			$('#password').css('border', 'solid 1px tomato');
		} else {
			$('#error-msg-password').hide();
			$('#password').css('border', 'solid 1px lightgrey');
		}
	});
	$('#nickname').focusout(e => {
		if ($('#nickname').val() === "") {
			$('#error-msg-nickname').show();
			$('#nickname').css('border', 'solid 1px tomato');
		} else {
			$('#error-msg-nickname').hide();
			$('#nickname').css('border', 'solid 1px lightgrey');
		}
	});

	$('#name').focusout(e => {
		if ($('#name').val() === "") {
			$('#error-msg-name').show();
			$('#name').css('border', 'solid 1px tomato');
		} else {
			$('#error-msg-name').hide();
			$('#name').css('border', 'solid 1px lightgrey');
		}
	});

	$('#security_number').focusout(e => {
		if ($('#security_number').val() === "") {
			$('#error-msg-security_number').show();
			$('#security_number').css('border', 'solid 1px tomato');
		} else {
			$('#error-msg-security_number').hide();
			$('#security_number').css('border', 'solid 1px lightgrey');
		}

		const security_number = $('#security_number').val();
		if (security_number.match(/\d{6}-\d{1}/) === null) {
			$('#error-msg-security_number-pattern').show();
			$('#security_number').css('border', 'solid 1px tomato');
		}
	}

	);

	$('#telecom').focusout(e => {
		if ($('#telecom').val() === null) {
			$('#error-msg-telecom').show();
			$('#telecom').css('border', 'solid 1px tomato');
		} else {
			$('#error-msg-telecom').hide();
			$('#telecom').css('border', 'solid 1px lightgrey');
			$('#telecom').css('border-bottom', 'none');
		}
	});

	$('#phone').focusout(e => {
		if ($('#phone').val() === "") {
			$('#error-msg-phone').show();
			$('#phone').css('border', 'solid 1px tomato');
		} else {
			$('#error-msg-phone').hide();
			$('#phone').css('border', 'solid 1px lightgrey');
		}
		const phone = $('#phone').val();
		if (phone.match(/\d{3}-\d{4}-\d{4} | \d{11}/) === null) {
			$('#error-msg-phone-pattern').show();
			$('#phone').css('border', 'solid 1px tomato');
		} else {
			if (phone.length === 11) {
				const update = `${phone.substr(0, 3)}-${phone.substr(3, 4)}-${phone.substr(7, 4)}`;
				$('#phone').val(update);
			}
		}
	});



	$('form').submit(e => {
		e.preventDefault();

		const id = $('#id').val();
		const password = $('#password').val();
		const nickname = $('#nickname').val();

		const name = $('#name').val();
		const security_number = $('#security_number').val();
		const telecom = $('#telecom').val();
		const phone = $('#phone').val();


		let isValid = true;

		if (id === "") {
			isValid = false;
			$('#error-msg-id').show();
			$('#id').css('border', 'solid 1px tomato');
		}
		if (password === "") {
			isValid = false;
			$('#error-msg-password').show();
			$('#password').css('border', 'solid 1px tomato');
		}
		if (nickname === "") {
			isValid = false;
			$('#error-msg-nickname').show();
			$('#name').css('border', 'solid 1px tomato');
		}
		if (name === "") {
			isValid = false;
			$('#error-msg-name').show();
			$('#name').css('border', 'solid 1px tomato');
		}
		if (security_number === "") {
			isValid = false;
			$('#error-msg-name').show();
			$('#name').css('border', 'solid 1px tomato');
		}

		if (telecom === null) {
			isValid = false;
			$('#error-msg-telecom').show();
			$('#telecom').css('border', 'solid 1px tomato');
		}
		if (phone === "") {
			isValid = false;
			$('#error-msg-phone').show();
			$('#phone').css('border', 'solid 1px tomato');
		}

		if (isValid) {
			e.target.submit();
		}

	});
});