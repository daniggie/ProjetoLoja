package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.ResultSet;
import br.com.dao.DatabaseConection;

public class ListaCarrinho {
//OKAY
	private Connection connection;

	public ListaCarrinho() {
		connection = DatabaseConection.getInstance().getConnection();
	}

	public ResultSet listarCarrinho(int idcliente) {
		double total = 0;
		
		if(idcliente == 0) {
			System.out.println("Precisa LOGAR para poder visualizar o carrinho.");
			return null;
		}

		try {
			java.sql.PreparedStatement preparedStatement;
			String sql = "SELECT nomeDoCliente FROM cliente WHERE idcliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idcliente);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String cliente = resultSet.getString("nomeDoCliente");
			System.out.printf("| ------------------------------------------------ |\n");
			System.out.printf("| CLIENTE %40s |\n", cliente);
			System.out.printf("| %48s |\n", "");

			preparedStatement = connection.prepareStatement("select * from carrinho WHERE idCliente = ?");
			preparedStatement.setInt(1, idcliente);
			resultSet = preparedStatement.executeQuery();

			System.out.printf("| %2s | %14s | %6s | %5s | %9s |\n", "ID", "Produto", "Qtd", "Preço", "R$ Total");
			System.out.printf("| ------------------------------------------------ |\n");
			while (resultSet.next()) {
				System.out.printf("| %2s | %14s | %6s | %5s | %9s |\n", resultSet.getInt("codigo"),
						resultSet.getString("nomeDoProduto"), resultSet.getInt("quantideDeProduto"),
						resultSet.getDouble("precoDoProduto"), resultSet.getDouble("totalCarrinho"));
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
