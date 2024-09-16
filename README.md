# Desafio Orla - Documentação da API RESTful

## Visão Geral

Esta API RESTful permite a gestão de projetos e funcionários, com operações de criação, leitura, atualização e exclusão de dados, além de associar funcionários a projetos e vice-versa.

## Tecnologias Utilizadas

- **Java:** Versão 11
- **Spring Boot:** Framework para desenvolvimento da API
- **Banco de Dados:** MySQL

## Estrutura do Banco de Dados

### Tabelas e Relacionamentos

**Projeto**
- `id` (BIGINT, chave primária, auto-incremento)
- `nome` (VARCHAR, não nulo)
- `data_criacao` (DATE, não nulo)

**Funcionario**
- `id` (BIGINT, chave primária, auto-incremento)
- `nome` (VARCHAR, não nulo)
- `cpf` (VARCHAR, não nulo, único)
- `email` (VARCHAR, não nulo, único)
- `salario` (DECIMAL, não nulo)

**Tabela de Associação: Projeto_Funcionario**
- `projeto_id` (BIGINT, chave estrangeira referenciando Projeto)
- `funcionario_id` (BIGINT, chave estrangeira referenciando Funcionario)

## Endpoints da API

### Projetos

#### Criar Projeto
- **Método:** POST
- **URL:** `/projetos`
- **Descrição:** Cria um novo projeto.
- **Corpo da Requisição:**
    ```json
    {
      "nome": "Nome do Projeto",
      "dataCriacao": "YYYY-MM-DD"
    }
    ```
- **Resposta:** Status 201 Created
    ```json
    {
      "id": 1,
      "nome": "Nome do Projeto",
      "dataCriacao": "YYYY-MM-DD",
      "funcionarios": []
    }
    ```

#### Listar Projetos
- **Método:** GET
- **URL:** `/projetos`
- **Descrição:** Lista todos os projetos.
- **Resposta:**
    ```json
    [
      {
        "id": 1,
        "nome": "Nome do Projeto 1",
        "dataCriacao": "2024-09-10",
        "funcionarios": [
          {
            "id": 1,
            "nome": "Funcionario do Projeto",
            "cpf": "123.456.789-00",
            "email": "funcionario@exemplo.com",
            "salario": 3000.00
          }
        ]
      },
      {
        "id": 2,
        "nome": "Nome do Projeto 2",
        "dataCriacao": "2024-09-11",
        "funcionarios": [
          {
            "id": 1,
            "nome": "Funcionario do Projeto 2",
            "cpf": "123.456.789-00",
            "email": "funcionario@exemplo.com",
            "salario": 3000.00
          }
        ]
      },
      {
        "id": 3,
        "nome": "Nome do Projeto 3",
        "dataCriacao": "2024-09-11",
        "funcionarios": [
          {
            "id": 2,
            "nome": "Funcionario do Projeto 3",
            "cpf": "123.456.789-00",
            "email": "funcionario@exemplo.com",
            "salario": 3000.00
          }
        ]
      }
    ]
    ```

#### Obter Projeto por ID
- **Método:** GET
- **URL:** `/projetos/{id}`
- **Descrição:** Obtém um projeto específico pelo ID.
- **Resposta:**
    ```json
    {
      "id": 1,
      "nome": "Projeto 1",
      "dataCriacao": "2024-09-10",
      "funcionarios": [
        {
          "id": 1,
          "nome": "Funcionário do Projeto 1",
          "cpf": "123.456.789-00",
          "email": "funcionario@exemplo.com",
          "salario": 3000.00
        }
      ]
    }
    ```

### Atualizar Projeto e Adicionar Funcionário

- **Método:** PUT
- **URL:** `/projetos/{id}`
- **Descrição:** Atualiza um projeto específico pelo ID e permite associar um ou mais funcionários ao projeto.
- **Corpo da Requisição:**
    ```json
    { 
        "id": 1,
        "nome": "Projeto 1",
        "dataCriacao": "2024-09-10",
        "funcionarios": [
            {"id": 1 }  // id do funcionário a ser adicionado
        ]
    }
    ```
- **Resposta:**
    ```json
    {
      "id": 1,
      "nome": "Projeto 1",
      "dataCriacao": "2024-09-10",
      "funcionarios": [
        {
          "id": 1,
          "nome": "Funcionário Adicionado",
          "cpf": "123.456.789-00",
          "email": "funcionario@exemplo.com",
          "salario": 3000.00
        }
      ]
    }
    ```

#### Deletar Projeto
- **Método:** DELETE
- **URL:** `/projetos/{id}`
- **Descrição:** Deleta um projeto específico pelo ID.
- **Resposta:** Status 204 No Content

### Funcionários

#### Criar Funcionário
- **Método:** POST
- **URL:** `/funcionarios`
- **Descrição:** Cria um novo funcionário.
- **Corpo da Requisição:**
    ```json
    {
      "nome": "Nome do Funcionário",
      "cpf": "12345678901",
      "email": "email@exemplo.com",
      "salario": 3000.00
    }
    ```
- **Resposta:** Status 201 Created
    ```json
    {
      "id": 1,
      "nome": "Nome do Funcionário",
      "cpf": "12345678901",
      "email": "email@exemplo.com",
      "salario": 3000.00
    }
    ```

#### Listar Funcionários
- **Método:** GET
- **URL:** `/funcionarios`
- **Descrição:** Lista todos os funcionários.
- **Resposta:**
    ```json
    [
      {
        "id": 1,
        "nome": "Funcionário 1",
        "cpf": "123.456.789-00",
        "email": "funcionario@exemplo.com",
        "salario": 3000.00,
        "projetos": [
          {
            "id": 2,
            "nome": "Projeto 2",
            "dataCriacao": "2024-09-11"
          },
          {
            "id": 1,
            "nome": "Projeto 1",
            "dataCriacao": "2024-09-10"
          }
        ]
      },
      {
        "id": 2,
        "nome": "Funcionário 2",
        "cpf": "789.456.789-00",
        "email": "funcionario@exemplo.com",
        "salario": 3000.00,
        "projetos": [
          {
            "id": 3,
            "nome": "Projeto 1",
            "dataCriacao": "2024-09-11"
          }
        ]
      }
    ]
    ```

#### Obter Funcionário por ID
- **Método:** GET
- **URL:** `/funcionarios/{id}`
- **Descrição:** Obtém um funcionário específico pelo ID.
- **Resposta:**
    ```json
    {
      "id": 1,
      "nome": "Funcionario 1",
      "cpf": "123.456.789-00",
      "email": "funcionario@teste.com",
      "salario": 3000.00,
      "projetos": [
        {
          "id": 2,
          "nome": "Projeto 2",
          "dataCriacao": "2024-09-11"
        },
        {
          "id": 1,
          "nome": "Projeto 1",
          "dataCriacao": "2024-09-10"
        }
      ]
    }
    ```

### Atualizar Funcionário e Adicionar Projeto

- **Método:** PUT
- **URL:** `/funcionarios/{id}`
- **Descrição:** Atualiza um funcionário específico pelo ID e permite associar um ou mais projetos ao funcionário.
- **Corpo da Requisição:**
    ```json
    {
        "id": 1,
        "nome": "Funcionario 1",
        "cpf": "123.456.789-00",
        "email": "funcionario@teste.com",
        "salario": 3000.00,
        "projetos": [
            {"id": 1 }  // id do projeto a ser adicionado
        ]
    }
    ```
- **Resposta:**
    ```json
    {
      "id": 1,
      "nome": "Funcionario 1",
      "cpf": "123.456.789-00",
      "email": "funcionario@teste.com",
      "salario": 3000.00,
      "projetos": [
        {
          "id": 1,
          "nome": "Projeto 1",
          "dataCriacao": "2024-09-10"
        }
      ]
    }
    ```

#### Deletar Funcionário
- **Método:** DELETE
- **URL:** `/funcionarios/{id}`
- **Descrição:** Deleta um funcionário específico pelo ID.
- **Resposta:** Status 204 No Content

## Instruções para Executar o Projeto

1. Clone o repositório:
    ```bash
    git clone https://github.com/lucasdcferraz/desafio-orla.git
    cd desafio-orla
    ```

2. Compile e empacote o aplicativo usando Gradle:
    ```bash
    ./gradlew build
    ```

3. Execute o aplicativo:
    ```bash
    ./gradlew bootRun
    ```

4. **Acesse a API:** 
   - URL base: `http://localhost:8080`
  
## Testes

Os testes foram implementados para garantir a funcionalidade correta da API. Eles cobrem:

- Validação de criação, atualização e exclusão de projetos e funcionários.
- Verificação dos relacionamentos entre projetos e funcionários.
- Garantia da integridade dos dados e conformidade com os requisitos da API.

**Para rodar os testes:** 
    ```bash
   ./gradlew test
    ```
