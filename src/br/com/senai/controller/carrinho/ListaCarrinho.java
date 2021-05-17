package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.ResultSet;
import br.com.dao.DatabaseConection;

public class ListaCarrinho {

	private Connection connection;

	public ListaCarrinho() {
		connection = DatabaseConection.getInstance().getConnection();
	}

	public ResultSet listarCarrinho() {
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select * from carrinho");
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.printf("| %3s | %2s | %15s | %8s | %4s | %9s |\n", "Car", "ID", "Produto", "Qtd", "Preço", "R$ Total");
			System.out.printf("| -------------------------------------------------- |\n");
			while (resultSet.next()) {
				System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", resultSet.getInt("codigoDoCarrinho"),
						resultSet.getInt("codigo"), resultSet.getString("nomeDoProduto"),
						resultSet.getInt("quantidadeDeProduto"), resultSet.getDouble("precoDoProduto"), resultSet.getDouble("totalCarrinho"));
			}

			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
}
