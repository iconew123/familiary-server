package communityComment.model;

public class CommunityCommentDao {

	private CommunityCommentDao() {

	}

	private static CommunityCommentDao instance = new CommunityCommentDao();

	public static CommunityCommentDao getInstance() {
		return instance;
	}

}
