import java.sql.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;


public class Server {
	public static void main(String[] args) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost/cafeLS?user=root&password=dytc1234&serverTimezone=UTC";
		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();
		
		Thread sTh = new ServerThread(stmt);
		sTh.start();
		System.out.println("server Start");
		//클라이언트에게 서비스를 제공할 쓰레드를 생성 시작
		
		new AdminLogin(stmt);
		//관리자기능을 제공하는 윈도우 생성
	}
}
	


