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
import baby.model.BabyDao;
import baby.model.BabyRequestDto;

public class BabyCreateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();
		System.out.println("method : " + method);

		if (method.equals("POST")) {
			// 요첨 값 받아오기
			Collection<Part> parts = request.getParts();

			String nickname = null;
			String name = null;
			String gender = null;
			String expected_date = null;
			Blob photo = null;
			String blood_type = null;

			// 각 Part 객체를 순회하며 이름과 내용을 출력
			for (Part part : parts) {
				System.out.print(part.getName() + " ");

				String partName = part.getName();
				InputStream in = part.getInputStream();

				if (partName.equals("photo")) {
					try {
					photo = createBlobFromPart(part);
					}catch(SQLException e) {
						e.printStackTrace();
						return;
					}
				} else {
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
					}

					br.close();
				}
				in.close();
			}

			// 생성 로직
			// BabyDto 생성 -> DB에 insert

			BabyRequestDto baby = new BabyRequestDto(nickname, name, gender, expected_date, photo, blood_type);
			BabyDao dao = new BabyDao();
			dao.createBaby(baby);
			
			// 결과를 응답하기
			JSONObject resObj = new JSONObject();
			resObj.put("status", 200);
			resObj.put("message", "아기가 성공적으로 등록되었습니다.");

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf8");

			response.getWriter().append(resObj.toString());
		} else {
			response.sendError(404, "유효하지 않은 접근 경로입니다.");
		}

	}

	private Blob createBlobFromPart(Part part) throws IOException, SQLException {
		
		InputStream inputStream = part.getInputStream();
		byte[] bytes = inputStream.readAllBytes();
		
		inputStream.close();
		return new SerialBlob(bytes);
	}

}
