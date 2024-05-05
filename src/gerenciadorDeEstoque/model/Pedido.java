package gerenciadorDeEstoque.model;

import java.util.Date;

public class Pedido {
    private int id;
    private Date dtCadastro;
    private int clienteId;

    public Pedido(int clienteId) {
        this.clienteId = clienteId;
        this.dtCadastro = new Date(); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
}

