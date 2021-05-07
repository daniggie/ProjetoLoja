package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;
import br.com.senai.controller.LojaController;
import br.com.senai.model.Carrinho;
import br.com.senai.model.ProdutoModel;

public class CarrinhoController {

	private Scanner sc;

	public CarrinhoController() {
		sc = new Scanner(System.in);
	}

	public void adicionarCarrinho(List<ProdutoModel> produtos, List<Carrinho> produtosComprados) {
		LojaController lojaController = new LojaController();
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
		Carrinho carrinhoCompra = new Carrinho();
		carrinhoCompra.setNomeDoProdutoComprado(produtos.get(idDoProduto).getNomeDoProduto());
		carrinhoCompra.setPrecoDoProdutoComprado(produtos.get(idDoProduto).getPrecoDoProduto());
		carrinhoCompra.setQuantidadeDoProdutoComprado(quantidadeDoProduto);
		carrinhoCompra.setPrecoFinalProdutoComprado(quantidadeDoProduto * carrinhoCompra.getPrecoDoProdutoComprado());
		// return carrinhoCompra;
		produtosComprados.add(carrinhoCompra);
	}

	public List<Carrinho> listarProdutosCarrinho(List<Carrinho> produtosComprados) {
		System.out.printf("\n----------------- SEU CARRINHO ---------------\n");
		System.out.printf("| %2s | %10s | %10s | %4s | %10s |\n", "ID", "Produto", " Preco", "Qtd ", "R$ Total");
		for (int i = 0; i < produtosComprados.size(); i++) {
			System.out.printf("| %2s | %10s | R$%8.2f | %4s | R$%8.2f |\n", i + 1,
					produtosComprados.get(i).getNomeDoProdutoComprado(),
					produtosComprados.get(i).getPrecoDoProdutoComprado(),
					produtosComprados.get(i).getQuantidadeDoProdutoComprado(),
					produtosComprados.get(i).getPrecoFinalProdutoComprado());
		}
		
		float precoTotal = 0;
		for (int i = 0; i < produtosComprados.size(); i++) {
			precoTotal += produtosComprados.get(i).getPrecoFinalProdutoComprado();
		}
		
		System.out.printf("| %3s  %10s  %10s  %6s | R$%8.2f |\n", "", "", "", "Total", precoTotal);
		
		return produtosComprados;
	}

}
