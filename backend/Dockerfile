# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
COPY . /usr/src/app
WORKDIR /usr/src/app

# Usamos maven para compilar el proyecto
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre
EXPOSE 8181
WORKDIR /app

# Copiamos el JAR generado por Spring Boot
COPY --from=build /usr/src/app/target/*.jar app.jar

# Ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
