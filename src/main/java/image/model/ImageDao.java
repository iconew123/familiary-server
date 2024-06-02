package image.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class ImageDao {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private ImageDao() {
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
        } finally {
            DBManager.close(conn, pstmt);
        }

        return false;
    }

    public boolean updateImage(ImageRequestDto uploadImage) {

        try {
            conn = DBManager.getConnection();
            String sql = "UPDATE image SET url=? , id =? WHERE code=? AND type=?;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, uploadImage.getUrl());
            pstmt.setString(2, uploadImage.getId());
            pstmt.setString(3, uploadImage.getCode());
            pstmt.setString(4, uploadImage.getType());

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

    public boolean deleteImage(int num) {

        try {
            conn = DBManager.getConnection();
            String sql = "UPDATE backup SET status=0 WHERE num=?;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, num);

            int result = pstmt.executeUpdate();
            if (result == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ImageResponseDto findImageByCodeAndType(String code, String type) {

        ImageResponseDto image = null;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT i.* , b.status FROM image i JOIN backup b ON i.num = b.num WHERE i.code=? AND type=?;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, code);
            pstmt.setString(2, type);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                String url = rs.getString(2);
                String id = rs.getString(3);
                String getType = rs.getString(4);
                Timestamp regDate = rs.getTimestamp(5);
                Timestamp moddate = rs.getTimestamp(6);
                String getCode = rs.getString(7);
                boolean status = rs.getBoolean(8);

                image = new ImageResponseDto(num, url, id, getType, getCode, status, regDate, moddate);
            }

            if (image == null) {
                System.out.println("이미지 READ 실패 또는 READ할 이미지가 없음");
            } else {
                System.out.println("이미지 READ 성공");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return image;
    }

    public List<ImageResponseDto> findAllImage(String babycode, String type) {
        List<ImageResponseDto> list = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT i.*, b.status FROM image i JOIN diary d ON d.code=i.code JOIN backup b ON b.num=i.num WHERE d.baby_code=? AND i.type=? AND b.status=1;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, babycode);
            pstmt.setString(2, type);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                String url = rs.getString(2);
                String id = rs.getString(3);
                String getType = rs.getString(4);
                Timestamp regDate = rs.getTimestamp(5);
                Timestamp modDate = rs.getTimestamp(6);
                String code = rs.getString(7);
                boolean status = rs.getBoolean(8);

                ImageResponseDto image = new ImageResponseDto(num, url, id, getType, code, status, regDate, modDate);
                list.add(image);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

}
