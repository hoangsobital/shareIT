package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.User;
import model.bean.UserRank;
import util.DBConnectionUtil;

public class UserDAO {
	private Connection con;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private String table = "users";
	public ArrayList<User> getItems() {
		ArrayList<User> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT users.id AS uid, username, password, fullname,email,active,users_rank.id AS rid, name FROM " + table + " INNER JOIN users_rank ON users.id_rank = users_rank.id ORDER BY users.id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int uid = rs.getInt("uid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				int active = rs.getInt("active");
				int rid = rs.getInt("rid");
				String name = rs.getString("name");
				UserRank userRank = new UserRank(rid, name);
				
				User item = new User(uid, username, password, fullname, email,active, userRank);
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
	public User checkAddUser(String username) {
		User item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT id,username,fullname,password,email,active FROM " + table + " WHERE username = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				int active = rs.getInt("active");
				item = new User(id, username, password, fullname, email,active, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null && pst != null && con != null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	public int addItem(User item) {
		int result = 0;
		con  = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO " + table + "(username,password,fullname,email,active,id_rank) VALUES(?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			pst.setString(4, item.getEmail());
			pst.setInt(5, item.getActive());
			pst.setInt(6, item.getUserRank().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null && pst != null && con != null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public User getItem(int id) {
		User item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT users.id AS uid,username,fullname,password,email,active,users_rank.id AS rid,name FROM " + table + " INNER JOIN users_rank ON users.id_rank = users_rank.id WHERE users.id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				int active = rs.getInt("active");
				int rid = rs.getInt("rid");
				String name = rs.getString("name");
				UserRank userRank = new UserRank(rid, name);
				item = new User(id, username, password, fullname, email,active, userRank);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null && pst != null && con != null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	public User checkEditUser(String username, int id) {
		User item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT id,username,fullname,password,email,active FROM " + table + " WHERE username = ? && id != ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			if(rs.next()){
				int uid = rs.getInt("id");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				int active = rs.getInt("active");
				item = new User(uid, username, password, fullname, email,active, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null && pst != null && con != null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	public int editItem(User item) {
		int result = 0;
		con  = DBConnectionUtil.getConnection();
		String sql = "UPDATE " + table + " SET username=?,password=?,fullname=?,email=?,id_rank=? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			pst.setString(4, item.getEmail());
			pst.setInt(5, item.getUserRank().getId());
			pst.setInt(6, item.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null && pst != null && con != null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int delItem(int id) {
		int result = 0;
		con  = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM " + table + " WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null && pst != null && con != null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public User checkLogin(String username, String password) {
		User item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT users.id AS uid,username,fullname,password,email,active,users_rank.id AS rid,name FROM " + table + " INNER JOIN users_rank ON users.id_rank = users_rank.id WHERE username = ? && password = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()){
				int uid = rs.getInt("uid");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				int active = rs.getInt("active");
				int rid = rs.getInt("rid");
				String name = rs.getString("name");
				UserRank userRank = new UserRank(rid, name);
				item = new User(uid, username, password, fullname, email,active, userRank);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null && pst != null && con != null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	public int changActive(int active, int id) {
		int result = 0;
		con  = DBConnectionUtil.getConnection();
		String sql = "UPDATE " + table + " SET active=? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, active);
			pst.setInt(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null && pst != null && con != null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
