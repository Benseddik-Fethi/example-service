# Example Service

## Description
"Example Service" est un projet de démonstration axé sur le développement d'APIs en utilisant Spring Boot 3 et Java 17. 

## Fonctionnalités
- API de démonstration développée avec Spring Boot 3 et Java 17.
- Intégration avec une base de données PostgreSQL.
- Gestion des migrations de base de données avec Liquibase.
- Mappage d'objets avec MapStruct.

## Technologies Utilisées
- Java 17
- Spring Boot 3
- PostgreSQL
- Liquibase
- MapStruct

## Prérequis
- Java 17 installé sur votre machine.
- PostgreSQL installé et une base de données nommée "example_service" créée ou utiliser un container Docker.
- Mettez à jour le fichier `application.yml` avec votre nom d'utilisateur et mot de passe PostgreSQL.

## Installation et Configuration
1. Clonez le dépôt GitHub de "Example Service".
2. Assurez-vous que Java 17 et PostgreSQL sont installés et configurés sur votre machine.
3. Créez une base de données PostgreSQL nommée "example_service".
4. Mettez à jour le fichier `application.yml` avec les détails de votre base de données. Par exemple :
   ```yaml
   datasource:
       username: [votre_nom_utilisateur]
       password: [votre_mot_de_passe]
5. Exécutez l'application via votre IDE ou en utilisant la commande `./mvnw spring-boot:run`.

## Guide d'Utilisation
Pour tester et interagir avec l'API, utilisez Swagger UI à l'adresse suivante : `http://localhost:8080/swagger-ui.html`. Cette interface vous permettra de visualiser et d'essayer toutes les opérations API disponibles.

## Contact
Pour toute question ou suggestion, veuillez contacter Fethi Benseddik à l'adresse email suivante : fbenseddik@insy2s.fr

## Licence
Ce projet est distribué sous la Licence MIT. Voir le fichier LICENSE pour plus d'informations.
