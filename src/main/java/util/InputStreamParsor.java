package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;

import org.json.JSONObject;

public class InputStreamParsor {
	public static String parseToString(InputStream in) {
		String data = "";

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			while (br.ready()) {
				data += br.readLine() + "\n";
			}

			br.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static JSONObject uploadImage(InputStream in, String contentType) throws IOException, ServletException {

		byte[] imageContent = in.readAllBytes();

		URL url = new URL("https://api.imgbb.com/1/upload");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("POST");

		String boundary = UUID.randomUUID().toString();
		conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

		String key = "";
		try {
			Context init = new InitialContext();
			key = (String) init.lookup("java:comp/env/Imgbb/Key");
			if (key == null || key.isEmpty()) {
				throw new RuntimeException("API key키를 찾지 못하였습니다.");
			}
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServletException("API key키 인증 실패", e);
		}

		conn.setDoOutput(true);
		try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {

			out.writeBytes("--" + boundary + "\r\n");
			out.writeBytes("Content-Disposition: form-data; name=\"key\"\r\n\r\n");
			out.writeBytes(key + "\r\n");

			out.writeBytes("--" + boundary + "\r\n");
			out.writeBytes("Content-Disposition: form-data; name=\"image\"; filename=\"image\"\r\n");
			out.writeBytes("Content-Type: " + contentType + "\r\n\r\n");
			out.write(imageContent);
			out.writeBytes("\r\n");

			out.writeBytes("--" + boundary + "--\r\n");
			out.flush();
		}

		try (InputStream res = conn.getInputStream()) {
			String responseData = InputStreamParsor.parseToString(res);
			return new JSONObject(responseData);
		} catch (IOException e) {
			InputStream errorStream = conn.getErrorStream();
			if (errorStream != null) {
				String errorResponse = new BufferedReader(new InputStreamReader(errorStream)).lines()
						.collect(Collectors.joining("\n"));
				System.err.println("Error response: " + errorResponse);
			}
			throw e;
		} finally {
			conn.disconnect();
		}
	}
}
