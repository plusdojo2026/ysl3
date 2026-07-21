package service;
import java.sql.Connection;

import dao.ProjectDAO;
import model.ProjectDTO;

public class ProjectService {

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
		
		public ProjectDTO projectSelectAll(int userId ) {
			ProjectDTO dto = null;
			// DB接続
			access();

			try {// DAOを実体化
				ProjectDAO dao = new ProjectDAO(this.conn);
				
				// ログイン処理を実施。DAOのメソッドを実行
				dto = dao.projectSelectAll(userId);
				
			} finally {
				
				// DB接続解除
				close();
			}
			
			// 戻り値
			return dto;
		}
		
		//projectSearchメソッド
		public ProjectDTO projectSearch(int projectStatus , int projectPriority) {
			ProjectDTO dto = null;
			// DB接続
			access();
			try {// DAOを実体化
				ProjectDAO dao = new ProjectDAO(this.conn);
				
				// ログイン処理を実施。DAOのメソッドを実行
				dto = dao.projectSearch( projectStatus , projectPriority);
				
				}finally {
					close();
				}
			return dto;
		}
		
		//projectRegistメソッド
		public int projectRegist(String projectCode , String projectName , int pmId , int projectStatus , int projectPriority , String projectStartDate , String projectEndDate , String projectExplain) {
			int ans = 0; 
			
			// DB接続
			access();
			
			try {

				// DAOを実体化
				ProjectDAO dao = new ProjectDAO(conn);
				// ユーザー登録処理を実施。DAOのメソッドを実行
				ans = dao.projectRegist(projectCode, projectName, pmId, projectStatus, projectPriority , projectStartDate ,  projectEndDate , projectExplain);
			} finally {
				
				// DB接続解除
				close();
			}
			
			
			// 戻り値
			return ans;
		}
		//projectUpdateメソッド
		public int projectUpdate(String projectCode , String projectName , int pmId , int projectStatus , int projectPriority , String projectStartDate , String projectEndDate , String projectExplain) {
			int ans = 0 ;
			
			// DB接続
			access();
			
			
			 
			try {

				// DAOを実体化
				ProjectDAO dao = new ProjectDAO(conn);
				// ユーザー登録処理を実施。DAOのメソッドを実行
				ans = dao.projectUpdate(projectCode, projectName, pmId, projectStatus, projectPriority , projectStartDate ,  projectEndDate , projectExplain);
			} finally {
				
				// DB接続解除
				close();
			}
			
			
			// 戻り値
			return ans;
		}
			
		//projectStatusChangeメソッド
		public int projectStatusChange(int projectStatus) {
			int ans = 0;
			
			//DB接続
			access();
			try {
				ProjectDAO dao = new ProjectDAO(conn);
				ans = dao. projectStatusChange(projectStatus);
			}finally {
				close();
				}
			
			return ans;
		}
		
		//projectdetailメソッド
		public ProjectDTO projectdetail(int projectId) {
			ProjectDTO dto = null;
			// DB接続
						access();
						try {// DAOを実体化
							ProjectDAO dao = new ProjectDAO(this.conn);
							
							// ログイン処理を実施。DAOのメソッドを実行
							dto = dao.projectdetail(projectId);
							
							}finally {
								close();
							}
			return dto;
		}
		
}
