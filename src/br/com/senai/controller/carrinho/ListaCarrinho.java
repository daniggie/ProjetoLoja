package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.ResultSet;
import br.com.dao.DatabaseConection;

public class ListaCarrinho {

	private Connection connection;

	public ListaCarrinho() {
		connection = DatabaseConection.getInstance().getConnection();
	}

	public ResultSet listarCarrinho(String cliente) {
		double total=0;
		java.sql.PreparedStatement preparedStatement;
		System.out.printf("| ------------------------------------------------ |\n");
		System.out.printf("| CLIENTE %40s |\n", cliente);
		System.out.printf("| %48s |\n", "");
		try {
			preparedStatement = connection.prepareStatement("select * from carrinho");
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.printf("| %2s | %14s | %6s | %4s | %9s |\n", "ID", "Produto", "Qtd", "Preço", "R$ Total");
			System.out.printf("| ------------------------------------------------ |\n");
			while (resultSet.next()) {
				System.out.printf("| %2s | %15s | %6s | %4s | %9s |\n",
						resultSet.getInt("codigo"), resultSet.getString("nomeDoProduto"),
						resultSet.getInt("quantideDeProduto"), resultSet.getDouble("precoDoProduto"), resultSet.getDouble("totalCarrinho"));
					total += resultSet.getDouble("totalCarrinho");
			
			}
			System.out.printf("| ------------------------------------------------ |\n");
			System.out.printf("| TOTAL R$ %39s |\n", total);
			

			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
