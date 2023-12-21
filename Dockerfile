# Étape 1: Build de l'application avec Maven
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copie du fichier pom.xml et téléchargement des dépendances pour mise en cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie du code source
COPY src src

# Compilation et packaging de l'application
RUN mvn package -DskipTests

# Étape 2: Création de l'image finale avec le JAR exécutable
FROM openjdk:17-slim
WORKDIR /app

# Copie du JAR de l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Commande pour exécuter l'application
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","app.jar"]
