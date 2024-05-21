package diaryComment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DiaryCommentDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private DiaryCommentDao() {

	}

	private static DiaryCommentDao instance = new DiaryCommentDao();

	public static DiaryCommentDao getInstance() {
		return instance;
	}
}
