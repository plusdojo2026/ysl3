package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	//projectRegistメソッド 完成
	public int projectRegist(String projectCode, String projectName ,String customer , int pmId , int projectStatus, int projectPriority, String projectStartDate , String projectEndDate , String projectExplain)
			throws SQLException{
		// SELECT文を準備する
		String sql = "insert into projects(project_code,project_name,customer,pm_id,project_status,project_priority,project_dtart_date,project_end_date,project_explain)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
		// まとめる
		int ans = 0 ;
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,  projectCode);
		pStmt.setString(2, projectName);
		pStmt.setString(3, customer);
		pStmt.setInt(4, pmId);
		pStmt.setInt(5, projectStatus);
		pStmt.setInt(6,  projectPriority);
		pStmt.setString(7, projectStartDate);
		pStmt.setString(8, projectEndDate);
		pStmt.setString(9, projectExplain);
		
		try(
			PreparedStatement ps =conn.prepareStatement(sql);){
			ps.setString(1,projectCode);
			ps.setString(2,projectName);
			ps.setString(3,customer);
			ps.setInt(4,pmId);
			ps.setInt(5, projectStatus);
			ps.setInt(6,projectPriority );
			ps.setString(7, projectStartDate);
			ps.setString(8, projectEndDate);
			ps.setString(9, projectExplain);
			
			ans=ps.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();		
			}
		return ans;
	}
	//projectUpdateメソッド
	public int projectUpdate(String projectCode , String projectName ,String customer, int pmId , int projectStatus , int projectPriority , String projectStartDate , String projectEndDate , String projectExplain)throws SQLException {
		// SELECT文を準備する
		String sql = "update projects SET project_code=?,project_name=?,customer=?,pm_Id=?,project_status=?,project_priority=?,project_startDate,project_end_date,project_explain where project_id=?_";
		int ans = 0 ;
		
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, projectCode);
		pStmt.setString(2, projectName);
		pStmt.setString(3, customer);
		pStmt.setInt(4, pmId);
		pStmt.setInt(5, projectStatus);
		pStmt.setInt(6,  projectPriority);
		pStmt.setString(7, projectStartDate);
		pStmt.setString(8, projectEndDate);
		pStmt.setString(9, projectExplain);
		
		try(
				PreparedStatement ps =conn.prepareStatement(sql);){
			ps.setString(1,projectCode);
			ps.setString(2,projectName);
			ps.setString(3,customer);
			ps.setInt(4,pmId);
			ps.setInt(5, projectStatus);
			ps.setInt(6,projectPriority );
			ps.setString(7, projectStartDate);
			ps.setString(8, projectEndDate);
			ps.setString(9, projectExplain);
			
			ans=ps.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();		
			}
		return ans;	
		
	
	}
	//projectStatusChangeメソッド
	// SELECT文を準備する
	public int projectStatusChange(int projectId,int projectStatus) {
		int ans = 0;
		return ans;
	}
	//projectdetailメソッド
	// SELECT文を準備する
	public ProjectDTO projectdetail(int projectId) {
		ProjectDTO dto = null;
		return dto;
	}
}
