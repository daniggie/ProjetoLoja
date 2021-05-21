package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DatabaseConection;

public class AdicionaCliente {

	Scanner entrada = new Scanner(System.in);
	private Connection connection;
	
	public AdicionaCliente() {
		connection = DatabaseConection.getInstance().getConnection();
	}
	
	public void adicionarCliente() {
		System.out.print("Informe o nome do novo cliente: ");
		String nome = entrada.next();
		
		try {
			PreparedStatement preparedStatement;
			String sql = "INSERT INTO cliente VALUES(null, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nome);
			preparedStatement.execute();
			
			System.out.println("Adicionado com sucesso!");
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir novo cliente.");
			return;
		}
	
	}
	
}
