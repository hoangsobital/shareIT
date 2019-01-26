package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.UserRank;
import util.DBConnectionUtil;

public class UserRankDAO {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private String table = "users_rank";
	public ArrayList<UserRank> getItems() {
		ArrayList<UserRank> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT id, name FROM " + table;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				UserRank item = new UserRank(id, name);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null && st != null && con != null){
					rs.close();
					st.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}
	

}
