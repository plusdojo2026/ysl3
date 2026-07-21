package dao;

import java.sql.Connection;
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
	//処理はのちに記述。今は返すだけ	
		return workList;
	}
	
	//工数登録するメソッド
	public int workRegist(int userId, int projectId, int taskId, String workDate , String explainText, float work) {
		 int ans = 0;
	//処理はのちに記述。今は返すだけ
		 return ans;
	}

	//工数を削除するメソッド
	public int workDelete(int workId, int taskId) {
		int ans = 0;
	//処理はのちに記述。今は返すだけ
		 return ans;
	}
	
	
	//工数を計算するメソッド
	public int workTally(int workId) {
		int ans = 0;
	//処理はのちに記述。今は返すだけ
		 return ans;
	}
	
	//ホームに工数ログを表示するメソッド
	public ArrayList<WorkDTO> homeWorkList(int userId) {
		ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();
	//処理はのちに記述。今は返すだけ	
		return workList;
	}
	
}