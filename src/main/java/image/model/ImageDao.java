package image.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class ImageDao {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ImageDao() {
    }

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

            while (rs.next()) {
                String code = rs.getString(1);
                String url = rs.getString(2);
                Timestamp regDate = rs.getTimestamp(4);

//                ImageResponseDto imageDto = new ImageResponseDto(code, url, type, regDate);

//                list.add(imageDto);
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
            pstmt.setString(4, uploadImage.getCode());

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateImage(ImageRequestDto uploadImage) {

        try {
            conn = DBManager.getConnection();
            String sql = "UPDATE image SET url=? , id =? WHERE code=?;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, uploadImage.getUrl());
            pstmt.setString(2, uploadImage.getId());
            pstmt.setString(3, uploadImage.getCode());

            int result = pstmt.executeUpdate();
            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return false;
    }

    public ImageResponseDto findImageByDateAndBabyCode(Date date , String babyCode){
        ImageResponseDto image = null;

        try {
            conn = DBManager.getConnection();
            String sql = "";
            pstmt = conn.prepareStatement(sql);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return image;
    }

    public ImageResponseDto findImageByCode(String code) {

        ImageResponseDto image = null;

        try {
            conn = DBManager.getConnection();

            String sql = "SELECT * FROM image WHERE code = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                String url = rs.getString("url");
                String type = rs.getString("type");
                Timestamp regDate = rs.getTimestamp("reg_date");

                image = new ImageResponseDto(code, url, type, regDate);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return image;
    }
}
