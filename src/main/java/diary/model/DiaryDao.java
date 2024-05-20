package diary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
