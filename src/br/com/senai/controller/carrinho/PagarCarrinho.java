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
	
	public void PagaCarrinho(int idCliente) {
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		
		if(idCliente == 0) {
			System.out.println("Precisa LOGAR para poder pagar o carrinho.");
			return;
		}
				
		try {
			PreparedStatement preparedStatement;
			System.out.println("CONFIRMAR COMPRA? 1)Sim / 2)Nao");
			int confirmar = entrada.nextInt();
			
			if(confirmar!=1) {
				System.out.println("Pagamento cancelado!");
				return;
			}
			
			System.out.println("\n\nNOTA FISCAL\n\n");
			listaCarrinho.listarCarrinho(idCliente);
			
			String sql = "DELETE FROM carrinho WHERE idCliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idCliente);
			preparedStatement.execute();
			
			System.out.println("Pagamento realizado com sucesso!");
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	

}
