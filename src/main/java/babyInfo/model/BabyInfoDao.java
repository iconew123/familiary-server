package babyInfo.model;

import baby.model.BabyDao;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BabyInfoDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BabyInfoDao() {

    }

    private static BabyDao instance = new BabyDao();

    // 3. 단일 인스턴스에 대한 getter
    public static BabyDao getInstance() {
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
}

