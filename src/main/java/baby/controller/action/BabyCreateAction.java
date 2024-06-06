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

import image.model.ImageDao;
import image.model.ImageRequestDto;
import org.json.JSONObject;

import baby.model.Baby;
import baby.model.BabyDao;
import baby.model.BabyRequestDto;
import baby.model.BabyResponseDto;
import enroll.model.EnrollDao;
import enroll.model.EnrollRequestDto;
import util.Action;
import util.InputStreamParsor;

public class BabyCreateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();

		String nickname = null;
		String name = null;
		String gender = null;
		String expected_date = null;
		String blood_type = null;

		String imageId = null;
		String imageUrl = null;

		String user_id = null;
		String position = null;

		if (method.equals("POST")) {
			Collection<Part> parts = request.getParts();

			for (Part part : parts) {
				String type = part.getContentType();
				String partName = part.getName();

				InputStream in = part.getInputStream();

				if (partName.equals("photo") && type != null) {

					try {
						JSONObject jsonResponse = InputStreamParsor.uploadImage(in, type);
						imageId = jsonResponse.getJSONObject("data").getString("id");
						imageUrl = jsonResponse.getJSONObject("data").getString("url");
						System.out.println("Image ID: " + imageId);
						System.out.println("Image URL: " + imageUrl);
					} catch (Exception e) {
						e.printStackTrace();
						throw new ServletException("이미지 업로드 실패", e);
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

						case "user_id":
							user_id = value;
							break;
						case "position":
							position = value;
							break;
					}

					br.close();
				}
				in.close();
			}
		}

		BabyRequestDto baby = new BabyRequestDto(nickname, name, gender, expected_date, blood_type);
		BabyDao dao = new BabyDao();
		dao.createBaby(baby);

		Baby sample = dao.readLatestBaby();
		String baby_code = sample.getCode();

		System.out.println("유저: " + user_id);
		System.out.println("아기: " + baby_code);

		EnrollRequestDto enroll = new EnrollRequestDto(user_id, baby_code, position);
		EnrollDao enrollDao = new EnrollDao();
		enrollDao.createEnroll(enroll);

		if ((imageId != null && imageUrl != null)) {
			ImageDao imageDao = ImageDao.getInstance();

			ImageRequestDto uploadImage = new ImageRequestDto(imageUrl, imageId, "baby", baby_code);
			System.out.println(uploadImage);

			boolean isUploadSuccess = imageDao.createImage(uploadImage);

			if (isUploadSuccess) {
				System.out.println("이미지 업로드 성공");
			} else {
				System.out.println("이미지 업로드 실패");
			}
		}

		JSONObject resObj = new JSONObject();
		resObj.put("status", 200);
		resObj.put("message", "아기가 성공적으로 등록되었습니다.");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf8");

		response.getWriter().append(resObj.toString());

	}

}