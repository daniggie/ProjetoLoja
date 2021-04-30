package br.com.senai.model;

public class ProdutoModel {
	// ATRIBUTOS
	private String nomeDoProduto;
	private double precoDoProduto;
	private int quantidadeDeProduto;
	private double saldoEmEstoque;

	// CONSTRUTORES

	public ProdutoModel(String nomeDoProduto, float precoDoProduto, int quantidadeDeProduto, float saldoEmEstoque) {
		super();
		this.nomeDoProduto = nomeDoProduto;
		this.precoDoProduto = precoDoProduto;
		this.quantidadeDeProduto = quantidadeDeProduto;
		this.saldoEmEstoque = saldoEmEstoque;
	}

	
	@Override
	public String toString() {
		return "Produto: " + nomeDoProduto + 
				"\nPreço: R$ " + precoDoProduto + 
				"\nQuantidade: " + quantidadeDeProduto + 
				"\nSaldo em estoque: R$ " + saldoEmEstoque;
	}


	public ProdutoModel() {
	}

	// METODOS
	public String getNomeDoProduto() {
		return nomeDoProduto;
	}

	public void setNomeDoProduto(String nomeDoProduto) {
		this.nomeDoProduto = nomeDoProduto;
	}

	public double getPrecoDoProduto() {
		return precoDoProduto;
	}

	public void setPrecoDoProduto(double d) {
		this.precoDoProduto = d;
	}

	public int getQuantidadeDeProduto() {
		return quantidadeDeProduto;
	}

	public void setQuantidadeDeProduto(int quantidadeDeProduto) {
		this.quantidadeDeProduto = quantidadeDeProduto;
	}

	public double getSaldoEmEstoque() {
		return saldoEmEstoque;
	}

	public void setSaldoEmEstoque(double d) {
		this.saldoEmEstoque = d;
	}
}
