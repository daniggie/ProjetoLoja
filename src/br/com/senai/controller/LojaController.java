package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.Carrinho;
import br.com.senai.model.ProdutoModel;

public class LojaController {

	private Scanner scanner;

	public LojaController() {
		scanner = new Scanner(System.in);
	}

	public void menu() {
		System.out.println("\n----MENU----\n" + "1) Cadastrar itens\n" + "2) Listar estoque\n" + "3) Editar item\n"
				+ "4) Remover item\n" + "5) Realizar venda\n" + "9) Sair do sistema\n" + "----------------------");
	}

	public int opcao() {
		System.out.println("> ");
		return scanner.nextInt();
	}

	public ProdutoModel cadastrarProdutos() {
		ProdutoModel lojaModel = new ProdutoModel();

		System.out.println("--- CADASTRAR PRODUTOS ---");
		System.out.print("Produto: ");
		scanner.nextLine();
		lojaModel.setNomeDoProduto(scanner.nextLine());
		System.out.println("Preço: ");
		lojaModel.setPrecoDoProduto(scanner.nextDouble());
		System.out.println("Quantidade: ");
		lojaModel.setQuantidadeDeProduto(scanner.nextInt());
		lojaModel.setSaldoEmEstoque(lojaModel.getQuantidadeDeProduto() * lojaModel.getPrecoDoProduto());

		return lojaModel;
	}

	public List<ProdutoModel> consultarProdutos(List<ProdutoModel> produtos) {

		System.out.println("\n-------------- PRODUTOS CADASTRADOS ------------- \n");
		System.out.printf(" | %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ TOTAL");

		for (int i = 0; i < produtos.size(); i++) {
			System.out.printf(" | %2s | %10s | %8s | %4s | %9s |\n", i + 1, produtos.get(i).getNomeDoProduto(),
					produtos.get(i).getPrecoDoProduto(), produtos.get(i).getQuantidadeDeProduto(),
					produtos.get(i).getSaldoEmEstoque());

		}

		return produtos;

	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {

		this.consultarProdutos(produtos);
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;

		if (produtos.size() <= 0) {
			System.out.println("\n-------------- NÃO HÁ PRODUTOS CADASTRADOS ------ \n");
			return null;
		}

		System.out.println("\n-------------- EDITAR PRODUTOS ------------------ \n");
		System.out.print("Insira o ID do produto: ");
		idDoProduto = scanner.nextInt() - 1;

		if ((idDoProduto + 1) > produtos.size()) {
			System.out.println("\n-------------- PRODUTO INEXISTENTE -------------- \n");
			return null;
		}

		System.out.println("---CAMPOS---");
		System.out.println("1) Nome do produto;");
		System.out.println("2) Preço unitário;");
		System.out.println("3) Quantidade.");
		System.out.print("Informe o campo que deseja editar: ");
		indexDoCampo = scanner.nextInt();

		switch (indexDoCampo) {
		case 1:
			System.out.print("Informe o novo nome do produto: ");
			produto.setNomeDoProduto(scanner.next());

			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			break;
		case 2:
			System.out.print("Informe o novo preço do produto: ");
			produto.setPrecoDoProduto(scanner.nextInt());

			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getQuantidadeDeProduto() * produto.getPrecoDoProduto());

			produtos.set(idDoProduto, produto);
			break;

		case 3:
			System.out.print("Informe a nova quantia do produto: ");
			produto.setQuantidadeDeProduto(scanner.nextInt());

			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());
			produtos.set(idDoProduto, produto);

		default:
			System.out.println("Opção invalida!!!");
			break;

		}

		return null;
	}

	public void removerProduto(List<ProdutoModel> produtos) {
		if (produtos.size() <= 0) {
			System.out.println("\n----------- NÃO HÁ PRODUTOS CADASTRADOS --------- \n");
			return;
		}
		this.consultarProdutos(produtos);
		System.out.println("\n-------------- REMOVER PRODUTO ----------------- \n");
		System.out.print("ID do Produto: ");
		int idDoProduto = scanner.nextInt();

		if (idDoProduto > produtos.size()) {
			System.out.println("\n-------------- PRODUTO INEXISTENTE -------------- \n");
			return;
		}
		produtos.remove(idDoProduto - 1);

		return;
	}

	public Carrinho cadastrarCarrinho() {
		List<ProdutoModel> produtos;
		Carrinho carrinhos = new Carrinho();

		System.out.println("--- ADICIONAR PRODUTOS AO CARRINHO ---");
		
		this.consultarProdutos(produtos);
		
		System.out.print("ID do Produto: ");
		int i = scanner.nextInt();
		carrinhos.setNomeDoProduto(produtos.get(i-1).getNomeDoProduto());
		System.out.print("Quantidade: ");
		carrinhos.setQuantidadeDeProduto(scanner.nextInt());
		carrinhos.setValorItem(produtos.get(i).getPrecoDoProduto() * carrinhos.getQuantidadeDeProduto());

		return carrinhos;

	}
	
	public List<Carrinho> consultarCarrinho(List<Carrinho> carrinhos) {

		
		System.out.println("\n-------------- PRODUTOS NO CARRINHO ------------- \n");
		System.out.printf(" | %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ TOTAL");

		if(carrinhos.size()<=0) {
			System.out.println("\n-------------- CARRINHO VAZIO ------------------ \n");
			return carrinhos;
		}
		for (int i = 0; i < carrinhos.size(); i++) {
			System.out.printf(" | %2s | %10s | %8s | %4s | --------- |\n", i + 1, carrinhos.get(i).getNomeDoProduto(),
					carrinhos.get(i).getPrecoDoProduto(), carrinhos.get(i).getQuantidadeDeProduto());
			

		}
		
		System.out.printf(" | -- | ---------- | -------- | ---- | %9s |\n", carrinhos.get(0).getValorTotal());

		return carrinhos;

	}

}
