package rpdms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputFilter.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.jasper.compiler.NewlineReductionServletWriter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory {

	InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

//	private static DataSource ds = null;
//
//	public static Connection getConnection() {
//		if (ds == null) {
//			try {
//				FileInputStream fis = new FileInputStream(new File(
//						"C:\\Users\\DELL\\eclipse-workspace\\rajasthan_police_duty_management_system\\src\\main\\java\\config.properties"));
//
//				Properties p = new Properties();
//				p.load(fis);
//
//				HikariConfig config = new HikariConfig();
//				config.setUsername(p.getProperty("db.username"));
//				config.setPassword(p.getProperty("db.password"));
//				config.setJdbcUrl(p.getProperty("db.url"));
//				config.setDriverClassName(p.getProperty("db.driver"));
//				ds = new HikariDataSource(config);
//				
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} 
//		}
//		try {
//			return ds.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			FileInputStream fis;
			Properties p = new Properties();

			try {
				fis = new FileInputStream(
						new File("C:\\Users\\DELL\\Downloads\\rajasthan_police_duty_management_system\\rajasthan_police_duty_management_system\\src\\main\\java\\config.properties"));

				p.load(fis);
				Class.forName(p.getProperty("db.driver"));
				conn = DriverManager.getConnection(p.getProperty("db.url"), p.getProperty("db.username"),
						p.getProperty("db.password"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("connection error");
			}
		}
		return conn;

	}

}
