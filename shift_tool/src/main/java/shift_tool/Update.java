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


@WebServlet(name = "/update", urlPatterns = { "/update" })
public class Update extends HttpServlet {
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

		String id = request.getParameter("human");
		String tuki = request.getParameter("month"); 
		String hi = request.getParameter("day"); 
		String gozengogo = request.getParameter("time");
		String hi2 = request.getParameter("day2");  //要確認
		String gozengogo2 = request.getParameter("time2");

		// 登録するためのSQLを作成する
		String sql = "update shift set  ";
		
		sql += "id='" + id + "',";
		
		sql += "tuki='" + tuki + "',";

		sql += "hi='" + hi + "',";
		
		sql += "gozengogo='" + gozengogo + "'";
		

		sql += "where id ='" + id + "'";
		
		sql += "AND tuki ='" + tuki + "'";
		
		sql += "AND hi ='" + hi2 + "'"; 
		
		sql += "AND gozengogo ='" + gozengogo2 + "';"; 
		
		
		
		
		System.out.println(sql);

		try {
			insertStaffDataForm(sql); 
			PrintWriter out = response.getWriter();
			out.print("更新が完了しました");
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
