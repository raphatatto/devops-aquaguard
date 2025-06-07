# 🌊 AquaGuard API

Sistema desenvolvido como parte do **Global Solution 2025/1** da FIAP, com foco na prevenção de enchentes e monitoramento de dados ambientais. Esta API foi construída utilizando **Java Spring Boot**, conectada a um banco **Oracle**, e implantada na nuvem via **Render.com**.

---

## ✅ Funcionalidades

- CRUD completo de usuários, sensores, regiões e alertas.
- Autenticação com **JWT**.
- Paginação e ordenação de resultados.
- Validação de dados com Bean Validation.
- Documentação da API via Swagger.
- Deploy Dockerizado na nuvem (Render).

---

## 🧰 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.1.1
- Spring Data JPA
- Oracle Database (via JDBC)
- JWT (Java Web Token)
- Maven
- Docker
- Render.com (Deploy)
- Swagger (Documentação REST)

---

## ⚙️ Requisitos para rodar localmente

- Java 17 ou superior
- Maven instalado
- Oracle Database acessível
- IDE como IntelliJ ou VSCode
- Docker (caso use o deploy local com container)

---

## 📁 Estrutura do Projeto

```
src/
 └── main/
     ├── java/
     │   └── br/com/fiap/
     │       ├── controller/
     │       ├── dto/
     │       ├── model/
     │       ├── repository/
     │       ├── security/
     │       ├── service/
     └── resources/
         └── application.properties
```

---

## 📦 Instalação e Execução Local

1. **Clone o projeto:**
```bash
git clone https://github.com/raphatatto/aquaguard-api-java.git
cd aquaguard-api-java
```

2. **Configure o `application.properties`:**
```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
server.port=8080
```

3. **Execute o projeto:**
```bash
./mvnw spring-boot:run
```
ou
```bash
mvn spring-boot:run
```

4. **Acesse o Swagger (caso ativo):**
```
http://localhost:8080/swagger-ui.html
```

---

## 🚀 Deploy no Render (com Docker)

1. **Certifique-se de ter um `Dockerfile`:**

```dockerfile
FROM maven:3.8.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/aquaguard-api-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

2. **Configure no painel do Render:**
- **Root Directory:** (deixe em branco)
- **Dockerfile:** selecione "Docker"
- **Porta:** 8080 (Render detecta automaticamente)

3. **Link de produção:**  
[https://gs-java-1sem.onrender.com](https://gs-java-1sem.onrender.com)



---

## 🔐 Autenticação JWT

- Após criar um usuário com `email` e `senha`, faça login na rota `/auth/login`
- O token gerado deve ser incluído no header das requisições:
```http
Authorization: Bearer <seu-token>
```

---

## 🧪 Endpoints principais

- `GET /api/usuarios`
- `POST /api/usuarios`
- `GET /api/usuarios/paged`
- `POST /auth/login`
- `GET /api/sensores`
- etc...

> Acesse a [documentação Swagger](https://gs-java-1sem.onrender.com/swagger-ui.html) para explorar todos os endpoints.

---

## 👨‍💻 Autores

- **Raphaela Oliveira Tatto**  
rm554983 
- **Tiago Ribeiro Capela**  
rm558021 

---

## 📄 Licença

Projeto acadêmico — uso livre para fins educacionais.
