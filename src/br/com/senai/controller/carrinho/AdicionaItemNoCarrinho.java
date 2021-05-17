package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DatabaseConection;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionaItemNoCarrinho {

	Scanner entrada = new Scanner(System.in);
	CarrinhoModel carrinhoModel;
	ListaProduto listaProduto;
	EditaProduto editaProduto;
	ProdutoModel produtoModel;
	private Connection connection;

	public AdicionaItemNoCarrinho() {
		connection = DatabaseConection.getInstance().getConnection();
	}

	public boolean encontrarProduto(int idDoProduto) {
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

	public void cadastrarItemNoCarrinho() {
		listaProduto = new ListaProduto();
		editaProduto = new EditaProduto();
		produtoModel = new ProdutoModel();
		PreparedStatement preparedStatement;
		listaProduto.listarProdutos();
		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		int id = entrada.nextInt();
		
		try {
			System.out.println("PASSO 1");
			String sql = "SELECT * FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			int quantidade=0;
			if (!resultSet.next()) {
				System.out.println("Este produto não existe.");
				return;
			}else {
				quantidade = resultSet.getInt("quantidadeDeProduto");
			}
			
			System.out.println("PASSO 2");
			
			System.out.print("Informe a quantidade desejada: ");
			int quantidadeDesejada = entrada.nextInt();
			if (quantidadeDesejada > quantidade) {
				System.out.println("Sem quantidade em estoque suficientes!");
				return;
			}
			
			
			String nomeDoProduto = resultSet.getString("nomeDoProduto");
			double precoDoProduto = resultSet.getDouble("precoDoProduto");
			double valorTotal = quantidadeDesejada * precoDoProduto; 

			sql = "INSERT INTO carrinho VALUES(null, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nomeDoProduto);
			preparedStatement.setInt(3, quantidadeDesejada);
			preparedStatement.setDouble(4, precoDoProduto);
			preparedStatement.setDouble(5, valorTotal);
			preparedStatement.execute();
			
			int newQuantidade = quantidade-quantidadeDesejada;
			sql = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, newQuantidade);
			preparedStatement.setDouble(2, precoDoProduto * newQuantidade);
			preparedStatement.setInt(3, id);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível adicionar esse produto ao carrinho.");
			return;
		}

	}
}
