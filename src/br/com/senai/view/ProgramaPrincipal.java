package br.com.senai.view;


import br.com.senai.controller.Controller;
import br.com.senai.controller.carrinho.AdicionaItemNoCarrinho;
import br.com.senai.controller.carrinho.ListaCarrinho;
import br.com.senai.controller.carrinho.PagarCarrinho;
import br.com.senai.controller.carrinho.RemoverDoCarrinho;
import br.com.senai.controller.cliente.AdicionaCliente;
import br.com.senai.controller.produto.CadastraProduto;
import br.com.senai.controller.produto.DeletaProduto;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;

public class ProgramaPrincipal {
	public static void main(String[] args) {
		

		Controller produtoController = new Controller();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		AdicionaItemNoCarrinho adicionaItemNoCarrinho = new AdicionaItemNoCarrinho();
		CadastraProduto cadastraProduto = new CadastraProduto();
		ListaProduto listaProduto = new ListaProduto();
		EditaProduto editaProduto = new EditaProduto();
		DeletaProduto deletaProduto = new DeletaProduto();
		AdicionaCliente adicionaCliente = new AdicionaCliente();
		RemoverDoCarrinho removerDoCarrinho = new RemoverDoCarrinho();
		PagarCarrinho pagarCarrinho = new PagarCarrinho();

		boolean sair = false;
		
		String cliente = "";

		do {
			produtoController.menu();
			int opc = produtoController.opcao();

			switch (opc) {
			case 1:
				cadastraProduto.cadastrarProduto();
				break;
			case 2:
				listaProduto.listarProdutos();
				break;
			case 3:
				editaProduto.editarProduto();
				break;
			case 4:
				deletaProduto.removerProduto();
				break;
			case 5:
				adicionaItemNoCarrinho.cadastrarItemNoCarrinho();
				break;
			case 6:
				listaCarrinho.listarCarrinho(cliente);
				break;
			case 7:
				removerDoCarrinho.removerProduto(cliente);
				break;
			case 8:
				pagarCarrinho.PagaCarrinho(cliente);
				break;
			case 0:
				cliente = adicionaCliente.definirCliente();
				break;
			case 9:
				sair = true;
				break;

			default:
				System.out.println("Opção inválida!!!");
				break;
			}
		} while (!sair);

		System.out.println("Sistema encerrado!!!");
	}
}
