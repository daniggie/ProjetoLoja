package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DatabaseConection;

public class DeletaProduto {

	Scanner entrada = new Scanner(System.in);
	ListaProduto listaProduto;
	private Connection connection;

	public DeletaProduto() {
		connection = DatabaseConection.getInstance().getConnection();
	}

	public boolean VerificaSeExiste(int idDoProduto) {
		PreparedStatement preparedStatement;

		try {
			String sql = "SELECT * FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				System.out.println("Este produto não foi cadastrado.");
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void removerProduto() {
		PreparedStatement preparedStatement;
		listaProduto = new ListaProduto();

		System.out.println("--- REMOVER PRODUTO ---");
		if (listaProduto.listarProdutos() == null) {
			return;
		}

		System.out.print("Informe o ID do produto a ser removido: ");
		int idDoProduto = entrada.nextInt();

		try {
			if (VerificaSeExiste(idDoProduto)) {
				return;
			}

			String sql = "DELETE FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir esta informação!");
			return;
		}
	}
}
