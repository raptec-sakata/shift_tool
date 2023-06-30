package shift_tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "/daySelectBox", urlPatterns = { "/daySelectBox" })
public class CreateDaySelectbox extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST処理を実行
		//request:pc→サーバー、response:サーバー→pc
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//JSでString型を使用するための文
			PrintWriter out = response.getWriter();
			out.print(getDayData());
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public static void main(String[]args) throws ClassNotFoundException, SQLException {
		
		getDayData();

	}
	
	public static String getDayData() throws ClassNotFoundException, SQLException {
		// コネクション取得
		// DbASのgetConnectionを呼び出し、取得結果の接続情報をconに代入する
		Connection con = DbAccessService.getConnection();
		

		// 取得するためのSQLを作成する
		String sql = "select * from calendar";
		//sql(select * from sample tableという文字列) プリコンパイル(コンピュータ言語)しpstmtに代入
		PreparedStatement pstmt = con.prepareStatement(sql);
		//クエリを実行し、結果をrsに代入
		ResultSet rs = pstmt.executeQuery();

		String selectBoxHTML = "";

		selectBoxHTML += "<option value=''>日付を選択してください</option> ";
		// rs(結果)を次のデータが無くなるまでselectBoxHTMLに代入を繰り返す
		while (rs.next()) {
			selectBoxHTML += "<option value = '"+rs.getString("hi")+"'>";
			selectBoxHTML += rs.getString("tuki");
			selectBoxHTML += "月";
			selectBoxHTML += rs.getString("hi");
			selectBoxHTML += "日";
			
			selectBoxHTML += "</option>";

		}

		selectBoxHTML += "</select>";
		
		return selectBoxHTML;
		 
		
	}
	
}