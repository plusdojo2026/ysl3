package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.WorkDAO;
import model.WorkDTO;

public class WorkService {
	// データベース接続を保持する変数
	private Connection conn = null;

	// データベース接続用 ※「test2」は、データベース名
	private static final String url ="jdbc:mysql://localhost:3306/test2?useSSL=false&serverTimezone=Asia/Tokyo&characterEncoding=UTF-8";
	private static final String dbUser = "root";
	private static final String dbPassword = "password";

	
	// データベースとの接続を行うメソッド
	private void access() {
		   try {
		       // MySQLドライバーを読み込む
		       Class.forName(
		               "com.mysql.cj.jdbc.Driver"
		       );
		       // DBへ接続
		       conn = DriverManager.getConnection(url, dbUser, dbPassword);
		   } catch (ClassNotFoundException e) {
		       throw new RuntimeException("MySQLドライバーが見つかりません", e);
		   } catch (SQLException e) {
		       throw new RuntimeException("データベースへの接続に失敗しました", e);
		   }
		}

	// データベースとの接続を切断するメソッド
	private void close() {
		   if (conn == null) {
		       return;
		   }
		   try {
		       conn.close();
		   } catch (SQLException e) {
		       throw new RuntimeException("データベースの切断に失敗しました", e);
		   } finally {
		       conn = null;
		   }
		}

	//工数一覧を表示するメソッド
	public ArrayList<WorkDTO> workSelectAll(int userId) {
		ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			WorkDAO dao = new WorkDAO(conn);

			// 工数一覧取得処理を実施。DAOのメソッドを実行
			workList = dao.workSelectAll(userId);

		} finally {

			// DB接続解除
			close();
		}

		return workList;
	}

	//工数登録するメソッド
	public int workRegist(int userId, int projectId, int taskId, String workDate, String explainText, String work) {
		int ans = 0;
		// DB接続
		access();

		try {

			// DAOを実体化
			WorkDAO dao = new WorkDAO(conn);

			// 工数登録処理を実施。DAOのメソッドを実行
			ans = dao.workRegist(userId, projectId, taskId, workDate, explainText, work);
		} finally {

			// DB接続解除
			close();
		}
		return ans;
	}

	//工数を削除するメソッド
	public int workDelete(int workId) {
		int ans = 0;
		// DB接続
		access();

		try {

			// DAOを実体化
			WorkDAO dao = new WorkDAO(conn);

			// 工数削除処理を実施。DAOのメソッドを実行
			ans = dao.workDelete(workId);
		} finally {

			// DB接続解除
			close();
		}
		return ans;
	}

	//工数を計算するメソッド
	public int workTally(int workId, int taskId) {
		int ans = 0;
		// DB接続
		access();

		try {

			// DAOを実体化
			WorkDAO dao = new WorkDAO(conn);

			// 工数計算処理を実施。DAOのメソッドを実行
			ans = dao.workTally(workId, taskId);
		} finally {

			// DB接続解除
			close();
		}
		return ans;
	}

	//ホームに工数ログを表示するメソッド
	public ArrayList<WorkDTO> homeWorkList(int userId) {
		ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();
		// DB接続
		access();

		try {
			// DAOを実体化
			WorkDAO dao = new WorkDAO(conn);

			// ホームに工数ログを表示。DAOのメソッドを実行
			workList = dao.workSelectAll(userId);

		} finally {

			// DB接続解除
			close();
		}

		return workList;
	}

}
