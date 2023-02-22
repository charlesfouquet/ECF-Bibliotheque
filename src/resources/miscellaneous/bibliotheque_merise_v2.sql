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

INSERT INTO auteurs (nom, prenom) VALUES ("Rowling", "J.K."),("Paolini", "Christopher"),("Berhoun", "Slimane-Baptiste"),("Servant", "Stéphane"),("Lewis", "C.S."),("Lang", "John"),("Collins", "Suzanne"),("Fisher", "Catherine"),("L'Homme", "Erik"),("Combrexelle", "Anthony"),("Beigbeder", "Frédéric"),("Sanderson", "Brandon"),("Weir", "Andy"),("Brierley", "Saroo"),("Moyes", "Jojo"),("Eljundir", "Myra"),("Bell", "Darcey"),("Atwood", "Margaret"),("Ingelman", "Catharine"),("Matthews", "Jason"),("Kwan", "Kevin"),("Jackson", "Shirley"),("Hill", "Joe"),("Franck", "Anne"),("Tolkien", "J.R.R.");

INSERT INTO editeurs (nomSocial) VALUES ("Folio Junior"),("Gallimard"),("Gallimard Jeunesse"),("Grasset"),("Bayard"),("Bragelonne"),("Rouergue"),("J'ai Lu"),("Pocket Jeunesse"),("404 Editions"),("Le Livre de Poche"),("City"),("Milady"),("Robert Laffont"),("Pocket"),("Pavillons Poche"),("Points"),("Albin Michel"),("Penguin Books");

INSERT INTO genres (theme) VALUES ("Fantastique"),("Conte"),("Drame"),("Science-Fiction"),("Dystopie"),("Aventure"),("Horreur"),("Essai"),("Romance"),("Humour"),("Thriller"),("Autobiographie");

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
("018474911-5", "Harry Potter à l'Ecole des Sorciers", "Le jour de ses onze ans, Harry Potter, un orphelin élevé par un oncle et une tante qui le détestent, voit son existence bouleversée. Un géant vient le chercher pour l'emmener à Poudlard, une école de sorcellerie ! Voler en balai, jeter des sorts, combattre les trolls : Harry se révèle un sorcier doué. Mais un mystère entoure sa naissance et l'effroyable V., le mage dont personne n'ose prononcer le nom.", "1998-10-09", 305, 1),
("177134993-X", "Harry Potter et la Chambre des Secrets", "Une rentrée fracassante en voiture volante, une étrange malédiction qui s'abat sur les élèves, cette deuxième année à l'école des sorciers ne s'annonce pas de tout repos ! Entre les cours de potions magiques, les matches de Quidditch et les combats de mauvais sorts, Harry et ses amis Ron et Hermione trouveront-ils le temps de percer le mystère de la Chambre des Secrets ?", "1999-03-23", 364, 1),
("708828225-9", "Harry Potter et le Prisonnier d'Azkaban", "Sirius Black, le dangereux criminel qui s'est échappé de la forteresse d'Azkaban, recherche Harry Potter. C'est donc sous bonne garde que l'apprenti sorcier fait sa troisième rentrée. Au programme : des cours de divination, la fabrication d'une potion de Ratatinage, le dressage des hippogriffes... Mais Harry est-il vraiment à l'abri du danger qui le menace ?", "1999-10-19", 474, 1),
("706256141-X", "Harry Potter et la Coupe de Feu", "Harry Potter a quatorze ans et entre en quatrième année au collège de Poudlard. Une grande nouvelle attend Harry, Ron et Hermione à leur arrivée : la tenue d'un tournoi de magie exceptionnel entre les plus célèbres écoles de sorcellerie. Déjà les délégations étrangères font leur entrée. Harry se réjouit... Trop vite. Il va se trouver plongé au coeur des événements les plus dramatiques qu'il ait jamais eu à affronter.", "2000-11-29", 656, 1),
("679001812-3", "Harry Potter et l'Ordre du Phoenix", "Au début de ce cinquième roman, Harry retrouve son parrain Sirius, Lupin, Hermione et la famille Weasley au 12 square Grimmaurd, qui devient le quartier général de l'ordre du Phénix, l'organisation fondée par Dumbledore au moment de la première ascension de Voldemort. Le ministère de la Magie de son côté, malgré les événements de l'an passé, refuse d'admettre le retour du mage noir. Harry, Ron et Hermione retournent à Poudlard, où un nouveau professeur de défense contre les forces du mal, Dolores Ombrage, engagée par le ministre de la Magie lui-même, ne tarde pas à instaurer des règles très strictes sur l'école, interdisant aux élèves de pratiquer la magie, de se rassembler en groupe ou de lire certains articles de presse défendant le point de vue de Harry Potter sur le retour de Voldemort. Hermione décide d'agir et de fonder une seconde organisation au sein-même de l'école, l'Armée de Dumbledore, pour contrer Ombrage et inciter les élèves volontaires à pratiquer la magie pour apprendre à se défendre face aux dangers extérieurs que les autorités souhaitent taire.", "2003-12-03", 984, 1),
("297153456-1", "Harry Potter et le Prince de Sang-Mêlé", "Dans un monde de plus en plus inquiétant, Harry se prépare à retrouver Ron et Hermione. Bientôt, ce sera la rentrée à Poudlard, avec les autres étudiants de sixième année. Mais pourquoi Dumbledore vient-il en personne chercher Harry chez les Dursley ? Dans quels extraordinaires voyages au coeur de la mémoire va-t-il l'entraîner ?", "2005-10-01", 752, 1),
("101866960-4", "Harry Potter et les Reliques de la Mort", "Cette année, Harry a dix-sept ans et ne retourne pas à Poudlard. Avec Ron et Hermione, il se consacre à la dernière mission confiée par Dumbledore. Mais le Seigneur des Ténèbres règne en maître. Traqués, les trois fidèles amis sont contraints à la clandestinité. D'épreuves en révélations, le courage, les choix et les sacrifices de Harry seront déterminants dans la lutte contre les forces du Mal.", "2007-10-26", 881, 1),
("504283795-7", "Harry Potter et l'Enfant Maudit", "Etre Harry Potter n'a jamais été facile et ne l'est pas davantage depuis qu'il travaille au coeur des secrets du ministère de la Magie. Marié et père de trois enfants, Harry se débat avec un passé qui refuse de le laisser en paix, tandis que son fils Albus affronte le poids d'un héritage familial dont il n'a jamais voulu. Quand passé et présent s'entremêlent dangereusement, père et fils se retrouvent face à une dure vérité : les ténèbres surviennent parfois des endroits les plus inattendus. L'édition définitive de la pièce. Avec de passionnants bonus inédits !", "2018-01-04", 464, 1),
("518953001-9", "Les Animaux Fantastiques: le Texte du Film", "J. K. Rowling, créatrice de la mythique saga Harry Potter, nous invite à découvrir une nouvelle ère du Monde des Sorciers, au côté du jeune explorateur et magizoologiste Norbert Dragonneau, cinquante ans avant le début de l'histoire de Harry. Revivez le film écrit par J. K. Rowling avec le texte original de l'auteur : l'intégrale des dialogues, mais aussi l'action, le jeu et les émotions des personnages, les descriptions des décors... Une aventure épique et fabuleuse, une grande histoire de magie et d'amitié.", "2019-01-03", 380, 2),
("274329958-4", "Les Animaux Fantastiques: Les Crimes de Grindelwald", "Paris, 1927. Quelques mois après sa capture, le puissant mage noir Gellert Grindelwald s'est évadé. Il menace de faire régner les sorciers de sang pur sur les êtres non-magiques. Devant ce danger, le jeune Albus Dumbledore, professeur de défense contre les forces du mal à Poudlard, fait appel à son ancien élève, Norbert Dragonneau.", "2022-04-07", 352, 2),
("289065341-2", "L'Ickabog", "La Cornucopia était un petit royaume heureux. On n'y manquait de rien, le roi portait la plus élégante des moustaches, et le pays était célèbre pour ses mets délicieux : Délice-des-Ducs ou Nacelles-de-Fées, nul ne pouvait goûter ses gâteaux divins sans pleurer de joie ! Mais dans tout le royaume, un monstre rôde : selon la légende, l'Ickabog habitait les Marécages brumeux et froids du nord du pays. On disait de cette créature qu'elle avait de formidables pouvoirs et sortait la nuit pour dévorer les moutons comme les enfants. Des histoires pour les petits et les naïfs ? Parfois, les mythes prennent vie de façon étonnante... Alors, si vous êtes courageux et voulez connaître la vérité, ouvrez ce livre, suivez deux jeunes héros déterminés et perspicaces dans une folle aventure qui changera pour toujours le sort de la Cornucopia.", "2020-12-03", 392, 3),
("661122125-5", "Une Place à Prendre", "Bienvenue à Pagford, petite bourgade en apparence idyllique. Un notable meurt. Sa place est à prendre... Comédie de mours, tragédie teintée d'humour noir, satire féroce de nos hypocrisies sociales et intimes, ce premier roman pour adultes révèle sous un jour inattendu un écrivain prodige.", "2012-09-28", 682, 4),
("483499503-8", "Eragon", "Eragon n'a que quinze ans, mais le destin de l'Empire eEragon n'a que quinze ans, mais le destin de l'Empire est désormais entre ses mains ! C'est en poursuivant une biche dans la montagne que le jeune Eragon, quinze ans, tombe sur une magnifique pierre bleue, qui s'avère être... un oeuf de dragon ! Fasciné, il l'emporte à Carvahall, le village où il vit pauvrement avec son oncle et son cousin. Il n'imagine pas alors qu'une dragonne, porteuse d'un héritage ancestral, va en éclore... Très vite, la vie d'Eragon est bouleversée. Contraint de quitter les siens, le jeune homme s'engage dans une quête qui le mènera aux confins de l'empire de l'Alagaësia. Armé de son épée et guidé par les conseils de Brom, le vieux conteur, Eragon va devoir affronter avec sa dragonne les terribles ennemis envoyés par le roi Galbatorix, dont la malveillance démoniaque ne connaît aucune limite.", "2019-09-18", 702, 5),
("743103681-7", "L'Aîné", "Une plongée dans les ténèbres : les certitudes s'évanouissent et les forces du mal règnent. Eragon et sa dragonne, Saphira, sortent à peine de la victoire de Farthen Dûr contre les Urgals, qu'une nouvelle horde de monstres surgit... Ajihad, le chef des Vardens, est tué. Nommée par le Conseil des Anciens, Nasuada, la fille du vieux chef, prend la tête des rebelles. Eragon et Saphira lui prêtent allégeance avant d'entreprendre un long et périlleux voyage vers Ellesméra, le royaume des elfes, où ils doivent suivre leur formation. Là, ils découvrent avec stupeur qu'Arya est la fille de la reine Islanzadì. Cette dernière leur présente en secret un dragon d'or, Glaedr, chevauché par un Dragonnier, Oromis, qui n'est autre que le Sage-en-Deuil, l'Estropié-qui-est-Tout, le personnage qui était apparu à Eragon lorsqu'il délirait, blessé par l'Ombre. Oromis va devenir leur maître. Le jeune Dragonnier commence sa formation. Mais il n'est pas au bout de ses découvertes. Des révélations dérangeantes entament sa confiance. Pendant longtemps, Eragon ne saura qui croire. Or, le danger n'est toujours pas écarté : à Carvahall, Roran, son cousin, a engagé le combat contre les Ra'zacs. Ceux-ci, persuadés qu'il détient l'oeuf qu'Eragon avait trouvé sur la Crête, finissent par enlever sa fiancée. Prêt à tout pour la sauver, Roran comprend cependant qu'il n'est pas de taille à les affronter. Il convainc les villageois de traverser la Crête pour rejoindre les rebelles au Surda, en guerre contre le roi de l'Empire, le cruel Galbatorix.", "2019-09-18", 810, 5),
("354312486-2", "Brisingr", "Eragon a une double promesse à tenir : aider Roran à délivrer sa fiancée, Katrina , prisonnière des Ra'zacs, et venger la mort de son oncle Garrow. Saphira emmène les deux cousins jusqu'à Helgrind, repaire des monstres. Or, depuis que Murtagh lui a repris Zar'oc, l'épée que Brom lui avait donnée, Eragon n'est plus armé que du bâton du vieux conteur. Cependant, depuis la Cérémonie du Sang, le jeune Dragonnier ne cesse de se transformer, acquérant peu à peu les fabuleuses capacités d'un elfe. Et Roran mérite plus que jamais son surnom de Puissant Marteau. Quant à Saphira, elle est une combattante redoutable. Ainsi commence cette troisième partie de l'Héritage, où l'on verra l'intrépide Nasuada, chef des Vardens, subir avec bravoure l'épreuve des Longs Couteaux ; les Vardens affronter les soldats démoniaques de Galbatorix ; Arya et Eragon rivaliser de délicates inventions magiques ; Murtagh chevauchant Thorn, son dragon rouge, batailler contre Eragon et Saphira. On s'enfoncera dans les galeries souterrainesdes nains ; on se laissera séduire par Nar Garzhvog, le formidable Urgal, et par l'énigmatique Lupusänghren, l'elfe au pelage de loup ; on retrouvera avec bonheur Oromis et Glaedr, le dragon d'or ; on constatera avec jubilation que Saphira montre toujours un goût certain pour l'hydromel. Et on saura enfin pourquoi le roman porte ce titre énigmatique : Brisingr, Feu en ancien langage.", "2019-09-18", 840, 5),
("824334341-5", "L'Héritage", "Il y a peu encore, Eragon n'était qu'un simple garçon de ferme, et Saphira, son dragon, une étrange pierre bleue ramassée dans la forêt... Depuis, le sort de plusieurs peuples repose sur leurs épaules. De longs mois d'entraînement et de combats, s'ils ont permis des victoires et ranimé l'espoir, ont aussi provoqué des pertes cruelles. Or, l'ultime bataille contre Galbatorix reste à mener. Certes, Eragon et Saphira ne sont pas seuls, ils ont des alliés : les Vardens conduits par Nasuada, Arya et les elfes, le roi Orik et ses nains, Garzhvog et ses redoutables Urgals. Le peuple des chats-garous s'est même joint à eux avec son roi, Grimrr Demi-Patte. Pourtant, si le jeune Dragonnier et sa puissante compagne aux écailles bleues ne trouvent pas en eux-mêmes la force d'abattre le tyran, personne n'y réussira. Ils n'auront pas de seconde chance. Tel est leur destin. Il faut renverser le roi maléfique, restaurer la paix et la justice en Alagaësia. Quel que soit le prix à payer.", "2019-09-18", 912, 5),
("969896727-3", "A la lueur d'une étoile inconnue", "Kira Navárez rêvait d'un monde nouveau. Elle vient de réveiller un cauchemar d'une ampleur intersidérale... Lors d'une mission de routine sur une planète inconnue, Kira découvre un organisme vivant d'origine extraterrestre. Fascinée, elle s'approche de l'étrange poussière noire. La substance s'étend sur tout son corps et commence à prendre le contrôle. Kira, en pleine transformation, va explorer les dernières limites de sa condition d'être humain. Mais quelle est l'origine de cette entité ? Quelles sont ses intentions ?La scientifique n'a pas le temps de répondre à ces questions : la guerre contre les aliens est déclarée, et Kira pourrait bien être le plus grand et le dernier espoir de l'humanité.", "2020-10-14", 848, 5),
("126301614-6", "Dormir dans un Océan d'Etoiles", "EN LUTTE CONTRE LES CAUCHEMARS, KIRA NAVÁREZ ESPERE FAIRE TRIOMPHER LA VIE. En pleine guerre interplanétaire, la vie de Kira bascule : un organisme vivant d'origine extraterrestre prend peu à peu possession de son corps, lui conférant des pouvoirs quasi divins. Et ces nouvelles aptitudes bouleversent profondément son identité. Tout son être évolue vers un intelligence supérieure hybride. La scientifique se donne une mission ambitieuse : rétablir la paix entre humains et aliens. La lutte pour un monde nouveau ne fait que commencer.", "2021-09-22", 562, 5),
("404759164-5", "La Meute", "Les éditions Bragelonne sont fières de vous faire découvrir la suite des aventures du Visiteur du Futur. Dans ce roman-feuilleton, mitonné par François Descraques et Slimane-Baptiste Berhoun, sont dévoilés (entre autres) les mystères du passé du Visiteur du Futur ! Comment dire non à une telle proposition (même quand on connaît les risques encourus par quiconque se décide à suivre le Visiteur...) ? La série Le Visiteur du Futur a débuté sur le Net en 2009 et a connu un fulgurant succès. Depuis la saison 3, la série est coproduite par Ankama et le studio 4. 0 de France Télévisions Nouvelles Ecritures. Peu de temps après le lancement de la saison 4 début 2014, Le Visiteur du Futur avait engendré plus de trente-trois millions de vues sur la Toile. Ankama assure également l'édition des DVD, de la bande originale, d'une bande dessinée, d'une gamme textile ainsi que du jeu de société.", "2022-07-06", 468, 6),
("019849199-9", "Sirius", "Alors que le monde se meurt, Avril, une jeune fille, tente tant bien que mal d'élever son petit frère, Kid. Réfugiés au coeur d'une forêt, ils se tiennent à l'écart des villes et de la folie des hommes... jusqu'au jour où le mystérieux passé d'Avril les jette brutalement sur la route. Pourchassés, il leur faut maintenant survivre dans cet univers livre au chaos et à la sauvagerie. Mais sur leur chemin, une rencontre va tout bouleverser : Sirius. Avec ce road trip post-apocalyptique, Stéphane Servant signe un grand roman d'aventure, brut et haletant.", "2017-08-23", 480, 7),
("455261908-X", "Le Neveu du Magicien", "Polly trouve parfois que la vie à Londres n'est guère passionnante... jusqu'au jour où elle rencontre son nouveau voisin, Digory. Il vit avec sa mère, gravement malade, et un vieil oncle au comportement étrange. Celui-ci force les deux enfants à essayer des bagues magiques qui les transportent dans un monde inconnu. Commence alors la plus extraordinaire des aventures...", "1955-05-02", 208, 1),
("368909556-5", "Le Lion, la Sorcière Blanche et l'Armoire Magique", "Narnia, un royaume condamné à un hiver éternel, attend d'être libéré d'une emprise maléfique. L'arrivée de quatre enfants fait renaître l'espoir : s'ils trouvent Aslan, le grand Lion, les pouvoirs de la Sorcière Blanche pourraient enfin être anéantis.", "1950-10-16", 208, 1),
("082461365-1", "Le Cheval et son Ecuyer", "Shasta, maltraité par le pêcheur qui l'a recueilli et élevé, quitte le pays de Calormen en compagnie de Bree, un cheval doué de parole. Ils n'ont qu'un espoir : rejoindre le merveilleux royaume de Narnia... En chemin, ils rencontrent une jeune fille de noble naissance, Aravis, qui fuit un mariage forcé. D'aventure en aventure, les deux héros perceront-ils le mystère qui entoure la naissance de Shasta ?", "1954-09-06", 240, 1),
("187618499-X", "Le Prince Caspian", "Peter, Susan, Edmund et Lucy sont sur le point de se séparer pour entamer une nouvelle année scolaire. Ils attendent le train qui doit les conduire en pension quand, tout à coup, ils sont transportés dans le pays de Narnia où ils ont régné autrefois. Mais si, pour eux, une année seulement s'est écoulée, dans leur ancien royaume des siècles ont passé. Le palais royal est en ruines. Parviendront-ils à ramener la paix dans le monde magique de Narnia ?", "1951-10-15", 244, 1),
("602523044-7", "L'Odyssée du Passeur d'Aurore", "Pour Edmund et Lucy, leur cousin Eustache Clarence est le garçon le plus insupportable d'Angleterre. Mais le jour où les trois enfants entrent dans un tableau et sont précipités dans les flots, à quelques brasses du navire de Caspian, roi de Narnia, Eustache perd sa belle assurance. Quelle part prendra-t-il à l'extraordinaire aventure qui les attend ?", "1952-09-15", 272, 1),
("338427321-4", "Le Fauteuil d'Argent", "Pour Jill et Eustache, la vie est dure à l'école expérimentale ! Un jour, voulant échapper à des élèves qui les brutalisent, les enfants ouvrent la petite porte du jardin. Au lieu de la lande morne et grise, ils découvrent une contrée radieuse, le pays d'Aslan, le grand lion. Celui-ci leur confie une mission : retrouver Rilian, prince héritier de Narnia, enlevé des années plus tôt par un horrible serpent... Le monde enchanté de Narnia, le pays imaginaire, vous attend.", "1953-09-07", 272, 1),
("526953057-0", "La Dernière Bataille", "Seul, captif, désespéré, le dernier roi de Narnia appelle à son secours les enfants qui, tant de fois, par le passé, ont sauvé le royaume de la destruction. Jill et Eustache se retrouvent donc, à nouveau, transportés dans l'univers enchanté de Narnia dont ils rêvent chaque jour en secret. Mais parviendront-ils, cette fois, à éviter le pire ? Car cette aventure pourrait bien être la dernière... Le monde enchanté de Narnia, le pays de l'imaginaire, vous attend.", "1956-09-04", 224, 1),
("657141567-4", "A l'Aventure, Compagnons", "Au commencement, il y avait un barbare en quête d'action, un voleur aux motivations troubles, une jeune et candide elfe des bois, un nain revêche, une magicienne entretenant une amitié suspecte avec un ogre peu locace, et un ranger aussi ambitieux qu'inexpérimenté. Rien ne les prédestinait à se rencontrer. Rien, sinon une insatiable faim d'aventure. Direction le Donjon de Naheulbeuk, là où tout a débuté.", "2014-11-05", 510, 8),
("834499835-2", "La Couette de l'Oubli", "Jouez hautbois, résonnez trompettes, les héros du Donjon de Naheulbeuk reprennent du service ! Ils se croyaient sortis d'affaire après avoir rempli leur contrat... que nenni ! En rapportant à leur commanditaire, le sorcier Gontran Théogal, la douzième statuette de Gladeulfeurha, ils ont œuvré à leur insu pour l'avènement de Dlul, le dieu du sommeil et de l'ennui, qui menace d'engloutir le monde dans la Grande Couette de l'Oubli Éternel. Il va bien falloir que quelqu'un s'y colle, mais entre les guerres de religion qui agitent les terres de Fhang, les objectifs incertains des Oracles et le déplorable humour nain, ça s'annonce compliqué !", "2009-10-07", 376, 8),
("390939200-8", "L'Orbe de Xaraz", "Après leurs précédentes mésaventures, les héros du Donjon de Naheulbeuk pensaient enfin pouvoir se la couler douce, mais il faut croire qu'une telle activité ne figure pas au programme de leur fiche de personnage l Jugez plutôt : l'un des leurs est resté sur le carreau. et aucun d'entre eux ne maîtrise le sort de résurrection. Direction Waldorg, la cité des magiciens, qui regorge de gens compétents, mais un brin susceptibles. Avec leur chance coutumière, nos aventuriers arrivent une fois encore au mauvais endroit et au plus mauvais moment...", "2011-03-09", 444, 8),
("406641788-4", "Le Conseil de Suak", "Le grand n'importe quoi règne une fois de plus en terres de Fangh : chacun semble pris de folie, au point qu'une guerre généralisée menace d'éclater. Cela n'est pas du tout du goût de la jolie Elfe tout juste promue reine et quelque peu perdue au milieu du chaos. Elle doit pourtant régner, et faire appliquer des décisions à tout le moins contestées... Il faut croire que nos héros ne sont bons qu'à explorer des donjons. Ce qui tombe très bien, car pour remettre un peu d'ordre dans tout ce bazar, il ne serait pas inutile d'aller en visiter un, encore plus vieux et plus périlleux que celui de Naheulbeuk !", "2012-10-10", 462, 8),
("715628208-5", "Chaos Sous la Montagne", "Alors que la guerre fait rage en terre de Fangh, menaçant de révéler au grand jour l'incompétence notoire des rescapés du donjon de Naheulbeuk, ces derniers se voient confier une mission qui les mènera loin sous terre, au plus profond des mines des nains. Comme tout un chacun le sait, ces infatigables travailleurs ont un sens de l'humour à peu près aussi développé que celui de leur verticalité, alors autant dire que la partie est serrée... Et pour ne rien arranger, nos anti-héros ont à leur trousses un magicien énervé et bien décidé à prendre sa revanche !", "2016-04-13", 509, 8),
("630445577-1", "Les Veilleurs de Glargh", "Dans la cité de Glargh, de fiers soldats font régner l'ordre. Bon, ça consiste surtout à rédiger des rapports sur les dégâts provoqués par les aventuriers, mais voir les uniformes rouges dans les rues rassure les badauds. L'unité Furets est considérée comme de la bleusaille incompétente. Et plutôt mal assortie : un charcutier, un ingénieur alcoolique, une amazone à la beigne facile, un médecin bavard, un Nain, une magicienne... Quand on leur confie enfin une enquête digne de ce nom - un double meurtre, rien que ça -, la joie retombe vite : et si on les avait engagés pour qu'ils échouent ?", "2021-05-19", 512, 8),
("455967781-6", "La Ballade du Serpent et de l'Oiseau Chanteur", "Dévoré d'ambition, poussé par la compétition, il va découvrir que la soif de pouvoir a un prix. C'est le matin de la Moisson qui doit ouvrir la dixième édition annuelle des Hunger Games. Au Capitole, Coriolanus Snow, dix-huit ans, se prépare à devenir pour la première fois mentor aux Jeux. L'avenir de la maison Snow, qui a connu des jours meilleurs, est désormais suspendu aux maigres chances de Coriolanus. Il devra faire preuve de charme, d'astuce et d'inventivité pour faire gagner sa candidate. Mais le sort s'acharne. Honte suprême, on lui a confié le plus misérable des tributs : une fille du district Douze. Leurs destins sont désormais liés. Chaque décision peut les conduire à la réussite ou à l'échec, au triomphe ou à la ruine. Dans l'arène, ce sera un combat à mort.", "2020-05-20", 608, 9),
("178191153-3", "Hunger Games", "Dans un futur sombre, sur les ruines des Etats-Unis, un jeu télévisé est créé pour contrôler le peuple par la terreur. Douze garçons et douze filles tirés au sort participent à cette sinistre téléréalité, que tout le monde est forcé de regarder en direct. Une seule règle dans l'arène : survivre, à tout prix. Quand sa petite soeur est appelée pour participer aux Hunger Games, Katniss n'hésite pas une seconde. Elle prend sa place, consciente du danger. A seize ans, Katniss a déjà été confrontée plusieurs fois à la mort. Chez elle, survivre est comme une seconde nature.", "2015-06-04", 420, 9),
("976152866-9", "La Révolte", "Après le succès des derniers Hunger Games, le peuple de Panem est impatient de retrouver Katniss et Peeta pour la Tournée de la victoire. Mais pour Katniss, il s'agit surtout d'une tournée de la dernière chance. Celle qui a osé défier le Capitole est devenue le symbole d'une rébellion qui pourrait bien embraser Panem. Si elle échoue à ramener le calme dans les districts, le président Snow n'hésitera pas à noyer dans le sang le feu de la révolte. A l'aube des Jeux de l'Expiation, le piège du Capitole se referme sur Katniss...", "2015-06-04", 428, 9),
("256597467-1", "L'Embrasement", "Contre toute attente, Katniss a survécu une seconde fois aux Hunger Games. Mais le Capitole crie vengeance. Katniss doit payer les humiliations qu'elle lui a fait subir. Et le président Snow a été très clair : Katniss n'est pas la seule à risquer sa vie. Sa famille, ses amis et tous les anciens habitants du district Douze sont visés par la colère sanglante du pouvoir. Pour sauver les siens, Katniss doit redevenir le geai moqueur, le symbole de la rébellion. Quel que soit le prix à payer.", "2015-06-04", 460, 9),
("416809666-1", "Incarceron", "INCARCERON, UNE PRISON A NULLE AUTRE PAREILLE: ELLE DECIDE DE QUI DOIT VIVRE... et de qui doit mourir. Rien ne peut lui échapper. Finn est prisonnier d'Incarceron, un univers pénitentiaire plein de dangers, de trahisons et de menaces. Il tente par tous les moyens de s'évader. Claudia, la fille du directeur d'Incarceron, vit à l'Exterieur, dans un royaume figé au XVIIIe siècle. Piégée par une existence qu'elle n'a pas choisi, elle cherche à percer les mystères de la Prison. Un jour, Finn et Claudia trouvent une clé, qui permet à chacun de communiquer avec l'autre. Alors surgit un espoir, la possibilité d'échapper à un destin tout tracé dont ils ne veulent pas.", "2010-06-03", 498, 9),
("460648236-2", "Le Cygne Noir", "Le monde va-t'il s'écrouler? C'est ce que ce demande Claudia, Finn, Keiro et Atia. Même s'ils ne se trouvent pas au même endroit, ils subissent tous des menaces qui pourraient bien leur couter la vie. Mais au fond de tous les complots, leur ennemi est toujours le même : La prison.", "2010-10-21", 515, 9),
("455529788-1", "Les Maîtres des Brisants", "Lorsqu'ils embarquent comme stagiaires sur le vaisseau de Chien-de-la-lune, Xâvier le stratège, Mörgane la devineresse et leur ami Mârk ignorent la périlleuse mission de leur capitaine : contrer la flotte de guerre du Khan qui menace de prendre le contrôle de la galaxie. Sur eux repose désormais la survie de l'empire...", "2019-06-06", 645, 1),
("100928450-9", "Le Livre des Etoiles", "Guillemot de Troïl est un garçon du Pays d'Ys, situé entre le monde réel et le Monde Incertain. D'où lui viennent ses dons pour la sorcellerie que lui enseigne Maître Qadehar ? Qu'est devenu Le Livre des Étoiles, inexorablement lié à son destin, et qui renferme le secret de puissants sortilèges ?", "2022-01-06", 880, 3),
("498185685-7", "Presque Minuit", "Six orphelins, une ville en danger. Prix de lancement à 7, 99 au lieu de 12, 99 jusqu'au 26 janvier ! Paris, 1889. Six orphelins en cavale, devenus gamins des rues par la force des événements, volent et détroussent les passants. Alors que l'Exposition universelle débute, ils font l'erreur de dérober le mauvais objet aux mauvaises personnes. Leurs mésaventures aux quatre coins de la ville les amèneront à découvrir les secrets d'un monde magique où s'affrontent créatures mythologiques, sorcières et terrifiants ennemis mécaniques. Plus que jamais, Moignon, Allumette, Bègue, Morve, Boiteux et Pleurs devront se battre pour sauver leur vie et celle des habitants de la capitale.", "2018-01-25", 305, 10),
("117332677-4", "Ordo", "Le pouvoir est dans le sang. New York, de nos jours. Dans l'ombre, les cinq familles de l'Ordo Magicae utilisent l'Obscur, une magie issue d'un monde démoniaque, pour étendre leur influence et diriger leurs affaires en ville. Elles sont liées par le sang d'un même ancêtre, Ambrose Donosius, 356 ans et toujours vivant... jusqu'à aujourd'hui : le patriarche de cette ""mafia de la magie noire"" est tué lors d'un attentat surnaturel en plein Manhattan. Cinq jeunes gens, fils et filles des dirigeants des cinq familles, sans perspective d'avenir face à des parents immortels, voient dans l'événement l'occasion de planifier un casse. Le cambriolage magique du siècle. Ils ont moins d'une semaine avant l'inhumation pour se préparer à infiltrer la Loge, le sanctuaire privé d'Ambrose. Leur objectif : voler la couronne d'un roi légendaire leur permettant de réaliser leurs voux les plus secrets. De quoi devenir rois à la place des rois et, enfin, régner sur l'Ordo. Par l'auteur de Presque minuit et Au Crépuscule.", "2020-10-01", 309, 10),
("955504630-1", "Une Vie Sans Fin", "Cette année, ma mère a fait un infarctus et mon père est tombé dans un hall d'hôtel. J'ai commencé à devenir un habitué des hôpitaux parisiens. En revenant de la clinique, Romy est entrée dans la cuisine avec un sourcil plus haut que l'autre. - Papa, si je comprends bien, tout le monde meurt ? Il va y avoir grand-père et grand-mère, puis ce sera maman, toi, moi, les animaux, les arbres et les fleurs ? Romy me regardait fixement comme si j'étais Dieu. Elle ajouta alors une phrase très aimable : - Papa, je n'ai pas envie que tu meures... - T'inquiète pas chérie, lui ai-je répondu, à partir de maintenant, plus personne ne meurt. Il ne me restait plus qu'à tenir cette promesse inconsidérée.", "2019-09-25", 343, 4),
("304173873-2", "Vers les Etoiles", "Installés sur la planète Détritus depuis des décennies, les derniers survivants de l'espèce humaine tentent de résister aux attaques répétées des Krell, un mystérieux peuple extraterrestre. Dans ce monde rythmé par les batailles spatiales, les pilotes sont vénérés comme des héros et font frissonner les nouvelles générations prêtes à en découdre. Parmi eux, Spensa rêve depuis l'enfance de piloter son propre vaisseau et de prouver son courage. Car elle est la fille d'un lâche. Son père, l'un des meilleurs pilotes de la Force de Défense Rebelle, a été exécuté lors de la bataille d'Alta après avoir déserté le combat, et cet héritage pourrait bien coûter à Spensa sa place au sein de l'école de pilotage. Plus que jamais déterminée à voler, elle redouble d'effort pour trouver sa place au sein d'une escouade de pilotes et convaincre sa hiérarchie que la lâcheté n'est pas héréditaire. Sa découverte accidentelle d'un vaisseau depuis longtemps oublié pourrait bien changer la donne...", "2021-09-15", 736, 11),
("935591713-9", "Seul sur Mars", "« Un des meilleurs thrillers que j'aie lus depuis longtemps : Apollo 13 puissance dix ! » Douglas Preston « Impossible à lâcher ! Un mélange rare de bonne histoire, de personnages réalistes et de précision technique fascinante. » Chris Hadfield, commandant de la Station spatiale internationale « Robinson Crusoé sur Mars, au XXIe siècle : fort, résistant et du cran à revendre. » Steve Berry, auteur de L'Héritage des Templiers « La science à la portée de tous, pour un suspense fascinant. » Publishers Weekly Mark Watney est l'un des premiers humains à poser le pied sur Mars. Il pourrait bien être le premier à y mourir. Lorsqu'une tempête de sable mortelle force ses coéquipiers à évacuer la planète, Mark se retrouve seul et sans ressources, irrémédiablement coupé de toute communication avec la Terre. Pourtant Mark n'est pas prêt à baisser les bras. Ingénieux, habile de ses mains et terriblement têtu, il affronte un par un des problèmes en apparence insurmontables. Isolé et aux abois, parviendra-t-il à défier le sort ? Le compte à rebours a déjà commencé... Seul sur Mars est adapté au cinéma par Ridley Scott, dont la sortie est prévue en 2015.", "2014-09-19", 408, 6),
("400498162-X", "Lion", "C'est un jour comme un autre dans la vie de Saroo. Le garçon, âgé de cinq ans, est dans une gare du fin fond de l'Inde en train de ramasser quelques pièces lorsqu'il monte dans un train à quai. Le lendemain, Saroo se réveille à Calcutta. Dans l'immense ville, il est complètement seul, sans aucun papier. Il est recueilli par un orphelinat où, quelques mois plus tard, un couple d'Australiens va l'adopter. Saroo grandit, mais, depuis l'Australie, il pense toujours à sa famille biologique. Pendant 25 ans, il scrute les villages indiens sur Internet, à la recherche d'images familières. Et là, le miracle se produit. L'orphelin va alors se lancer dans un long voyage pour enfin retrouver sa mère et rentrer à la maison. L'émouvante histoire d'un garçon qui, d'un continent à l'autre, a recherché sa mère pendant 25 ans.", "2018-04-11", 320, 12),
("421595393-6", "Avant Toi", "Ses jours sont comptés. Mais quand on aime, on ne compte pas. Lou est une fille ordinaire qui mène une vie monotone à souhait. Quand elle se retrouve au chômage, dans ce trou paumé de l'Angleterre dont elle n'est jamais sortie, Lou accepte un contrat de six mois pour tenir compagnie à un handicapé. Malgré l'accueil glacial qu'il lui réserve, Lou va découvrir en lui un jeune homme exceptionnel. Mais depuis l'accident qui l'a rendu tétraplégique, Will veut mettre fin à ses jours. Lou n'a que quelques mois pour lui redonner goût à la vie. ""Cette histoire d'amour inespérée est un véritable choc émotionnel - sortez les mouchoirs"". Elle ""Un roman poignant qui amorce une belle réflexion sur l'art de donner la mort. Mascara waterproof indispensable"". Marie Claire", "2021-01-06", 528, 13),
("490879362-X", "Après Toi", "N'oublie jamais que tu n'as qu'une seule vie... Lou a promis à l'homme qu'elle aimait de vivre chaque jour comme si c'était le dernier. Mais sans lui, le monde paraît bien sombre et elle peine à tourner la page. Sa vie londonienne ne la rend pas heureuse : dans le bar d'aéroport où elle travaille sous les ordres d'un patron tyrannique, elle regarde chaque jour les autres s'envoler tandis qu'elle reste désespérément clouée au sol... Honorer la promesse faite à Will lui paraît impossible. Pourtant, au moment où elle croit avoir touché le fond, sa rencontre inattendue avec Lily sera peut-être le nouveau départ qu'elle espérait. Et le meilleur moyen de tenir sa promesse.", "2021-02-03", 473, 13),
("427632648-6", "Apres Tout", "Il est toujours temps de découvrir qui tu es... Quand Lou s'envole pour New York, elle est certaine de pouvoir vivre pleinement cette aventure malgré les milliers de kilomètres qui la séparent de Sam. Elle rejoint la très fortunée famille Gopnik, se jette à corps perdu dans son nouveau travail, et découvre les joies de la vie new-yorkaise. C'est alors que sa route croise celle de Josh, qui éveille en elle des souvenirs enfouis. Troublée par cette rencontre, Lou s'évertue à rassembler les deux parties de son coeur séparées par un océan. Mais le dilemme auquel elle est confrontée menace de faire voler en éclats son fragile équilibre. Le moment n'est-il pas venu de se demander qui elle est vraiment ?", "2021-03-10", 571, 13),
("505488956-6", "Kaleb", "Kaleb Hellgusson a 18 ans et la beauté du diable. De lui, on sait peu de chose, si ce n'est que sa mère est morte en couches et que, depuis l'éruption du volcan islandais Eyjafjöll, il se découvre un don d'empathe. Il est capable de se brancher sur les émotions d'autrui pour le meilleur comme pour le pire. Le pire peut conduire quiconque se met en travers de sa route au suicide ou à la folie… Qui est vraiment Kaleb ? D'où lui vient ce don et jusqu'où évoluera-t-il ? Pourquoi un petit groupe de scientifiques le traque-t-il où qu'il aille ? A-t-il raison de les fuir ? Au cours de son échappée, qui le conduira en Irlande et en Islande, Kaleb rencontrera d'autres personnes aux aptitudes troublantes (dont une succube au charme de laquelle il succombera, bien qu'ils soient dans des camps opposés), qui font toutes partie d'un projet qui les dépasse, les lie intimement à l'Islande, et qui existe depuis que l'homme sait écrire…", "2012-06-14", 290, 14),
("945346022-2", "Abigail", "Le mâle dans la peau... SAISON 2. Le pouvoir... Tel est tout l'enjeu de cette nouvelle saison. Mais qui le détient vraiment ? Kaleb qui, depuis qu'on lui a fait ce mystérieux tatouage, se laisse envahir par le Mal et entraîne Abigail en enfer ? Ou bien Abigail qui s'est, malgré le danger, jetée corps et âme dans cette folle passion avec Kaleb et abdique chaque jour un peu plus d'elle-même pour une noirceur qui la fascine et la grise ? Lequel des deux amants a vraiment l'ascendant sur l'autre et le pousse à aller toujours plus loin ? Les apparences sont parfois trompeuses... Au final, ne sont-ils pas juste les pions d'une partie maléfique qui a débuté il y a bien longtemps, quand sont apparus les premiers Enfants du Volcan, et dont la finale se jouera maintenant, qu'ils le veuillent ou non ? Le deuxième tome d'une trilogie de sang et de ténèbres qui fera voyager les jeunes adultes dans les terres les plus reculées d'Islande et d'Irlande, et qui fera renaître des légendes oubliées, dont celle de l'elfe noir.", "2013-02-28", 223, 14),
("160343028-8", "Fusion", "Tout est bien qui finit mal... Saison 3. La prophétie du volcan prédit l'avènement d'une nouvelle ère, initiée par l'Élu... Or qui, de Kaleb, Abigail, le colonel Bergsson ou encore Mary-Ann bouleversera à jamais le destin des enfants du volcan ? Et si la mort est la clé, tous ne sont-ils pas des morts en sursis ? Seul le Livre du volcan peut apporter des réponses à Kaleb et lui permettre de survivre au volcan qu'il a réveillé. Mais le tenir entre ses mains peut se révéler plus destructeur que tout... Ce dernier tome de la trilogie de Kaleb lève le voile sur une mythologie qui prend racine bien au-delà de ce que vous pouviez imaginer. Plus que jamais, il est question de pouvoir, de sombre passion, de manipulation machiavélique et du parfum sulfureux d'une saga millénaire dont le dénouement pourrait bien vous faire penser que tout est bien qui finit mal.", "2013-11-14", 320, 14),
("898581942-9", "L'Ombre d'Emily", "Elle est votre meilleure amie. Elle connaît tous vos secrets. Et c'est ce qui la rend si dangereuse. Tout oppose en apparence Stephanie, une jeune veuve sans emploi qui partage son temps entre son fils Miles et la rédaction de son ""blog de maman"", et Emily, une femme d'affaires sophistiquée et mariée. Elles s'entendent pourtant à merveille et ont noué, dans leur petite ville du Connecticut, une amitié aussi forte que celle qui lie leurs deux garçons de cinq ans. Lorsque Emily demande à Stephanie de récupérer son fils Nicky à la sortie de l'école, celle-ci accepte tout naturellement. Les jours passent et Emily ne revient pas. Suicide ? Meurtre ? Peu à peu, le vernis des apparences se craquelle et les masques tombent : tout doit disparaître...", "2018-09-06", 404, 15),
("425042488-X", "La Servante Ecarlate", "Les meilleurs récits dystopiques sont universels et intemporels. [... ]La Servante écarlate éclaire d'une lumière terrifiante l'Amérique contemporaine.  Télérama. Devant la chute drastique de la fécondité, la république de Galaad, récemment fondée par des fanatiques religieux, a réduit au rang d'esclaves sexuelles les quelques femmes encore fertiles. Vêtue de rouge, Defred, servante écarlate parmi d'autres à qui l'on a ôté jusqu'à son nom, met donc son corps au service de son Commandant et de sa femme. Le soir, dans sa chambre à l'austérité monacale, elle songe au temps où les femmes avaient le droit de lire, de travailler... En rejoignant un réseau clandestin, elle va tout tenter pour recouvrer sa liberté. Paru en 1985, La Servante écarlate est aujourd'hui un classique de la littérature anglo-saxonne et un étendard de la lutte pour les droits des femmes. Si la série adaptée de ce chef-d'oeuvre a donné un visage à Defred, celui d'Elisabeth Moss, cette nouvelle traduction révèle toute sa modernité ainsi que la finesse et l'intelligence de Margaret Atwood. La Servante est un roman polysémique, empli de références littéraires et bibliques, drôle même... et c'est à nous, lecteurs, de découvrir ses multiples facettes.", "2021-01-14", 576, 16),
("054526495-2", "Les Testaments", "La suite de La Servante écarlate. Quinze ans après les événements racontés dans La Servante écarlate, roman dystopique désormais culte, le régime théocratique de la République de Galaad a toujours la mainmise sur le pouvoir, mais certains signes ne trompent pas : il est en train de pourrir de l'intérieur. A ce moment crucial, les vies de trois femmes radicalement différentes convergent, avec des conséquences potentiellement explosives. Avec Les Testaments, Margaret Atwood poursuit l'histoire de Galaad dans un savant mélange de suspense, de vivacité et de virtuosité.", "2021-05-12", 672, 16),
("217291886-5", "Comment Braquer une Banque sans Perdre son Dentier", "Ils sont trois femmes, deux hommes : Märtha, Stina, Anna-Greta, le Génie, et le Râteau, chacun 80 ans au compteur. Ils chantent dans la même chorale et dépérissent dans la même maison de retraite à Stockholm. Nourriture insipide, traitement lamentable, restrictions constantes, pas étonnant que les résidents passent l'arme à gauche... Ils ne vivront pas un jour de plus dans ce mouroir. Un brin rebelles et idéalistes, les cinq comparses décident de se lancer dans le grand banditisme. Avec leurs cheveux blancs et leurs déambulateurs, ils s'apprêtent à commettre le casse du siècle. Mais l'aventure s'emballe et rien ne va se passer comme prévu...", "2015-02-05", 480, 15),
("244349541-8", "Red Sparrow", "A l'heure où la Russie de Poutine inquiète de plus en plus les Occidentaux, la jeune Dominika est recrutée contre sa volonté par les services secrets russes. Ancienne ballerine dont la carrière a été brisée par une chute, elle s'entraîne à utiliser ses charmes et découvre bientôt l'ampleur de son pouvoir... Jusqu'à devenir l'un des meilleurs agents. Sa première cible : un agent infiltré de la CIA en Russie. Entre manipulation et séduction, un jeu dangereux s'installe entre eux. Après plusieurs décennies passées à la CIA, Jason Matthews nous offre un premier roman captivant. Sa connaissance du terrain et de la géopolitique donne un aspect brut à ce grand livre d'actualité où la politique, le suspense et l'amour se conjuguent à merveille. Traduit de l'anglais (Etats-Unis) par Hubert Tézenas", "2018-03-22", 640, 17),
("252230642-1", "Crazy Rich à Singapour", "Lorsque la New-Yorkaise Rachel Chu débarque à Singapour au bras de son boyfriend Nicholas Young, venu assister au mariage de son meilleur ami, elle pense juste passer de paisibles vacances en amoureux. Mais le beau Nick a «oublié» de lui dire que sa famille est l'une des plus fortunées d'Asie, que le mariage prévu est l'Evénement de l'année, et qu'il est l'héritier le plus convoité de tout l'Extrême-Orient ! Pour Rachel, le séjour de rêve se transforme en un véritable parcours du combattant - en stilettos et robes couture... Sino-américaine, pauvre et roturière : bonne chance !", "2015-04-29", 524, 18),
("315555255-7", "The Haunting of Hill House", "Construite par un riche industriel du XIXe siècle, Hill House est à l'image de son créateur: labyrinthique, monstrueuse, ténébreuse à souhait. De plus, on la dit hantée. Fasciné par les phénomènes paranormaux, le docteur Montagu invite des sujets réceptifs au surnaturel à passer l'été à Hill House afin de mener une enquête. Une enquête qui va tourner au cauchemar... ", "2019-09-01", 272, 19),
("265113087-7", "NOSFERA2", "Au volant de sa Rolls-Royce Wraith immatriculée Nosfera2, Charles Manx enlève régulièrement des enfants pour les conduire à Christmasland où Noël est éternel. Mais à quel prix... Sur sa bicyclette, Vic McQueen retrouve tout ce qui est perdu, personnes disparues ou objets égarés. Quand le face à face entre Manx et McQueen devient inévitable, deux mondes vont s'affronter, peuplés d'images sorties de nos cauchemars les plus obsédants.", "2015-11-11", 763, 8),
("503839284-9", "Le Journal d'Anne Franck", "Je vais pouvoir, j'espère, te confier toutes sortes de choses, comme je n'ai encore pu le faire à personne, et j'espère que tu me seras d'un grand soutien. En 1942, la jeune Anne Frank a 13 ans. Elle vit heureuse à Amsterdam avec sa soeur Margot et ses parents, malgré la guerre. En juillet, ils s'installent clandestinement dans ""l'Annexe"" de l'immeuble du 263, Prinsenchracht. En 1944, ils sont arrêtés sur dénonciation. Anne est déportée à Auschwitz, puis à Bergen-Belsen, où elle meurt du typhus au début de 1945, peu après sa soeur. Son journal, qu'elle a tenu du 12 juin 1942 au 1er août 1944, est un des témoignages les plus bouleversants qui nous soient parvenus sur la vie quotidienne d'une famille juive sous le joug nazi. Depuis la première publication de ce journal aux Pays-Bas en 1947, la voix de cette jeune fille pleine d'espoir hante des millions de lecteurs dans le monde entier.", "2017-06-07", 360, 11),
("628715773-9", "La Communauté de l'Anneau", "Dans les vertes prairies du Comté, les Hobbits, ou Demi-Hommes, vivaient en paix... jusqu'au jour fatal où l'un d'entre eux, au cours de ses voyages, entra en possession de l'Anneau Unique aux immenses pouvoirs. Pour le reconquérir, Sauron, le Seigneur Sombre, va déchaîner toutes les forces du Mal... Frodo, le Porteur de l'Anneau, Gandalf, le magicien, et leurs intrépides compagnons réussiront-ils à écarter la menace qui pèse sur la Terre du Milieu ?", "2019-10-03", 722, 1),
("998528293-0", "Les Deux Tours", "Dispersée dans les terres de l'Ouest, la Fraternité de l'Anneau affronte les périls de la guerre, tandis que Frodo, accompagné du fidèle Sam, poursuit une quête presque désespérée : détruire l'Anneau Unique en le jetant dans les crevasses de l'Orodruin, le Mont Destin. Mais aux frontières du Pays de Mordor, une mystérieuse créature les épie... pour les perdre ou pour les sauver ?", "2019-10-03", 596, 1),
("057453797-X", "Le Retour du Roi", "Le royaume de Gondor s'arme contre Sauron, le Seigneur Sombre, qui veut asservir tous les peuples libres, Hommes et Elfes, Nains et Hobbits. Mais la vaillance des soldats de Minas Tirith ne peut rien désormais contre la puissance maléfique de Mordor. Un fragile espoir, toutefois, demeure : Frodo, le Porteur de l'Anneau, s'approche jour après jour de la montagne où brûle le feu du destin, seul capable de détruire l'Anneau Unique et de provoquer la chute de Sauron...", "2019-10-03", 721, 1)
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

INSERT INTO series (nomSerie) VALUES ("Harry Potter"),("Les Animaux Fantastiques"),("L'Héritage"),("Idéalis"),("Les Chroniques de Narnia"),("Le Donjon de Naheulbeuk"),("Hunger Games"),("Incarceron"),("Skyward"),("Avant Toi"),("Kaleb"),("La Servante Ecarlate"),("Crazy Rich Asians"),("Le Seigneur des Anneaux");

INSERT INTO livres_series (ISBN_livre, id_serie, position) VALUES ("018474911-5", 1, 1),
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

INSERT INTO livres_auteurs (ISBN_livre, id_auteur) VALUES ("018474911-5", 1),
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

INSERT INTO livres_genres (ISBN_livre, id_genre) VALUES ("018474911-5", 1),
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
		    SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Existe déjà";
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
		    SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Existe déjà";
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
		    SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Existe déjà";
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
		    SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Existe déjà";
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
		    SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Existe déjà";
        END IF;
	END //
DELIMITER ;