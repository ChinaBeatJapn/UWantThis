package com.swordzpp.downP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveSingleBookInfo {
	public static final String url = "jdbc:mysql://localhost:3307/spider?useUnicode=true&characterEncoding=utf-8";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "940815";

	public Connection conn = null;
	public PreparedStatement pst = null;

	public void saveBookInfo(String sql) {
		try {
			Class.forName(name);// ָ����������
			conn = DriverManager.getConnection(url, user, password);// ��ȡ����
			pst = conn.prepareStatement(sql);// ׼��ִ�����
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.conn.close();
				this.pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
