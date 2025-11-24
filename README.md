# OMPay - Portefeuille Électronique

## Vue d'ensemble

OMPay est une application de portefeuille électronique développée en Java avec Spring Boot. Elle permet aux utilisateurs de gérer des comptes, effectuer des transactions (transferts et paiements) et sécuriser l'accès via OTP et JWT. L'application est conçue pour être scalable, sécurisée et facile à déployer.

### Fonctionnalités principales

- **Gestion des comptes** : Création et gestion de comptes utilisateurs avec différents types (Client, Distributeur, Marchand)
- **Authentification sécurisée** : Système OTP via SMS (Twilio) et authentification JWT
- **Transactions** :
  - Transferts entre comptes
  - Paiements vers les marchands
  - Calcul automatique des soldes
- **API RESTful** : Documentation complète via Swagger/OpenAPI
- **Sécurité** : Authentification stateless, blacklist de tokens, gestion des rôles
- **Internationalisation** : Support multilingue (Français/Anglais)
- **Déploiement** : Containerisation Docker et déploiement sur Render

## Technologies utilisées

### Backend
- **Java 17** : Langage de programmation principal
- **Spring Boot 3.5.7** : Framework pour le développement rapide d'applications
- **Spring Data JPA** : ORM pour l'accès aux données
- **Spring Security** : Gestion de la sécurité et de l'authentification
- **PostgreSQL** : Base de données relationnelle
- **JWT (JSON Web Tokens)** : Authentification stateless
- **Twilio** : Service SMS pour les OTP
- **Swagger/OpenAPI** : Documentation automatique des API

### Outils de développement
- **Maven** : Gestion des dépendances et build
- **Lombok** : Réduction du code boilerplate
- **MapStruct** : Mapping objet-objet
- **Docker** : Containerisation
- **Render** : Plateforme de déploiement cloud

### Bonnes pratiques implémentées

#### Architecture
- **Architecture en couches** : Séparation claire entre contrôleurs, services et repositories
- **DTOs (Data Transfer Objects)** : Séparation des entités de domaine et des objets de transfert
- **Mappers** : Utilisation de MapStruct pour les conversions automatiques
- **Gestion d'erreurs globale** : Intercepteur pour uniformiser les réponses d'erreur

#### Sécurité
- **Authentification à deux facteurs** : OTP + PIN
- **JWT stateless** : Pas de sessions côté serveur
- **Blacklist de tokens** : Invalidation des tokens lors de la déconnexion
- **Validation des entrées** : Annotations Bean Validation
- **CORS configuré** : Gestion des origines autorisées

#### Code
- **Clean Code** : Code lisible et maintenable
- **SOLID Principles** : Principes de conception orientée objet
- **Exception Handling** : Gestion appropriée des erreurs
- **Logging** : Traces pour le debugging
- **Tests unitaires** : Framework Spring Boot Test

#### DevOps
- **Containerisation** : Image Docker multi-étapes optimisée
- **CI/CD** : Déploiement automatisé sur Render
- **Configuration externalisée** : Variables d'environnement
- **Profiles Spring** : Gestion des environnements (local, prod)

## Architecture

### Structure du projet

```
src/main/java/com/example/ompay/
├── config/                 # Configurations Spring
│   ├── CorsConfig.java
│   ├── SecurityConfig.java
│   ├── SwaggerConfig.java
│   └── TwilioConfig.java
├── controller/             # Contrôleurs REST
│   ├── AuthController.java
│   ├── CompteController.java
│   └── TransactionController.java
├── dto/                    # Objets de transfert de données
│   ├── request/
│   └── response/
├── entity/                 # Entités JPA
│   ├── Compte.java
│   ├── Transaction.java
│   ├── Utilisateur.java
│   └── enums/
├── mapper/                 # Mappers MapStruct
├── repository/             # Repositories Spring Data
├── security/               # Composants de sécurité
├── service/                # Logique métier
└── utils/                  # Utilitaires
```

### Modèle de données

#### Entités principales

- **Utilisateur** : Informations personnelles (nom, prénom, CIN, adresse, date de naissance)
- **Compte** : Compte bancaire virtuel (téléphone, PIN hashé, type client, solde calculé)
- **Transaction** : Opérations financières (montant, frais, type, statut, comptes source/destination)

#### Énumérations

- **TypeClient** : CLIENT, DISTRIBUTEUR, MARCHAND
- **TypeTransaction** : DEPOT, RETRAIT, PAIEMENT
- **StatutTransaction** : REUSSI, ECHEC
- **StatutCompte** : ACTIF, SUSPENDU, BLOQUE

### Flux d'authentification

1. **Envoi OTP** : Génération et envoi d'un code à 4 chiffres via SMS
2. **Validation OTP** : Vérification du code en cache mémoire
3. **Connexion** : Validation du PIN et génération des tokens JWT
4. **Accès protégé** : Vérification du token à chaque requête

## API REST

### Endpoints principaux

#### Authentification (`/api/auth`)
- `POST /send-otp` : Envoi d'OTP
- `POST /validate-otp` : Validation d'OTP
- `POST /login` : Connexion avec PIN
- `POST /logout` : Déconnexion (blacklist token)

#### Comptes (`/api/comptes`)
- `POST /` : Création de compte
- `GET /me` : Récupération du compte connecté

#### Transactions (`/api/transactions`)
- `GET /` : Liste des transactions du compte
- `POST /transfert` : Transfert vers un autre compte
- `POST /paiement` : Paiement vers un marchand

### Format des réponses

Toutes les réponses suivent le format uniforme :

```json
{
  "success": true,
  "message": "Opération réussie",
  "data": { ... },
  "timestamp": "2024-01-01T12:00:00"
}
```

### Documentation API

Accessible via Swagger UI : `http://localhost:8080/swagger-ui.html`

## Installation et configuration

### Prérequis

- Java 17
- Maven 3.6+
- PostgreSQL (ou Docker pour la base)
- Compte Twilio (pour les SMS)

### Configuration locale

1. **Cloner le repository**
   ```bash
   git clone <repository-url>
   cd ompay
   ```

2. **Configurer la base de données**
   ```sql
   CREATE DATABASE ompay;
   ```

3. **Variables d'environnement**
   Créer un fichier `.env` :
   ```env
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=ompay
   DB_USER=your_user
   DB_PASSWORD=your_password

   TWILIO_ACCOUNT_SID=your_sid
   TWILIO_AUTH_TOKEN=your_token
   TWILIO_PHONE_NUMBER=your_number
   ```

4. **Build et exécution**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

5. **Accès à l'application**
   - API : `http://localhost:8080`
   - Swagger : `http://localhost:8080/swagger-ui.html`

### Configuration production

Utiliser le profil `prod` avec les variables d'environnement appropriées.

## Déploiement

### Docker

```bash
# Build de l'image
docker build -t ompay .

# Exécution
docker run -p 8080:8080 --env-file .env ompay
```

### Render

Le déploiement est automatisé via `render.yaml` pour la plateforme Render.

## Concepts clés et bonnes pratiques

### Gestion des transactions

- **Atomicité** : Les transactions sont traitées de manière atomique
- **Vérification des soldes** : Contrôle automatique des fonds disponibles
- **Frais configurables** : Système extensible pour les commissions
- **Historique complet** : Traçabilité de toutes les opérations

### Sécurité

- **Hashage des mots de passe** : Utilisation de BCrypt pour les PINs
- **Expiration des tokens** : Tokens JWT avec durée de vie limitée
- **Protection CSRF** : Désactivée pour les API REST, à implémenter côté frontend
- **Validation des données** : Annotations `@Valid` sur tous les DTOs

### Performance

- **Lazy loading** : Chargement paresseux des relations JPA
- **Indexation** : Index sur les champs fréquemment recherchés
- **Cache mémoire** : Cache des OTP en mémoire (à remplacer par Redis en prod)
- **Pagination** : Support pour les listes volumineuses

### Maintenabilité

- **Clean Architecture** : Séparation des préoccupations
- **Tests automatisés** : Couverture des cas d'usage principaux
- **Logging structuré** : Traces détaillées pour le monitoring
- **Documentation** : Code autodocumenté et README complet

## Contribution

1. Fork le projet
2. Créer une branche feature (`git checkout -b feature/AmazingFeature`)
3. Commit les changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

### Standards de code

- Utiliser les conventions Java standard
- Ajouter des tests pour les nouvelles fonctionnalités
- Documenter les méthodes publiques
- Respecter les principes SOLID

## Licence

Ce projet est sous licence Apache 2.0 - voir le fichier [LICENSE](LICENSE) pour plus de détails.

## Contact

Ousmane Marra - ousmanemarra70@gmail.com

Lien du projet : [https://github.com/username/ompay](https://github.com/username/ompay)

---