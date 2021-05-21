package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DatabaseConection;

public class EscolheCliente {
//OKAY
	
	Scanner entrada = new Scanner(System.in);
	private Connection connection;
	
	public EscolheCliente() {
		connection = DatabaseConection.getInstance().getConnection();
	}
	
	public int RetornaIdCliente() {
		
		try {
			ListaCliente listaCliente = new ListaCliente();
			
			listaCliente.listarCliente();
			System.out.println("Qual o seu ID: ");
			int idDoCliente = entrada.nextInt();
						
			if(VerificaSeExiste(idDoCliente)) {
				return -1;
			}
			System.out.println("LOGADO NO ID: "+idDoCliente);
			return idDoCliente;
						
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}

	private boolean VerificaSeExiste(int idDoCliente) {
		PreparedStatement preparedStatement;

		try {
			String sql = "SELECT * FROM cliente WHERE idcliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoCliente);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				System.out.println("Este cliente não foi cadastrado.");
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
