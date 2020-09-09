package br.com.lembrete.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.lembrete.excecoes.ApiException;
import br.com.lembrete.orm.Database;

public class LembreteRepository {
	private static final int PAGE_LENGTH = 5;
	
	
	public List<Lembrete> getTodosPorPaginacao(int page){
		List<Lembrete> lista = new ArrayList<>();
		
		try {
			Connection conexao = Database.getConnection();
			page = (page - 1) * PAGE_LENGTH;
			
			PreparedStatement statement = conexao.prepareStatement("SELECT * FROM lembrete LIMIT ?, ?");
			statement.setInt(1,  page);
			statement.setInt(2, PAGE_LENGTH);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Lembrete lembrete = new Lembrete(
						rs.getInt("id"), 
						rs.getString("titulo"), 
						rs.getString("descricao"));
				
				lista.add(lembrete);
			}
			
			
		} catch (Exception e) {
			throw new ApiException(500, "Erro ao buscar os dados no banco de dados " + e.getMessage());
		}
		
		return lista;
	}

	public List<Lembrete> getTodos(){
		List<Lembrete> lista = new ArrayList<>();
		
		try {
			Connection conexao = Database.getConnection();
			PreparedStatement statement = conexao.prepareStatement("SELECT * FROM lembrete LIMIT " + PAGE_LENGTH);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Lembrete lembrete = new Lembrete(
						rs.getInt("id"), 
						rs.getString("titulo"), 
						rs.getString("descricao"));
				
				lista.add(lembrete);
			}
			
			
		} catch (Exception e) {
			throw new ApiException(500, "Erro ao buscar os dados no banco de dados " + e.getMessage());
		}
		
		return lista;
	}
}















