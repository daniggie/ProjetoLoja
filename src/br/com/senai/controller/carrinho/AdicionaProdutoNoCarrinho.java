package br.com.senai.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.senai.controller.ProdutoController;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionaProdutoNoCarrinho {

	Scanner sc = new Scanner(System.in);

	public void adicionarCarrinho(List<ProdutoModel> produtos, List<CarrinhoModel> produtosComprados) {
		ProdutoController lojaController = new ProdutoController();
		lojaController.consultarProdutos(produtos);
		System.out.print("ID do produto desejado: ");
		int idDoProduto = sc.nextInt();
		if (idDoProduto > produtos.size()) {
			System.out.println("Não existe o produto digitado!");
			return;
		}
		idDoProduto--;
		System.out.print("Quantidade desejada: ");
		int quantidadeDoProduto = sc.nextInt();
		if (quantidadeDoProduto > produtos.get(idDoProduto).getQuantidadeDeProduto()) {
			System.out.println("Desculpe, mas não temos há quantidade que você deseja");
			return;
		}
		produtos.get(idDoProduto)
				.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto() - quantidadeDoProduto);
		produtos.get(idDoProduto).setSaldoEmEstoque(
				produtos.get(idDoProduto).getQuantidadeDeProduto() * produtos.get(idDoProduto).getPrecoDoProduto());
		CarrinhoModel carrinhoCompra = new CarrinhoModel();
		carrinhoCompra.setNomeDoProdutoComprado(produtos.get(idDoProduto).getNomeDoProduto());
		carrinhoCompra.setPrecoDoProdutoComprado(produtos.get(idDoProduto).getPrecoDoProduto());
		carrinhoCompra.setQuantidadeDoProdutoComprado(quantidadeDoProduto);
		carrinhoCompra.setPrecoFinalProdutoComprado(quantidadeDoProduto * carrinhoCompra.getPrecoDoProdutoComprado());
		// return carrinhoCompra;
		produtosComprados.add(carrinhoCompra);
	}

}
