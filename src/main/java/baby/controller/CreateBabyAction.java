package baby.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import baby.model.BabyDao;
import baby.model.BabyRequestDto;
import baby.model.BabyResponseDto;

public class CreateBabyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String code = request.getParameter("code");
		String nickname = request.getParameter("nickname");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String expected_date = request.getParameter("expected_date");
		String blood_type = request.getParameter("blood_type");

		System.out.println("code: " + code);
		System.out.println("nickname: " + nickname);
		System.out.println("name1: " + name);
		System.out.println("gender1: " + gender);
		System.out.println("expected_date1: " + expected_date);
		System.out.println("blood_type" + blood_type);
		boolean isValid = true;

		if (nickname == null || nickname.equals(""))
			isValid = false;
		else if (expected_date == null || expected_date.equals(""))
			isValid = false;

		System.out.println("isValid : " + isValid);
		if (isValid) {
			SerialBlob photoBlob = null;
			try {
				InputStream inputStream = request.getPart("photo").getInputStream();
				byte[] photoBytes = new byte[inputStream.available()];
				inputStream.read(photoBytes);
				photoBlob = new SerialBlob(photoBytes);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			BabyRequestDto babyDto = new BabyRequestDto(code, nickname, name, gender, expected_date, photoBlob,
					blood_type);
			
			BabyDao babyDao = BabyDao.getInstance();
			BabyResponseDto baby = babyDao.createBaby(babyDto);
			
			if(baby == null ) {
				System.out.println("실패");
			} else {
				System.out.println("성공");
			}

		} else {
			// null 값 존재
		}
	}

}
