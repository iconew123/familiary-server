package babyInfo.model;

import util.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BabyInfoDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BabyInfoDao() {

    }

    private static BabyInfoDao instance = new BabyInfoDao();

    public static BabyInfoDao getInstance() {
        return instance;
    }

    public BabyInfoResponseDto createBabyInfo(BabyInfoRequestDto babyInfoDto) {

        try {
            conn = DBManager.getConnection();

            String sql = "INSERT INTO babyinfo(baby_code, height, weight, spec_note) VALUES(?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, babyInfoDto.getBaby_code());
            pstmt.setInt(2, babyInfoDto.getHeight());
            pstmt.setInt(3, babyInfoDto.getWeight());
            pstmt.setString(4,babyInfoDto.getSpec_note());

            pstmt.execute();

            return new BabyInfoResponseDto(babyInfoDto.getBaby_code(), babyInfoDto.getHeight(), babyInfoDto.getWeight(), babyInfoDto.getSpec_note());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return null;
    }

    public BabyInfo findBabyInfoByCodeAndDate(String babyCode, String date) {

        BabyInfo info = null;

        try {
            conn = DBManager.getConnection();

            String sql = "SELECT * FROM babyinfo WHERE baby_code = ? AND date = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, babyCode);
            pstmt.setString(2, date);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                String spec_note = rs.getString("spec_note");
                Timestamp regDate = rs.getTimestamp("reg_date");
                Timestamp modDate = rs.getTimestamp("mod_date");

                info = new BabyInfo(babyCode, date, height, weight, spec_note, regDate, modDate);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.close(conn, pstmt);
        }

        return info;
    }

    public boolean updateBabyInfo(BabyInfoRequestDto info) {
        boolean success = false;

        try {
            conn = DBManager.getConnection();

            String sql = "UPDATE babyinfo SET height=?, weight=?, spec_note=? WHERE baby_code=? AND date=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, info.getHeight());
            pstmt.setInt(2, info.getWeight());
            pstmt.setString(3, info.getSpec_note());
            pstmt.setString(4, info.getBaby_code());
            pstmt.setString(5, info.getDate());

            int rowsAffected = pstmt.executeUpdate();

            success = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            DBManager.close(conn, pstmt);
        }

        return success;
    }

    public boolean deleteBabyInfo(String baby_code, String date){

        try {
            conn = DBManager.getConnection();

            String sql = "DELETE FROM babyinfo WHERE baby_code=? AND date=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, baby_code);
            pstmt.setString(2, date);

            pstmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.close(conn, pstmt);
        }

        return false;
    }

    public List<BabyInfo> findAllInfo(String babyCode) {
        List<BabyInfo> infoList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT * FROM babyinfo WHERE baby_code = ? ORDER BY date DESC;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, babyCode);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String code = rs.getString(1);
                String date = rs.getString(2);
                int height = rs.getInt(3);
                int weight = rs.getInt(4);
                String spec_note = rs.getString(5);
                Timestamp readDate = rs.getTimestamp(6);
                Timestamp updateDate = rs.getTimestamp(7);

                BabyInfo info = new BabyInfo(code, date, height, weight, spec_note, readDate, updateDate);
                infoList.add(info);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return infoList;
    }
}

