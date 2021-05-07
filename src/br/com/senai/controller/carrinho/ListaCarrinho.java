package br.com.senai.controller.carrinho;

import java.util.List;

import br.com.senai.model.CarrinhoModel;

public class ListaCarrinho {
	
	public List<CarrinhoModel> listarProdutosCarrinho(List<CarrinhoModel> produtosComprados) {
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
