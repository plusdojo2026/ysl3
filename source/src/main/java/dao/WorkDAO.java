package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.WorkDTO;

public class WorkDAO {
	Connection conn = null;

	//コンストラクタ
	public WorkDAO(Connection conn) {
		this.conn = conn;
	}

	//工数一覧を表示するメソッド
	public ArrayList<WorkDTO> workSelectAll(int userId) {
		ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();

		// SELECT文を準備する
		String sql = "SELECT * FROM works WHERE user_id = ?";

		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, userId);

			try (
					ResultSet rs = pStmt.executeQuery()) {
		// 移し替え
				while (rs.next()) {
					WorkDTO dto = new WorkDTO();
					dto.setId(rs.getInt("work_id"));
					dto.setTaskId(rs.getInt("task_id"));
					dto.setWork(rs.getFloat("work"));
					dto.setExplainText(rs.getString("work_explanation"));
					dto.setWorkDate(rs.getString("work_date"));
					workList.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return workList;
	}

	//工数登録するメソッド
	public int workRegist(int userId, int taskId, String workDate, String explainText, float work) {
		int ans = 0;
		//SQLを準備
		String sql = "INSERT INTO works VALUES (0,?,?,?,?,?)";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, taskId);
			pStmt.setFloat(2, work);
			pStmt.setString(3, explainText);
			pStmt.setInt(4, userId);
			pStmt.setString(5, workDate);

			ans = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			}
			return ans;
		}

	//工数を削除するメソッド
	public int workDelete(int workId) {
		int ans = 0;

		String sql = "DELETE FROM works WHERE work_id = ?";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			// ?に値をセット   
			pStmt.setInt(1, workId);

			// DELETE文を実行し、削除された行数を取得する
			ans = pStmt.executeUpdate();

		} catch (SQLException e) {

		}

		return ans;

	}

	//工数を計算するメソッド
	public int workTally(int workId, int taskId) {
		int ans = 0;
		
		String sql = "SELECT ";
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			
			pStmt.setInt(1, workId);
			pStmt.setInt(2, taskId);
			
			try (ResultSet rs = pStmt.executeQuery()) {
	         if (rs.next()) {
	        	 ans = rs.getInt(1);
	            }
	        }
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ans;
	}

	//ホームに工数ログを表示するメソッド
	public ArrayList<WorkDTO> homeWorkList(int userId) {
		ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();
		// SELECT文を準備する
		String sql = "SELECT * FROM works WHERE user_id = ?";

		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		// まとめる
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, userId);

			try (ResultSet rs = pStmt.executeQuery()) {
				// 移し替え
				while (rs.next()) {
					WorkDTO dto = new WorkDTO();
					dto.setId(rs.getInt("work_id"));
					dto.setTaskId(rs.getInt("task_id"));
					dto.setWork(rs.getFloat("work"));
					dto.setExplainText(rs.getString("work_explanation"));
					dto.setWorkDate(rs.getString("work_date"));
					workList.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return workList;
	}

	
	//工数登録画面で案件名とタスク名を表示する	
	public WorkDTO workToRegist(int taskId) {
		WorkDTO ans = null;
		
		String sql = "SELECT * FROM works WHERE task_id = ?";
		
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, taskId);

			try (ResultSet rs = pStmt.executeQuery()) {
				// 移し替え
				while (rs.next()) {
					WorkDTO dto = new WorkDTO();
					dto.setId(rs.getInt("work_id"));
					dto.setTaskId(rs.getInt("task_id"));
	
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return ans;
	}

//サマリーカード
//稼働メンバー
public int getMemberCount(String month) {
	
	int MemberCount = 0;
	
	//SQL文
	 String sql =
	            "SELECT COUNT(DISTINCT user_id) AS memberCount "
	          + "FROM works "
	          + "WHERE DATE_FORMAT(work_date,'%Y-%m') = ?";
	 try (PreparedStatement ps = conn.prepareStatement(sql);) 
		{
		
     // 対象年月をセット
	 ps.setString(1, month);
	 
     //SQL文を実行
     ResultSet rs = ps.executeQuery();
     
     // 取得結果が存在する場合
     if (rs.next()) {

         // 稼働メンバーを取得
         MemberCount = rs.getInt("MemberCount");
     }

		} catch (SQLException e) {

	     // SQL実行時のエラー表示
	     e.printStackTrace();
	 }
		
	 // 稼働メンバーを返す
	 return MemberCount;
	}

}