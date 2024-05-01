# Vendas_Oficial2POO2

Use a biblioteca 'mysql-connector-j-8.4.0'

Tabelas do banco de dados


-- Tabela clientes
CREATE TABLE clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100)
);
-- Tabela produtos
CREATE TABLE produtos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    preco DECIMAL(10, 2),
    descricao TEXT
);

-- Tabela Pedido
CREATE TABLE Pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dtCadastro DATE,
    ClienteId INT,
    FOREIGN KEY (ClienteId) REFERENCES Cliente(id)
);

-- Tabela Item
CREATE TABLE Item (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    PedidoId INT,
    ProdutoId INT,
    Preco DECIMAL(10, 2),
    FOREIGN KEY (PedidoId) REFERENCES Pedido(id),
    FOREIGN KEY (ProdutoId) REFERENCES Produto(id)
);

