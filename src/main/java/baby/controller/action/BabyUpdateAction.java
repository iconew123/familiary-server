package baby.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import image.model.ImageDao;
import image.model.ImageRequestDto;
import org.json.JSONObject;

import baby.model.BabyDao;
import baby.model.BabyRequestDto;
import util.Action;
import util.InputStreamParsor;

public class BabyUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();

		String code = null;
		String nickname = null;
		String name = null;
		String gender = null;
		String expected_date = null;
		String blood_type = null;

		String imageId = null;
		String imageUrl = null;

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

					} catch (Exception e) {
						e.printStackTrace();
						throw new ServletException("이미지 업로드 실패", e);
					}
				} else {
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					String value = br.readLine();

					switch (partName) {
						case "code":
							code = value;
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
		}
		BabyDao dao = new BabyDao();
		BabyRequestDto baby = new BabyRequestDto(code, nickname, name, gender, expected_date, blood_type);
		boolean success = dao.updateBaby(baby);

		if ((imageId != null && imageUrl != null)) {
			ImageDao imageDao = ImageDao.getInstance();

			ImageRequestDto uploadImage = new ImageRequestDto(imageUrl, imageId, "baby", code);

			dao.DeleteImageStatus(baby);
			imageDao.createImage(uploadImage);

		}

		JSONObject jsonResponse = new JSONObject();
		if (success) {
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "아기 정보가 성공적으로 업데이트되었습니다.");
		} else {
			jsonResponse.put("status", 500);
			jsonResponse.put("message", "아기 정보 업데이트에 실패했습니다.");
		}

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonResponse.toString());

	}

}
