package br.com.inatel.drury.dm107;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {
	private Connection conn = null;
	
	public UsuarioDAO() {
		if (conn == null) {
			try {
				this.conn = new ConnectionFactory().getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkLogin(String usuario, String senha) {

		boolean exists = false;
		int countUsers=0;
		
		String sql ="select count(*) as countUsers from usuario where"
				+ " usuario='"+usuario+"' and senha='"+senha+"'";
		
		try(Statement sttm = conn.createStatement();
			ResultSet  rs = sttm.executeQuery(sql);) {

			while(rs.next()){
				countUsers = rs.getInt("countUsers");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(countUsers>0){
			exists= true;
		}
		
		return exists;
	}

}
