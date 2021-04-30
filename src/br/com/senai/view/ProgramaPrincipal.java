package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.controller.CarrinhoController;
import br.com.senai.controller.LojaController;
import br.com.senai.model.Carrinho;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<Carrinho> carrinhos = new ArrayList<Carrinho>();
		LojaController lojaController = new LojaController();
		CarrinhoController carrinhoController = new CarrinhoController();
		
		boolean sair = false;
		do {
			lojaController.menu();
			int opcao = lojaController.opcao();
	
			switch(opcao) {
			case 1:
				produtos.add(lojaController.cadastrarProdutos());
				break;
			case 2:
				lojaController.consultarProdutos(produtos);
				break;
			case 3:
				lojaController.editarProduto(produtos);
				break;
			case 4:
				lojaController.removerProduto(produtos);
				break;
			case 5:
				carrinhoController.adicionarCarrinho(produtos, carrinhos);
				break;
			case 6:
				carrinhoController.listarProdutosCarrinho(carrinhos);
				break;
			case 7:
				carrinhoController.fecharFaturaCarrinho(carrinhos);
				break;
			case 9:
				sair = true;
				break;
			default:
				System.out.println("!!!ERRO OPCAO INVÁLIDA!!!");
				break;
				
			}
		}while(!sair);
	}

}
