package dao;

import java.sql.Connection;

import model.ProjectDTO;

	public class ProjectDAO {
		Connection conn = null;
		public ProjectDAO(Connection conn) {
			this.conn = conn;
	}
		
		
	//projectSelectAllメソッド
		public ProjectDTO projectSelectAll(int userId ) {
			ProjectDTO dto = null;
			return dto;
		}

		//projectSearchメソッド
	public ProjectDTO projectSearch(int projectStatus , int projectPriority) {
		ProjectDTO dto = null;
		return dto;
	}
	//projectRegistメソッド
	public int projectRegist(String projectCode, String projectName , int pmId , int projectStatus, int projectPriority, String projectStartDate , String projectEndDate , String projectExplain) {
		int ans = 0 ;
		return ans;
	}
	//projectUpdateメソッド
	public int projectUpdate(String projectCode , String projectName , int pmId , int projectStatus , int projectPriority , String projectStartDate , String projectEndDate , String projectExplain) {
		int ans = 0 ;
		return ans ; 
	}
	//projectStatusChangeメソッド
	public int projectStatusChange(int projectStatus) {
		int ans = 0;
		return ans;
	}
	//projectdetailメソッド
	public ProjectDTO projectdetail(int projectId) {
		ProjectDTO dto = null;
		return dto;
	}
}
