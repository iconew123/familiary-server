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
		}

		return readDiary;
	}

	public boolean createDiary(DiaryRequestDto diary) {

		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO diary(baby_code, title, content, category) VALUE(?,?,?,?);";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, diary.getBabyCode());
			pstmt.setString(2, diary.getTitle());
			pstmt.setString(3, diary.getContent());
			pstmt.setString(4, diary.getCategory());

			int result = pstmt.executeUpdate();
			System.out.println(result);

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

}
