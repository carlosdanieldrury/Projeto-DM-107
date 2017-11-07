package br.com.inatel.drury.dm107;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {
	
	private Connection conn = null;
	
	public EntregaDAO(){
		if (conn == null) {
			try {
				this.conn = new ConnectionFactory().getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkLogin(String userName, String passWord)
	{

		boolean exists = false;
		int countUsers=0;
		
		String sql ="select count(*) as countUsers from dm107users where"
				+ " user_name='"+userName+"' and password='"+passWord+"'";
		
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
	
	public List<EntregaEntity> list() throws SQLException {
		String sql = "SELECT *FROM entrega";
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		EntregaEntity entrega = null;
		List<EntregaEntity> entregas = new ArrayList<EntregaEntity>();
		
		while(rs.next()) {
			entrega = new EntregaEntity();
			entrega.setId(rs.getInt("id"));
			entrega.setNumeroPedido(rs.getInt("numeroPedido"));
			entrega.setIdCliente(rs.getInt("idCliente"));
			entrega.setNomeRecebedor(rs.getString("nomeRecebedor"));
			entrega.setCpfRecebedor(rs.getString("cpfRecebedor"));
			entrega.setDataHoraEntrega(rs.getDate("dataHoraEntrega"));
			entregas.add(entrega);
		}
		return entregas;
	}
	
	public EntregaEntity getEntrega(int numeroPedido) throws SQLException {
		String sql = "SELECT * FROM entrega where numeroPedido = " + String.valueOf(numeroPedido);
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		EntregaEntity entrega = null;
		
		while(rs.next()) {
			entrega = new EntregaEntity();
			entrega.setId(rs.getInt("id"));
			entrega.setNumeroPedido(rs.getInt("numeroPedido"));
			entrega.setIdCliente(rs.getInt("idCliente"));
			entrega.setNomeRecebedor(rs.getString("nomeRecebedor"));
			entrega.setCpfRecebedor(rs.getString("cpfRecebedor"));
			entrega.setDataHoraEntrega(rs.getDate("dataHoraEntrega"));
		}
		
		return entrega;
	}
	
	public EntregaEntity insertEntrega (EntregaEntity entrega) throws SQLException {

		
		return entrega;

	}
		
	public EntregaEntity updateItem (EntregaEntity entityToUpdate) {
		
		
		
		return entityToUpdate;
	}
		
	public long getLastIdInserted() throws SQLException
	{
		long id=0;
		String sql = "select max(id)+1 as nextid from delivery";
		

		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		id = rs.getLong("id");

		return id;
		
	}

	public boolean delete(int numeroPedido) throws SQLException {
		EntregaEntity entity = getEntrega(numeroPedido);
		String sql = "delete from entrega where id = " + entity.getId();
		
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		if (rs != null){
			return true;
		}
		return false;
	}
	

}
