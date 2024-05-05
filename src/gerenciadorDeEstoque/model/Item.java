package gerenciadorDeEstoque.model;

public class Item {
    private int id;
    private int pedidoId;
    private int produtoId;
    private int quantidade;
    private double preco;
    
    public Item(int pedidoId, int produtoId, int quantidade, double preco) {
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.preco = preco;
    }

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

	public int getQuantidade() {
		return quantidade;
	}
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;;
    }
}
