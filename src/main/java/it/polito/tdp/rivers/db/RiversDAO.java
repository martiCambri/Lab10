package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public List<Flow>  getAllMisurazioni(int idRiver )  {
	
		final String sql = "select f.river, COUNT(f.id) as misurazioniTot, MIN(f.day) as primaMis, MAX(f.day) as ultimaMis,AVG(f.flow) as mediaFlow " +
				"from flow as f " + 
				"where f.river =  ? ";
		List <Flow> misurazioni = new ArrayList<Flow>(); 
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, idRiver);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				misurazioni.add(new Flow(res.getInt("f.river"), res.getDate("primaMis").toLocalDate(), res.getDate("ultimaMis").toLocalDate(),res.getInt("misurazioniTot"), res.getDouble("mediaFlow")));
			}
			

			conn.close();
			return misurazioni;
			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
	}
	
	public List<Flow>  getFlussi(String nomeFiume)  {
		
		final String sql = "select f.flow " +
				"from flow as f , river as r " + 
				"where  f.river = r.id and r.name =  ? ";
		List <Flow> flussi = new ArrayList<Flow>(); 
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nomeFiume);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				flussi.add(new Flow(res.getDouble("f.flow")));
			
			}
			conn.close();
			return flussi;
			
			
		} catch (SQLException e) {
			   e.printStackTrace();
			   return null;
			//throw new RuntimeException("SQL Error");
		}
		
	}
	
	
}
