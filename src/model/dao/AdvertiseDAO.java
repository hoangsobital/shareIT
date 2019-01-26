package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Advertise;
import util.DBConnectionUtil;

public class AdvertiseDAO {
	private Connection con;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private String table = "advertise";

	public ArrayList<Advertise> getItems() {
		ArrayList<Advertise> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT id, name, picture,link FROM " +table;
		try {
			st = con.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String picture = rs.getString("picture");
				String link = rs.getString("link");
				Advertise item = new Advertise(id, name, picture, link);
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

	public int addItem(Advertise item) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO " + table +"(name,picture,link) VALUES(?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPicture());
			pst.setString(3, item.getLink());
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

	public Advertise getItem(int id) {
		Advertise item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT id,name,picture,link FROM " +table+" WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				String picture = rs.getString("picture");
				String link = rs.getString("link");
				item = new Advertise(id, name, picture, link);
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

	public int editItem(Advertise item) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE " + table +" SET name=?,picture=?,link=? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPicture());
			pst.setString(3, item.getLink());
			pst.setInt(4, item.getId());
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
		String sql = "DELETE FROM " + table +" WHERE id = ?";
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

}
