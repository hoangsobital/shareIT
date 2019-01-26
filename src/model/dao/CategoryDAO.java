package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import util.DBConnectionUtil;

public class CategoryDAO {
	private Connection con;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private String table = "cat_list";
	public ArrayList<Category> getItems() {
		ArrayList<Category> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT id,name,parent_id FROM " + table;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int parent_id = rs.getInt("parent_id");
				Category item = new Category(id, name, parent_id);
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
	public Category checkAddCat(String name) {
		Category item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT id,name,parent_id FROM " + table + " WHERE name = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				int parent_id = rs.getInt("parent_id");
				item = new Category(id, name, parent_id);
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
	public int addItem(Category category) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO " + table+ "(name,parent_id) VALUES(?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, category.getName());
			pst.setInt(2, category.getParent_id());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(pst != null && con != null){
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public Category getItem(int id) {
		Category item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT id,name,parent_id FROM " + table + " WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				int parent_id = rs.getInt("parent_id");
				item = new Category(id, name, parent_id);
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
	public Category checkEditCat(String name, int cid) {
		Category item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT id,name,parent_id FROM " + table + " WHERE name = ? && id != ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, cid);
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				int parent_id = rs.getInt("parent_id");
				item = new Category(id, name, parent_id);
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
	public int editItem(Category category) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE " + table+ " SET name = ?, parent_id = ? WHERE id =?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, category.getName());
			pst.setInt(2, category.getParent_id());
			pst.setInt(3, category.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}finally{
			try {
				if(pst != null && con != null){
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
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM " + table+ " WHERE id =?";
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
				if(pst != null && con != null){
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int delItemC(int id) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM " + table+ " WHERE parent_id =?";
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
				if(pst != null && con != null){
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}
	////////////////////////
	public ArrayList<Category> getItemsC(int idc) {
		ArrayList<Category> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT id,name,parent_id FROM " + table + " WHERE parent_id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idc);
			rs = pst.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int parent_id = rs.getInt("parent_id");
				Category item = new Category(id, name, parent_id);
				items.add(item);
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
		return items;
	}
	
	
}
