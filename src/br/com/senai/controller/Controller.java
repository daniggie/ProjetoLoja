package br.com.senai.controller;

import java.util.Scanner;

public class Controller {

	private Scanner entrada;

	public Controller() {
		entrada = new Scanner(System.in);
	}

	public int opcao() {
		System.out.print("> ");
		return entrada.nextInt();
	}

	public void menu() {
		System.out.println("\n--- MENU ESTOQUE DE PRODUTO---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover produto do estoque");
		System.out.println("\n--- MENU CARRINHO ---\n");
		System.out.println("5) Adicionar ao carrinho");
		System.out.println("6) Listar itens no carrinho");
		System.out.println("7) Remover produto do carrinho");
		System.out.println("8) Pagar e remover carrinho");
		System.out.println("\n--- MENU CLIENTE ---\n");
		System.out.println("0) Adicionar cliente");
		System.out.println("10) Logar");
		System.out.println("--------------------");
		System.out.println("9) Sair do sistema");
	}

}
