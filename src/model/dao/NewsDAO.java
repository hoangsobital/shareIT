package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.News;
import model.bean.User;
import model.bean.UserRank;
import util.DBConnectionUtil;
import util.DefineUtil;

public class NewsDAO {
	private Connection con;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private String table = "news";
	public ArrayList<News> getItems() {
		ArrayList<News> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT news.id AS nid,news.name AS nname,preview,detail, DATE_FORMAT(date_create,'%d/%m/%Y') AS date_create,created_by,picture,is_slide, cat_list.id AS cid,cat_list.name AS cname,parent_id, users.id AS uid, username, password, fullname, id_rank,email,active FROM " + table + " INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN users ON news.created_by = users.id ORDER BY news.id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int nid = rs.getInt("nid");
				String nname = rs.getString("nname");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				String date_create = rs.getString("date_create");
				int created_by = rs.getInt("created_by");
				String picture = rs.getString("picture");
				int is_slide = rs.getInt("is_slide");
				
				int cid = rs.getInt("cid");
				int parent_id = rs.getInt("parent_id");
				String cname = rs.getString("cname");
				Category category = new Category(cid, cname, parent_id);
				
				int uid = rs.getInt("uid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				int active = rs.getInt("active");
				int id_rank = rs.getInt("id_rank");
				User user = new User(uid, username, password, fullname, email,active, new UserRank(id_rank, null));
				
				News item = new News(nid, nname, preview, detail, date_create, created_by, picture, is_slide, category, user);
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
	public int addItem(News news) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO " + table+ "(name,preview,detail, DATE_FORMAT(date_create,'%d/%m/%Y') AS date_create,created_by,picture,cat_id,is_slide) VALUES(?,?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setString(2, news.getPreview());
			pst.setString(3, news.getDetail());
			pst.setString(4, news.getDate_create());
			pst.setInt(5, news.getCreated_by());
			pst.setString(6, news.getPicture());
			pst.setInt(7, news.getCategory().getId());
			pst.setInt(8, news.getIs_slide());
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
	public News getItem(int id) {
		News item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT news.id AS nid,news.name AS nname,preview,detail, DATE_FORMAT(date_create,'%d/%m/%Y') AS date_create,created_by,picture,is_slide, cat_list.id AS cid,cat_list.name AS cname,parent_id, users.id AS uid, username, password, fullname, id_rank,email,active FROM " + table + " INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN users ON news.created_by = users.id WHERE news.id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				String nname = rs.getString("nname");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				String date_create = rs.getString("date_create");
				int created_by = rs.getInt("created_by");
				String picture = rs.getString("picture");
				int is_slide = rs.getInt("is_slide");
				
				int cid = rs.getInt("cid");
				int parent_id = rs.getInt("parent_id");
				String cname = rs.getString("cname");
				Category category = new Category(cid, cname, parent_id);
				
				int uid = rs.getInt("uid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				int active = rs.getInt("active");
				int id_rank = rs.getInt("id_rank");
				User user = new User(uid, username, password, fullname, email,active, new UserRank(id_rank, null));
				
				item = new News(id, nname, preview, detail, date_create, created_by, picture, is_slide, category, user);
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
		return item;
	}
	public int editItem(News oldNew) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE " + table+ " SET name=?,preview=?,detail=?,picture=?,cat_id=?,is_slide=? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, oldNew.getName());
			pst.setString(2, oldNew.getPreview());
			pst.setString(3, oldNew.getDetail());
			pst.setString(4, oldNew.getPicture());
			pst.setInt(5, oldNew.getCategory().getId());
			pst.setInt(6, oldNew.getIs_slide());
			pst.setInt(7, oldNew.getId());
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
		String sql = "DELETE FROM " + table+ " WHERE id = ?";
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
	public int countNews() {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS sumNews FROM " + table + " INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN users ON news.created_by = users.id";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				result = rs.getInt("sumNews");
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
		return result;
	}
	public ArrayList<News> getItemsPagination(int offset) {
		ArrayList<News> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT news.id AS nid,news.name AS nname,preview,detail, DATE_FORMAT(date_create,'%d/%m/%Y') AS date_create,created_by,picture,is_slide, cat_list.id AS cid,cat_list.name AS cname,parent_id, users.id AS uid, username, password, fullname, id_rank, email,active FROM " + table + " INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN users ON news.created_by = users.id ORDER BY news.id DESC LIMIT ?,"+DefineUtil.ROW_COUNT_ADMIN;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, offset);
			rs = pst.executeQuery();
			while(rs.next()){
				int nid = rs.getInt("nid");
				String nname = rs.getString("nname");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				String date_create = rs.getString("date_create");
				int created_by = rs.getInt("created_by");
				String picture = rs.getString("picture");
				int is_slide = rs.getInt("is_slide");
				
				int cid = rs.getInt("cid");
				int parent_id = rs.getInt("parent_id");
				String cname = rs.getString("cname");
				Category category = new Category(cid, cname, parent_id);
				
				int uid = rs.getInt("uid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				int active = rs.getInt("active");
				int id_rank = rs.getInt("id_rank");
				User user = new User(uid, username, password, fullname, email,active, new UserRank(id_rank, null));
				
				News item = new News(nid, nname, preview, detail, date_create, created_by, picture, is_slide, category, user);
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
	public int countNews(String search) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS sumNews FROM " + table + " INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN users ON news.created_by = users.id WHERE news.name LIKE '%"+search+"%'";
		try {
			st = con.createStatement();
			//pst.setString(1, search);
			rs = st.executeQuery(sql);
			if(rs.next()){
				result = rs.getInt("sumNews");
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
		return result;
	}
	public ArrayList<News> getItemsPaginationS(String search, int offset) {
		ArrayList<News> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT news.id AS nid,news.name AS nname,preview,detail, DATE_FORMAT(date_create,'%d/%m/%Y') AS date_create,created_by,picture,is_slide, cat_list.id AS cid,cat_list.name AS cname,parent_id, users.id AS uid, username, password, fullname, id_rank, email,active FROM " + table + " INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN users ON news.created_by = users.id WHERE news.name LIKE '%"+search+"%' ORDER BY news.id DESC LIMIT "+offset+","+DefineUtil.ROW_COUNT_ADMIN;
		try {
			st = con.createStatement();
			//pst.setString(1, search);
			//pst.setInt(2, offset);
			rs = st.executeQuery(sql);
			while(rs.next()){
				int nid = rs.getInt("nid");
				String nname = rs.getString("nname");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				String date_create = rs.getString("date_create");
				int created_by = rs.getInt("created_by");
				String picture = rs.getString("picture");
				int is_slide = rs.getInt("is_slide");
				
				int cid = rs.getInt("cid");
				int parent_id = rs.getInt("parent_id");
				String cname = rs.getString("cname");
				Category category = new Category(cid, cname, parent_id);
				
				int uid = rs.getInt("uid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				int active = rs.getInt("active");
				int id_rank = rs.getInt("id_rank");
				User user = new User(uid, username, password, fullname, email,active, new UserRank(id_rank, null));
				
				News item = new News(nid, nname, preview, detail, date_create, created_by, picture, is_slide, category, user);
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
	public int delItemN(int id) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM "+table+" WHERE cat_id = ?";
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
