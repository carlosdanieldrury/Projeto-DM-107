package br.com.inatel.drury.dm107;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {
	
	private Connection conn = null;
	
	public EntregaDAO() {
		if (conn == null) {
			try {
				this.conn = new ConnectionFactory().getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		
	public List<EntregaEntity> list() throws SQLException {
		String sql = "SELECT * FROM entrega";
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
			entrega.setDataHoraEntrega(rs.getDate("dataEntrega"));
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
			entrega.setDataHoraEntrega(rs.getDate("dataEntrega"));
		}
		
		return entrega;
	}
	
	public boolean insertEntrega (EntregaEntity entrega) throws SQLException {
		System.out.println("Insert Entrega");
		String sql = "INSERT INTO entrega (id, NumeroPedido, idCliente) VALUES (NULL, ?, ?)";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, entrega.getNumeroPedido());
		pstm.setInt(2, entrega.getIdCliente());
//		pstm.setString(3, entrega.getNomeRecebedor());
//		pstm.setString(4, entrega.getCpfRecebedor());
//		pstm.setDate(2, entrega.getDataHoraEntrega());
		boolean result = pstm.execute();
		
		
		if (result) {
			return true;
		}
		return false;
	}
		
	public EntregaEntity updateItem (EntregaEntity entityToBeUpdated) throws SQLException {
		String sql = "UPDATE entrega SET numeroPedido=?, idCliente=?, nomeRecebedor=?, cpfRecebedor=?, dataEntrega=? WHERE id=?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, entityToBeUpdated.getNumeroPedido());
		statement.setInt(2, entityToBeUpdated.getIdCliente());
		statement.setString(3, entityToBeUpdated.getNomeRecebedor());
		statement.setString(4, entityToBeUpdated.getCpfRecebedor());
		statement.setDate(5, entityToBeUpdated.getDataHoraEntrega());
		statement.setInt(6, entityToBeUpdated.getId());

		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("An existing user was updated successfully!");
		}
		statement.close();

		return entityToBeUpdated;
		
	}
		
	public long getNextFreeId() throws SQLException {
		long id=0;
		String sql = "select max(id)+1 as nextid from entrega";

		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		id = rs.getLong("id");

		return id;
		
	}

	public boolean delete(int numeroPedido) throws SQLException {
		String sql = "delete from entrega where numeroPedido = ?";
		
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, numeroPedido);
		int numberRows = pstm.executeUpdate();
		
		if (numberRows > 0)
			return true;
		return false;
		
	}
	

}
