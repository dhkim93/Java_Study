//20170925_김덕현

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import util.DBUtil;
import vo.UserVO;

public class UserDAO {
	Scanner sc = new Scanner(System.in);

	public UserVO insertUser() {// 사용자 입력을 받아 DB에 이름, 나이 저장
		// TODO Auto-generated method stub
		String name = "";
		int age = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("---------자료입력---------");
		System.out.println("이름 :");
		name = sc.next();

		System.out.println("나이 :");
		age = Integer.parseInt(sc.next());
		UserVO uVO = new UserVO(name, age);
		String insertQuery = "INSERT INTO userinfo values(?,?);";
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, uVO.getName());
			pstmt.setInt(2, uVO.getAge());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("입력 성공!");
			} else {
				System.out.println("입력 실패!");
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {// JDBC 종료
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return uVO;// UserVO객체 리턴

	}

	public void updateUser() {// 이름을 입력받고, 입력받은 이름의 레코드의 나이를 35로 변경
		// TODO Auto-generated method stub
		String name = "";

		Connection con = null;
		PreparedStatement pstmt = null;

		System.out.println("-----------수정-----------");
		System.out.println("이름 :");
		name = sc.next();

		String updateQuery = "UPDATE userinfo set age=35 WHERE name=?;";
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(updateQuery);

			pstmt.setString(1, name);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("수정 성공!");
			} else {
				System.out.println("수정 실패!");
			}
		} catch (SQLException se) {
			System.out.println(se);
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {// JDBC 종료
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}

	}

	public ArrayList searchName() {// 테이블의 모든 자료 검색
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println("---------자료검색---------");
		String searchQuery = "SELECT * FROM userinfo;";
		UserVO uVO = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(searchQuery);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				uVO = new UserVO(rs.getString(1), rs.getInt(2));
				System.out.print("이름 : " + rs.getString(1));
				System.out.println(", 나이 : " + rs.getInt(2));
				list.add(uVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {// JDBC 종료
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}

	public void deleteUser() {// 이름을 입력받고 입력받은 이름의 레코드를 삭제
		// TODO Auto-generated method stub

		String name = "";

		Connection con = null;
		PreparedStatement pstmt = null;

		System.out.println("---------자료삭제---------");
		System.out.println("이름 :");
		name = sc.next();

		String deleteQuery = "DELETE FROM userinfo WHERE name=?;";
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(deleteQuery);

			pstmt.setString(1, name);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("삭제 성공!");
			} else {
				System.out.println("삭제 실패!");
			}
			System.out.println("-------프로그램종료-------");
		} catch (SQLException se) {
			System.out.println(se);
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {// JDBC 종료
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
	}

}
