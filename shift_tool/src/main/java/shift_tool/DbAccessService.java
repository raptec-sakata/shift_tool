package shift_tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shift_tool.DbAccessForm;

public class DbAccessService {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String value = null;

	// 接続を取得
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		// DBアクセスクラスのインスタンス生成
		
		// DbAFクラスの呼び出し 変数dbへ代入
		DbAccessForm db = new DbAccessForm();
		
		// mysqlのデータベースのドライバーの読み込み
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 接続情報取得してconに代入
		con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());
		return con;

	}
}
