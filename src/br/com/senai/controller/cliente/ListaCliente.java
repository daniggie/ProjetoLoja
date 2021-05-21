package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.ResultSet;

import br.com.dao.DatabaseConection;

public class ListaCliente {
//OKAY
	private Connection connection;

	public ListaCliente() {
		connection = DatabaseConection.getInstance().getConnection();
	}

	public ResultSet listarCliente() {
		java.sql.PreparedStatement preparedStatement;
		System.out.printf("| -------------------------------------------------- |\n");
		try {
			preparedStatement = connection.prepareStatement("select * from cliente");
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.printf("| %2s | %45s |\n", "ID", "Nome");
			System.out.printf("| -------------------------------------------------- |\n");
			while (resultSet.next()) {
				System.out.printf("| %2s | %45s |\n",
						resultSet.getInt("idcliente"), resultSet.getString("nomeDoCliente"));
			
			}
			System.out.printf("| -------------------------------------------------- |\n");
			

			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
