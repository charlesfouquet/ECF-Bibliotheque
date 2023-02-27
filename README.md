# ECF-Bibliotheque
<table>
    <tbody>
        <tr valign="top">
            <td width="90px" align="center">
            <span><strong>Java SE</strong></span><br>
            <img height="32px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg">
            </td>
            <td width="80px" align="center">
            <span><strong>Swing</strong></span><br>
            <img height="32px" src="https://i.imgur.com/7pkUw66.png">
            </td>
            <td width="80px" align="center">
            <span><strong>SQL</strong></span><br>
            <img height="32px" src="https://cdn-icons-png.flaticon.com/512/337/337953.png">
            </td>
        </tr>
    </tbody>
</table>

Projet collaboratif rendu dans le cadre de l'évaluation sur Java SE et Swing, tenu au bout de 4 mois de formation
Temps alloué : 10 jours ouvrés (soit 20 jours-homme)
Développé avec Karim Arfi (@KarimARFI)

Cette application permet à l'utilisateur de consulter le catalogue complet d'une bibliothèque (ou la liste des livres disponibles à l'emprunt à l'aide d'un filtre).

Il peut également s'inscrire (ou se connecter si son compte est déjà créé) et emprunter des livres dans la limite de 5, chaque livre étant soumis à une durée d'emprunt maximale de 1 mois. Si ce délai est dépassé, l'utilisateur ne pourra plus emprunter de livres tant que le ou les livres en retard n'ont pas été rendus.

Il dispose d'un espace "Mon compte" où il peut consulter ses emprunts, modifier ses infos personnelles, renseigner son adresse postale, modifier son mot de passe ou bien désactiver son compte (une fois tous les livres rendus).

Les utilisateurs peuvent également profiter d'un espace communautaire permettant de commenter chaque livre. Les commentaires persistent en cas de désactivation du compte, mais l'identité de l'auteur du message est modifiée à "Ancien utilisateur".

Les employés de la bibliothèque ont accès dans leur espace "Mon Compte" à un Back Office leur permettant de créer ou modifier des livres, et ce sur tous leurs aspects : titre, couverture, date de sortie, auteur, etc.

Un champ de recherche permet à tout moment d'effectuer une recherche multi critères sur l'intégralité du catalogue.

Un menu donne accès à des pages permettant de trier le catalogue par Auteur, Genre ou Série.

Ce dépôt présente le code de l'application ainsi que les éléments ayant aidé à sa réalisation (schémas de conception, script SQL de dataset, livre test à rajouter, livre test à modifier, comptes et scénarios d'emprunt déjà créés, etc.)

Objectifs : 
- Définir les fonctionnalités d'une application simulant une bibliothèque
- Concevoir l'application à l'aide de Merise
- Maquetter l'application (réalisé sous Figma)
- Préparer la base de données avec un jeu de données suffisamment significatif pour prévoir le plus de cas d'utilisation possibles
- Effectuer la connexion entre l'application et la base de données en local
- Utiliser le modèle MVC pour le développement
- Designer l'interface utilisateur de l'application en utilisant Swing
- Créer les classes modèles pour les différents objets à instancier
- Développer le contrôleur pour traiter les données et interagir avec l'utilisateur
- Ecrire au moins un CRUD complet (1 CRUD pour le User, 1 CRUD pour les exemplaires de livres, et 1 CRU pour les informations des livres)

<h3>Technologies et connaissances mobilisées :</h3>

- Java Standard Edition
- Java Swing
- JDatePicker (Librairie externe)
- SQL
