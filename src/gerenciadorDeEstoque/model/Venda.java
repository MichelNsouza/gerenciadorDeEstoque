package gerenciadorDeEstoque.model;

public class Venda {
    private int id;
    private int idCliente;
    private double valorTotal;

    public Venda(int id, int idCliente, double valorTotal) {
        this.id = id;
        this.idCliente = idCliente;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
