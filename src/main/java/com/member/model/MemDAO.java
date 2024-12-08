package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemDAO implements MemDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/checkinout");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_A_BY_PK_STMT = " SELECT * FROM member WHERE member_id = ?";
	private static final String GET_ALL_STMT = " SELECT * FROM member order by member_id";
	private static final String INSERT_STMT = "INSERT INTO member ( account, password, last_name, first_name,avatar ,phone_number, birthday, gender, status) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE member set account=?, password=?, last_name=?, first_name=? ,avatar=? ,phone_number=?, birthday=?, gender=?, status=? where member_id = ?";
	private static final String DELETE_STMT = "DELETE FROM member where member_id = ?";

	@Override
	public void insert(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getAccount());
			pstmt.setString(2, memVO.getPassword());
			pstmt.setString(3, memVO.getLastName());
			pstmt.setString(4, memVO.getFirstName());
			pstmt.setBytes(5, memVO.getAvatar());
			pstmt.setString(6, memVO.getPhoneNumber());
			pstmt.setDate(7, memVO.getBirthday());
			pstmt.setString(8, memVO.getGender());
			pstmt.setInt(9, memVO.getStatus());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, memVO.getAccount());
			pstmt.setString(2, memVO.getPassword());
			pstmt.setString(3, memVO.getLastName());
			pstmt.setString(4, memVO.getFirstName());
			pstmt.setBytes(5, memVO.getAvatar());
			pstmt.setString(6, memVO.getPhoneNumber());
			pstmt.setDate(7, memVO.getBirthday());
			pstmt.setString(8, memVO.getGender());
			pstmt.setInt(9, memVO.getStatus());
			pstmt.setInt(10, memVO.getMemberID());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public MemVO findByPK(Integer memberID) {
		// TODO Auto-generated method stub
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_A_BY_PK_STMT);
			pstmt.setInt(1, memberID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemberID(rs.getInt("member_id"));
				memVO.setAccount(rs.getString("account"));
				memVO.setPassword(rs.getString("password"));
				memVO.setLastName(rs.getString("last_name"));
				memVO.setFirstName(rs.getString("first_name"));
				memVO.setAvatar(rs.getBytes("avatar"));
				memVO.setBirthday(rs.getDate("birthday"));
				memVO.setPhoneNumber(rs.getString("phone_number"));
				memVO.setGender(rs.getString("gender"));
				memVO.setStatus(rs.getByte("status"));
				memVO.setCreateTime(rs.getTimestamp("create_time"));
				memVO.setAvatarBase64(rs.getBytes("avatar"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}

	@Override
	public void delete(Integer memberID) {

		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, memberID);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<MemVO> getAll() {
		// TODO Auto-generated method stub
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemberID(rs.getInt("member_id"));
				memVO.setAccount(rs.getString("account"));
				memVO.setPassword(rs.getString("password"));
				memVO.setLastName(rs.getString("last_name"));
				memVO.setFirstName(rs.getString("first_name"));
				memVO.setAvatar(rs.getBytes("avatar"));
				memVO.setBirthday(rs.getDate("birthday"));
				memVO.setPhoneNumber(rs.getString("phone_number"));
				memVO.setGender(rs.getString("gender"));
				memVO.setStatus(rs.getByte("status"));
				memVO.setCreateTime(rs.getTimestamp("create_time"));
				memVO.setAvatarBase64(rs.getBytes("avatar"));
				list.add(memVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
