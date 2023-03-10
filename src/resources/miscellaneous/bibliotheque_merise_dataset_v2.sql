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
prenom VARCHAR(100) NOT NULL);

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

INSERT INTO auteurs (nom, prenom) VALUES ("Rowling", "J.K."),("Paolini", "Christopher"),("Berhoun", "Slimane-Baptiste"),("Servant", "St??phane"),("Lewis", "C.S."),("Lang", "John"),("Collins", "Suzanne"),("Fisher", "Catherine"),("L'Homme", "Erik"),("Combrexelle", "Anthony"),("Beigbeder", "Fr??d??ric"),("Sanderson", "Brandon"),("Weir", "Andy"),("Brierley", "Saroo"),("Moyes", "Jojo"),("Eljundir", "Myra"),("Bell", "Darcey"),("Atwood", "Margaret"),("Ingelman", "Catharine"),("Matthews", "Jason"),("Kwan", "Kevin"),("Jackson", "Shirley"),("Hill", "Joe"),("Franck", "Anne"),("Tolkien", "J.R.R.");

INSERT INTO editeurs (nomSocial) VALUES ("Folio Junior"),("Gallimard"),("Gallimard Jeunesse"),("Grasset"),("Bayard"),("Bragelonne"),("Rouergue"),("J'ai Lu"),("Pocket Jeunesse"),("404 Editions"),("Le Livre de Poche"),("City"),("Milady"),("Robert Laffont"),("Pocket"),("Pavillons Poche"),("Points"),("Albin Michel"),("Penguin Books");

INSERT INTO genres (theme) VALUES ("Fantastique"),("Conte"),("Drame"),("Science-Fiction"),("Dystopie"),("Aventure"),("Horreur"),("Essai"),("Romance"),("Humour"),("Thriller"),("Autobiographie");

DELIMITER //
CREATE OR REPLACE TRIGGER autoCouvertureBefore
	BEFORE INSERT ON livres
	FOR EACH ROW
	BEGIN
		SET NEW.couverture = CONCAT(NEW.ISBN, ".jpg");
	END //
DELIMITER ;
        -- IF NEW.couverture IS NULL THEN
        -- END IF;

INSERT INTO livres (ISBN, titre, resume, datePubli, nbPages, id_editeur) VALUES 
("018474911-5", "Harie Peauteur ?? l'Ecole", "J'ai pas trouv?? le r??sum??", "2001-11-16", 2, 13),
("177134993-X", "Harry Potter et la Chambre des Secrets", "Une rentr??e fracassante en voiture volante, une ??trange mal??diction qui s'abat sur les ??l??ves, cette deuxi??me ann??e ?? l'??cole des sorciers ne s'annonce pas de tout repos ! Entre les cours de potions magiques, les matches de Quidditch et les combats de mauvais sorts, Harry et ses amis Ron et Hermione trouveront-ils le temps de percer le myst??re de la Chambre des Secrets ?", "1999-03-23", 364, 1),
("708828225-9", "Harry Potter et le Prisonnier d'Azkaban", "Sirius Black, le dangereux criminel qui s'est ??chapp?? de la forteresse d'Azkaban, recherche Harry Potter. C'est donc sous bonne garde que l'apprenti sorcier fait sa troisi??me rentr??e. Au programme : des cours de divination, la fabrication d'une potion de Ratatinage, le dressage des hippogriffes... Mais Harry est-il vraiment ?? l'abri du danger qui le menace ?", "1999-10-19", 474, 1),
("706256141-X", "Harry Potter et la Coupe de Feu", "Harry Potter a quatorze ans et entre en quatri??me ann??e au coll??ge de Poudlard. Une grande nouvelle attend Harry, Ron et Hermione ?? leur arriv??e : la tenue d'un tournoi de magie exceptionnel entre les plus c??l??bres ??coles de sorcellerie. D??j?? les d??l??gations ??trang??res font leur entr??e. Harry se r??jouit... Trop vite. Il va se trouver plong?? au coeur des ??v??nements les plus dramatiques qu'il ait jamais eu ?? affronter.", "2000-11-29", 656, 1),
("679001812-3", "Harry Potter et l'Ordre du Phoenix", "Au d??but de ce cinqui??me roman, Harry retrouve son parrain Sirius, Lupin, Hermione et la famille Weasley au 12 square Grimmaurd, qui devient le quartier g??n??ral de l'ordre du Ph??nix, l'organisation fond??e par Dumbledore au moment de la premi??re ascension de Voldemort. Le minist??re de la Magie de son c??t??, malgr?? les ??v??nements de l'an pass??, refuse d'admettre le retour du mage noir. Harry, Ron et Hermione retournent ?? Poudlard, o?? un nouveau professeur de d??fense contre les forces du mal, Dolores Ombrage, engag??e par le ministre de la Magie lui-m??me, ne tarde pas ?? instaurer des r??gles tr??s strictes sur l'??cole, interdisant aux ??l??ves de pratiquer la magie, de se rassembler en groupe ou de lire certains articles de presse d??fendant le point de vue de Harry Potter sur le retour de Voldemort. Hermione d??cide d'agir et de fonder une seconde organisation au sein-m??me de l'??cole, l'Arm??e de Dumbledore, pour contrer Ombrage et inciter les ??l??ves volontaires ?? pratiquer la magie pour apprendre ?? se d??fendre face aux dangers ext??rieurs que les autorit??s souhaitent taire.", "2003-12-03", 984, 1),
("297153456-1", "Harry Potter et le Prince de Sang-M??l??", "Dans un monde de plus en plus inqui??tant, Harry se pr??pare ?? retrouver Ron et Hermione. Bient??t, ce sera la rentr??e ?? Poudlard, avec les autres ??tudiants de sixi??me ann??e. Mais pourquoi Dumbledore vient-il en personne chercher Harry chez les Dursley ? Dans quels extraordinaires voyages au coeur de la m??moire va-t-il l'entra??ner ?", "2005-10-01", 752, 1),
("101866960-4", "Harry Potter et les Reliques de la Mort", "Cette ann??e, Harry a dix-sept ans et ne retourne pas ?? Poudlard. Avec Ron et Hermione, il se consacre ?? la derni??re mission confi??e par Dumbledore. Mais le Seigneur des T??n??bres r??gne en ma??tre. Traqu??s, les trois fid??les amis sont contraints ?? la clandestinit??. D'??preuves en r??v??lations, le courage, les choix et les sacrifices de Harry seront d??terminants dans la lutte contre les forces du Mal.", "2007-10-26", 881, 1),
("504283795-7", "Harry Potter et l'Enfant Maudit", "Etre Harry Potter n'a jamais ??t?? facile et ne l'est pas davantage depuis qu'il travaille au coeur des secrets du minist??re de la Magie. Mari?? et p??re de trois enfants, Harry se d??bat avec un pass?? qui refuse de le laisser en paix, tandis que son fils Albus affronte le poids d'un h??ritage familial dont il n'a jamais voulu. Quand pass?? et pr??sent s'entrem??lent dangereusement, p??re et fils se retrouvent face ?? une dure v??rit?? : les t??n??bres surviennent parfois des endroits les plus inattendus. L'??dition d??finitive de la pi??ce. Avec de passionnants bonus in??dits !", "2018-01-04", 464, 1),
("518953001-9", "Les Animaux Fantastiques: le Texte du Film", "J. K. Rowling, cr??atrice de la mythique saga Harry Potter, nous invite ?? d??couvrir une nouvelle ??re du Monde des Sorciers, au c??t?? du jeune explorateur et magizoologiste Norbert Dragonneau, cinquante ans avant le d??but de l'histoire de Harry. Revivez le film ??crit par J. K. Rowling avec le texte original de l'auteur : l'int??grale des dialogues, mais aussi l'action, le jeu et les ??motions des personnages, les descriptions des d??cors... Une aventure ??pique et fabuleuse, une grande histoire de magie et d'amiti??.", "2019-01-03", 380, 2),
("274329958-4", "Les Animaux Fantastiques: Les Crimes de Grindelwald", "Paris, 1927. Quelques mois apr??s sa capture, le puissant mage noir Gellert Grindelwald s'est ??vad??. Il menace de faire r??gner les sorciers de sang pur sur les ??tres non-magiques. Devant ce danger, le jeune Albus Dumbledore, professeur de d??fense contre les forces du mal ?? Poudlard, fait appel ?? son ancien ??l??ve, Norbert Dragonneau.", "2022-04-07", 352, 2),
("289065341-2", "L'Ickabog", "La Cornucopia ??tait un petit royaume heureux. On n'y manquait de rien, le roi portait la plus ??l??gante des moustaches, et le pays ??tait c??l??bre pour ses mets d??licieux : D??lice-des-Ducs ou Nacelles-de-F??es, nul ne pouvait go??ter ses g??teaux divins sans pleurer de joie ! Mais dans tout le royaume, un monstre r??de : selon la l??gende, l'Ickabog habitait les Mar??cages brumeux et froids du nord du pays. On disait de cette cr??ature qu'elle avait de formidables pouvoirs et sortait la nuit pour d??vorer les moutons comme les enfants. Des histoires pour les petits et les na??fs ? Parfois, les mythes prennent vie de fa??on ??tonnante... Alors, si vous ??tes courageux et voulez conna??tre la v??rit??, ouvrez ce livre, suivez deux jeunes h??ros d??termin??s et perspicaces dans une folle aventure qui changera pour toujours le sort de la Cornucopia.", "2020-12-03", 392, 3),
("661122125-5", "Une Place ?? Prendre", "Bienvenue ?? Pagford, petite bourgade en apparence idyllique. Un notable meurt. Sa place est ?? prendre... Com??die de mours, trag??die teint??e d'humour noir, satire f??roce de nos hypocrisies sociales et intimes, ce premier roman pour adultes r??v??le sous un jour inattendu un ??crivain prodige.", "2012-09-28", 682, 4),
("483499503-8", "Eragon", "Eragon n'a que quinze ans, mais le destin de l'Empire eEragon n'a que quinze ans, mais le destin de l'Empire est d??sormais entre ses mains ! C'est en poursuivant une biche dans la montagne que le jeune Eragon, quinze ans, tombe sur une magnifique pierre bleue, qui s'av??re ??tre... un oeuf de dragon ! Fascin??, il l'emporte ?? Carvahall, le village o?? il vit pauvrement avec son oncle et son cousin. Il n'imagine pas alors qu'une dragonne, porteuse d'un h??ritage ancestral, va en ??clore... Tr??s vite, la vie d'Eragon est boulevers??e. Contraint de quitter les siens, le jeune homme s'engage dans une qu??te qui le m??nera aux confins de l'empire de l'Alaga??sia. Arm?? de son ??p??e et guid?? par les conseils de Brom, le vieux conteur, Eragon va devoir affronter avec sa dragonne les terribles ennemis envoy??s par le roi Galbatorix, dont la malveillance d??moniaque ne conna??t aucune limite.", "2019-09-18", 702, 5),
("743103681-7", "L'A??n??", "Une plong??e dans les t??n??bres : les certitudes s'??vanouissent et les forces du mal r??gnent. Eragon et sa dragonne, Saphira, sortent ?? peine de la victoire de Farthen D??r contre les Urgals, qu'une nouvelle horde de monstres surgit... Ajihad, le chef des Vardens, est tu??. Nomm??e par le Conseil des Anciens, Nasuada, la fille du vieux chef, prend la t??te des rebelles. Eragon et Saphira lui pr??tent all??geance avant d'entreprendre un long et p??rilleux voyage vers Ellesm??ra, le royaume des elfes, o?? ils doivent suivre leur formation. L??, ils d??couvrent avec stupeur qu'Arya est la fille de la reine Islanzad??. Cette derni??re leur pr??sente en secret un dragon d'or, Glaedr, chevauch?? par un Dragonnier, Oromis, qui n'est autre que le Sage-en-Deuil, l'Estropi??-qui-est-Tout, le personnage qui ??tait apparu ?? Eragon lorsqu'il d??lirait, bless?? par l'Ombre. Oromis va devenir leur ma??tre. Le jeune Dragonnier commence sa formation. Mais il n'est pas au bout de ses d??couvertes. Des r??v??lations d??rangeantes entament sa confiance. Pendant longtemps, Eragon ne saura qui croire. Or, le danger n'est toujours pas ??cart?? : ?? Carvahall, Roran, son cousin, a engag?? le combat contre les Ra'zacs. Ceux-ci, persuad??s qu'il d??tient l'oeuf qu'Eragon avait trouv?? sur la Cr??te, finissent par enlever sa fianc??e. Pr??t ?? tout pour la sauver, Roran comprend cependant qu'il n'est pas de taille ?? les affronter. Il convainc les villageois de traverser la Cr??te pour rejoindre les rebelles au Surda, en guerre contre le roi de l'Empire, le cruel Galbatorix.", "2019-09-18", 810, 5),
("354312486-2", "Brisingr", "Eragon a une double promesse ?? tenir : aider Roran ?? d??livrer sa fianc??e, Katrina , prisonni??re des Ra'zacs, et venger la mort de son oncle Garrow. Saphira emm??ne les deux cousins jusqu'?? Helgrind, repaire des monstres. Or, depuis que Murtagh lui a repris Zar'oc, l'??p??e que Brom lui avait donn??e, Eragon n'est plus arm?? que du b??ton du vieux conteur. Cependant, depuis la C??r??monie du Sang, le jeune Dragonnier ne cesse de se transformer, acqu??rant peu ?? peu les fabuleuses capacit??s d'un elfe. Et Roran m??rite plus que jamais son surnom de Puissant Marteau. Quant ?? Saphira, elle est une combattante redoutable. Ainsi commence cette troisi??me partie de l'H??ritage, o?? l'on verra l'intr??pide Nasuada, chef des Vardens, subir avec bravoure l'??preuve des Longs Couteaux ; les Vardens affronter les soldats d??moniaques de Galbatorix ; Arya et Eragon rivaliser de d??licates inventions magiques ; Murtagh chevauchant Thorn, son dragon rouge, batailler contre Eragon et Saphira. On s'enfoncera dans les galeries souterrainesdes nains ; on se laissera s??duire par Nar Garzhvog, le formidable Urgal, et par l'??nigmatique Lupus??nghren, l'elfe au pelage de loup ; on retrouvera avec bonheur Oromis et Glaedr, le dragon d'or ; on constatera avec jubilation que Saphira montre toujours un go??t certain pour l'hydromel. Et on saura enfin pourquoi le roman porte ce titre ??nigmatique : Brisingr, Feu en ancien langage.", "2019-09-18", 840, 5),
("824334341-5", "L'H??ritage", "Il y a peu encore, Eragon n'??tait qu'un simple gar??on de ferme, et Saphira, son dragon, une ??trange pierre bleue ramass??e dans la for??t... Depuis, le sort de plusieurs peuples repose sur leurs ??paules. De longs mois d'entra??nement et de combats, s'ils ont permis des victoires et ranim?? l'espoir, ont aussi provoqu?? des pertes cruelles. Or, l'ultime bataille contre Galbatorix reste ?? mener. Certes, Eragon et Saphira ne sont pas seuls, ils ont des alli??s : les Vardens conduits par Nasuada, Arya et les elfes, le roi Orik et ses nains, Garzhvog et ses redoutables Urgals. Le peuple des chats-garous s'est m??me joint ?? eux avec son roi, Grimrr Demi-Patte. Pourtant, si le jeune Dragonnier et sa puissante compagne aux ??cailles bleues ne trouvent pas en eux-m??mes la force d'abattre le tyran, personne n'y r??ussira. Ils n'auront pas de seconde chance. Tel est leur destin. Il faut renverser le roi mal??fique, restaurer la paix et la justice en Alaga??sia. Quel que soit le prix ?? payer.", "2019-09-18", 912, 5),
("969896727-3", "A la lueur d'une ??toile inconnue", "Kira Nav??rez r??vait d'un monde nouveau. Elle vient de r??veiller un cauchemar d'une ampleur intersid??rale... Lors d'une mission de routine sur une plan??te inconnue, Kira d??couvre un organisme vivant d'origine extraterrestre. Fascin??e, elle s'approche de l'??trange poussi??re noire. La substance s'??tend sur tout son corps et commence ?? prendre le contr??le. Kira, en pleine transformation, va explorer les derni??res limites de sa condition d'??tre humain. Mais quelle est l'origine de cette entit?? ? Quelles sont ses intentions ?La scientifique n'a pas le temps de r??pondre ?? ces questions : la guerre contre les aliens est d??clar??e, et Kira pourrait bien ??tre le plus grand et le dernier espoir de l'humanit??.", "2020-10-14", 848, 5),
("126301614-6", "Dormir dans un Oc??an d'Etoiles", "EN LUTTE CONTRE LES CAUCHEMARS, KIRA NAV??REZ ESPERE FAIRE TRIOMPHER LA VIE. En pleine guerre interplan??taire, la vie de Kira bascule : un organisme vivant d'origine extraterrestre prend peu ?? peu possession de son corps, lui conf??rant des pouvoirs quasi divins. Et ces nouvelles aptitudes bouleversent profond??ment son identit??. Tout son ??tre ??volue vers un intelligence sup??rieure hybride. La scientifique se donne une mission ambitieuse : r??tablir la paix entre humains et aliens. La lutte pour un monde nouveau ne fait que commencer.", "2021-09-22", 562, 5),
("404759164-5", "La Meute", "Les ??ditions Bragelonne sont fi??res de vous faire d??couvrir la suite des aventures du Visiteur du Futur. Dans ce roman-feuilleton, mitonn?? par Fran??ois Descraques et Slimane-Baptiste Berhoun, sont d??voil??s (entre autres) les myst??res du pass?? du Visiteur du Futur ! Comment dire non ?? une telle proposition (m??me quand on conna??t les risques encourus par quiconque se d??cide ?? suivre le Visiteur...) ? La s??rie Le Visiteur du Futur a d??but?? sur le Net en 2009 et a connu un fulgurant succ??s. Depuis la saison 3, la s??rie est coproduite par Ankama et le studio 4. 0 de France T??l??visions Nouvelles Ecritures. Peu de temps apr??s le lancement de la saison 4 d??but 2014, Le Visiteur du Futur avait engendr?? plus de trente-trois millions de vues sur la Toile. Ankama assure ??galement l'??dition des DVD, de la bande originale, d'une bande dessin??e, d'une gamme textile ainsi que du jeu de soci??t??.", "2022-07-06", 468, 6),
("019849199-9", "Sirius", "Alors que le monde se meurt, Avril, une jeune fille, tente tant bien que mal d'??lever son petit fr??re, Kid. R??fugi??s au coeur d'une for??t, ils se tiennent ?? l'??cart des villes et de la folie des hommes... jusqu'au jour o?? le myst??rieux pass?? d'Avril les jette brutalement sur la route. Pourchass??s, il leur faut maintenant survivre dans cet univers livre au chaos et ?? la sauvagerie. Mais sur leur chemin, une rencontre va tout bouleverser : Sirius. Avec ce road trip post-apocalyptique, St??phane Servant signe un grand roman d'aventure, brut et haletant.", "2017-08-23", 480, 7),
("455261908-X", "Le Neveu du Magicien", "Polly trouve parfois que la vie ?? Londres n'est gu??re passionnante... jusqu'au jour o?? elle rencontre son nouveau voisin, Digory. Il vit avec sa m??re, gravement malade, et un vieil oncle au comportement ??trange. Celui-ci force les deux enfants ?? essayer des bagues magiques qui les transportent dans un monde inconnu. Commence alors la plus extraordinaire des aventures...", "1955-05-02", 208, 1),
("368909556-5", "Le Lion, la Sorci??re Blanche et l'Armoire Magique", "Narnia, un royaume condamn?? ?? un hiver ??ternel, attend d'??tre lib??r?? d'une emprise mal??fique. L'arriv??e de quatre enfants fait rena??tre l'espoir : s'ils trouvent Aslan, le grand Lion, les pouvoirs de la Sorci??re Blanche pourraient enfin ??tre an??antis.", "1950-10-16", 208, 1),
("082461365-1", "Le Cheval et son Ecuyer", "Shasta, maltrait?? par le p??cheur qui l'a recueilli et ??lev??, quitte le pays de Calormen en compagnie de Bree, un cheval dou?? de parole. Ils n'ont qu'un espoir : rejoindre le merveilleux royaume de Narnia... En chemin, ils rencontrent une jeune fille de noble naissance, Aravis, qui fuit un mariage forc??. D'aventure en aventure, les deux h??ros perceront-ils le myst??re qui entoure la naissance de Shasta ?", "1954-09-06", 240, 1),
("187618499-X", "Le Prince Caspian", "Peter, Susan, Edmund et Lucy sont sur le point de se s??parer pour entamer une nouvelle ann??e scolaire. Ils attendent le train qui doit les conduire en pension quand, tout ?? coup, ils sont transport??s dans le pays de Narnia o?? ils ont r??gn?? autrefois. Mais si, pour eux, une ann??e seulement s'est ??coul??e, dans leur ancien royaume des si??cles ont pass??. Le palais royal est en ruines. Parviendront-ils ?? ramener la paix dans le monde magique de Narnia ?", "1951-10-15", 244, 1),
("602523044-7", "L'Odyss??e du Passeur d'Aurore", "Pour Edmund et Lucy, leur cousin Eustache Clarence est le gar??on le plus insupportable d'Angleterre. Mais le jour o?? les trois enfants entrent dans un tableau et sont pr??cipit??s dans les flots, ?? quelques brasses du navire de Caspian, roi de Narnia, Eustache perd sa belle assurance. Quelle part prendra-t-il ?? l'extraordinaire aventure qui les attend ?", "1952-09-15", 272, 1),
("338427321-4", "Le Fauteuil d'Argent", "Pour Jill et Eustache, la vie est dure ?? l'??cole exp??rimentale ! Un jour, voulant ??chapper ?? des ??l??ves qui les brutalisent, les enfants ouvrent la petite porte du jardin. Au lieu de la lande morne et grise, ils d??couvrent une contr??e radieuse, le pays d'Aslan, le grand lion. Celui-ci leur confie une mission : retrouver Rilian, prince h??ritier de Narnia, enlev?? des ann??es plus t??t par un horrible serpent... Le monde enchant?? de Narnia, le pays imaginaire, vous attend.", "1953-09-07", 272, 1),
("526953057-0", "La Derni??re Bataille", "Seul, captif, d??sesp??r??, le dernier roi de Narnia appelle ?? son secours les enfants qui, tant de fois, par le pass??, ont sauv?? le royaume de la destruction. Jill et Eustache se retrouvent donc, ?? nouveau, transport??s dans l'univers enchant?? de Narnia dont ils r??vent chaque jour en secret. Mais parviendront-ils, cette fois, ?? ??viter le pire ? Car cette aventure pourrait bien ??tre la derni??re... Le monde enchant?? de Narnia, le pays de l'imaginaire, vous attend.", "1956-09-04", 224, 1),
("657141567-4", "A l'Aventure, Compagnons", "Au commencement, il y avait un barbare en qu??te d'action, un voleur aux motivations troubles, une jeune et candide elfe des bois, un nain rev??che, une magicienne entretenant une amiti?? suspecte avec un ogre peu locace, et un ranger aussi ambitieux qu'inexp??riment??. Rien ne les pr??destinait ?? se rencontrer. Rien, sinon une insatiable faim d'aventure. Direction le Donjon de Naheulbeuk, l?? o?? tout a d??but??.", "2014-11-05", 510, 8),
("834499835-2", "La Couette de l'Oubli", "Jouez hautbois, r??sonnez trompettes, les h??ros du Donjon de Naheulbeuk reprennent du service ! Ils se croyaient sortis d'affaire apr??s avoir rempli leur contrat... que nenni ! En rapportant ?? leur commanditaire, le sorcier Gontran Th??ogal, la douzi??me statuette de Gladeulfeurha, ils ont ??uvr?? ?? leur insu pour l'av??nement de Dlul, le dieu du sommeil et de l'ennui, qui menace d'engloutir le monde dans la Grande Couette de l'Oubli ??ternel. Il va bien falloir que quelqu'un s'y colle, mais entre les guerres de religion qui agitent les terres de Fhang, les objectifs incertains des Oracles et le d??plorable humour nain, ??a s'annonce compliqu?? !", "2009-10-07", 376, 8),
("390939200-8", "L'Orbe de Xaraz", "Apr??s leurs pr??c??dentes m??saventures, les h??ros du Donjon de Naheulbeuk pensaient enfin pouvoir se la couler douce, mais il faut croire qu'une telle activit?? ne figure pas au programme de leur fiche de personnage l Jugez plut??t : l'un des leurs est rest?? sur le carreau. et aucun d'entre eux ne ma??trise le sort de r??surrection. Direction Waldorg, la cit?? des magiciens, qui regorge de gens comp??tents, mais un brin susceptibles. Avec leur chance coutumi??re, nos aventuriers arrivent une fois encore au mauvais endroit et au plus mauvais moment...", "2011-03-09", 444, 8),
("406641788-4", "Le Conseil de Suak", "Le grand n'importe quoi r??gne une fois de plus en terres de Fangh : chacun semble pris de folie, au point qu'une guerre g??n??ralis??e menace d'??clater. Cela n'est pas du tout du go??t de la jolie Elfe tout juste promue reine et quelque peu perdue au milieu du chaos. Elle doit pourtant r??gner, et faire appliquer des d??cisions ?? tout le moins contest??es... Il faut croire que nos h??ros ne sont bons qu'?? explorer des donjons. Ce qui tombe tr??s bien, car pour remettre un peu d'ordre dans tout ce bazar, il ne serait pas inutile d'aller en visiter un, encore plus vieux et plus p??rilleux que celui de Naheulbeuk !", "2012-10-10", 462, 8),
("715628208-5", "Chaos Sous la Montagne", "Alors que la guerre fait rage en terre de Fangh, mena??ant de r??v??ler au grand jour l'incomp??tence notoire des rescap??s du donjon de Naheulbeuk, ces derniers se voient confier une mission qui les m??nera loin sous terre, au plus profond des mines des nains. Comme tout un chacun le sait, ces infatigables travailleurs ont un sens de l'humour ?? peu pr??s aussi d??velopp?? que celui de leur verticalit??, alors autant dire que la partie est serr??e... Et pour ne rien arranger, nos anti-h??ros ont ?? leur trousses un magicien ??nerv?? et bien d??cid?? ?? prendre sa revanche !", "2016-04-13", 509, 8),
("630445577-1", "Les Veilleurs de Glargh", "Dans la cit?? de Glargh, de fiers soldats font r??gner l'ordre. Bon, ??a consiste surtout ?? r??diger des rapports sur les d??g??ts provoqu??s par les aventuriers, mais voir les uniformes rouges dans les rues rassure les badauds. L'unit?? Furets est consid??r??e comme de la bleusaille incomp??tente. Et plut??t mal assortie : un charcutier, un ing??nieur alcoolique, une amazone ?? la beigne facile, un m??decin bavard, un Nain, une magicienne... Quand on leur confie enfin une enqu??te digne de ce nom - un double meurtre, rien que ??a -, la joie retombe vite : et si on les avait engag??s pour qu'ils ??chouent ?", "2021-05-19", 512, 8),
("455967781-6", "La Ballade du Serpent et de l'Oiseau Chanteur", "D??vor?? d'ambition, pouss?? par la comp??tition, il va d??couvrir que la soif de pouvoir a un prix. C'est le matin de la Moisson qui doit ouvrir la dixi??me ??dition annuelle des Hunger Games. Au Capitole, Coriolanus Snow, dix-huit ans, se pr??pare ?? devenir pour la premi??re fois mentor aux Jeux. L'avenir de la maison Snow, qui a connu des jours meilleurs, est d??sormais suspendu aux maigres chances de Coriolanus. Il devra faire preuve de charme, d'astuce et d'inventivit?? pour faire gagner sa candidate. Mais le sort s'acharne. Honte supr??me, on lui a confi?? le plus mis??rable des tributs : une fille du district Douze. Leurs destins sont d??sormais li??s. Chaque d??cision peut les conduire ?? la r??ussite ou ?? l'??chec, au triomphe ou ?? la ruine. Dans l'ar??ne, ce sera un combat ?? mort.", "2020-05-20", 608, 9),
("178191153-3", "Hunger Games", "Dans un futur sombre, sur les ruines des Etats-Unis, un jeu t??l??vis?? est cr???? pour contr??ler le peuple par la terreur. Douze gar??ons et douze filles tir??s au sort participent ?? cette sinistre t??l??r??alit??, que tout le monde est forc?? de regarder en direct. Une seule r??gle dans l'ar??ne : survivre, ?? tout prix. Quand sa petite soeur est appel??e pour participer aux Hunger Games, Katniss n'h??site pas une seconde. Elle prend sa place, consciente du danger. A seize ans, Katniss a d??j?? ??t?? confront??e plusieurs fois ?? la mort. Chez elle, survivre est comme une seconde nature.", "2015-06-04", 420, 9),
("976152866-9", "La R??volte", "Apr??s le succ??s des derniers Hunger Games, le peuple de Panem est impatient de retrouver Katniss et Peeta pour la Tourn??e de la victoire. Mais pour Katniss, il s'agit surtout d'une tourn??e de la derni??re chance. Celle qui a os?? d??fier le Capitole est devenue le symbole d'une r??bellion qui pourrait bien embraser Panem. Si elle ??choue ?? ramener le calme dans les districts, le pr??sident Snow n'h??sitera pas ?? noyer dans le sang le feu de la r??volte. A l'aube des Jeux de l'Expiation, le pi??ge du Capitole se referme sur Katniss...", "2015-06-04", 428, 9),
("256597467-1", "L'Embrasement", "Contre toute attente, Katniss a surv??cu une seconde fois aux Hunger Games. Mais le Capitole crie vengeance. Katniss doit payer les humiliations qu'elle lui a fait subir. Et le pr??sident Snow a ??t?? tr??s clair : Katniss n'est pas la seule ?? risquer sa vie. Sa famille, ses amis et tous les anciens habitants du district Douze sont vis??s par la col??re sanglante du pouvoir. Pour sauver les siens, Katniss doit redevenir le geai moqueur, le symbole de la r??bellion. Quel que soit le prix ?? payer.", "2015-06-04", 460, 9),
("416809666-1", "Incarceron", "INCARCERON, UNE PRISON A NULLE AUTRE PAREILLE: ELLE DECIDE DE QUI DOIT VIVRE... et de qui doit mourir. Rien ne peut lui ??chapper. Finn est prisonnier d'Incarceron, un univers p??nitentiaire plein de dangers, de trahisons et de menaces. Il tente par tous les moyens de s'??vader. Claudia, la fille du directeur d'Incarceron, vit ?? l'Exterieur, dans un royaume fig?? au XVIIIe si??cle. Pi??g??e par une existence qu'elle n'a pas choisi, elle cherche ?? percer les myst??res de la Prison. Un jour, Finn et Claudia trouvent une cl??, qui permet ?? chacun de communiquer avec l'autre. Alors surgit un espoir, la possibilit?? d'??chapper ?? un destin tout trac?? dont ils ne veulent pas.", "2010-06-03", 498, 9),
("460648236-2", "Le Cygne Noir", "Le monde va-t'il s'??crouler? C'est ce que ce demande Claudia, Finn, Keiro et Atia. M??me s'ils ne se trouvent pas au m??me endroit, ils subissent tous des menaces qui pourraient bien leur couter la vie. Mais au fond de tous les complots, leur ennemi est toujours le m??me : La prison.", "2010-10-21", 515, 9),
("455529788-1", "Les Ma??tres des Brisants", "Lorsqu'ils embarquent comme stagiaires sur le vaisseau de Chien-de-la-lune, X??vier le strat??ge, M??rgane la devineresse et leur ami M??rk ignorent la p??rilleuse mission de leur capitaine : contrer la flotte de guerre du Khan qui menace de prendre le contr??le de la galaxie. Sur eux repose d??sormais la survie de l'empire...", "2019-06-06", 645, 1),
("100928450-9", "Le Livre des Etoiles", "Guillemot de Tro??l est un gar??on du Pays d'Ys, situ?? entre le monde r??el et le Monde Incertain. D'o?? lui viennent ses dons pour la sorcellerie que lui enseigne Ma??tre Qadehar ? Qu'est devenu Le Livre des ??toiles, inexorablement li?? ?? son destin, et qui renferme le secret de puissants sortil??ges ?", "2022-01-06", 880, 3),
("498185685-7", "Presque Minuit", "Six orphelins, une ville en danger. Prix de lancement ?? 7, 99 au lieu de 12, 99 jusqu'au 26 janvier ! Paris, 1889. Six orphelins en cavale, devenus gamins des rues par la force des ??v??nements, volent et d??troussent les passants. Alors que l'Exposition universelle d??bute, ils font l'erreur de d??rober le mauvais objet aux mauvaises personnes. Leurs m??saventures aux quatre coins de la ville les am??neront ?? d??couvrir les secrets d'un monde magique o?? s'affrontent cr??atures mythologiques, sorci??res et terrifiants ennemis m??caniques. Plus que jamais, Moignon, Allumette, B??gue, Morve, Boiteux et Pleurs devront se battre pour sauver leur vie et celle des habitants de la capitale.", "2018-01-25", 305, 10),
("117332677-4", "Ordo", "Le pouvoir est dans le sang. New York, de nos jours. Dans l'ombre, les cinq familles de l'Ordo Magicae utilisent l'Obscur, une magie issue d'un monde d??moniaque, pour ??tendre leur influence et diriger leurs affaires en ville. Elles sont li??es par le sang d'un m??me anc??tre, Ambrose Donosius, 356 ans et toujours vivant... jusqu'?? aujourd'hui : le patriarche de cette ""mafia de la magie noire"" est tu?? lors d'un attentat surnaturel en plein Manhattan. Cinq jeunes gens, fils et filles des dirigeants des cinq familles, sans perspective d'avenir face ?? des parents immortels, voient dans l'??v??nement l'occasion de planifier un casse. Le cambriolage magique du si??cle. Ils ont moins d'une semaine avant l'inhumation pour se pr??parer ?? infiltrer la Loge, le sanctuaire priv?? d'Ambrose. Leur objectif : voler la couronne d'un roi l??gendaire leur permettant de r??aliser leurs voux les plus secrets. De quoi devenir rois ?? la place des rois et, enfin, r??gner sur l'Ordo. Par l'auteur de Presque minuit et Au Cr??puscule.", "2020-10-01", 309, 10),
("955504630-1", "Une Vie Sans Fin", "Cette ann??e, ma m??re a fait un infarctus et mon p??re est tomb?? dans un hall d'h??tel. J'ai commenc?? ?? devenir un habitu?? des h??pitaux parisiens. En revenant de la clinique, Romy est entr??e dans la cuisine avec un sourcil plus haut que l'autre. - Papa, si je comprends bien, tout le monde meurt ? Il va y avoir grand-p??re et grand-m??re, puis ce sera maman, toi, moi, les animaux, les arbres et les fleurs ? Romy me regardait fixement comme si j'??tais Dieu. Elle ajouta alors une phrase tr??s aimable : - Papa, je n'ai pas envie que tu meures... - T'inqui??te pas ch??rie, lui ai-je r??pondu, ?? partir de maintenant, plus personne ne meurt. Il ne me restait plus qu'?? tenir cette promesse inconsid??r??e.", "2019-09-25", 343, 4),
("304173873-2", "Vers les Etoiles", "Install??s sur la plan??te D??tritus depuis des d??cennies, les derniers survivants de l'esp??ce humaine tentent de r??sister aux attaques r??p??t??es des Krell, un myst??rieux peuple extraterrestre. Dans ce monde rythm?? par les batailles spatiales, les pilotes sont v??n??r??s comme des h??ros et font frissonner les nouvelles g??n??rations pr??tes ?? en d??coudre. Parmi eux, Spensa r??ve depuis l'enfance de piloter son propre vaisseau et de prouver son courage. Car elle est la fille d'un l??che. Son p??re, l'un des meilleurs pilotes de la Force de D??fense Rebelle, a ??t?? ex??cut?? lors de la bataille d'Alta apr??s avoir d??sert?? le combat, et cet h??ritage pourrait bien co??ter ?? Spensa sa place au sein de l'??cole de pilotage. Plus que jamais d??termin??e ?? voler, elle redouble d'effort pour trouver sa place au sein d'une escouade de pilotes et convaincre sa hi??rarchie que la l??chet?? n'est pas h??r??ditaire. Sa d??couverte accidentelle d'un vaisseau depuis longtemps oubli?? pourrait bien changer la donne...", "2021-09-15", 736, 11),
("935591713-9", "Seul sur Mars", "?? Un des meilleurs thrillers que j'aie lus depuis longtemps : Apollo 13 puissance dix ! ?? Douglas Preston ?? Impossible ?? l??cher ! Un m??lange rare de bonne histoire, de personnages r??alistes et de pr??cision technique fascinante. ?? Chris Hadfield, commandant de la Station spatiale internationale ?? Robinson Cruso?? sur Mars, au XXIe si??cle : fort, r??sistant et du cran ?? revendre. ?? Steve Berry, auteur de L'H??ritage des Templiers ?? La science ?? la port??e de tous, pour un suspense fascinant. ?? Publishers Weekly Mark Watney est l'un des premiers humains ?? poser le pied sur Mars. Il pourrait bien ??tre le premier ?? y mourir. Lorsqu'une temp??te de sable mortelle force ses co??quipiers ?? ??vacuer la plan??te, Mark se retrouve seul et sans ressources, irr??m??diablement coup?? de toute communication avec la Terre. Pourtant Mark n'est pas pr??t ?? baisser les bras. Ing??nieux, habile de ses mains et terriblement t??tu, il affronte un par un des probl??mes en apparence insurmontables. Isol?? et aux abois, parviendra-t-il ?? d??fier le sort ? Le compte ?? rebours a d??j?? commenc??... Seul sur Mars est adapt?? au cin??ma par Ridley Scott, dont la sortie est pr??vue en 2015.", "2014-09-19", 408, 6),
("400498162-X", "Lion", "C'est un jour comme un autre dans la vie de Saroo. Le gar??on, ??g?? de cinq ans, est dans une gare du fin fond de l'Inde en train de ramasser quelques pi??ces lorsqu'il monte dans un train ?? quai. Le lendemain, Saroo se r??veille ?? Calcutta. Dans l'immense ville, il est compl??tement seul, sans aucun papier. Il est recueilli par un orphelinat o??, quelques mois plus tard, un couple d'Australiens va l'adopter. Saroo grandit, mais, depuis l'Australie, il pense toujours ?? sa famille biologique. Pendant 25 ans, il scrute les villages indiens sur Internet, ?? la recherche d'images famili??res. Et l??, le miracle se produit. L'orphelin va alors se lancer dans un long voyage pour enfin retrouver sa m??re et rentrer ?? la maison. L'??mouvante histoire d'un gar??on qui, d'un continent ?? l'autre, a recherch?? sa m??re pendant 25 ans.", "2018-04-11", 320, 12),
("421595393-6", "Avant Toi", "Ses jours sont compt??s. Mais quand on aime, on ne compte pas. Lou est une fille ordinaire qui m??ne une vie monotone ?? souhait. Quand elle se retrouve au ch??mage, dans ce trou paum?? de l'Angleterre dont elle n'est jamais sortie, Lou accepte un contrat de six mois pour tenir compagnie ?? un handicap??. Malgr?? l'accueil glacial qu'il lui r??serve, Lou va d??couvrir en lui un jeune homme exceptionnel. Mais depuis l'accident qui l'a rendu t??trapl??gique, Will veut mettre fin ?? ses jours. Lou n'a que quelques mois pour lui redonner go??t ?? la vie. ""Cette histoire d'amour inesp??r??e est un v??ritable choc ??motionnel - sortez les mouchoirs"". Elle ""Un roman poignant qui amorce une belle r??flexion sur l'art de donner la mort. Mascara waterproof indispensable"". Marie Claire", "2021-01-06", 528, 13),
("490879362-X", "Apr??s Toi", "N'oublie jamais que tu n'as qu'une seule vie... Lou a promis ?? l'homme qu'elle aimait de vivre chaque jour comme si c'??tait le dernier. Mais sans lui, le monde para??t bien sombre et elle peine ?? tourner la page. Sa vie londonienne ne la rend pas heureuse : dans le bar d'a??roport o?? elle travaille sous les ordres d'un patron tyrannique, elle regarde chaque jour les autres s'envoler tandis qu'elle reste d??sesp??r??ment clou??e au sol... Honorer la promesse faite ?? Will lui para??t impossible. Pourtant, au moment o?? elle croit avoir touch?? le fond, sa rencontre inattendue avec Lily sera peut-??tre le nouveau d??part qu'elle esp??rait. Et le meilleur moyen de tenir sa promesse.", "2021-02-03", 473, 13),
("427632648-6", "Apres Tout", "Il est toujours temps de d??couvrir qui tu es... Quand Lou s'envole pour New York, elle est certaine de pouvoir vivre pleinement cette aventure malgr?? les milliers de kilom??tres qui la s??parent de Sam. Elle rejoint la tr??s fortun??e famille Gopnik, se jette ?? corps perdu dans son nouveau travail, et d??couvre les joies de la vie new-yorkaise. C'est alors que sa route croise celle de Josh, qui ??veille en elle des souvenirs enfouis. Troubl??e par cette rencontre, Lou s'??vertue ?? rassembler les deux parties de son coeur s??par??es par un oc??an. Mais le dilemme auquel elle est confront??e menace de faire voler en ??clats son fragile ??quilibre. Le moment n'est-il pas venu de se demander qui elle est vraiment ?", "2021-03-10", 571, 13),
("505488956-6", "Kaleb", "Kaleb Hellgusson a 18 ans et la beaut?? du diable. De lui, on sait peu de chose, si ce n'est que sa m??re est morte en couches et que, depuis l'??ruption du volcan islandais Eyjafj??ll, il se d??couvre un don d'empathe. Il est capable de se brancher sur les ??motions d'autrui pour le meilleur comme pour le pire. Le pire peut conduire quiconque se met en travers de sa route au suicide ou ?? la folie??? Qui est vraiment Kaleb ? D'o?? lui vient ce don et jusqu'o?? ??voluera-t-il ? Pourquoi un petit groupe de scientifiques le traque-t-il o?? qu'il aille ? A-t-il raison de les fuir ? Au cours de son ??chapp??e, qui le conduira en Irlande et en Islande, Kaleb rencontrera d'autres personnes aux aptitudes troublantes (dont une succube au charme de laquelle il succombera, bien qu'ils soient dans des camps oppos??s), qui font toutes partie d'un projet qui les d??passe, les lie intimement ?? l'Islande, et qui existe depuis que l'homme sait ??crire???", "2012-06-14", 290, 14),
("945346022-2", "Abigail", "Le m??le dans la peau... SAISON 2. Le pouvoir... Tel est tout l'enjeu de cette nouvelle saison. Mais qui le d??tient vraiment ? Kaleb qui, depuis qu'on lui a fait ce myst??rieux tatouage, se laisse envahir par le Mal et entra??ne Abigail en enfer ? Ou bien Abigail qui s'est, malgr?? le danger, jet??e corps et ??me dans cette folle passion avec Kaleb et abdique chaque jour un peu plus d'elle-m??me pour une noirceur qui la fascine et la grise ? Lequel des deux amants a vraiment l'ascendant sur l'autre et le pousse ?? aller toujours plus loin ? Les apparences sont parfois trompeuses... Au final, ne sont-ils pas juste les pions d'une partie mal??fique qui a d??but?? il y a bien longtemps, quand sont apparus les premiers Enfants du Volcan, et dont la finale se jouera maintenant, qu'ils le veuillent ou non ? Le deuxi??me tome d'une trilogie de sang et de t??n??bres qui fera voyager les jeunes adultes dans les terres les plus recul??es d'Islande et d'Irlande, et qui fera rena??tre des l??gendes oubli??es, dont celle de l'elfe noir.", "2013-02-28", 223, 14),
("160343028-8", "Fusion", "Tout est bien qui finit mal... Saison 3. La proph??tie du volcan pr??dit l'av??nement d'une nouvelle ??re, initi??e par l'??lu... Or qui, de Kaleb, Abigail, le colonel Bergsson ou encore Mary-Ann bouleversera ?? jamais le destin des enfants du volcan ? Et si la mort est la cl??, tous ne sont-ils pas des morts en sursis ? Seul le Livre du volcan peut apporter des r??ponses ?? Kaleb et lui permettre de survivre au volcan qu'il a r??veill??. Mais le tenir entre ses mains peut se r??v??ler plus destructeur que tout... Ce dernier tome de la trilogie de Kaleb l??ve le voile sur une mythologie qui prend racine bien au-del?? de ce que vous pouviez imaginer. Plus que jamais, il est question de pouvoir, de sombre passion, de manipulation machiav??lique et du parfum sulfureux d'une saga mill??naire dont le d??nouement pourrait bien vous faire penser que tout est bien qui finit mal.", "2013-11-14", 320, 14),
("898581942-9", "L'Ombre d'Emily", "Elle est votre meilleure amie. Elle conna??t tous vos secrets. Et c'est ce qui la rend si dangereuse. Tout oppose en apparence Stephanie, une jeune veuve sans emploi qui partage son temps entre son fils Miles et la r??daction de son ""blog de maman"", et Emily, une femme d'affaires sophistiqu??e et mari??e. Elles s'entendent pourtant ?? merveille et ont nou??, dans leur petite ville du Connecticut, une amiti?? aussi forte que celle qui lie leurs deux gar??ons de cinq ans. Lorsque Emily demande ?? Stephanie de r??cup??rer son fils Nicky ?? la sortie de l'??cole, celle-ci accepte tout naturellement. Les jours passent et Emily ne revient pas. Suicide ? Meurtre ? Peu ?? peu, le vernis des apparences se craquelle et les masques tombent : tout doit dispara??tre...", "2018-09-06", 404, 15),
("425042488-X", "La Servante Ecarlate", "Les meilleurs r??cits dystopiques sont universels et intemporels. [... ]La Servante ??carlate ??claire d'une lumi??re terrifiante l'Am??rique contemporaine.  T??l??rama. Devant la chute drastique de la f??condit??, la r??publique de Galaad, r??cemment fond??e par des fanatiques religieux, a r??duit au rang d'esclaves sexuelles les quelques femmes encore fertiles. V??tue de rouge, Defred, servante ??carlate parmi d'autres ?? qui l'on a ??t?? jusqu'?? son nom, met donc son corps au service de son Commandant et de sa femme. Le soir, dans sa chambre ?? l'aust??rit?? monacale, elle songe au temps o?? les femmes avaient le droit de lire, de travailler... En rejoignant un r??seau clandestin, elle va tout tenter pour recouvrer sa libert??. Paru en 1985, La Servante ??carlate est aujourd'hui un classique de la litt??rature anglo-saxonne et un ??tendard de la lutte pour les droits des femmes. Si la s??rie adapt??e de ce chef-d'oeuvre a donn?? un visage ?? Defred, celui d'Elisabeth Moss, cette nouvelle traduction r??v??le toute sa modernit?? ainsi que la finesse et l'intelligence de Margaret Atwood. La Servante est un roman polys??mique, empli de r??f??rences litt??raires et bibliques, dr??le m??me... et c'est ?? nous, lecteurs, de d??couvrir ses multiples facettes.", "2021-01-14", 576, 16),
("054526495-2", "Les Testaments", "La suite de La Servante ??carlate. Quinze ans apr??s les ??v??nements racont??s dans La Servante ??carlate, roman dystopique d??sormais culte, le r??gime th??ocratique de la R??publique de Galaad a toujours la mainmise sur le pouvoir, mais certains signes ne trompent pas : il est en train de pourrir de l'int??rieur. A ce moment crucial, les vies de trois femmes radicalement diff??rentes convergent, avec des cons??quences potentiellement explosives. Avec Les Testaments, Margaret Atwood poursuit l'histoire de Galaad dans un savant m??lange de suspense, de vivacit?? et de virtuosit??.", "2021-05-12", 672, 16),
("217291886-5", "Comment Braquer une Banque sans Perdre son Dentier", "Ils sont trois femmes, deux hommes : M??rtha, Stina, Anna-Greta, le G??nie, et le R??teau, chacun 80 ans au compteur. Ils chantent dans la m??me chorale et d??p??rissent dans la m??me maison de retraite ?? Stockholm. Nourriture insipide, traitement lamentable, restrictions constantes, pas ??tonnant que les r??sidents passent l'arme ?? gauche... Ils ne vivront pas un jour de plus dans ce mouroir. Un brin rebelles et id??alistes, les cinq comparses d??cident de se lancer dans le grand banditisme. Avec leurs cheveux blancs et leurs d??ambulateurs, ils s'appr??tent ?? commettre le casse du si??cle. Mais l'aventure s'emballe et rien ne va se passer comme pr??vu...", "2015-02-05", 480, 15),
("244349541-8", "Red Sparrow", "A l'heure o?? la Russie de Poutine inqui??te de plus en plus les Occidentaux, la jeune Dominika est recrut??e contre sa volont?? par les services secrets russes. Ancienne ballerine dont la carri??re a ??t?? bris??e par une chute, elle s'entra??ne ?? utiliser ses charmes et d??couvre bient??t l'ampleur de son pouvoir... Jusqu'?? devenir l'un des meilleurs agents. Sa premi??re cible : un agent infiltr?? de la CIA en Russie. Entre manipulation et s??duction, un jeu dangereux s'installe entre eux. Apr??s plusieurs d??cennies pass??es ?? la CIA, Jason Matthews nous offre un premier roman captivant. Sa connaissance du terrain et de la g??opolitique donne un aspect brut ?? ce grand livre d'actualit?? o?? la politique, le suspense et l'amour se conjuguent ?? merveille. Traduit de l'anglais (Etats-Unis) par Hubert T??zenas", "2018-03-22", 640, 17),
("252230642-1", "Crazy Rich ?? Singapour", "Lorsque la New-Yorkaise Rachel Chu d??barque ?? Singapour au bras de son boyfriend Nicholas Young, venu assister au mariage de son meilleur ami, elle pense juste passer de paisibles vacances en amoureux. Mais le beau Nick a ??oubli???? de lui dire que sa famille est l'une des plus fortun??es d'Asie, que le mariage pr??vu est l'Ev??nement de l'ann??e, et qu'il est l'h??ritier le plus convoit?? de tout l'Extr??me-Orient ! Pour Rachel, le s??jour de r??ve se transforme en un v??ritable parcours du combattant - en stilettos et robes couture... Sino-am??ricaine, pauvre et roturi??re : bonne chance !", "2015-04-29", 524, 18),
("315555255-7", "The Haunting of Hill House", "Construite par un riche industriel du XIXe si??cle, Hill House est ?? l'image de son cr??ateur: labyrinthique, monstrueuse, t??n??breuse ?? souhait. De plus, on la dit hant??e. Fascin?? par les ph??nom??nes paranormaux, le docteur Montagu invite des sujets r??ceptifs au surnaturel ?? passer l'??t?? ?? Hill House afin de mener une enqu??te. Une enqu??te qui va tourner au cauchemar... ", "2019-09-01", 272, 19),
("265113087-7", "NOSFERA2", "Au volant de sa Rolls-Royce Wraith immatricul??e Nosfera2, Charles Manx enl??ve r??guli??rement des enfants pour les conduire ?? Christmasland o?? No??l est ??ternel. Mais ?? quel prix... Sur sa bicyclette, Vic McQueen retrouve tout ce qui est perdu, personnes disparues ou objets ??gar??s. Quand le face ?? face entre Manx et McQueen devient in??vitable, deux mondes vont s'affronter, peupl??s d'images sorties de nos cauchemars les plus obs??dants.", "2015-11-11", 763, 8),
("503839284-9", "Le Journal d'Anne Franck", "Je vais pouvoir, j'esp??re, te confier toutes sortes de choses, comme je n'ai encore pu le faire ?? personne, et j'esp??re que tu me seras d'un grand soutien. En 1942, la jeune Anne Frank a 13 ans. Elle vit heureuse ?? Amsterdam avec sa soeur Margot et ses parents, malgr?? la guerre. En juillet, ils s'installent clandestinement dans ""l'Annexe"" de l'immeuble du 263, Prinsenchracht. En 1944, ils sont arr??t??s sur d??nonciation. Anne est d??port??e ?? Auschwitz, puis ?? Bergen-Belsen, o?? elle meurt du typhus au d??but de 1945, peu apr??s sa soeur. Son journal, qu'elle a tenu du 12 juin 1942 au 1er ao??t 1944, est un des t??moignages les plus bouleversants qui nous soient parvenus sur la vie quotidienne d'une famille juive sous le joug nazi. Depuis la premi??re publication de ce journal aux Pays-Bas en 1947, la voix de cette jeune fille pleine d'espoir hante des millions de lecteurs dans le monde entier.", "2017-06-07", 360, 11),
("628715773-9", "La Communaut?? de l'Anneau", "Dans les vertes prairies du Comt??, les Hobbits, ou Demi-Hommes, vivaient en paix... jusqu'au jour fatal o?? l'un d'entre eux, au cours de ses voyages, entra en possession de l'Anneau Unique aux immenses pouvoirs. Pour le reconqu??rir, Sauron, le Seigneur Sombre, va d??cha??ner toutes les forces du Mal... Frodo, le Porteur de l'Anneau, Gandalf, le magicien, et leurs intr??pides compagnons r??ussiront-ils ?? ??carter la menace qui p??se sur la Terre du Milieu ?", "2019-10-03", 722, 1),
("998528293-0", "Les Deux Tours", "Dispers??e dans les terres de l'Ouest, la Fraternit?? de l'Anneau affronte les p??rils de la guerre, tandis que Frodo, accompagn?? du fid??le Sam, poursuit une qu??te presque d??sesp??r??e : d??truire l'Anneau Unique en le jetant dans les crevasses de l'Orodruin, le Mont Destin. Mais aux fronti??res du Pays de Mordor, une myst??rieuse cr??ature les ??pie... pour les perdre ou pour les sauver ?", "2019-10-03", 596, 1),
("057453797-X", "Le Retour du Roi", "Le royaume de Gondor s'arme contre Sauron, le Seigneur Sombre, qui veut asservir tous les peuples libres, Hommes et Elfes, Nains et Hobbits. Mais la vaillance des soldats de Minas Tirith ne peut rien d??sormais contre la puissance mal??fique de Mordor. Un fragile espoir, toutefois, demeure : Frodo, le Porteur de l'Anneau, s'approche jour apr??s jour de la montagne o?? br??le le feu du destin, seul capable de d??truire l'Anneau Unique et de provoquer la chute de Sauron...", "2019-10-03", 721, 1)
;

INSERT INTO exemplaires (ISBN_livre) VALUES ("018474911-5"),("018474911-5"),("018474911-5"),
("177134993-X"),("177134993-X"),
("708828225-9"),("708828225-9"),
("706256141-X"),("706256141-X"),
("679001812-3"),("679001812-3"),
("297153456-1"),("297153456-1"),
("101866960-4"),
("504283795-7"),
("518953001-9"),
("274329958-4"),
("289065341-2"),
("661122125-5"),("661122125-5"),
("483499503-8"),
("743103681-7"),
("354312486-2"),
("824334341-5"),
("969896727-3"),
("126301614-6"),
("404759164-5"),
("019849199-9"),
("455261908-X"),
("368909556-5"),("368909556-5"),("368909556-5"),
("082461365-1"),
("187618499-X"),("187618499-X"),
("602523044-7"),("602523044-7"),
("338427321-4"),
("526953057-0"),
("657141567-4"),("657141567-4"),
("834499835-2"),("834499835-2"),("834499835-2"),
("390939200-8"),("390939200-8"),
("406641788-4"),
("715628208-5"),
("630445577-1"),
("455967781-6"),("455967781-6"),
("178191153-3"),("178191153-3"),("178191153-3"),
("976152866-9"),("976152866-9"),("976152866-9"),
("256597467-1"),("256597467-1"),("256597467-1"),
("416809666-1"),
("460648236-2"),
("455529788-1"),("455529788-1"),
("100928450-9"),("100928450-9"),
("498185685-7"),
("117332677-4"),
("955504630-1"),
("304173873-2"),
("935591713-9"),
("400498162-X"),("400498162-X"),
("421595393-6"),("421595393-6"),("421595393-6"),
("490879362-X"),
("427632648-6"),
("505488956-6"),("505488956-6"),
("945346022-2"),
("160343028-8"),
("898581942-9"),
("425042488-X"),("425042488-X"),("425042488-X"),
("054526495-2"),("054526495-2"),
("217291886-5"),
("244349541-8"),
("252230642-1"),("252230642-1"),
("315555255-7"),
("265113087-7"),
("503839284-9"),("503839284-9"),
("628715773-9"),("628715773-9"),
("998528293-0"),
("057453797-X");
INSERT INTO series (nomSerie) VALUES ("Harry Potter"),("Les Animaux Fantastiques"),("L'H??ritage"),("Id??alis"),("Les Chroniques de Narnia"),("Le Donjon de Naheulbeuk"),("Hunger Games"),("Incarceron"),("Skyward"),("Avant Toi"),("Kaleb"),("La Servante Ecarlate"),("Crazy Rich Asians"),("Le Seigneur des Anneaux");

INSERT INTO livres_series (ISBN_livre, id_serie, position) VALUES ("018474911-5", 5, 4),
("177134993-X", 1, 2),
("708828225-9", 1, 3),
("706256141-X", 1, 4),
("679001812-3", 1, 5),
("297153456-1", 1, 6),
("101866960-4", 1, 7),
("504283795-7", 1, 8),
("518953001-9", 2, 1),
("274329958-4", 2, 2),
("483499503-8", 3, 1),
("743103681-7", 3, 2),
("354312486-2", 3, 3),
("824334341-5", 3, 4),
("969896727-3", 4, 1),
("126301614-6", 4, 2),
("455261908-X", 5, 1),
("368909556-5", 5, 2),
("082461365-1", 5, 3),
("187618499-X", 5, 4),
("602523044-7", 5, 5),
("338427321-4", 5, 6),
("526953057-0", 5, 7),
("657141567-4", 6, 1),
("834499835-2", 6, 2),
("390939200-8", 6, 3),
("406641788-4", 6, 4),
("715628208-5", 6, 5),
("455967781-6", 7, 1),
("178191153-3", 7, 2),
("976152866-9", 7, 3),
("256597467-1", 7, 4),
("416809666-1", 8, 1),
("460648236-2", 8, 2),
("304173873-2", 9, 1),
("421595393-6", 10, 1),
("490879362-X", 10, 2),
("427632648-6", 10, 3),
("505488956-6", 11, 1),
("945346022-2", 11, 2),
("160343028-8", 11, 3),
("425042488-X", 12, 1),
("054526495-2", 12, 2),
("252230642-1", 13, 1),
("628715773-9", 14, 1),
("998528293-0", 14, 2),
("057453797-X", 14, 3);

INSERT INTO livres_auteurs (ISBN_livre, id_auteur) VALUES ("018474911-5", 20),
("177134993-X", 1),
("708828225-9", 1),
("706256141-X", 1),
("679001812-3", 1),
("297153456-1", 1),
("101866960-4", 1),
("504283795-7", 1),
("518953001-9", 1),
("274329958-4", 1),
("289065341-2", 1),
("661122125-5", 1),
("483499503-8", 2),
("743103681-7", 2),
("354312486-2", 2),
("824334341-5", 2),
("969896727-3", 2),
("126301614-6", 2),
("404759164-5", 3),
("019849199-9", 4),
("455261908-X", 5),
("368909556-5", 5),
("082461365-1", 5),
("187618499-X", 5),
("602523044-7", 5),
("338427321-4", 5),
("526953057-0", 5),
("657141567-4", 6),
("834499835-2", 6),
("390939200-8", 6),
("406641788-4", 6),
("715628208-5", 6),
("630445577-1", 6),
("455967781-6", 7),
("178191153-3", 7),
("976152866-9", 7),
("256597467-1", 7),
("416809666-1", 8),
("460648236-2", 8),
("455529788-1", 9),
("100928450-9", 9),
("498185685-7", 10),
("117332677-4", 10),
("955504630-1", 11),
("304173873-2", 12),
("935591713-9", 13),
("400498162-X", 14),
("421595393-6", 15),
("490879362-X", 15),
("427632648-6", 15),
("505488956-6", 16),
("945346022-2", 16),
("160343028-8", 16),
("898581942-9", 17),
("425042488-X", 18),
("054526495-2", 18),
("217291886-5", 19),
("244349541-8", 20),
("252230642-1", 21),
("315555255-7", 22),
("265113087-7", 23),
("503839284-9", 24),
("628715773-9", 25),
("998528293-0", 25),
("057453797-X", 25);

INSERT INTO livres_genres (ISBN_livre, id_genre) VALUES ("018474911-5", 4),
("177134993-X", 1),
("708828225-9", 1),
("706256141-X", 1),
("679001812-3", 1),
("297153456-1", 1),
("101866960-4", 1),
("504283795-7", 1),
("518953001-9", 1),
("274329958-4", 1),
("289065341-2", 2),
("661122125-5", 3),
("483499503-8", 1),
("743103681-7", 1),
("354312486-2", 1),
("824334341-5", 1),
("969896727-3", 4),
("126301614-6", 4),
("404759164-5", 4),
("019849199-9", 4),
("455261908-X", 1),
("368909556-5", 1),
("082461365-1", 1),
("187618499-X", 1),
("602523044-7", 1),
("338427321-4", 1),
("526953057-0", 1),
("657141567-4", 1),
("834499835-2", 1),
("390939200-8", 1),
("406641788-4", 1),
("715628208-5", 1),
("630445577-1", 1),
("455967781-6", 5),
("178191153-3", 5),
("976152866-9", 5),
("256597467-1", 5),
("416809666-1", 5),
("460648236-2", 5),
("455529788-1", 4),
("100928450-9", 1),
("498185685-7", 6),
("117332677-4", 7),
("955504630-1", 8),
("304173873-2", 4),
("935591713-9", 4),
("400498162-X", 3),
("421595393-6", 9),
("490879362-X", 9),
("427632648-6", 9),
("505488956-6", 1),
("945346022-2", 1),
("160343028-8", 1),
("898581942-9", 3),
("425042488-X", 5),
("054526495-2", 5),
("217291886-5", 10),
("244349541-8", 11),
("252230642-1", 9),
("315555255-7", 7),
("265113087-7", 7),
("503839284-9", 12),
("628715773-9", 1),
("998528293-0", 1),
("057453797-X", 1);

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
('2023-01-12', null, 1, 1),
('2022-12-02', null, 1, 50),
('2023-01-23', null, 1, 14),
('2023-01-23', '2023-01-29', 1, 48),
('2023-01-30', '2023-02-05', 1, 56),
('2023-02-06', null, 1, 17),
('2023-02-03', null, 1, 18),
('2022-12-25', null, 3, 100),
('2022-12-28', '2023-01-03', 1, 2),
('2022-12-22', '2022-12-28', 1, 3),
('2022-12-07', null, 2, 23),
('2022-12-17', null, 2, 16),
('2022-12-29', '2023-01-04', 3, 37),
('2022-12-23', '2022-12-29', 3, 62),
('2022-12-20', null, 2, 11),
('2022-12-29', null, 3, 12),
('2023-02-18', null, 3, 14),
('2022-12-05', null, 3, 19),
('2022-12-16', '2022-12-22', 2, 53),
('2022-12-11', '2022-12-17', 2, 49),
('2023-02-14', null, 3, 27),
('2022-12-04', '2022-12-10', 7, 25),
('2023-01-20', '2023-01-26', 7, 34),
('2023-01-16', null, 4, 29),
('2022-12-28', null, 4, 37),
('2022-12-01', null, 4, 38),
('2023-01-15', null, 4, 39),
('2022-12-15', '2022-12-21', 5, 24),
('2023-01-06', null, 4, 40),
('2022-12-26', '2023-01-01', 5, 13),
('2023-01-23', '2023-01-29', 5, 14),
('2023-01-17', null, 5, 41),
('2023-02-11', null, 5, 42),
('2023-01-23', '2023-01-29', 4, 8),
('2023-02-10', null, 6, 43),
('2022-12-20', '2022-12-26', 4, 9),
('2023-02-04', null, 6, 44),
('2023-01-27', '2023-02-02', 4, 10),
('2022-12-13', null, 6, 45),
('2023-01-25', null, 7, 46),
('2023-01-26', null, 7, 47),
('2022-12-27', '2023-01-02', 8, 18),
('2022-12-17', null, 7, 48),
('2023-02-12', '2023-02-18', 1, 16),
('2023-01-01', null, 8, 49),
('2023-01-14', null, 8, 50),
('2023-02-11', '2023-02-17', 1, 46),
('2022-12-04', '2022-12-10', 1, 39),
('2023-02-09', '2023-02-15', 1, 16),
('2023-02-14', null, 8, 28);

INSERT INTO commentaires (contenu, dateCom, id_user, ISBN_livre) VALUES
('Automated bifurcated matrices', '2022-12-23 19:55:28', 7, '404759164-5'),
('Secured demand-driven database', '2022-12-29 08:33:21', 7, '404759164-5'),
('Synergized actuating benchmark', '2023-01-30 07:00:34', 8, '256597467-1'),
('Cross-group modular throughput', '2022-12-08 00:06:23', 8, '455529788-1'),
('Advanced asymmetric orchestration', '2023-02-16 22:07:51', 1, '743103681-7'),
('Reduced object-oriented contingency', '2022-12-22 15:37:02', 7, '390939200-8'),
('Open-source 4th generation website', '2023-02-07 23:08:08', 3, '354312486-2'),
('Multi-lateral tangible time-frame', '2023-01-02 20:14:31', 7, '679001812-3'),
('Persevering analyzing pricing structure', '2022-12-11 05:09:11', 4, '416809666-1'),
('Networked leading edge frame', '2022-12-24 15:03:23', 6, '126301614-6'),
('Secured didactic knowledge user', '2023-01-24 14:17:54', 8, '187618499-X'),
('Up-sized actuating support', '2023-02-11 18:50:10', 3, '834499835-2'),
('Visionary fault-tolerant process improvement', '2022-12-15 12:53:29', 5, '117332677-4'),
('Stand-alone maximized projection', '2023-02-04 19:40:56', 5, '518953001-9'),
('Object-based empowering time-frame', '2023-01-19 05:30:21', 1, '160343028-8'),
('Secured content-based policy', '2023-02-05 04:21:53', 4, '504283795-7'),
('Open-architected leading edge extranet', '2022-12-15 13:50:28', 6, '630445577-1'),
('Switchable 3rd generation pricing structure', '2022-12-09 16:22:44', 3, '082461365-1'),
('Optimized maximized extranet', '2023-01-03 05:52:19', 2, '187618499-X'),
('Synchronised explicit open system', '2023-01-02 16:59:17', 8, '661122125-5'),
('Profound secondary strategy', '2023-02-13 03:58:03', 6, '404759164-5'),
('Inverse hybrid encryption', '2023-01-16 10:55:18', 4, '455261908-X'),
('Operative incremental workforce', '2022-12-28 11:05:21', 5, '265113087-7'),
('Compatible next generation functionalities', '2022-12-14 16:22:21', 4, '252230642-1'),
('Multi-tiered mobile frame', '2023-01-19 10:51:17', 6, '416809666-1'),
('Triple-buffered non-volatile extranet', '2023-02-18 18:22:40', 2, '100928450-9'),
('Managed mission-critical monitoring', '2023-01-19 10:48:54', 5, '160343028-8'),
('Reduced modular moratorium', '2023-01-29 06:39:04', 4, '018474911-5'),
('Fundamental zero defect function', '2023-01-15 15:51:37', 1, '834499835-2'),
('Front-line multimedia matrices', '2022-12-28 02:06:29', 3, '178191153-3'),
('Profound actuating moderator', '2022-12-08 18:44:54', 8, '898581942-9'),
('Multi-lateral foreground local area network', '2023-01-10 09:46:38', 4, '898581942-9'),
('Expanded high-level help-desk', '2022-12-15 08:55:52', 1, '935591713-9'),
('Optional coherent local area network', '2023-01-15 13:51:16', 8, '054526495-2'),
('Organic discrete implementation', '2023-01-01 01:19:18', 1, '187618499-X'),
('Decentralized static implementation', '2023-01-24 14:36:45', 1, '955504630-1'),
('Automated full-range concept', '2023-02-16 03:22:16', 2, '935591713-9'),
('Persistent incremental knowledge user', '2022-12-16 01:03:20', 3, '455967781-6'),
('Organic actuating interface', '2023-01-01 04:41:21', 4, '274329958-4'),
('Proactive incremental forecast', '2022-12-09 10:32:36', 8, '427632648-6'),
('Progressive homogeneous installation', '2023-01-18 12:08:07', 2, '706256141-X'),
('Exclusive asymmetric parallelism', '2023-02-07 11:52:33', 2, '460648236-2'),
('Grass-roots 24/7 firmware', '2023-02-01 08:18:29', 2, '101866960-4'),
('Self-enabling next generation website', '2023-01-06 02:23:48', 1, '297153456-1'),
('Sharable multi-state encoding', '2023-01-23 00:23:00', 6, '082461365-1'),
('Centralized value-added project', '2023-02-20 21:25:32', 1, '526953057-0'),
('Visionary client-server moderator', '2023-01-15 15:02:28', 6, '526953057-0'),
('Phased dedicated access', '2023-02-03 18:21:00', 6, '455261908-X'),
('Re-engineered systematic interface', '2023-02-06 18:25:41', 6, '455967781-6'),
('Synergized mission-critical open system', '2023-01-30 18:08:25', 7, '256597467-1'),
('Switchable value-added leverage', '2023-01-07 00:06:46', 3, '526953057-0'),
('Managed incremental database', '2023-01-21 01:34:33', 3, '427632648-6'),
('Front-line bottom-line workforce', '2022-12-19 22:06:24', 8, '898581942-9'),
('Intuitive hybrid framework', '2023-02-17 11:36:06', 2, '252230642-1'),
('Organized leading edge contingency', '2023-02-10 03:22:17', 6, '404759164-5'),
('Ameliorated global function', '2023-01-30 17:48:22', 8, '455967781-6'),
('Organic static focus group', '2022-12-13 06:38:53', 4, '998528293-0'),
('Synchronised modular task-force', '2023-01-06 01:19:38', 7, '177134993-X'),
('Diverse radical intranet', '2023-01-23 00:47:30', 8, '217291886-5'),
('Exclusive stable system engine', '2022-12-10 04:53:28', 1, '160343028-8'),
('Enhanced object-oriented structure', '2022-12-24 01:21:41', 1, '460648236-2'),
('Progressive 4th generation software', '2023-01-13 08:54:57', 8, '630445577-1'),
('Adaptive national protocol', '2022-12-02 08:01:41', 6, '390939200-8'),
('Quality-focused transitional contingency', '2023-01-19 02:07:47', 3, '898581942-9'),
('Robust coherent portal', '2022-12-08 22:57:44', 7, '187618499-X'),
('Re-contextualized foreground capability', '2023-01-05 15:31:02', 3, '661122125-5'),
('Decentralized didactic archive', '2023-02-17 10:32:42', 6, '244349541-8'),
('Extended attitude-oriented methodology', '2023-02-02 07:11:21', 7, '955504630-1'),
('Polarised object-oriented attitude', '2022-12-06 11:16:28', 3, '217291886-5'),
('Upgradable global policy', '2023-01-21 00:56:27', 6, '518953001-9'),
('Multi-layered multimedia database', '2023-01-09 01:31:42', 3, '976152866-9'),
('Innovative mobile adapter', '2023-02-05 05:31:07', 6, '715628208-5'),
('Multi-tiered uniform challenge', '2022-12-01 03:39:02', 2, '955504630-1'),
('User-friendly needs-based installation', '2022-12-27 03:27:18', 7, '824334341-5'),
('Managed hybrid portal', '2023-02-04 03:44:22', 6, '416809666-1'),
('Proactive incremental complexity', '2022-12-28 19:31:48', 3, '416809666-1'),
('Multi-channelled intangible matrix', '2023-01-03 14:43:58', 5, '400498162-X'),
('Future-proofed grid-enabled neural-net', '2023-01-10 07:02:03', 6, '054526495-2'),
('Vision-oriented solution-oriented monitoring', '2022-12-07 11:22:17', 8, '126301614-6'),
('Implemented fault-tolerant architecture', '2022-12-18 00:16:39', 4, '708828225-9'),
('Persevering background model', '2023-01-25 09:58:54', 4, '498185685-7'),
('Persevering multi-tasking standardization', '2022-12-28 21:55:12', 5, '019849199-9'),
('Right-sized intangible application', '2023-02-16 17:33:53', 3, '518953001-9'),
('Synergized bottom-line infrastructure', '2023-02-07 01:53:42', 5, '315555255-7'),
('Triple-buffered clear-thinking product', '2023-01-27 21:40:15', 4, '628715773-9'),
('Digitized directional pricing structure', '2022-12-04 11:36:49', 4, '338427321-4'),
('Implemented local framework', '2022-12-18 12:54:45', 2, '834499835-2'),
('Grass-roots context-sensitive task-force', '2023-02-08 18:46:40', 8, '526953057-0'),
('Cloned intermediate standardization', '2022-12-12 16:12:32', 1, '824334341-5'),
('Multi-layered didactic moderator', '2022-12-11 12:21:16', 1, '425042488-X'),
('Progressive tertiary solution', '2023-02-15 10:43:25', 7, '425042488-X'),
('Streamlined systemic archive', '2022-12-20 02:06:05', 2, '368909556-5'),
('Grass-roots full-range circuit', '2023-02-20 18:58:44', 4, '935591713-9'),
('Reactive 6th generation standardization', '2022-12-17 04:47:45', 3, '898581942-9'),
('Switchable explicit support', '2023-02-12 19:23:13', 7, '505488956-6'),
('Polarised human-resource hardware', '2023-01-07 04:56:58', 6, '503839284-9'),
('Robust 24 hour pricing structure', '2023-01-12 01:02:22', 7, '256597467-1'),
('Virtual motivating encoding', '2023-01-22 07:00:40', 7, '743103681-7'),
('Synergistic 5th generation initiative', '2023-01-01 10:49:23', 4, '518953001-9'),
('Cross-group 24/7 architecture', '2022-12-08 01:05:08', 4, '265113087-7');

CREATE VIEW catalogue_complet AS
SELECT l.id idL, l.ISBN ISBN_livreL, ex.id idEx, l.titre titreL, l.resume resumeL, l.datePubli datePubliL, l.nbPages nbPagesL, l.couverture couvertureL, a.nom nomA, a.prenom prenomA, e.nomSocial nomSocialE, g.theme themeG, s.nomSerie nomSerieS, ls.position tomeLS
FROM livres l 
JOIN livres_auteurs la ON l.ISBN = la.ISBN_livre 
JOIN auteurs a ON la.id_auteur = a.id 
JOIN editeurs e ON l.id_editeur = e.id 
JOIN livres_genres lg ON l.ISBN = lg.ISBN_livre 
JOIN genres g ON lg.id_genre = g.id 
JOIN exemplaires ex ON ex.ISBN_livre = l.ISBN
LEFT JOIN livres_series ls ON l.ISBN = ls.ISBN_livre 
LEFT JOIN series s ON ls.id_serie = s.id
ORDER BY `idL` DESC;

DROP VIEW IF EXISTS livres_dispos;
CREATE VIEW IF NOT EXISTS livres_dispos AS
SELECT l.id idL, l.ISBN ISBN_livreL, ex.id idEx, l.titre titreL, l.resume resumeL, l.datePubli datePubliL, l.nbPages nbPagesL, l.couverture couvertureL, a.nom nomA, a.prenom prenomA, ed.nomSocial nomSocialE, g.theme themeG, s.nomSerie nomSerieS, ls.position tomeLS
FROM livres l 
JOIN livres_auteurs la ON l.ISBN = la.ISBN_livre 
JOIN auteurs a ON la.id_auteur = a.id 
JOIN editeurs ed ON l.id_editeur = ed.id 
JOIN livres_genres lg ON l.ISBN = lg.ISBN_livre 
JOIN genres g ON lg.id_genre = g.id 
JOIN exemplaires ex ON ex.ISBN_livre = l.ISBN
LEFT JOIN livres_series ls ON l.ISBN = ls.ISBN_livre 
LEFT JOIN series s ON ls.id_serie = s.id
LEFT JOIN emprunts em ON ex.id = em.id_exemplaire
WHERE em.dateRetour IS NOT NULL OR ex.id NOT IN (SELECT id_exemplaire FROM emprunts)  
ORDER BY `idL` DESC;

DELIMITER //
CREATE OR REPLACE TRIGGER checkAuteurIfExists
	BEFORE INSERT ON auteurs
	FOR EACH ROW
	BEGIN
        SET @ID = (SELECT id FROM auteurs WHERE nom = NEW.nom AND prenom = NEW.prenom LIMIT 1);
        IF @ID IS NOT NULL THEN
		    SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Existe d??j??";
        END IF;
	END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE TRIGGER checkGenreIfExists
	BEFORE INSERT ON genres
	FOR EACH ROW
	BEGIN
        SET @ID = (SELECT id FROM genres WHERE theme = NEW.theme LIMIT 1);
        IF @ID IS NOT NULL THEN
		    SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Existe d??j??";
        END IF;
	END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE TRIGGER checkSerieIfExists
	BEFORE INSERT ON series
	FOR EACH ROW
	BEGIN
        SET @ID = (SELECT id FROM series WHERE nomSerie = NEW.nomSerie LIMIT 1);
        IF @ID IS NOT NULL THEN
		    SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Existe d??j??";
        END IF;
	END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE TRIGGER checkEditeurIfExists
	BEFORE INSERT ON editeurs
	FOR EACH ROW
	BEGIN
        SET @ID = (SELECT id FROM editeurs WHERE nomSocial = NEW.nomSocial LIMIT 1);
        IF @ID IS NOT NULL THEN
		    SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Existe d??j??";
        END IF;
	END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE TRIGGER checkLivreIfExists
	BEFORE INSERT ON livres
	FOR EACH ROW
	BEGIN
        SET @ID = (SELECT id FROM livres WHERE ISBN = NEW.ISBN LIMIT 1);
        IF @ID IS NOT NULL THEN
		    SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Existe d??j??";
        END IF;
	END //
DELIMITER ;

DROP TRIGGER autoCouvertureBefore;