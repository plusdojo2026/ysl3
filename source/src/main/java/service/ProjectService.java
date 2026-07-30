package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProjectDAO;
import dao.TaskDAO;
import dao.WorkDAO;
import model.ProjectDTO;

public class ProjectService {

	// データベース接続を保持する変数
	private Connection conn = null;

	// データベース接続用 ※「romance_magic」は、データベース名
	private static final String url = "jdbc:mysql://localhost:3306/ysl3?useSSL=false&serverTimezone=Asia/Tokyo&characterEncoding=UTF-8";
	private static final String dbUser = "ysl3";
	private static final String dbPassword = "Kz3Dvi22zPhkEzDg";
	
	// ローカル接続用 ※「romance_magic」は、データベース名
//	private static final String url = "jdbc:mysql://localhost:3306/romance_magic?useSSL=false&serverTimezone=Asia/Tokyo&characterEncoding=UTF-8";
//	private static final String dbUser = "root";
//	private static final String dbPassword = "password";

	// データベースとの接続を行うメソッド
	private void access() {
		try {
			// MySQLドライバーを読み込む
			Class.forName(
					"com.mysql.cj.jdbc.Driver");
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

	//projectSelectAllメソッド
	public ArrayList<ProjectDTO> projectSelectAll() {
		ArrayList<ProjectDTO> projectList = null;

		// DB接続
		access();

		try {// DAOを実体化
			ProjectDAO dao = new ProjectDAO(this.conn);

			projectList = dao.projectSelectAll();

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return projectList;
	}

	//projectSearchメソッド
	public ArrayList<ProjectDTO> projectSearch(int projectStatus, int projectPriority, String projectName)
			throws SQLException {
		ArrayList<ProjectDTO> projectList = new ArrayList<ProjectDTO>();
		// DB接続
		access();
		try {// DAOを実体化
			ProjectDAO dao = new ProjectDAO(conn);

			// ログイン処理を実施。DAOのメソッドを実行
			projectList = dao.projectSearch(projectStatus, projectPriority, projectName);

		} finally {
			close();
		}
		return projectList;
	}

	//projectRegistメソッド
	public int projectRegist(String projectCode, String projectName, String customer, int pmId, int projectStatus,
			int projectPriority, String projectStartDate, String projectEndDate, float projectEstimatedWorks,
			String projectExplain, String projectLimit)
			throws SQLException {
		int ans = 0;

		// DB接続
		access();

		try {

			// DAOを実体化
			ProjectDAO dao = new ProjectDAO(conn);
			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.projectRegist(projectCode, projectName, customer, pmId, projectStatus, projectPriority,
					projectStartDate, projectEndDate, projectEstimatedWorks, projectExplain, projectLimit);
		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return ans;
	}

	//projectUpdateメソッド
	public int projectUpdate(String projectCode, String projectName, String customer, int pmId, int projectStatus,
			int projectPriority, String projectStartDate, String projectEndDate, Float projectEstimatedWorks,
			String projectExplain, String projectLimit, int projectId)
			throws SQLException {
		int ans = 0;

		// DB接続
		access();

		try {

			// DAOを実体化
			ProjectDAO dao = new ProjectDAO(conn);
			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.projectUpdate(projectCode, projectName, customer, pmId, projectStatus, projectPriority,
					projectStartDate, projectEndDate, projectEstimatedWorks, projectExplain, projectLimit, projectId);
		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return ans;
	}

	//projectStatusChangeメソッド
	public int projectStatusChange(int projectId, int projectStatus) throws SQLException {
		int ans = 0;

		//DB接続
		access();
		try {
			ProjectDAO dao = new ProjectDAO(conn);
			ans = dao.projectStatusChange(projectId, projectStatus);
		} finally {
			close();
		}

		return ans;
	}

	//projectDetailメソッド
	public ProjectDTO projectDetail(int projectId) throws SQLException {
		ProjectDTO dto = null;
		// DB接続
		access();
		try {// DAOを実体化
			ProjectDAO dao = new ProjectDAO(this.conn);

			// ログイン処理を実施。DAOのメソッドを実行
			dto = dao.projectDetail(projectId);

		} finally {
			close();
		}
		return dto;
	}

	//追加メソッド
	public ProjectDTO projectToEdit(int projectId)
			throws SQLException {

		ProjectDTO dto = null;

		access();
		try {
			ProjectDAO dao = new ProjectDAO(conn);

			dto = dao.projectToEdit(projectId);
		} finally {
			close();
		}
		return dto;

	}

	// サマリーカード表示用データ取得
	//	 稼働メンバー数
	//	 総工数
	//	 予定工数
	//	 残工数を取得する	

	public List<ProjectDTO> getSummaryCard(String month) {

		// 【修正】DB接続を開始する（これを忘れていたため null になっていました）
		access();

		// 念のため、戻り値用のリストをtryの外で定義
		List<ProjectDTO> list = new ArrayList<>();

		try {
			// 各DAOを実体化（access() の後なので conn に接続情報が入っています）
			ProjectDAO projectdao = new ProjectDAO(conn);
			TaskDAO taskdao = new TaskDAO(conn);
			WorkDAO workdao = new WorkDAO(conn);

			// 【修正】総工数を取得（引数に month を渡すように変更）
			float TotalWork = projectdao.getTotalWork(month);

			// 対象月の予定工数を取得
			float PlannedWork = taskdao.getPlannedWork(month);

			// 対象月の稼働メンバーを取得
			int MemberCount = workdao.getMemberCount(month);

			// 残工数を計算
			// 残工数 = 総工数 - 予定工数
			float RemainWork = TotalWork - PlannedWork;

			// DTOへ取得結果を設定
			ProjectDTO dto = new ProjectDTO();

			// 稼働メンバー数
			dto.setMemberCount(MemberCount);

			// 総工数
			dto.setTotalWork(TotalWork);

			// 予定工数
			dto.setEstimatedWork(PlannedWork);

			// 残工数
			dto.setRemainWork(RemainWork);

			// Action側がListで受け取るためList化
			list.add(dto);

		} finally {
			// 【修正】使い終わったら必ず接続を解除する
			close();
		}

		// サマリーカードを返却
		return list;
	}

	//	案件別実績
	public List<ProjectDTO> getProjectSummary(String month) {
		List<ProjectDTO> list = null;

		// 【修正】DB接続
		access();

		try {
			// DAO実体化	
			ProjectDAO dao = new ProjectDAO(conn);
			list = dao.getProjectSummary(month);

		} finally {
			// 【修正】DB接続解除
			close();
		}

		return list;
	}

	//	ユーザー別実績
	public List<ProjectDTO> getUserSummary(String month) {
		List<ProjectDTO> list = null;

		// 【修正】DB接続
		access();

		try {
			// DAO実体化	
			ProjectDAO dao = new ProjectDAO(conn);
			list = dao.getUserSummary(month);

		} finally {
			// 【修正】DB接続解除
			close();
		}

		return list;
	}

}
