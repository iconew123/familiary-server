package diary.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBManager;

public class DiaryDao {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private DiaryDao() {

    }

    private static DiaryDao instance = new DiaryDao();

    public static DiaryDao getInstance() {
        return instance;
    }

    public DiaryResponseDto findDiaryOfDate(Date date) {
        DiaryResponseDto readDiary = null;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT * FROM diary WHERE date=?;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setDate(1, date);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                int diarycode = rs.getInt(1);
                String babyCode = rs.getString(2);
                Date getDate = rs.getDate(3);
                String title = rs.getString(4);
                String content = rs.getString(5);
                String category = rs.getString(6);

                readDiary = new DiaryResponseDto(diarycode, babyCode, getDate, title, content, category);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return readDiary;
    }

    public boolean createDiary(DiaryRequestDto diary) {

        try {
            conn = DBManager.getConnection();
            String sql = "INSERT INTO diary(baby_code, date ,title, content, category) VALUE(?,?,?,?,?);";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, diary.getBabyCode());
            pstmt.setDate(2, diary.getDate());
            pstmt.setString(3, diary.getTitle());
            pstmt.setString(4, diary.getContent());
            pstmt.setString(5, diary.getCategory());

            int result = pstmt.executeUpdate();

            if (result == 1)
                return true;
            else
                return false;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return false;
    }

    public boolean updateDiary(DiaryRequestDto diary) {
        try {
            conn = DBManager.getConnection();
            String sql = "UPDATE diary SET title = ? , content = ? , category = ? WHERE code=?;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, diary.getTitle());
            pstmt.setString(2, diary.getContent());
            pstmt.setString(3, diary.getCategory());
            pstmt.setInt(4, diary.getCode());

            int result = pstmt.executeUpdate();

            if (result == 1)
                return true;
            else
                return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.close(conn, pstmt);
        }

    }

    public boolean deleteDiary(Date date) {

        try {
            conn = DBManager.getConnection();
            String sql = "DELETE FROM diary WHERE date=?;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setDate(1, date);
            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.close(conn, pstmt);
        }

    }

}
