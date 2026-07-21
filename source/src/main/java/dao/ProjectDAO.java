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

		//projectSearch(projectStatus:int, projectPriority:int) : ArrayList<ProjectDTO>
	public ProjectDTO projectSearch(int projectStatus , int projectPriority) {
		ProjectDTO dto = null;
		return dto;
	}
	
	public int projectRegist(String projectCode, String projectName , int pmId , int projectStatus, int projectPriority, String projectStartDate , String projectEndDate , String projectExplan) {
		int ans = 0 ;
		return ans;
	}
	
	public int projectUpdate(String projectCode , String projectName , int pmId , int projectStatus , int projectPriority , String projectStartDate , String projectEndDate , String projectExplan) {
		int ans = 0 ;
		return ans ; 
	}
	
	public int projectStatusChange(int projectStatus) {
		int ans = 0;
		return ans;
	}
	
	public ProjectDTO projectgdetail(int projectId) {
		ProjectDTO dto = null;
		return dto;
	}
}
