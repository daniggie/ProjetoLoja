package br.com.senai.model;

public class Carrinho extends ProdutoModel{
	private double valorTotal, valorItem;
	
	public Carrinho(float valorTotal, float valorItem) {
		super();
		this.valorTotal = valorTotal;
		this.valorItem = valorItem;
	}

	public Carrinho() {
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorItem() {
		return valorItem;
	}

	public void setValorItem(double valorItem) {
		this.valorItem = valorItem;
	}
	
	
}
