  DROP TABLE tournoiEnCours;
  DROP TABLE typeSport;
  DROP TABLE equipe;
  DROP TABLE participant; 
  DROP TABLE users;


  CREATE TABLE typeSport (
    id NUMBER(10) NOT NULL,
    nom VARCHAR(100) NOT NULL,  
    nbMembre NUMBER(2) NOT NULL,
    PRIMARY KEY (id)
  );

  CREATE TABLE tournoiEnCours(
    id NUMBER(10) NOT NULL,
    nom VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
  );

  CREATE TABLE equipe (
    id NUMBER(10) NOT NULL,
    nom VARCHAR(100) NOT NULL,
    localite VARCHAR(100) NOT NULL,
    typeSportID NUMBER(10) NOT NULL,
    nbMembre Number(2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(typeSportID) REFERENCES typeSport(id)
  );

  CREATE TABLE participant (
    id NUMBER(10) NOT NULL,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    age NUMBER(2) NOT NULL,
    sexe VARCHAR(1) NOT NULL constraint sexe check (sexe='f' OR sexe='m'),
    localite VARCHAR(100) NOT NULL,
    equipeID NUMBER(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (equipeID) REFERENCES equipe(id)
  );

  CREATE TABLE users (
    id NUMBER(10) NOT NULL,
    pseudo VARCHAR(100) NOT NULL,
    mdp VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
  );


  INSERT INTO users VALUES (001, 'admin', 'admin');
  INSERT INTO participant VALUES (001, 'dupont', 'charles', 15,'m');
  INSERT INTO equipe VALUES (001, 'france', 'France', 1, 11);

  INSERT INTO tournoiEnCours VALUES (01, 'Coupe de la league');
  INSERT INTO typeSport VALUES (1, 'football', 11);
  INSERT INTO typeSport VALUES (2, 'volley ball', 6);
  INSERT INTO typeSport VALUES (3, 'basket', 5);
  INSERT INTO typeSport VALUES (4, 'handball', 7);



/** Equipe **/
Select * From equipe;

INSERT INTO equipe VALUES();

ALTER TABLE equipe 




