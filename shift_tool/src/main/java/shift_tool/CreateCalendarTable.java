package shift_tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "/calendarTable", urlPatterns = { "/calendarTable" })
public class CreateCalendarTable extends HttpServlet {

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
			out.print(getStaffShiftData());
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		getStaffShiftData();

	}

	public static String getStaffShiftData() throws ClassNotFoundException, SQLException {
		// コネクション取得
		// DbASのgetConnectionを呼び出し、取得結果の接続情報をconに代入する
		Connection con = DbAccessService.getConnection();

		// 取得するためのSQLを作成する
		String sql = "select * from shift inner join staff on shift.id = staff.id";
		//sql(select * from sample tableという文字列) プリコンパイル(コンピュータ言語)しpstmtに代入
		PreparedStatement pstmt = con.prepareStatement(sql);
		//クエリを実行し、結果をrsに代入
		
		ResultSet rs = pstmt.executeQuery();
		List<ShiftDataForm> shiftDataFormList = new ArrayList <ShiftDataForm>();
		while (rs.next()) {
			ShiftDataForm shiftDataForm = new ShiftDataForm();
			shiftDataForm.setHi(rs.getString("hi"));
			shiftDataForm.setTuki(rs.getString("tuki"));
			shiftDataForm.setGozengogo(rs.getString("gozengogo"));
			shiftDataForm.setSei(rs.getString("sei"));
			shiftDataForm.setMei(rs.getString("mei"));
			
			shiftDataFormList.add(shiftDataForm);
		}

		String selectBoxHTML = "";

		//曜日表示は固定のため、for文の外
		selectBoxHTML += "<tr class='week' align='center'>";
		selectBoxHTML += "<th>月</th>";
		selectBoxHTML += "<th>火</th>";
		selectBoxHTML += "<th>水</th>";
		selectBoxHTML += "<th>木</th>";
		selectBoxHTML += "<th>金</th>";
		selectBoxHTML += "<th>土</th>";
		selectBoxHTML += "<th>日</th></tr>";

		int hiniti = 1;

		//iが1から始まり、iの値に1を足して、7未満(6週目まで)の場合処理を繰り返す
		for (int i = 1; i < 7; i++) {
			

			//iが1(1週目)の時のセルとレコードを作成(2023年6月時

			if (i == 1) {
				selectBoxHTML += "<tr class='number'>";
				selectBoxHTML += "<td></td>";
				selectBoxHTML += "<td></td>";
				selectBoxHTML += "<td></td>";

				selectBoxHTML += "<td>" + hiniti + "</td>";
				selectBoxHTML += "<td>" + (hiniti + 1) + "</td>";
				selectBoxHTML += "<td>" + (hiniti + 2) + "</td>";
				selectBoxHTML += "<td>" + (hiniti + 3) + "</td>";
				selectBoxHTML += "</tr>";

				selectBoxHTML += "<tr class='shift1'>";
				selectBoxHTML += "<td></td>";
				selectBoxHTML += "<td></td>";
				selectBoxHTML += "<td></td>";

				String shiftName1 = "";
				String shiftName2 = "";
				String shiftName3 = "";
				String shiftName4 = "";
				String shiftName1a = "";
				String shiftName2a = "";
				String shiftName3a = "";
				String shiftName4a = "";

					for(ShiftDataForm item:shiftDataFormList) {
					if (String.valueOf(hiniti).equals(item.getHi()) 
							&& ("0").equals(item.getGozengogo())) {
						shiftName1 = item.getSei() + " " + item.getMei();
					}
					if (String.valueOf(hiniti + 1).equals(item.getHi())
							&& ("0").equals(item.getGozengogo())) {
						shiftName2 = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti + 2).equals(item.getHi())
							&& ("0").equals(item.getGozengogo())) {
						shiftName3 = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti + 3).equals(item.getHi())
							&& ("0").equals(item.getGozengogo())) {
						shiftName4 = item.getSei() + " " + item.getMei();

					}if (String.valueOf(hiniti).equals(item.getHi()) 
							&& ("1").equals(item.getGozengogo())) {
						shiftName1a = item.getSei() + " " + item.getMei();
					}
					if (String.valueOf(hiniti + 1).equals(item.getHi())
							&& ("1").equals(item.getGozengogo())) {
						shiftName2a = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti + 2).equals(item.getHi())
							&& ("1").equals(item.getGozengogo())) {
						shiftName3a = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti + 3).equals(item.getHi())
							&& ("1").equals(item.getGozengogo())) {
						shiftName4a = item.getSei() + " " + item.getMei();

					}
				}

				selectBoxHTML += "<td>" + shiftName1 + "</td>";
				selectBoxHTML += "<td>" + shiftName2 + "</td>";
				selectBoxHTML += "<td>" + shiftName3 + "</td>";
				selectBoxHTML += "<td>" + shiftName4 + "</td></tr>";

				


				selectBoxHTML += "<tr class='shift2'>";
				selectBoxHTML += "<td></td>";
				selectBoxHTML += "<td></td>";
				selectBoxHTML += "<td></td>";
				selectBoxHTML += "<td>" + shiftName1a + "</td>";
				selectBoxHTML += "<td>" + shiftName2a + "</td>";
				selectBoxHTML += "<td>" + shiftName3a + "</td>";
				selectBoxHTML += "<td>" + shiftName4a + "</td></tr>";

				//iが1以外(2週目以降)の時の作成するセルとレコード

			} else {
				selectBoxHTML += "<tr class='number'>";

				//変数hinitiが8(hiniti=hiniti + 7）のデータを入れていて、4までは1週目で使用しているので、5から始まるように-3をする
				//hiniti - 3 が30(6月のため)以下の時の作成するセルとレコード

				if (hiniti - 3 <= 30) {
					selectBoxHTML += "<td>" + (hiniti - 3) + "</td>";

					//hiniti - 3 が30を超えたの時はセルのみ作成する

				} else {
					selectBoxHTML += "<td></td>";
				}

				//hiniti - 3 の時と同様の処理

				if (hiniti - 2 <= 30) {
					selectBoxHTML += "<td>" + (hiniti - 2) + "</td>";
				} else {
					selectBoxHTML += "<td></td>";
				}
				if (hiniti - 1 <= 30) {
					selectBoxHTML += "<td>" + (hiniti - 1) + "</td>";
				} else {
					selectBoxHTML += "<td></td>";
				}
				if (hiniti <= 30) {
					selectBoxHTML += "<td>" + hiniti + "</td>";
				} else {
					selectBoxHTML += "<td></td>";
				}
				if (hiniti + 1 <= 30) {
					selectBoxHTML += "<td>" + (hiniti + 1) + "</td>";
				} else {
					selectBoxHTML += "<td></td>";
				}
				if (hiniti + 2 <= 30) {
					selectBoxHTML += "<td>" + (hiniti + 2) + "</td>";
				} else {
					selectBoxHTML += "<td></td>";
				}
				if (hiniti + 3 <= 30) {
					selectBoxHTML += "<td>" + (hiniti + 3) + "</td>";
				} else {
					selectBoxHTML += "<td></td>";
				}

				selectBoxHTML += "</tr>";

				String shiftName1 = "";
				String shiftName2 = "";
				String shiftName3 = "";
				String shiftName4 = "";
				String shiftName5 = "";
				String shiftName6 = "";
				String shiftName7 = "";
				String shiftName1a = "";
				String shiftName2a = "";
				String shiftName3a = "";
				String shiftName4a = "";
				String shiftName5a = "";
				String shiftName6a = "";
				String shiftName7a = "";
				
				for(ShiftDataForm item:shiftDataFormList) {
					if (String.valueOf(hiniti - 3).equals(item.getHi()) 
							&& ("0").equals(item.getGozengogo())) {
						shiftName1 = item.getSei() + " " + item.getMei();
					}
					if (String.valueOf(hiniti - 2).equals(item.getHi())
							&& ("0").equals(item.getGozengogo())) {
						shiftName2 = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti - 1).equals(item.getHi())
							&& ("0").equals(item.getGozengogo())) {
						shiftName3 = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti).equals(item.getHi())
							&& ("0").equals(item.getGozengogo())) {
						shiftName4 = item.getSei() + " " + item.getMei();
						
					
					}
					if (String.valueOf(hiniti + 1).equals(item.getHi())
							&& ("0").equals(item.getGozengogo())) {
						shiftName5 = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti + 2).equals(item.getHi())
							&& ("0").equals(item.getGozengogo())) {
						shiftName6 = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti + 3).equals(item.getHi())
							&& ("0").equals(item.getGozengogo())) {
						shiftName7 = item.getSei() + " " + item.getMei();

					}
					
					if (String.valueOf(hiniti - 3).equals(item.getHi()) 
							&& ("1").equals(item.getGozengogo())) {
						shiftName1a = item.getSei() + " " + item.getMei();
					}
					if (String.valueOf(hiniti - 2).equals(item.getHi())
							&& ("1").equals(item.getGozengogo())) {
						shiftName2a = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti - 1).equals(item.getHi())
							&& ("1").equals(item.getGozengogo())) {
						shiftName3a = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti).equals(item.getHi())
							&& ("1").equals(item.getGozengogo())) {
						shiftName4a = item.getSei() + " " + item.getMei();

					}
					

					if (String.valueOf(hiniti + 1).equals(item.getHi())
							&& ("1").equals(item.getGozengogo())) {
						shiftName5a = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti + 2).equals(item.getHi())
							&& ("1").equals(item.getGozengogo())) {
						shiftName6a = item.getSei() + " " + item.getMei();

					}
					if (String.valueOf(hiniti + 3).equals(item.getHi())
							&& ("1").equals(item.getGozengogo())) {
						shiftName7a = item.getSei() + " " + item.getMei();

					}
				}

				

				selectBoxHTML += "<tr class='shift1'>";
				selectBoxHTML += "<td>" + shiftName1 + "</td>";
				selectBoxHTML += "<td>" + shiftName2 + "</td>";
				selectBoxHTML += "<td>" + shiftName3 + "</td>";
				selectBoxHTML += "<td>" + shiftName4 + "</td>";
				selectBoxHTML += "<td>" + shiftName5 + "</td>";
				selectBoxHTML += "<td>" + shiftName6 + "</td>";
				selectBoxHTML += "<td>" + shiftName7 + "</td></tr>";

				

				 
				

				selectBoxHTML += "<tr class='shift2'>";
				selectBoxHTML += "<td>" + shiftName1a + "</td>";
				selectBoxHTML += "<td>" + shiftName2a + "</td>";
				selectBoxHTML += "<td>" + shiftName3a + "</td>";
				selectBoxHTML += "<td>" + shiftName4a + "</td>";
				selectBoxHTML += "<td>" + shiftName5a + "</td>";
				selectBoxHTML += "<td>" + shiftName6a + "</td>";
				selectBoxHTML += "<td>" + shiftName7a + "</td></tr>";
			}

			hiniti = hiniti + 7;
		}

		return selectBoxHTML;

	}

}