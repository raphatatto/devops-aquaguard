# Etapa de build
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/aquaguard-api-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
