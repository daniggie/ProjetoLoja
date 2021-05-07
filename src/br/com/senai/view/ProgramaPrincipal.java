package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;
import br.com.senai.controller.ProdutoController;
import br.com.senai.controller.carrinho.AdicionaProdutoNoCarrinho;
import br.com.senai.controller.carrinho.ListaCarrinho;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoModel> carrinhos = new ArrayList<CarrinhoModel>();
		ProdutoController produtoController = new ProdutoController();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		AdicionaProdutoNoCarrinho adiciona = new AdicionaProdutoNoCarrinho();

		boolean sair = false;

		String cliente = produtoController.definirCliente();

		do {
			produtoController.menu();
			int opcao = produtoController.opcao();

			switch (opcao) {
			case 1:
				produtos.add(produtoController.cadastrarProdutos());
				break;
			case 2:
				produtoController.consultarProdutos(produtos);
				break;
			case 3:
				produtoController.editarProduto(produtos);
				break;
			case 4:
				produtoController.removerProduto(produtos);
				break;
			case 5:
				adiciona.adicionarCarrinho(produtos, carrinhos);
				break;
			case 6:
				listaCarrinho.listarProdutosCarrinho(carrinhos);
				break;
			case 7:
				produtoController.notaFiscal(carrinhos, cliente);
				break;
			case 9:
				sair = true;
				break;
			default:
				System.out.println("!!!ERRO OPCAO INVÁLIDA!!!");
				break;

			}
		} while (!sair);
	}

}
