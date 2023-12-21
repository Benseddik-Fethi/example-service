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

## Exécution des Tests
- Pour exécuter des tests, assurez-vous que le profil de test est activé.
  Cela utilisera la base de données H2 et la configuration spécifique définie dans `application-test.yml`.
- Les tests peuvent être exécutés en utilisant la commande Maven : `./mvnw test`.
- Les migrations Liquibase seront automatiquement appliquées à la base de données H2 lors de l'exécution des tests.

## Lancement de l'Application via Docker et Docker Compose
- Pour lancer l'application via Docker, assurez-vous que Docker est installé et configuré sur votre machine.
- Exécutez la commande suivante pour lancez l'application : `docker-compose up --build`.
- L'application sera accessible à l'adresse suivante : `http://localhost:8080`.
- Pour arrêter l'application, exécutez la commande suivante : `docker-compose down`.

## Guide d'Utilisation
Pour tester et interagir avec l'API, utilisez Swagger UI à l'adresse suivante : `http://localhost:8080/swagger-ui.html`.
Cette interface vous permettra de visualiser et d'essayer toutes les opérations API disponibles.

## Contact
Pour toute question ou suggestion, veuillez contacter Fethi Benseddik à l'adresse email suivante : fbenseddik@insy2s.fr

## Licence
Ce projet est distribué sous la Licence MIT. Voir le fichier LICENSE pour plus d'informations.


## Modifications Récentes
- Configuration d'une base de données H2 pour les tests. Cela permet d'exécuter des tests indépendamment de la base de données principale PostgreSQL.
- Configuration Hibernate pour les tests avec H2 : Hibernate est configuré pour créer les tables et les relations nécessaires dans la base de données H2 lors de l'exécution des tests.
- Ajout d'un profil de test dans `application-test.yml` pour faciliter la gestion de la configuration spécifique aux tests.
- Mise à jour de la configuration de Liquibase dans `application-test.yml` pour utiliser la base de données H2 pendant les tests.

