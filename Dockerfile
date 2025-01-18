# Utilise une image de base Java pour Spring Boot
FROM openjdk:17-jdk-slim

# Définit le répertoire de travail
WORKDIR /app

# Copie le fichier JAR généré dans le conteneur
COPY target/Projetweb-copy-1.0.jar app.jar

# Expose le port 8281
EXPOSE 8281

# Commande pour exécuter l'application
CMD ["java", "-jar", "app.jar"]
