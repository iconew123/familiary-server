package image.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class ImageDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private ImageDao() {}
	
	private static ImageDao instance = new ImageDao();
	
	public static ImageDao getInstance() {
		return instance;
	}
	
	public List<ImageResponseDto> findImagesAllByTypeAndContentType(String type, String contentType) {
		List<ImageResponseDto> list = new ArrayList<ImageResponseDto>();
		
		String sql = "SELECT code, url, type, reg_date FROM image WHERE type=? AND content_type=? AND status=1";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, type);
			pstmt.setString(2, contentType);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String code = rs.getString(1);
				String url = rs.getString(2);
				Timestamp regDate = rs.getTimestamp(4);
				
				ImageResponseDto imageDto = new ImageResponseDto(code, url, type, regDate);
				
				list.add(imageDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public boolean createImage(ImageRequestDto uploadImage) {
		
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO image  (url, id, type, code) VALUES(?,?,?,?);";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, uploadImage.getUrl());
			pstmt.setString(2, uploadImage.getId());
			pstmt.setString(3, uploadImage.getType());
			pstmt.setInt(4, uploadImage.getCode());
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
