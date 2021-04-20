package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;
import br.com.senai.model.ProdutoModel;

public class LojaController {
	
	private Scanner scanner;
	
	public LojaController() {
		scanner = new Scanner(System.in);
	}
	
	public void menu() {
	System.out.println("\n----MENU----\n"
			+ "1) Cadastrar itens\n"
			+ "2) Listar estoque\n"
			+ "3) Editar item\n"
			+ "4) Remover item\n"
			+ "5) Realizar venda\n"
			+ "9) Sair do sistema\n"
			+ "----------------------");
	}
	
	public int opcao() {
		System.out.println("> ");
		return scanner.nextInt();
	}
	public  ProdutoModel cadastrarProdutos() {
		ProdutoModel lojaModel = new ProdutoModel();
		
		System.out.println("--- CADASTRAR PRODUTOS ---");
		System.out.print("Produto: ");
		scanner.nextLine();
		lojaModel.setNomeDoProduto(scanner.nextLine());
		System.out.println("Preço: ");
		lojaModel.setPrecoDoProduto(scanner.nextDouble());
		System.out.println("Quantidade: ");
		lojaModel.setQuantidadeDeProduto(scanner.nextInt());
		lojaModel.setSaldoEmEstoque(
				lojaModel.getQuantidadeDeProduto()*lojaModel.getPrecoDoProduto()
				);
		
		
		return lojaModel;		
	}
	
	public void consultarProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- PRODUTOS CADASTRADOS --- ");
		System.out.printf("| %10s | %8s | %4s | %9s |\n", "Produto", "Preço", "Quantidade", "R$ Total");
		for(ProdutoModel produtoModel : produtos) {
			System.out.println(produtoModel);
			System.out.println("------------------");
		}
	}
}
