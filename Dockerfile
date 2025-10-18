# 1. Fase de Build: Compilar e empacotar o Java
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src/ ./src
RUN ./mvnw clean install -DskipTests

# 2. Fase de Execução: Rodar o JAR compilado no ambiente JRE (mais leve)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/todolist-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]