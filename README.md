# Aquaguard DevOps

## Integrantes
- **Raphaela Oliveira Tatto** (RM 554983)
- **Tiago Capela** (RM 558021)

## Visão Geral
Este repositório contém a solução de containerização da API Java (Aquaguard) e do banco de dados PostgreSQL, conforme solicitado na disciplina DevOps Tools & Cloud Computing 

## Objetivos
- Containerizar a API Java em um container Docker usando Dockerfile.
- Containerizar o banco de dados PostgreSQL em um container Docker usando Dockerfile.
- Garantir comunicação entre containers via rede Docker dedicada.
- Atender aos seguintes critérios técnicos:

  1. **Container da Aplicação**  
     - Imagem customizada via Dockerfile.  
     - Executado com usuário não-root.  
     - Definição de diretório de trabalho.  
     - Uso de variáveis de ambiente para configuração.  
     - Expor porta 8080 para acesso externo.  
     - CRUD completo persistindo dados em tabela no PostgreSQL.

  2. **Container do Banco de Dados**  
     - Imagem customizada via Dockerfile.  
     - Executado com usuário não-root.  
     - Definição de diretório de trabalho padrão do PostgreSQL.  
     - Uso de variáveis de ambiente para definição de DB, usuário e senha.  
     - Volume nomeado para persistência de dados.  
     - Expor porta 5432 para acesso externo.  
     - Não usar H2.

## Estrutura do Repositório
```
├── db/
│   └── Dockerfile
├
│── Dockerfile
└── README.md
```

## Instruções de Build e Execução

1. **Criar rede Docker**
```bash
docker network create aquaguard-net
```

2. **Build da imagem do banco de dados**
```bash
docker build -t aquaguard-db:1.0 -f db/Dockerfile .
```

3. **Executar container do banco de dados**
```bash
docker run -d --name aquaguard-db --network aquaguard-net -p 5432:5432 -v pgdata:/var/lib/postgresql/data aquaguard-db:1.0
```

4. **Build da imagem da API**
```bash
docker build -t aquaguard-api:1.0 -f api/Dockerfile .
```

5. **Executar container da API**
```bash
docker run -d --name aquaguard-api --network aquaguard-net -p 8080:8080   -e SPRING_DATASOURCE_URL=jdbc:postgresql://aquaguard-db:5432/aquaguarddb   -e SPRING_DATASOURCE_USERNAME=aquaguarduser   -e SPRING_DATASOURCE_PASSWORD=supersecret   -e SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver   -e SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect   aquaguard-api:1.0
```

## Testes e Evidências
- Acesse o Swagger UI em: `http://localhost:8080/swagger-ui.html`
- Realize operações de CRUD via Swagger ou curl.
- Verifique persistência de dados no banco:
```bash
docker exec -it aquaguard-db psql -U aquaguarduser -d aquaguarddb -c "SELECT * FROM tb_aqua_usuario;"
```
- API de Java log
![Log API de java](https://github.com/raphatatto/devops-aquaguard/blob/main/img/log-api.png)

- Banco de dados Log
![Log do banco](https://github.com/raphatatto/devops-aquaguard/blob/main/img/logs-db.png)

*Evidências apresentadas no vídeo: execução dos containers, logs, CRUD e consulta no banco.* 
