package dao;

import java.sql.Connection;
import java.util.ArrayList;

import model.ProjectDTO;

	public class ProjectDAO {
		Connection conn = null;
		public ProjectDAO(Connection conn) {
			this.conn = conn;
	}
		
		
	//projectSelectAllメソッド
		public ArrayList<ProjectDTO> projectSelectAll() {
			ArrayList<ProjectDTO> projectList = new ArrayList<>();
			return projectList;
		}

		//projectSearchメソッド
	public ArrayList<ProjectDTO> projectSearch(int projectStatus , int projectPriority) {
		ArrayList<ProjectDTO> projectList  = new ArrayList<>();
		return projectList;
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
	public int projectStatusChange(int projectId,int projectStates) {
		int ans = 0;
		return ans;
	}
	//projectdetailメソッド
	public ProjectDTO projectdetail(int projectId) {
		ProjectDTO dto = null;
		return dto;
	}
}
