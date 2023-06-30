package shift_tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "/register", urlPatterns = { "/register" })
public class Register extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST処理を実行
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		//getParameter("");の""の中身をセレクトタグのそれぞれのname属性を入れる

		String id = request.getParameter("human");
		String tuki = request.getParameter("month");
		String hi = request.getParameter("day"); 
		String gozengogo = request.getParameter("time");

		// 登録するためのSQLを作成する
		String sql = "insert into shift value(";
		sql += "'" + id + "',";
		sql += "'" + tuki + "',";
		sql += "'" + hi + "',";
		sql += "'" + gozengogo + "');";
		System.out.println(sql);

		try {
			insertStaffDataForm(sql); 
			PrintWriter out = response.getWriter();
			out.print("登録が完了しました");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	/**
	 * 入力データからデータベース登録を行う
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insertStaffDataForm(String sql) throws ClassNotFoundException, SQLException {
		// コネクション取得
		Connection con = DbAccessService.getConnection();

		PreparedStatement pstmt = con.prepareStatement(sql);
		int i = 0;

		//INSERT文を実行する
		i = pstmt.executeUpdate();

		return i;
	}

}
