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
tel VARCHAR(15),
id_role INT DEFAULT 1);

DROP TABLE IF EXISTS emprunts;
CREATE TABLE emprunts (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
dateSortie TIMESTAMP,
dateRetour TIMESTAMP NULL,
id_user INT,
id_exemplaire INT(5) ZEROFILL);

DROP TABLE IF EXISTS commentaires;
CREATE TABLE commentaires (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
contenu TEXT NOT NULL,
dateCom DATETIME,
id_user INT,
ISBN_livre VARCHAR(17));

DROP TABLE IF EXISTS livres;
CREATE TABLE livres (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
ISBN VARCHAR(17) UNIQUE NOT NULL,
titre VARCHAR(255) NOT NULL,
resume TEXT NOT NULL,
datePubli DATE NOT NULL,
nbPages INT NOT NULL,
couverture VARCHAR(255) NULL,
id_editeur INT NOT NULL);

DROP TABLE IF EXISTS exemplaires;
CREATE TABLE exemplaires (id INT(5) ZEROFILL PRIMARY KEY AUTO_INCREMENT NOT NULL,
ISBN_livre VARCHAR(17) NOT NULL);

DROP TABLE IF EXISTS auteurs;
CREATE TABLE auteurs (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nom VARCHAR(100) NOT NULL,
prenom VARCHAR(100) NOT NULL,
bio TEXT NOT NULL);

DROP TABLE IF EXISTS series;
CREATE TABLE series (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nomSerie VARCHAR(150) NOT NULL);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
theme VARCHAR(100) NOT NULL);

DROP TABLE IF EXISTS editeurs;
CREATE TABLE editeurs (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nomSocial VARCHAR(150) NOT NULL);

DROP TABLE IF EXISTS livres_auteurs ;
CREATE TABLE livres_auteurs (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
ISBN_livre VARCHAR(17),
id_auteur INT);

DROP TABLE IF EXISTS livres_genres ;
CREATE TABLE livres_genres (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
ISBN_livre VARCHAR(17),
id_genre INT);

DROP TABLE IF EXISTS livres_series ;
CREATE TABLE livres_series (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
ISBN_livre VARCHAR(17),
id_serie INT,
position INT NOT NULL);

DROP TABLE IF EXISTS roles ;
CREATE TABLE roles (id INT PRIMARY KEY NOT NULL,
poste VARCHAR(20));

ALTER TABLE emprunts ADD CONSTRAINT FK_emprunts_id_user FOREIGN KEY (id_user) REFERENCES users(id);
ALTER TABLE emprunts ADD CONSTRAINT FK_emprunts_id_exemplaire FOREIGN KEY (id_exemplaire) REFERENCES exemplaires(id);
ALTER TABLE commentaires ADD CONSTRAINT FK_commentaires_id_user FOREIGN KEY (id_user) REFERENCES users(id);
ALTER TABLE commentaires ADD CONSTRAINT FK_commentaires_ISBN_livre FOREIGN KEY (ISBN_livre) REFERENCES livres(ISBN);
ALTER TABLE livres ADD CONSTRAINT FK_livres_id_editeur FOREIGN KEY (id_editeur) REFERENCES editeurs(id);
ALTER TABLE livres_auteurs ADD CONSTRAINT FK_livres_auteurs_ISBN_livre FOREIGN KEY (ISBN_livre) REFERENCES livres(ISBN);
ALTER TABLE livres_auteurs ADD CONSTRAINT FK_livres_auteurs_id_auteur FOREIGN KEY (id_auteur) REFERENCES auteurs(id);
ALTER TABLE livres_genres ADD CONSTRAINT FK_livres_genres_ISBN_livre FOREIGN KEY (ISBN_livre) REFERENCES livres(ISBN);
ALTER TABLE livres_genres ADD CONSTRAINT FK_livres_genres_id_genre FOREIGN KEY (id_genre) REFERENCES genres(id);
ALTER TABLE livres_series ADD CONSTRAINT FK_livres_series_ISBN_livre FOREIGN KEY (ISBN_livre) REFERENCES livres(ISBN);
ALTER TABLE livres_series ADD CONSTRAINT FK_livres_series_id_serie FOREIGN KEY (id_serie) REFERENCES series(id);
ALTER TABLE exemplaires ADD CONSTRAINT FK_exemplaires_ISBN_livre FOREIGN KEY (ISBN_livre) REFERENCES livres(ISBN);
ALTER TABLE users ADD CONSTRAINT FK_users_id_role FOREIGN KEY (id_role) REFERENCES roles(id);

INSERT INTO auteurs (nom, prenom, bio) VALUES ("Rowling", "J.K.", "Born in 1965. J.K. Rowling is a British author and screenwriter best known for her seven-book Harry Potter children's book series. The series has sold more than 500 million copies and was adapted into a blockbuster film franchise."), ("Tolkien", "J.R.R.", "Born in 1892 - Died in 1973. J.R.R. Tolkien was an English fantasy author and academic. Tolkien settled in England as a child, going on to study at Exeter College. While teaching at Oxford University, he published the popular fantasy novels The Hobbit and The Lord of the Rings trilogy. The works have had a devoted international fan base and been adapted into award-winning blockbuster films."), ("Lewis", "C.S.", "Born in 1898 - Died in 1963. Writer and scholar C.S. Lewis taught at Oxford University and became a renowned Christian apologist writer, using logic and philosophy to support the tenets of his faith. He is also known throughout the world as the author of The Chronicles of Narnia fantasy series, which have been adapted into various films for the big and small screens.");

INSERT INTO editeurs (nomSocial) VALUES ("Michel Lafon"), ("Hachette"), ("Gallimard");

INSERT INTO genres (theme) VALUES ("Fantastique"), ("Horreur"), ("Science-Fiction"), ("Policier"), ("Romance");

DELIMITER //
CREATE OR REPLACE TRIGGER autoCouverture
	BEFORE INSERT ON livres
	FOR EACH ROW
	BEGIN
        IF NEW.couverture IS NULL THEN
		    SET NEW.couverture = CONCAT(NEW.ISBN, ".jpg");
        END IF;
	END //
DELIMITER ;

INSERT INTO livres (ISBN, titre, resume, datePubli, nbPages, id_editeur) VALUES 
("722664568-8", "Harry Potter à l'École des Sorciers", "L'intrigue du premier roman débute durant l'été 1991. Peu avant son onzième anniversaire, Harry reçoit une lettre l'invitant à se présenter lors de la rentrée des classes à l'école de sorcellerie de Poudlard. Malgré les tentatives de son oncle et de sa tante pour l'empêcher de s'y rendre, Rubeus Hagrid, un « demi-géant » envoyé par le directeur de Poudlard, Albus Dumbledore, va faire découvrir à Harry le monde des sorciers et l'amener à se rendre à la gare de King's Cross de Londres, où il prendra le Poudlard Express qui le conduira jusqu'à sa nouvelle école. Une fois à Poudlard, Harry apprend à maîtriser et utiliser les pouvoirs magiques qu'il possède et se fait deux amis inséparables : Ronald Weasley et Hermione Granger. Le trio tente d'empêcher Voldemort de s'emparer de la pierre philosophale de Nicolas Flamel, conservée sous bonne garde à Poudlard.", "1998-10-09", 305, 3), 
("015335736-3", "Harry Potter et la Chambre des Secrets", "L'année suivante, Harry et ses amis doivent faire face à une nouvelle menace à Poudlard. La fameuse Chambre des secrets, bâtie plusieurs siècles plus tôt par l'un des fondateurs de l'école, Salazar Serpentard, aurait été rouverte par son « héritier ». Cette Chambre, selon la légende, contiendrait un gigantesque monstre destiné à tuer les enfants sorciers nés de parents moldus acceptés à l'école contre le souhait de Serpentard. Hermione, née de parents moldus, se retrouve elle aussi menacée. Harry, sachant parler le fourchelang, est accusé en premier lieu d'être l'héritier de Serpentard par la plupart des élèves, tandis que Ginny Weasley, la sœur de Ron, est curieusement manipulée par un journal intime ayant appartenu à un certain Tom Jedusor. Harry apprend par la suite que Jedusor et Voldemort sont une seule et même personne, et que Jedusor est le véritable héritier de Serpentard, agissant sur l'école par le biais de ses souvenirs conservés dans son journal.", "1999-03-23", 364, 3), 
("382625757-X", "Harry Potter et le Prisonnier d'Azkaban", "À l'été 1993, les sorciers, ainsi que les Moldus, sont informés de l'évasion de prison d'un dangereux criminel nommé Sirius Black. Un peu plus tard, Harry apprend que la motivation de Black est de le tuer afin de permettre à Voldemort, son maître, de retrouver l'étendue de son pouvoir. Un important dispositif de sécurité est donc mis en place à Poudlard pour assurer la protection de Harry durant l'année. En parallèle, celui-ci fait la connaissance de son nouveau professeur de défense contre les forces du mal, le professeur Lupin, un ancien ami de ses parents et dont il devient très proche. Harry utilise régulièrement la cape d'invisibilité de son père ainsi que la carte du Maraudeur pour explorer les recoins méconnus du château et se rendre au village voisin de Pré-au-Lard, malgré son interdiction de quitter l'école. En fin d'année, Sirius Black parvient à attirer Harry, Ron et Hermione à l'extérieur de l'école et, en présence de Lupin qui vient les retrouver, leur explique les réelles motivations de son évasion : retrouver et tuer Peter Pettigrow, un sorcier qui se cache depuis douze ans sous l'apparence du rat de compagnie de Ron. Selon Black, Pettigrow serait le responsable de la trahison de James et Lily Potter. Avant de mourir, ceux-ci avaient fait de Sirius Black leur témoin de mariage et le parrain de leur fils, Harry.", "1999-10-19", 474, 3), 
("916051335-7", "Harry Potter et la Coupe de Feu", "Dans l'intrigue du quatrième roman, une édition du célèbre tournoi des Trois Sorciers se déroule exceptionnellement à Poudlard et deux autres délégations européennes se rendent sur place pour participer à la compétition : des élèves de l'Académie de magie Beauxbâtons et ceux de l'Institut Durmstrang. La Coupe de feu, juge impartiale chargée de sélectionner le champion de chaque école, choisit exceptionnellement deux champions pour Poudlard : Cedric Diggory et Harry Potter, ce dernier n'ayant pourtant pas l'âge requis pour participer à la compétition. Mais le règlement est strict et stipule que les organisateurs doivent obéir au choix de la Coupe de feu. Par conséquent, Harry se voit contraint de participer au tournoi, qui se déroule sur trois épreuves réparties sur l’année. La première consiste à récupérer un œuf d'or protégé par un dragon, la seconde à récupérer une personne aimée au fond du lac de Poudlard et la dernière, à progresser dans un labyrinthe à obstacles pour atteindre le trophée de la victoire dissimulé à l'intérieur. Alors que Harry et Cedric saisissent le trophée en même temps, ils sont téléportés auprès de Peter Pettigrow. Après avoir tué Cedric Diggory, Pettigrow utilise le sang de Harry pour faire renaître Voldemort et ôter au garçon sa protection naturelle l'ayant immunisé jusqu'alors contre les pouvoirs du mage noir. Harry affronte Voldemort qui a repris forme humaine, mais parvient à lui échapper en attrapant une nouvelle fois le trophée qui le ramène à Poudlard. Convaincu par le récit de Harry, Dumbledore décide de reformer une ancienne organisation qui avait pris fin à la première chute de Voldemort, quinze ans plus tôt. Il fait alors appel à ses anciens membres, notamment Sirius Black, Remus Lupin, Severus Rogue, le professeur McGonagall et la famille Weasley.", "2000-11-29", 656, 3), 
("660392002-6", "Harry Potter et l'Ordre du Phoenix", "Au début de ce cinquième roman, Harry retrouve son parrain Sirius, Lupin, Hermione et la famille Weasley au 12 square Grimmaurd, qui devient le quartier général de l'ordre du Phénix, l'organisation fondée par Dumbledore au moment de la première ascension de Voldemort. Le ministère de la Magie de son côté, malgré les événements de l’an passé, refuse d'admettre le retour du mage noir. Harry, Ron et Hermione retournent à Poudlard, où un nouveau professeur de défense contre les forces du mal, Dolores Ombrage, engagée par le ministre de la Magie lui-même, ne tarde pas à instaurer des règles très strictes sur l'école, interdisant aux élèves de pratiquer la magie, de se rassembler en groupe ou de lire certains articles de presse défendant le point de vue de Harry Potter sur le retour de Voldemort. Hermione décide d'agir et de fonder une seconde organisation au sein-même de l'école, l'Armée de Dumbledore, pour contrer Ombrage et inciter les élèves volontaires à pratiquer la magie pour apprendre à se défendre face aux dangers extérieurs que les autorités souhaitent taire. À la fin de l'année, piégé par Voldemort, Harry se rend au ministère de la Magie où il pense que son parrain est détenu et torturé. Il est accompagné de Ron, Hermione, Ginny Weasley, Luna Lovegood et Neville Londubat. Severus Rogue prévient les autres membres de l’Ordre, qui se précipitent au secours de Harry et de ses amis aux prises avec les mangemorts. Sirius Black meurt durant la bataille, tué par sa propre cousine et bras droit de Voldemort. De retour à Poudlard, Harry apprend le contenu de la prophétie qui le concernait depuis sa naissance : il est la seule personne à avoir une chance de vaincre définitivement Voldemort. Après les événements du ministère dont il a été témoin, le ministre Cornelius Fudge admet enfin le retour de Voldemort et la deuxième guerre débute officiellement.", "2003-12-03", 984, 3), 
("628715773-9", "La Communauté de l'Anneau", "Le Tiers Age touche à sa fin, et la Terre du Milieu à son crépuscule. La Compagnie de l'Anneau va donc tâcher de déjouer les projets infernaux de Sauron, force du mal d'autant plus difficile à combattre qu'elle est désincarnée. Qui, des cinq héros, mènera à bien la mission ? Gandalf, grand sage qui seul mesure la portée de la quête ? Aragorn, qui dissimule ses origines princières sous les traits d'un rôdeur taciturne ? Frodon et Sam, qui sont chargés de la phase finale de la mission ou Gollum, créature abjecte qui fut un temps dépositaire de l'anneau ? Mais quel est donc le pouvoir de cet anneau tant convoité ? Quelle est donc la signification de l'énigme qui figure en première page, en manière d'épigraphe ? Tant de questions qui ne sont qu'une infime partie du mystère féerique qui, depuis 1954, ravit l'imagination, autorisée pour un temps à s'attarder dans un séjour magique aux forêts profondes et ancestrales, aux paysages argentés peuplés d'êtres aériens, de jeunes guerrières intrépides, de destriers sauvages ayant la fierté de leurs maîtres, de viles créatures dont la laideur physique reflète la méchanceté... Tous, nous avons rêvé de ce repos de l'âme, de même que nous avons craint, enfants, la menace sourde et inexplicable. Tolkien, lui, a su nommer cet univers, et en faire une épopée passionnante, quête initiatique où l'errance humaine est regardée avec une tolérance rassurante. Mais quels sont ces petits êtres rigolos et surprenants, ces hobbits ? D'où viennent ces accents folkloriques, ce langage essentiel, cette mélancolie onirique qui teinte l'esprit d'un brouillard étrange une fois le volume refermé ? Tant de questions auxquelles, heureusement, Tolkien ne répond jamais tout à fait malgré les cartes, généalogies, lexiques et autres appendices passionnants que son imagination prolifique a fournis sur La Terre du Milieu.", "2005-03-03", 704, 2), 
("998528293-0", "Les Deux Tours", "Frodon le Hobbit et ses Compagnons se sont engagés, au Grand Conseil d'Elrond, à détruire l'Anneau de Puissance dont Sauron de Mordor cherche à s'emparer pour asservir tous les peuples de la terre habitée : Elfes et Nains, Hommes et Hobbits.", "2005-03-03", 576, 2), 
("057453797-X", "Le Retour du Roi", "Avec Le Retour du Roi s'achèvent dans un fracas d'apocalypse les derniers combats de la guerre de l'Anneau. Tandis que le continent se couvre de ténèbres, annonçant pour le peuple des Hobbits l'aube d'une ère nouvelle, Frodon poursuit son entreprise. Alors qu'il n'a pu franchir la Porte Noire, il se demande comment atteindre le Mont du Destin. Peut-être est-il trop tard : le Seigneur des Ténèbres mobilise ses troupes. Les Rohirrim n'ont plus le temps d'en finir avec le traître assiégé dans l'imprenable tour d'Orthanc ; ils doivent se rassembler pour faire face à l'ennemi. Tentant une fois de plus sa chance, Frodon passe par le Haut Col, où il sera livré à l'abominable Arachné. Survivra-t-il à son dangereux périple à travers le Pays Noir ?", "2005-03-03", 576, 2), 
("219254832-X", "Le Lion, la Sorcière blanche et l'Armoire magique", "Narnia... Un royaume merveilleux condamné à un hiver éternel, un pays qui attend d'être libéré d'une emprise maléfique. L'arrivée extraordinnaire de quatre enfants fait renaître l'espoir. S'ils trouvent Aslan, le Grand Lion, les pouvoirs de la Sorcière Blanche pourraient enfin être anéantis... Quatre enfants, Peter, Edmund, Susan et Lucy, éloignés de Londres à la suite des raids aériens, pendant la Seconde guerre mondiale, trouvent refuge chez un vieux professeur quelque peu excentrique. Au cours d'une partie de cache-cache, Lucie pénètre dans une armoire. Elle s'enfonce au milieu des vêtements qui, insensiblement, deviennent les arbres d'une forêt. C'est ainsi qu'elle découvre, pou la première fois, le monde magique et merveilleux de Narnia, où les animaux parler et sur lequel règne la terrible Sorcière blanche. Lucie entraîne les autres enfants, d'abord incrédules, dans ce royaume. Ils y rencontrent le lion Aslan, si beau, si sage, si brave, qui doit chasser la Sorcière Blanche. Aslan meurt pour sauver Edmond des griffes de la sorcière. Mais il renaît grâce à un très ancien sortilège... Devenus, après bien des aventures, rois et reines de Narnia, les enfants vivent de longues années dans ce royaume enchanté. Pourtant, lorsqu'ils repassent la porte de l'armoire, personne ne semble avoir remarqué leur absence... Clive Staples Lewis est né en 1898 à Belfast, en Irlande du Nord, et mort en 1963. II fut professeur à l'Université d'Oxford. Théologien chrétien, spécialiste de la culture médiévale, des mythes nordiques et des légendes classiques, C.S. Lewis utilise l'allégorie comme l'un des fondements de sa narration. Entre 1950 et 1956, il a publié les sept volumes constituant les Chroniques de Narnia. L'armoire magique fut le premier écrit mais est le deuxième, chronologiquement, de la série. C'est le titre le plus célèbre (en anglais The Lion, the Witch and the Wardrobe)", "1952-01-23", 189, 1), 
("771915247-6", "Le Prince Caspian", "Peter, Susan, Edmund et Lucy sont sur le point de se séparer pour entamer une nouvelle année scolaire. Ils attendent le train qui doit les conduire en pension quand, tout à coup, ils sont transportés dans le pays de Narnia où ils ont régné autrefois. Mais si, pour eux, une année seulement s'est écoulée, dans leur ancien royaume des siècles ont passé. Le palais royal est en ruines. Parviendront-ils à ramener la paix dans le monde magique de Narnia ? Le monde enchanté de Narnia, le pays de l'imaginaire, vous attend.", "1953-04-17", 187, 1);

INSERT INTO exemplaires (ISBN_livre) VALUES ("722664568-8"), ("015335736-3"), ("382625757-X"), ("916051335-7"), ("660392002-6"), ("722664568-8"), ("722664568-8"), ("015335736-3"), ("382625757-X"), ("660392002-6"), ("382625757-X"), ("628715773-9"), ("998528293-0"), ("057453797-X"), ("219254832-X"), ("219254832-X"), ("771915247-6"), ("219254832-X"), ("219254832-X"), ("771915247-6");

INSERT INTO series (nomSerie) VALUES ("Harry Potter"), ("Les Chroniques de Narnia"), ("Le Seigneur des Anneaux");

INSERT INTO livres_series (ISBN_livre, id_serie, position) VALUES ('722664568-8', 1, 1), ('015335736-3', 1, 2), ('382625757-X', 1, 3), ('916051335-7', 1, 4), ('660392002-6', 1, 5), ('628715773-9', 3, 1), ('998528293-0', 3, 2), ('057453797-X', 3, 3), ('219254832-X', 2, 2), ('771915247-6', 2, 4);

INSERT INTO livres_auteurs (ISBN_livre, id_auteur) VALUES ('722664568-8', 1), ('015335736-3', 1), ('382625757-X', 1), ('916051335-7', 1), ('660392002-6', 1), ('628715773-9', 2), ('998528293-0', 2), ('057453797-X', 2), ('219254832-X', 3), ('771915247-6', 3);

INSERT INTO livres_genres (ISBN_livre, id_genre) VALUES ('722664568-8', 1), ('015335736-3', 1), ('382625757-X', 1), ('382625757-X', 2), ('916051335-7', 1), ('660392002-6', 1), ('660392002-6', 2), ('660392002-6', 5), ('628715773-9', 1), ('628715773-9', 4), ('998528293-0', 1), ('057453797-X', 1), ('057453797-X', 4), ('057453797-X', 5), ('219254832-X', 3), ('219254832-X', 1), ('771915247-6', 1), ('771915247-6', 5);

INSERT INTO roles (id, poste) VALUES (1, "user"),  (943, "employe"), (183, "admin");

INSERT INTO users (nom, prenom, email, password, adresse, cp, ville, tel) VALUES
    ('Jaggers', 'Joyce', 'jjaggers0@techcrunch.com', PASSWORD('DNHHsno'), '37550 Hollow Ridge Terrace', '31029', 'Toulouse', '0165453412'),
    ('Stentiford', 'Tibold', 'tstentiford1@mail.ru', PASSWORD('wlmTCroZZlv'), '73 Gerald Avenue', '75020', 'Paris', '0609764853'),
    ('Hryniewicz', 'Quintus', 'qhryniewicz2@spiegel.de', PASSWORD('2NmGp8KcZG'), '7737 Havey Place', '13012', 'Marseille', '0298572645'),
    ('Neate', 'Jeramey', 'jneate3@domainmarket.com', PASSWORD('i9fd1OVdq6gG'), '73 Utah Hill', '77000', 'Melun', '0187451309'),
    ('Kalf', 'Violette', 'vkalf4@nature.com', PASSWORD('gZb9L42iHIY'), '5349 Jana Park', '64000', 'Hendaye', '0364897510')
;
INSERT INTO users (nom, prenom, email, password, adresse, cp, ville, tel, id_role) VALUES
    ('Simpson', 'Homer', 'hsimpson@bibli.fr', PASSWORD('test'), '111 Random Street', '75012', 'Paris', '0612345678', 943)
;
INSERT INTO users (nom, prenom, email, password, adresse, cp, ville, tel, id_role) VALUES
    ('TestN', 'TestP', 'test@test.fr', PASSWORD('test'), '111 Random Street', '75012', 'Paris', '0612345678', 183)
;

INSERT INTO emprunts (dateSortie, dateRetour, id_user, id_exemplaire) VALUES
    ('2022/3/26', null, 3, 1),
    ('2022/7/9', '2022/7/10', 2, 2),
    ('2022/7/1', '2022/11/12', 3, 3),
    ('2022/10/17', '2022/10/19', 4, 4),
    ('2022/7/26', '2022/8/1', 3, 5),
    ('2022/2/9', '2022/2/28', 2, 6),
    ('2022/4/30', '2022/4/10', 5, 6),
    ('2022/1/26', '2022/2/15', 5, 7),
    ('2022/2/24', '2022/3/10', 3, 8),
    ('2022/5/21', '2022/5/26', 1, 10),
    ('2022/5/27', null, 1, 14)
;

INSERT INTO commentaires (contenu, dateCom, id_user, ISBN_livre) VALUES
    ('Très bon livre', '2022/2/13', 3, '722664568-8'),
    ('Très bon livre', '2022/7/31', 2, '628715773-9'),
    ('Très bon livre', '2022/5/7', 5, '628715773-9'),
    ('Très bon livre', '2022/1/8', 1, '771915247-6'),
    ('Très bon livre', '2022/2/20', 5, '998528293-0')
;

CREATE VIEW catalogue_complet AS
SELECT l.id idL, l.ISBN ISBN_livreL, ex.id idEx, l.titre titreL, l.resume resumeL, l.datePubli datePubliL, l.nbPages nbPagesL, l.couverture couvertureL, a.nom nomA, a.prenom prenomA, a.bio bioA, e.nomSocial nomSocialE, g.theme themeG, s.nomSerie nomSerieS, ls.position tomeLS
FROM livres l 
JOIN livres_auteurs la ON l.ISBN = la.ISBN_livre 
JOIN auteurs a ON la.id_auteur = a.id 
JOIN editeurs e ON l.id_editeur = e.id 
JOIN livres_genres lg ON l.ISBN = lg.ISBN_livre 
JOIN genres g ON lg.id_genre = g.id 
JOIN livres_series ls ON l.ISBN = ls.ISBN_livre 
JOIN series s ON ls.id_serie = s.id
JOIN exemplaires ex ON ex.ISBN_livre = l.ISBN
ORDER BY `idL` DESC;

DROP VIEW IF EXISTS livres_dispos;
CREATE VIEW IF NOT EXISTS livres_dispos AS
SELECT l.id idL, l.ISBN ISBN_livreL, ex.id idEx, l.titre titreL, l.resume resumeL, l.datePubli datePubliL, l.nbPages nbPagesL, l.couverture couvertureL, a.nom nomA, a.prenom prenomA, a.bio bioA, ed.nomSocial nomSocialE, g.theme themeG, s.nomSerie nomSerieS, ls.position tomeLS
FROM livres l 
JOIN livres_auteurs la ON l.ISBN = la.ISBN_livre 
JOIN auteurs a ON la.id_auteur = a.id 
JOIN editeurs ed ON l.id_editeur = ed.id 
JOIN livres_genres lg ON l.ISBN = lg.ISBN_livre 
JOIN genres g ON lg.id_genre = g.id 
JOIN livres_series ls ON l.ISBN = ls.ISBN_livre 
JOIN series s ON ls.id_serie = s.id
JOIN exemplaires ex ON ex.ISBN_livre = l.ISBN
LEFT JOIN emprunts em ON ex.id = em.id_exemplaire
WHERE em.dateRetour IS NOT NULL OR ex.id NOT IN (SELECT id_exemplaire FROM emprunts)  
ORDER BY `idL` DESC;