# Sistema de Gestão de Estoque

Este repositório contém o projeto proposto na disciplina de Programação Orientada a Objetos 2, ministrada pelo Professor Eliel Mota no curso de BSI da UNIME.

## Sobre o projeto

O sistema se trata de uma aplicação desktop construida em Java com o framework Swing, onde relizamos CRUD completo para produtos, clientes e pedidos.
Optei por organizar o projeto utilizado o design patterns mvc.

## Configuração do Projeto

Use a biblioteca 'mysql-connector-j-8.4.0' para realizar a conexão com o banco de dados MySQL e utilize a seguinte query em seu SGBD de preferencia:

```sql
CREATE TABLE Clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100)
);

CREATE TABLE Produtos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    preco DECIMAL(10, 2),
    descricao TEXT
);

CREATE TABLE Pedidos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dtCadastro DATE,
    ClienteId INT, 
    FOREIGN KEY (ClienteId) REFERENCES Clientes(id)
);

CREATE TABLE Itens (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    PedidoId INT,
    ProdutoId INT,
    Quantidade INT,
    Preco DECIMAL(10, 2),
    FOREIGN KEY (PedidoId) REFERENCES Pedidos(id),
    FOREIGN KEY (ProdutoId) REFERENCES Produtos(id)
);
```
# Imagens do projeto
