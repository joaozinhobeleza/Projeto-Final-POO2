Lista de Tarefas — Módulo de Gerenciamento de Usuários

Objetivo
Implementar o módulo completo de gerenciamento de usuários, incluindo cadastro, login, permissões e manutenção de dados, integrado ao restante do sistema (máquinas, manutenções, peças).

Estrutura Proposta
src/
├── dao/
│   └── UsuarioDAO.java
├── dto/
│   └── UsuarioDTO.java
├── view/
│   ├── TelaLogin.java
│   ├── TelaCadastroUsuario.java
│   ├── TelaListaUsuarios.java
│   └── TelaPermissoes.java
└── util/
    └── ConexaoDAO.java

Funcionalidades
*Cadastro de novos usuários com nome, e-mail, senha e tipo de acesso.
*Autenticação de login (usuário e senha).
*Controle de permissões por tipo de usuário:
    *Administrador: acesso total.
    *Técnico: acesso ao módulo de manutenção e peças.
    *Visualizador: apenas leitura.
*Listagem e edição de usuários cadastrados.
*Exclusão de contas e redefinição de senhas.
*Validação de login com restrição de acesso às telas do sistema.

Tecnologias Utilizadas
Java SE 17
Swing — Interface gráfica desktop
MySQL — Banco de dados relacional
DAO/DTO Pattern — Organização e desacoplamento de camadas
Maven — Gerenciamento de dependências
NetBeans — IDE de desenvolvimento

Banco de Dados
Tabela:
CREATE DATABASE IF NOT EXISTS lista;
USE lista;

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    usuario VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);

-- Tabela de tarefas/lista
CREATE TABLE IF NOT EXISTS lista (
    id INT AUTO_INCREMENT PRIMARY KEY,
    check_list VARCHAR(50) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    conteudo TEXT NOT NULL,
    prazo VARCHAR(10) NOT NULL
);

Como Usar
1.Crie o banco no MySQL executando o script acima.
2.Configure a classe ConexaoDAO com seus dados de acesso:
String url = "jdbc:mysql://localhost:3306/lista";
String user = "root";
String password = "1234";
3.Compile e execute o projeto no NetBeans.
4.Cadastre um usuário e acesse a aplicação.

Desenvolvedores
João Porto
Lucas Crippa
Nathan Benedetto

Licença
Projeto de uso livre sob a licença MIT.
