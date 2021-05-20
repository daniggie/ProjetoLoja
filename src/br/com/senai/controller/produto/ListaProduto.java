package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.ResultSet;
import br.com.dao.DatabaseConection;

public class ListaProduto {
	
	private Connection connection;
	
	public ListaProduto() {
		connection = DatabaseConection.getInstance().getConnection();
	}
	
	public ResultSet listarProdutos() {
		java.sql.PreparedStatement preparedStatement;
		double total=0; 
		try {
			preparedStatement = connection.prepareStatement("select * from produto order by nomeDoProduto asc");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");
			System.out.printf("| -------------------------------------------------- |\n");
			while(resultSet.next()) {
				System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", 
						resultSet.getInt("codigo"),
						resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeProduto"),
						resultSet.getDouble("saldoEmEstoque"));
				total += resultSet.getDouble("saldoEmEstoque");
				
			}
			System.out.printf("| -------------------------------------------------- |\n");
			System.out.printf("| TOTAL R$ %41s |\n", total);
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
}
