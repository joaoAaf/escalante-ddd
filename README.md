# Projeto Escalante

### **EM CONSTRUÇÃO!**

## Visão Geral

O Projeto Escalante tem o objetivo principal de automatizar a criação de escalas de serviço operacional dos militares de um quartel do CBMCE. O sistema visa otimizar o processo de elaboração das escalas, reduzindo o tempo gasto pelo escalante e garantindo o cumprimento das regras de negócio específicas do [domínio](docs/DominioProjeto.md).

### Principais Funcionalidades

- Geração automática de escalas mensais, considerando datas de início, fim e quantidade de dias de serviço por equipe.
- Distribuição dos militares nas funções de acordo com suas especificidades, respeitando a antiguidade e a patente de cada militar.
- Exportação da escala gerada em formato XLSX.
- Integração com o contexto de Ajudância para obtenção dos dados dos militares aptos.

### Regras de Negócio Atendidas

- Cada equipe deve ter pelo menos 5 militares, um para cada função: Fiscal de Dia, C.O.V., Permanente, Operador de Linha e Ajudante de Linha.
- As funções são distribuídas conforme a patente e antiguidade dos militares, seguindo prioridades específicas.
- O sistema respeita folgas por função, militar ou patente.
- Permite exportação da escala em planilha Excel.

### Estrutura do Projeto

- `apis` - Código-fonte principal (Java, Spring Boot)
    - `escalante/` - Subdominio principal do sistema
        - `controllers/` - Controllers REST
        - `domain/` - Entidades de domínio e lógica de negócio
        - `services/` - Serviços de aplicação
        - `usecases/` - Interfaces de casos de uso
        - `utils/` - Utilitários, mapeadores, fábricas e adapters
    - `ajudancia/` - Subdominio suporte do sistema
        - `...`
- `docs` - Documentação do domínio, regras de negócio e diagramas

Para mais detalhes, consulte a documentação de regras de negócio em [ContextoEscalante.md](docs/regrasNegocio/ContextoEscalante.md), [ContextoAjudancia.md](docs/regrasNegocio/ContextoAjudancia.md) e [DominioProjeto.md](docs/DominioProjeto.md).

## Como Executar

**Pré-requisitos**:
   - Java 21+ ou superior;
   - Maven 3.9+;
   - Postman (ou similar) para testar os endpoints da aplicação.

**Executando a Aplicação**:
   1. No terminal entre na pasta `raiz` do projeto e em seguida na pasta `apis`;
   2. Execute o comando abaixo para executar a aplicação:
   ```sh
   mvn spring-boot:run
   ```
   3. A aplicação será iniciada em `http://localhost:8080` por padrão.

## Endpoints Disponíveis

### Recurso `/escala`

- **Metodo POST**

  Gera uma escala automática para o período e parâmetros informados e retorna o arquivo XLSX da escala.

  **Exemplo de Request Body** (`application/json`):

  ```json
  {
    "dataInicio": "2024-09-01",
    "dataFim": "2024-09-30",
    "diasServico": 2
  }
  ```
  **Response:**
    - `200 OK` com o arquivo `escala.xlsx` (MIME: `application/vnd.openxmlformats-officedocument.spreadsheetml.sheet`);
    - `503 Service Unavailable` em caso de erro.

## Dependências e Tecnologias Utilizadas

* Spring Boot 3.5.x
* Java 21
* Maven
* H2 (banco de dados em memória)
* JPA