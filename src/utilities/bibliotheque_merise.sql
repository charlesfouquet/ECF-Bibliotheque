DROP DATABASE IF EXISTS ecf_bibliotheque;
CREATE DATABASE IF NOT EXISTS ecf_bibliotheque;
USE ecf_bibliotheque;

DROP TABLE IF EXISTS users;
CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nom VARCHAR(100) NOT NULL,
prenom VARCHAR(100) NOT NULL,
email VARCHAR(150) NOT NULL,
password VARCHAR(100) NOT NULL,
adresse VARCHAR(255),
cp INT,
ville VARCHAR(100),
tel VARCHAR(15));

DROP TABLE IF EXISTS emprunts;
CREATE TABLE emprunts (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
dateSortie TIMESTAMP,
dateRetour TIMESTAMP NULL,
id_user INT,
id_livre INT);

DROP TABLE IF EXISTS commentaires;
CREATE TABLE commentaires (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
contenu TEXT NOT NULL,
dateCom DATETIME,
id_livre INT,
id_user INT);

DROP TABLE IF EXISTS livres;
CREATE TABLE livres (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
titre VARCHAR(255) NOT NULL,
resume TEXT NOT NULL,
datePubli DATE NOT NULL,
nbPages INT NOT NULL,
couverture BLOB NULL,
id_editeur INT NOT NULL);

DROP TABLE IF EXISTS auteurs;
CREATE TABLE auteurs (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nom VARCHAR(100) NOT NULL,
prenom VARCHAR(100) NOT NULL,
bio TEXT NOT NULL);

DROP TABLE IF EXISTS series;
CREATE TABLE series (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nomSerie VARCHAR(150) NOT NULL,
position INT NOT NULL,
id_livre INT);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
theme VARCHAR(100) NOT NULL);

DROP TABLE IF EXISTS editeurs;
CREATE TABLE editeurs (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nomSocial VARCHAR(150) NOT NULL);

DROP TABLE IF EXISTS livres_auteurs ;
CREATE TABLE livres_auteurs (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
id_livre INT,
id_auteur INT);

DROP TABLE IF EXISTS livres_genres ;
CREATE TABLE livres_genres (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
id_livre INT,
id_genre INT);

ALTER TABLE emprunts ADD CONSTRAINT FK_emprunts_id_user FOREIGN KEY (id_user) REFERENCES users(id);
ALTER TABLE emprunts ADD CONSTRAINT FK_emprunts_id_livre FOREIGN KEY (id_livre) REFERENCES livres(id);
ALTER TABLE commentaires ADD CONSTRAINT FK_commentaires_id_user FOREIGN KEY (id_user) REFERENCES users(id);
ALTER TABLE commentaires ADD CONSTRAINT FK_commentaires_id_livre FOREIGN KEY (id_livre) REFERENCES livres(id);
ALTER TABLE livres ADD CONSTRAINT FK_livres_id_editeur FOREIGN KEY (id_editeur) REFERENCES editeurs(id);
ALTER TABLE livres_auteurs ADD CONSTRAINT FK_livres_auteurs_id_livre FOREIGN KEY (id_livre) REFERENCES livres(id);
ALTER TABLE livres_auteurs ADD CONSTRAINT FK_livres_auteurs_id_auteur FOREIGN KEY (id_auteur) REFERENCES auteurs(id);
ALTER TABLE livres_genres ADD CONSTRAINT FK_livres_genres_id_livre FOREIGN KEY (id_livre) REFERENCES livres(id);
ALTER TABLE livres_genres ADD CONSTRAINT FK_livres_genres_id_genre FOREIGN KEY (id_genre) REFERENCES genres(id);
ALTER TABLE series ADD CONSTRAINT FK_series_id_livre FOREIGN KEY (id_livre) REFERENCES livres(id);