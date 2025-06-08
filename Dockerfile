# Etapa de build (já existente)
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de execução ajustada
FROM eclipse-temurin:17
# 1) criar usuário + 2) usar usuário
RUN useradd --create-home appuser
USER appuser

# 3) WORKDIR
WORKDIR /home/appuser

# 4) copiar JAR
COPY --from=build /app/target/aquaguard-api-1.0-SNAPSHOT.jar app.jar

# 5) variáveis de ambiente para o Spring
ENV DATABASE_URL=jdbc:postgresql://db:5432/aquaguarddb \
    DATABASE_USER=aquaguarduser \
    DATABASE_PASSWORD=supersecret

# 6) expor porta
EXPOSE 8080

# 7) comando de inicialização
ENTRYPOINT ["java","-jar","app.jar"]
