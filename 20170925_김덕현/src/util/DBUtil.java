//20170925_김덕현

package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	static final String driver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/bsdb";//db명 : bsdb

	public static Connection getConnection() throws Exception {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "root", ""); //DB 접속정보
		return con;
	}
}
