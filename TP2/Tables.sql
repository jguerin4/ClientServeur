DROP TABLE Voitures;
DROP TABLE Factures;

CREATE TABLE Voitures
(
	no_serie		INTEGER			NOT NULL,
	marque			VARCHAR(30)		NOT NULL,
	modele			VARCHAR(30)		NOT NULL,
	poids			FLOAT			NOT NULL,
	prix			FLOAT			NOT NULL,
	couleur			VARCHAR(30)		NOT NULL,
	annee			INTEGER			NOT NULL,
	PRIMARY KEY(no_serie)
);

CREATE TABLE Factures
(
	no_factures		INTEGER			NOT NULL,
	montant			FLOAT			NOT NULL,
	nomAcheteur		VARCHAR(60)		NOT NULL,
	PRIMARY KEY(no_factures)
);

commit;