package diary.controller.action;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import util.Action;
import util.InputStreamParsor;

public class CreateDiaryAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equals("POST")) {

            List<Part> parts = (List<Part>) request.getParts();

            for (Part part : parts) {
                String name = part.getName();
                String type = part.getContentType();
                System.out.println("name : " + name );
                System.out.println("type : " + type);

                InputStream in = part.getInputStream();

                if (name.equals("photo")) {
                    // 이미지 파일의 원본 데이터 읽기
                    byte[] imageContent = in.readAllBytes();

                    // 요청 준비
                    URL url = new URL("https://api.imgbb.com/1/upload");
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");

                    String boundary = UUID.randomUUID().toString();
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                    // API 키 가져오기
                    String key = "";
                    try {
                        Context init = new InitialContext();
                        key = (String) init.lookup("java:comp/env/Imgbb/Key");
                        if (key == null || key.isEmpty()) {
                            throw new RuntimeException("API key is not set or found");
                        }
                    } catch (NamingException e) {
                        e.printStackTrace();
                        throw new ServletException("Failed to retrieve API key", e);
                    }

                    // 연결을 출력 모드로 설정
                    conn.setDoOutput(true);
                    try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
                        // 키 부분 작성
                        out.writeBytes("--" + boundary + "\r\n");
                        out.writeBytes("Content-Disposition: form-data; name=\"key\"\r\n\r\n");
                        out.writeBytes(key + "\r\n");

                        // 이미지 부분 작성
                        out.writeBytes("--" + boundary + "\r\n");
                        out.writeBytes("Content-Disposition: form-data; name=\"image\"; filename=\"image\"\r\n");
                        out.writeBytes("Content-Type: " + type + "\r\n\r\n");
                        out.write(imageContent);
                        out.writeBytes("\r\n");

                        // 멀티파트 폼 데이터 끝
                        out.writeBytes("--" + boundary + "--\r\n");
                        out.flush();
                    }

                    // 응답 읽기
                    try (InputStream res = conn.getInputStream()) {
                        String responseData = InputStreamParsor.parseToString(res);
                        System.out.println("response data : " + responseData);

                        // JSON 형식의 응답을 JSON 객체로 변환
                        JSONObject jsonResponse = new JSONObject(responseData);

                        // JSON 응답에서 필요한 데이터 추출
                        String imageUrl = jsonResponse.getJSONObject("data").getString("url");
                        
                        System.out.println("Image URL: " + imageUrl);
                    } catch (IOException e) {
                        InputStream errorStream = conn.getErrorStream();
                        if (errorStream != null) {
                            String errorResponse = new BufferedReader(new InputStreamReader(errorStream))
                                .lines().collect(Collectors.joining("\n"));
                            System.err.println("Error response: " + errorResponse);
                        }
                        throw e; // 예외 처리 후 재발생
                    } finally {
                        conn.disconnect();
                    }
                } else {
                    String data = InputStreamParsor.parseToString(in);
                }

                in.close();
            }
        }
    }
}