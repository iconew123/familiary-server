package diary.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

//    public DiaryResponseDto findDiaryOfDate(Date date) {
//        DiaryResponseDto readDiary = null;
//
//        try {
//            conn = DBManager.getConnection();
//            String sql = "SELECT * FROM diary WHERE date=?;";
//            pstmt = conn.prepareStatement(sql);
//
//            pstmt.setDate(1, date);
//
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                int diarycode = rs.getInt(1);
//                String babyCode = rs.getString(2);
//                Date getDate = rs.getDate(3);
//                String title = rs.getString(4);
//                String content = rs.getString(5);
//                String category = rs.getString(6);
//                Timestamp readDate = rs.getTimestamp(7);
//                Timestamp updateDate = rs.getTimestamp(8);
//
//                readDiary = new DiaryResponseDto(diarycode, babyCode, getDate, title, content, category, readDate, updateDate);
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            DBManager.close(conn, pstmt, rs);
//        }
//
//        return readDiary;
//    }

    public DiaryResponseDto findDiaryOfDateAndCode(Date date, String babycode) {
        DiaryResponseDto readDiary = null;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT * FROM diary WHERE date=? AND baby_code=?;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setDate(1, date);
            pstmt.setString(2, babycode);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                int diarycode = rs.getInt(1);
                String babyCode = rs.getString(2);
                Date getDate = rs.getDate(3);
                String title = rs.getString(4);
                String content = rs.getString(5);
                String category = rs.getString(6);
                Timestamp readDate = rs.getTimestamp(7);
                Timestamp updateDate = rs.getTimestamp(8);

                readDiary = new DiaryResponseDto(diarycode, babyCode, getDate, title, content, category, readDate, updateDate);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return readDiary;
    }

    //2번 방식
    public Date getDate(int diarycode) {
        Date date = null;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT date FROM diary WHERE code=?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, diarycode);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                date = rs.getDate(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return date;
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

    public List<DiaryResponseDto> findAllDiary(String babycode) {
        List<DiaryResponseDto> diaryList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT * FROM diary WHERE baby_code = ? ORDER BY date DESC;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, babycode);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                int code = rs.getInt(1);
                String babyCode = rs.getString(2);
                Date date = rs.getDate(3);
                String title = rs.getString(4);
                String content = rs.getString(5);
                String category = rs.getString(6);
                Timestamp readDate = rs.getTimestamp(7);
                Timestamp updateDate = rs.getTimestamp(8);

                DiaryResponseDto diary = new DiaryResponseDto(code, babyCode, date, title, content, category, readDate, updateDate);
                diaryList.add(diary);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return diaryList;
    }
}
