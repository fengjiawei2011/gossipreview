package helper;

/*import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;*/
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*import java.util.ArrayList;
import java.util.List;*/
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/*import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;*/

import com.google.gson.Gson;

public class ProjectHelper {

	private String dbDriver = "com.mysql.jdbc.Driver";
	private String username = "root";
	private String password = "111111";
	// my database: jdbc:mysql://localhost/Rideo
	// tcl database: jdbc:mysql://192.168.1.55/Rideo
	private String URL = "jdbc:mysql://localhost/Rideo";

	public Connection connectDatabase() {
		Connection connection = null;
		try {
			Class.forName(dbDriver).newInstance();
			connection = DriverManager.getConnection(URL, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void write(HttpServletResponse response,
			Map<String, Object> map) {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(new Gson().toJson(map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Gson error");
		}
	}

}
