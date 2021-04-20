package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;
import br.com.senai.controller.LojaController;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

		LojaController lojaController = new LojaController();		
		
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
			case 9:
				sair = true;
				break;
			default:
				System.out.println("ERRO OPCAO INVÁLIDA");
				break;
			}
		}while(!sair);
	}

}
