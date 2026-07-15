# Etapa 1: Compilar
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN cd APICompras && mvn clean package -DskipTests

# Etapa 2: Ejecutar
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/APICompras/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
