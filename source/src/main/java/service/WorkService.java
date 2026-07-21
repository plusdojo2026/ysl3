package service;

import java.sql.Connection;
import java.util.ArrayList;

import dao.WorkDAO;
import model.WorkDTO;

public class WorkService {
	// データベース接続を保持する変数
		private Connection conn = null;

		// データベースとの接続を行うメソッド
		private void access() {
			// のちに処理記述
		}

		// データベースとの接続を切断するメソッド
		private void close() {
			// のちに処理記述	
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
		public int workRegist(int userId, int projectId, int taskId, String workDate , String explanText, float work) {
			 int ans = 0;
			// DB接続
				access();
				
			try {
					
				// DAOを実体化
				WorkDAO dao = new WorkDAO(conn);
					
				// 工数登録処理を実施。DAOのメソッドを実行
					ans = dao.workRegist(userId, projectId, taskId, workDate , explanText, work);
			} finally {
					
				// DB接続解除
				close();
			}		
			return ans;
		}
		
		//工数を削除するメソッド
		public int workDelete(int workId, int taskId) {
			int ans = 0;
			// DB接続
				access();
			
			try {
				
				// DAOを実体化
				WorkDAO dao = new WorkDAO(conn);
				
				// 工数削除処理を実施。DAOのメソッドを実行
				ans = dao.workDelete(workId, taskId);
			} finally {
				
				// DB接続解除
				close();
			}		
			return ans;
		}	
			 
		//工数を計算するメソッド
		public int workTally(int workId) {
			int ans = 0;
				// DB接続
				access();
						
			try {
							
				// DAOを実体化
				WorkDAO dao = new WorkDAO(conn);
							
				// 工数計算処理を実施。DAOのメソッドを実行
							ans = dao.workTally(workId);
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


