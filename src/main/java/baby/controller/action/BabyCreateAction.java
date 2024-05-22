package baby.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.json.JSONObject;

import baby.controller.Action;
import baby.model.Baby;
import baby.model.BabyDao;
import baby.model.BabyRequestDto;
import baby.model.BabyResponseDto;
import enroll.model.EnrollDao;
import enroll.model.EnrollRequestDto;

public class BabyCreateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();
		System.out.println("method : " + method);

		String nickname = null;
		String name = null;
		String gender = null;
		String expected_date = null;
		String blood_type = null;
		
		String user_id = null;
		String position = null;
		
		if (method.equals("POST")) {
			// 요첨 값 받아오기
			Collection<Part> parts = request.getParts();


			// 각 Part 객체를 순회하며 이름과 내용을 출력
			for (Part part : parts) {
				System.out.print(part.getName() + " ");

				String partName = part.getName();
				InputStream in = part.getInputStream();

				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String value = br.readLine();

				switch (partName) {
				case "nickname":
					nickname = value;
					break;
				case "name":
					name = value;
					break;
				case "gender":
					gender = value;
					break;
				case "expected_date":
					expected_date = value;
					break;
				case "blood_type":
					blood_type = value;
					break;
				
				case "user_id":
					user_id = value;
					break;
				case "position":
					position = value;
					break;
				}

				br.close();
			}
		}

		// 생성 로직
		// BabyDto 생성 -> DB에 insert

		BabyRequestDto baby = new BabyRequestDto(nickname, name, gender, expected_date, blood_type);		
		BabyDao dao = new BabyDao();
		dao.createBaby(baby);
		
		Baby sample = dao.readLatestBaby();
		String baby_code = sample.getCode();

		EnrollRequestDto enroll = new EnrollRequestDto(user_id, baby_code, position);
		EnrollDao enrollDao = new EnrollDao();
		enrollDao.createEnroll(enroll);

		
		// 결과를 응답하기
		JSONObject resObj = new JSONObject();
		resObj.put("status", 200);
		resObj.put("message", "아기가 성공적으로 등록되었습니다.");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf8");

		response.getWriter().append(resObj.toString());

	}

}
