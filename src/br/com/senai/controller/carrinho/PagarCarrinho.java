package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DatabaseConection;

public class PagarCarrinho {
	
	private Connection connection;
	Scanner entrada = new Scanner(System.in);
	
	public PagarCarrinho() {
		connection = DatabaseConection.getInstance().getConnection();
	}
	
	public void PagaCarrinho(String cliente) {
		ListaCarrinho listaCarrinho = new ListaCarrinho();
				
		try {
			PreparedStatement preparedStatement;
			System.out.println("CONFIRMAR COMPRA? 1)Sim / 2)Nao");
			int confirmar = entrada.nextInt();
			
			if(confirmar!=1) {
				System.out.println("Pagamento cancelado!");
				return;
			}
			
			System.out.println("Gerando Nota FISCAL...\n");
			listaCarrinho.listarCarrinho(cliente);
			
			String sql = "TRUNCATE TABLE carrinho";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			
			System.out.println("Pagamento realizado com sucesso!");
			return;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
