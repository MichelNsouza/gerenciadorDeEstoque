package gerenciadorDeEstoque.model;

public class Venda {
    private int id;
    private int clienteId;
    private double total;

    public Venda(int clienteId, double total) {
        this.clienteId = clienteId;
        this.total = total;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}