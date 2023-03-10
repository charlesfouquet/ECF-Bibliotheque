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
dateSortie TIMESTAMP NULL,
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
("722664568-8", "Harry Potter ?? l'??cole des Sorciers", "L'intrigue du premier roman d??bute durant l'??t?? 1991. Peu avant son onzi??me anniversaire, Harry re??oit une lettre l'invitant ?? se pr??senter lors de la rentr??e des classes ?? l'??cole de sorcellerie de Poudlard. Malgr?? les tentatives de son oncle et de sa tante pour l'emp??cher de s'y rendre, Rubeus Hagrid, un ?? demi-g??ant ?? envoy?? par le directeur de Poudlard, Albus Dumbledore, va faire d??couvrir ?? Harry le monde des sorciers et l'amener ?? se rendre ?? la gare de King's Cross de Londres, o?? il prendra le Poudlard Express qui le conduira jusqu'?? sa nouvelle ??cole. Une fois ?? Poudlard, Harry apprend ?? ma??triser et utiliser les pouvoirs magiques qu'il poss??de et se fait deux amis ins??parables : Ronald Weasley et Hermione Granger. Le trio tente d'emp??cher Voldemort de s'emparer de la pierre philosophale de Nicolas Flamel, conserv??e sous bonne garde ?? Poudlard.", "1998-10-09", 305, 3), 
("015335736-3", "Harry Potter et la Chambre des Secrets", "L'ann??e suivante, Harry et ses amis doivent faire face ?? une nouvelle menace ?? Poudlard. La fameuse Chambre des secrets, b??tie plusieurs si??cles plus t??t par l'un des fondateurs de l'??cole, Salazar Serpentard, aurait ??t?? rouverte par son ?? h??ritier ??. Cette Chambre, selon la l??gende, contiendrait un gigantesque monstre destin?? ?? tuer les enfants sorciers n??s de parents moldus accept??s ?? l'??cole contre le souhait de Serpentard. Hermione, n??e de parents moldus, se retrouve elle aussi menac??e. Harry, sachant parler le fourchelang, est accus?? en premier lieu d'??tre l'h??ritier de Serpentard par la plupart des ??l??ves, tandis que Ginny Weasley, la s??ur de Ron, est curieusement manipul??e par un journal intime ayant appartenu ?? un certain Tom Jedusor. Harry apprend par la suite que Jedusor et Voldemort sont une seule et m??me personne, et que Jedusor est le v??ritable h??ritier de Serpentard, agissant sur l'??cole par le biais de ses souvenirs conserv??s dans son journal.", "1999-03-23", 364, 3), 
("382625757-X", "Harry Potter et le Prisonnier d'Azkaban", "?? l'??t?? 1993, les sorciers, ainsi que les Moldus, sont inform??s de l'??vasion de prison d'un dangereux criminel nomm?? Sirius Black. Un peu plus tard, Harry apprend que la motivation de Black est de le tuer afin de permettre ?? Voldemort, son ma??tre, de retrouver l'??tendue de son pouvoir. Un important dispositif de s??curit?? est donc mis en place ?? Poudlard pour assurer la protection de Harry durant l'ann??e. En parall??le, celui-ci fait la connaissance de son nouveau professeur de d??fense contre les forces du mal, le professeur Lupin, un ancien ami de ses parents et dont il devient tr??s proche. Harry utilise r??guli??rement la cape d'invisibilit?? de son p??re ainsi que la carte du Maraudeur pour explorer les recoins m??connus du ch??teau et se rendre au village voisin de Pr??-au-Lard, malgr?? son interdiction de quitter l'??cole. En fin d'ann??e, Sirius Black parvient ?? attirer Harry, Ron et Hermione ?? l'ext??rieur de l'??cole et, en pr??sence de Lupin qui vient les retrouver, leur explique les r??elles motivations de son ??vasion : retrouver et tuer Peter Pettigrow, un sorcier qui se cache depuis douze ans sous l'apparence du rat de compagnie de Ron. Selon Black, Pettigrow serait le responsable de la trahison de James et Lily Potter. Avant de mourir, ceux-ci avaient fait de Sirius Black leur t??moin de mariage et le parrain de leur fils, Harry.", "1999-10-19", 474, 3), 
("916051335-7", "Harry Potter et la Coupe de Feu", "Dans l'intrigue du quatri??me roman, une ??dition du c??l??bre tournoi des Trois Sorciers se d??roule exceptionnellement ?? Poudlard et deux autres d??l??gations europ??ennes se rendent sur place pour participer ?? la comp??tition : des ??l??ves de l'Acad??mie de magie Beauxb??tons et ceux de l'Institut Durmstrang. La Coupe de feu, juge impartiale charg??e de s??lectionner le champion de chaque ??cole, choisit exceptionnellement deux champions pour Poudlard : Cedric Diggory et Harry Potter, ce dernier n'ayant pourtant pas l'??ge requis pour participer ?? la comp??tition. Mais le r??glement est strict et stipule que les organisateurs doivent ob??ir au choix de la Coupe de feu. Par cons??quent, Harry se voit contraint de participer au tournoi, qui se d??roule sur trois ??preuves r??parties sur l???ann??e. La premi??re consiste ?? r??cup??rer un ??uf d'or prot??g?? par un dragon, la seconde ?? r??cup??rer une personne aim??e au fond du lac de Poudlard et la derni??re, ?? progresser dans un labyrinthe ?? obstacles pour atteindre le troph??e de la victoire dissimul?? ?? l'int??rieur. Alors que Harry et Cedric saisissent le troph??e en m??me temps, ils sont t??l??port??s aupr??s de Peter Pettigrow. Apr??s avoir tu?? Cedric Diggory, Pettigrow utilise le sang de Harry pour faire rena??tre Voldemort et ??ter au gar??on sa protection naturelle l'ayant immunis?? jusqu'alors contre les pouvoirs du mage noir. Harry affronte Voldemort qui a repris forme humaine, mais parvient ?? lui ??chapper en attrapant une nouvelle fois le troph??e qui le ram??ne ?? Poudlard. Convaincu par le r??cit de Harry, Dumbledore d??cide de reformer une ancienne organisation qui avait pris fin ?? la premi??re chute de Voldemort, quinze ans plus t??t. Il fait alors appel ?? ses anciens membres, notamment Sirius Black, Remus Lupin, Severus Rogue, le professeur McGonagall et la famille Weasley.", "2000-11-29", 656, 3), 
("660392002-6", "Harry Potter et l'Ordre du Phoenix", "Au d??but de ce cinqui??me roman, Harry retrouve son parrain Sirius, Lupin, Hermione et la famille Weasley au 12 square Grimmaurd, qui devient le quartier g??n??ral de l'ordre du Ph??nix, l'organisation fond??e par Dumbledore au moment de la premi??re ascension de Voldemort. Le minist??re de la Magie de son c??t??, malgr?? les ??v??nements de l???an pass??, refuse d'admettre le retour du mage noir. Harry, Ron et Hermione retournent ?? Poudlard, o?? un nouveau professeur de d??fense contre les forces du mal, Dolores Ombrage, engag??e par le ministre de la Magie lui-m??me, ne tarde pas ?? instaurer des r??gles tr??s strictes sur l'??cole, interdisant aux ??l??ves de pratiquer la magie, de se rassembler en groupe ou de lire certains articles de presse d??fendant le point de vue de Harry Potter sur le retour de Voldemort. Hermione d??cide d'agir et de fonder une seconde organisation au sein-m??me de l'??cole, l'Arm??e de Dumbledore, pour contrer Ombrage et inciter les ??l??ves volontaires ?? pratiquer la magie pour apprendre ?? se d??fendre face aux dangers ext??rieurs que les autorit??s souhaitent taire. ?? la fin de l'ann??e, pi??g?? par Voldemort, Harry se rend au minist??re de la Magie o?? il pense que son parrain est d??tenu et tortur??. Il est accompagn?? de Ron, Hermione, Ginny Weasley, Luna Lovegood et Neville Londubat. Severus Rogue pr??vient les autres membres de l???Ordre, qui se pr??cipitent au secours de Harry et de ses amis aux prises avec les mangemorts. Sirius Black meurt durant la bataille, tu?? par sa propre cousine et bras droit de Voldemort. De retour ?? Poudlard, Harry apprend le contenu de la proph??tie qui le concernait depuis sa naissance : il est la seule personne ?? avoir une chance de vaincre d??finitivement Voldemort. Apr??s les ??v??nements du minist??re dont il a ??t?? t??moin, le ministre Cornelius Fudge admet enfin le retour de Voldemort et la deuxi??me guerre d??bute officiellement.", "2003-12-03", 984, 3), 
("628715773-9", "La Communaut?? de l'Anneau", "Le Tiers Age touche ?? sa fin, et la Terre du Milieu ?? son cr??puscule. La Compagnie de l'Anneau va donc t??cher de d??jouer les projets infernaux de Sauron, force du mal d'autant plus difficile ?? combattre qu'elle est d??sincarn??e. Qui, des cinq h??ros, m??nera ?? bien la mission ? Gandalf, grand sage qui seul mesure la port??e de la qu??te ? Aragorn, qui dissimule ses origines princi??res sous les traits d'un r??deur taciturne ? Frodon et Sam, qui sont charg??s de la phase finale de la mission ou Gollum, cr??ature abjecte qui fut un temps d??positaire de l'anneau ? Mais quel est donc le pouvoir de cet anneau tant convoit?? ? Quelle est donc la signification de l'??nigme qui figure en premi??re page, en mani??re d'??pigraphe ? Tant de questions qui ne sont qu'une infime partie du myst??re f??erique qui, depuis 1954, ravit l'imagination, autoris??e pour un temps ?? s'attarder dans un s??jour magique aux for??ts profondes et ancestrales, aux paysages argent??s peupl??s d'??tres a??riens, de jeunes guerri??res intr??pides, de destriers sauvages ayant la fiert?? de leurs ma??tres, de viles cr??atures dont la laideur physique refl??te la m??chancet??... Tous, nous avons r??v?? de ce repos de l'??me, de m??me que nous avons craint, enfants, la menace sourde et inexplicable. Tolkien, lui, a su nommer cet univers, et en faire une ??pop??e passionnante, qu??te initiatique o?? l'errance humaine est regard??e avec une tol??rance rassurante. Mais quels sont ces petits ??tres rigolos et surprenants, ces hobbits ? D'o?? viennent ces accents folkloriques, ce langage essentiel, cette m??lancolie onirique qui teinte l'esprit d'un brouillard ??trange une fois le volume referm?? ? Tant de questions auxquelles, heureusement, Tolkien ne r??pond jamais tout ?? fait malgr?? les cartes, g??n??alogies, lexiques et autres appendices passionnants que son imagination prolifique a fournis sur La Terre du Milieu.", "2005-03-03", 704, 2), 
("998528293-0", "Les Deux Tours", "Frodon le Hobbit et ses Compagnons se sont engag??s, au Grand Conseil d'Elrond, ?? d??truire l'Anneau de Puissance dont Sauron de Mordor cherche ?? s'emparer pour asservir tous les peuples de la terre habit??e : Elfes et Nains, Hommes et Hobbits.", "2005-03-03", 576, 2), 
("057453797-X", "Le Retour du Roi", "Avec Le Retour du Roi s'ach??vent dans un fracas d'apocalypse les derniers combats de la guerre de l'Anneau. Tandis que le continent se couvre de t??n??bres, annon??ant pour le peuple des Hobbits l'aube d'une ??re nouvelle, Frodon poursuit son entreprise. Alors qu'il n'a pu franchir la Porte Noire, il se demande comment atteindre le Mont du Destin. Peut-??tre est-il trop tard : le Seigneur des T??n??bres mobilise ses troupes. Les Rohirrim n'ont plus le temps d'en finir avec le tra??tre assi??g?? dans l'imprenable tour d'Orthanc ; ils doivent se rassembler pour faire face ?? l'ennemi. Tentant une fois de plus sa chance, Frodon passe par le Haut Col, o?? il sera livr?? ?? l'abominable Arachn??. Survivra-t-il ?? son dangereux p??riple ?? travers le Pays Noir ?", "2005-03-03", 576, 2), 
("219254832-X", "Le Lion, la Sorci??re blanche et l'Armoire magique", "Narnia... Un royaume merveilleux condamn?? ?? un hiver ??ternel, un pays qui attend d'??tre lib??r?? d'une emprise mal??fique. L'arriv??e extraordinnaire de quatre enfants fait rena??tre l'espoir. S'ils trouvent Aslan, le Grand Lion, les pouvoirs de la Sorci??re Blanche pourraient enfin ??tre an??antis... Quatre enfants, Peter, Edmund, Susan et Lucy, ??loign??s de Londres ?? la suite des raids a??riens, pendant la Seconde guerre mondiale, trouvent refuge chez un vieux professeur quelque peu excentrique. Au cours d'une partie de cache-cache, Lucie p??n??tre dans une armoire. Elle s'enfonce au milieu des v??tements qui, insensiblement, deviennent les arbres d'une for??t. C'est ainsi qu'elle d??couvre, pou la premi??re fois, le monde magique et merveilleux de Narnia, o?? les animaux parler et sur lequel r??gne la terrible Sorci??re blanche. Lucie entra??ne les autres enfants, d'abord incr??dules, dans ce royaume. Ils y rencontrent le lion Aslan, si beau, si sage, si brave, qui doit chasser la Sorci??re Blanche. Aslan meurt pour sauver Edmond des griffes de la sorci??re. Mais il rena??t gr??ce ?? un tr??s ancien sortil??ge... Devenus, apr??s bien des aventures, rois et reines de Narnia, les enfants vivent de longues ann??es dans ce royaume enchant??. Pourtant, lorsqu'ils repassent la porte de l'armoire, personne ne semble avoir remarqu?? leur absence... Clive Staples Lewis est n?? en 1898 ?? Belfast, en Irlande du Nord, et mort en 1963. II fut professeur ?? l'Universit?? d'Oxford. Th??ologien chr??tien, sp??cialiste de la culture m??di??vale, des mythes nordiques et des l??gendes classiques, C.S. Lewis utilise l'all??gorie comme l'un des fondements de sa narration. Entre 1950 et 1956, il a publi?? les sept volumes constituant les Chroniques de Narnia. L'armoire magique fut le premier ??crit mais est le deuxi??me, chronologiquement, de la s??rie. C'est le titre le plus c??l??bre (en anglais The Lion, the Witch and the Wardrobe)", "1952-01-23", 189, 1), 
("771915247-6", "Le Prince Caspian", "Peter, Susan, Edmund et Lucy sont sur le point de se s??parer pour entamer une nouvelle ann??e scolaire. Ils attendent le train qui doit les conduire en pension quand, tout ?? coup, ils sont transport??s dans le pays de Narnia o?? ils ont r??gn?? autrefois. Mais si, pour eux, une ann??e seulement s'est ??coul??e, dans leur ancien royaume des si??cles ont pass??. Le palais royal est en ruines. Parviendront-ils ?? ramener la paix dans le monde magique de Narnia ? Le monde enchant?? de Narnia, le pays de l'imaginaire, vous attend.", "1953-04-17", 187, 1);

INSERT INTO exemplaires (ISBN_livre) VALUES ("722664568-8"), ("015335736-3"), ("382625757-X"), ("916051335-7"), ("660392002-6"), ("722664568-8"), ("722664568-8"), ("015335736-3"), ("382625757-X"), ("660392002-6"), ("382625757-X"), ("628715773-9"), ("998528293-0"), ("057453797-X"), ("219254832-X"), ("219254832-X"), ("771915247-6"), ("219254832-X"), ("219254832-X"), ("771915247-6");

INSERT INTO series (nomSerie) VALUES ("Harry Potter"), ("Les Chroniques de Narnia"), ("Le Seigneur des Anneaux");

INSERT INTO livres_series (ISBN_livre, id_serie, position) VALUES ('722664568-8', 1, 1), ('015335736-3', 1, 2), ('382625757-X', 1, 3), ('916051335-7', 1, 4), ('660392002-6', 1, 5), ('628715773-9', 3, 1), ('998528293-0', 3, 2), ('057453797-X', 3, 3), ('219254832-X', 2, 2), ('771915247-6', 2, 4);

INSERT INTO livres_auteurs (ISBN_livre, id_auteur) VALUES ('722664568-8', 1), ('015335736-3', 1), ('382625757-X', 1), ('916051335-7', 1), ('660392002-6', 1), ('628715773-9', 2), ('998528293-0', 2), ('057453797-X', 2), ('219254832-X', 3), ('771915247-6', 3);

INSERT INTO livres_genres (ISBN_livre, id_genre) VALUES ('722664568-8', 1), ('015335736-3', 1), ('382625757-X', 1), ('382625757-X', 2), ('916051335-7', 1), ('660392002-6', 1), ('660392002-6', 2), ('660392002-6', 5), ('628715773-9', 1), ('628715773-9', 4), ('998528293-0', 1), ('057453797-X', 1), ('057453797-X', 4), ('057453797-X', 5), ('219254832-X', 3), ('219254832-X', 1), ('771915247-6', 1), ('771915247-6', 5);

INSERT INTO roles (id, poste) VALUES (1, "user"),  (943, "employe"), (183, "admin");

INSERT INTO users (nom, prenom, email, password, adresse, cp, ville, tel) VALUES
    ('Jaggers', 'Joyce', 'jjaggers0@techcrunch.com', PASSWORD('DNHHsno'), '37550 Hollow Ridge Terrace', '31029', 'Toulouse', '0165453412'),
    ('Stentiford', 'Tibold', 'tstentiford1@mail.ru', PASSWORD('wlmTCroZZlv'), '73 Gerald Avenue', '75020', 'Paris', '0609764853'),
    ('Hryniewicz', 'Quintus', 'qqq@qqq.fr', PASSWORD('test'), '7737 Havey Place', '13012', 'Marseille', '0298572645'),
    ('Neate', 'Jeramey', 'jneate3@domainmarket.com', PASSWORD('i9fd1OVdq6gG'), '73 Utah Hill', '77000', 'Melun', '0187451309'),
    ('Kalf', 'Violette', 'vkalf4@nature.com', PASSWORD('gZb9L42iHIY'), '5349 Jana Park', '64000', 'Hendaye', '0364897510'),
    ('& Karim', 'Charles', 'aaa@aaa.fr', PASSWORD('aaaAAA111!'), null, null, null, null)
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
    ('Tr??s bon livre', '2022/2/13', 3, '722664568-8'),
    ('Tr??s bon livre', '2022/7/31', 2, '628715773-9'),
    ('Tr??s bon livre', '2022/5/7', 5, '628715773-9'),
    ('Tr??s bon livre', '2022/1/8', 1, '771915247-6'),
    ('Tr??s bon livre', '2022/2/20', 5, '998528293-0')
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