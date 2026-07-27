package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TaskDAO;
import model.TaskDTO;

public class TaskService {

	// データベース接続を保持する変数
	private Connection conn = null;

	// データベース接続用 ※「romance_magic」は、データベース名
	private static final String url ="jdbc:mysql://localhost:3306/romance_magic?useSSL=false&serverTimezone=Asia/Tokyo&characterEncoding=UTF-8";
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

	// タスク一覧を取得するメソッド
	public ArrayList<TaskDTO> taskSelectAll(int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// タスク一覧取得処理を実施。DAOのメソッドを実行
			taskList = dao.taskSelectAll(userId);

		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return taskList;
	}

	// タスク検索をするメソッド
	public ArrayList<TaskDTO> taskSearch(int taskId, int projectId, int status, int userId, String name) throws SQLException {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// タスク検索を実施。DAOのメソッドを実行
			taskList = dao.taskSearch(taskId, projectId, name, status, userId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return taskList;
	}

	// タスク登録メソッド
	public int taskRegist(int taskId, int userId,  String name, int status, int priority,
			 String limitDate,String explainText,float estimatedWorks,int projectId, String startDate, int progress ) throws SQLException {
		int ans = 0;

		// DB接続
		access();

		try {

			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.taskRegist(taskId, userId, name, status, priority,
					limitDate, explainText,estimatedWorks, projectId,  startDate, progress);
		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return ans;
	}

	// タスク編集メソッド
	public int taskUpdate(int taskId, int userId,  String name, int status, int priority,
			String explainText, String limitDate,float estimatedWorks, int projectId,String startDate, int progress) throws SQLException {
		int ans = 0;

		// DB接続
		access();

		try {

			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.taskUpdate(taskId,userId,name, status, priority,
					 explainText,limitDate,estimatedWorks, projectId, startDate,progress);
		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return ans;
	}

	// ユーザー削除メソッド
	public int taskDelete(int taskId) throws SQLException {
		int ans = 0;

		// DB接続
		access();

		try {

			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.taskDelete(taskId);
		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return ans;
	}

	// タスクステータス変更メソッド
	public int statusChange(int taskId) {
		int ans = 0;

		// DB接続
		access();

		try {

			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.statusChange(taskId);
		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return ans;
	}

	//タスク詳細を表示するメソッド
	public TaskDTO taskDetail(int taskId) throws SQLException {
		TaskDTO dto = null;

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(this.conn);

			// ログイン処理を実施。DAOのメソッドを実行
			dto = dao.taskDetail(taskId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return dto;
	}

	//  ホーム画面のタスク一覧を表示するメソッド
	public ArrayList<TaskDTO> homeTaskList(int userId) throws SQLException {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);
			

			// タスク一覧取得処理を実施。DAOのメソッドを実行
			taskList = dao.homeTaskList(userId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return taskList;
	}

	//  案件一覧画面のタスク項目を表示するメソッド
	public ArrayList<TaskDTO> projectTaskList(int taskId, int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// タスク一覧取得処理を実施。DAOのメソッドを実行
			taskList = dao.projectList(taskId, userId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return taskList;
	}

	//  案件詳細画面のタスク項目を削除するメソッド
	public ArrayList<TaskDTO> projectDetailDelete(int taskId,int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// タスク一覧取得処理を実施。DAOのメソッドを実行
			taskList = dao.projectDetailDelete(taskId, userId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return taskList;
	}

	//   工数を登録するメソッド
	public int workRegist(int taskId,int userId) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}
	
	// 新規登録初期表示用メソッド
	public TaskDTO taskToEdit(int taskId) throws SQLException {
		TaskDTO dto = null;

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(this.conn);

			// ログイン処理を実施。DAOのメソッドを実行
			dto = dao.taskToEdit(taskId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return dto;
	}
}
