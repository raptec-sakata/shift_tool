package shift_tool;

public class DbAccessForm {
	// 各種DBアクセス定義情報
	final static String DB_URL ="jdbc:mysql://localhost/rapsta?characterEncoding=UTF-8&serverTimezone=JST";
	final static String DB_USER ="root";
	final static String DB_PASS ="root";

	private String url;
	private String user;
	private String pass;

	// 接続したいDBに合わせて変更する

	public String getUrl() {
		url = DB_URL;
		return url;
	}

	public String getUser() {
		user = DB_USER;
		return user;
	}

	public String getPass() {
		pass = DB_PASS;
		return pass;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
