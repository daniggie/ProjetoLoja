package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DatabaseConection;

public class RemoverDoCarrinho {
	// OKAY
	Scanner entrada = new Scanner(System.in);
	ListaCarrinho listaCarrinho;
	private Connection connection;

	public RemoverDoCarrinho() {
		connection = DatabaseConection.getInstance().getConnection();
	}

	public boolean VerificaSeExiste(int idDoProduto, int idCliente) {
		PreparedStatement preparedStatement;

		try {
			String sql = "SELECT * FROM carrinho WHERE codigo = ? AND idCliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.setInt(2, idCliente);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				System.out.println("Este produto não foi cadastrado.");
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean verificaQuantidade(int idDoProduto, int quantidadeRemovida) {
		PreparedStatement preparedStatement;

		try {
			String sql = "SELECT quantideDeProduto FROM carrinho WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int quantidadeNoCarrinho = resultSet.getInt("quantideDeProduto");

			if (quantidadeNoCarrinho < quantidadeRemovida) {
				System.out.println("Não pode-se retirar mais do que já tem no carrinho. Tente novamente");
				return true;
			} else {
				System.out.println("Removendo...");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void removerProduto(int idCliente) {
		PreparedStatement preparedStatement;
		listaCarrinho = new ListaCarrinho();
		
		if(idCliente == 0) {
			System.out.println("Precisa LOGAR para poder remover do carrinho.");
			return;
		}

		System.out.println("--- REMOVER PRODUTO ---");
		if (listaCarrinho.listarCarrinho(idCliente) == null) {
			return;
		}

		System.out.print("Informe o ID do produto a ser removido: ");
		int idDoProduto = entrada.nextInt();
		if (VerificaSeExiste(idDoProduto, idCliente)) {
			return;
		}

		try {

			System.out.print("Informe a quantidade do produto a ser removido: ");
			int quantidadeRemovida = entrada.nextInt();

			if (verificaQuantidade(idDoProduto, quantidadeRemovida)) {
				return;
			}

			String sql = "SELECT quantideDeProduto, precoDoProduto FROM carrinho WHERE codigo = ? AND idCliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.setInt(2, idCliente);

			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int quantidadeCarrinho = resultSet.getInt("quantideDeProduto");
			double precoDoProduto = resultSet.getDouble("precoDoProduto");
			double precoRetirado = precoDoProduto * quantidadeRemovida;

			preparedStatement.clearParameters();
			sql = "SELECT quantidadeDeProduto, saldoEmEstoque FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int quantidadeEstoque = resultSet.getInt("quantidadeDeProduto");
			double precoAntigo = resultSet.getDouble("saldoEmEstoque");
			int newQuantidadeCarrinho = quantidadeCarrinho - quantidadeRemovida;

			preparedStatement.clearParameters();
			sql = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, quantidadeRemovida + quantidadeEstoque);
			preparedStatement.setDouble(2, precoAntigo + precoRetirado);
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.execute();
			preparedStatement.clearParameters();

			double newTotal = newQuantidadeCarrinho * precoDoProduto;
			sql = "UPDATE carrinho SET quantideDeProduto = ?, totalCarrinho = ? WHERE codigo = ? AND idCliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, newQuantidadeCarrinho);
			preparedStatement.setDouble(2, newTotal);
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.setInt(4, idCliente);
			preparedStatement.execute();
			
			if(newQuantidadeCarrinho==0) {
				sql = "DELETE FROM carrinho WHERE codigo = ? AND idCliente = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.setInt(2, idCliente);
				preparedStatement.execute();
			}else {
				System.out.println("nn deleto");
			}
			
			System.out.println("Removido!");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível remover produto!");
			return;
		}
	}

}
