package bank;

import java.util.*;

/**
* Cette classe g�re un seul compte
*/
public class Compte {

// La position courante du compte
private Position position;

// Constructeur
Compte(double solde_initial) {
  // on cr�e la position courante du compte
  position = new Position(solde_initial);
}

// ajoute une somme � la position courante
// et met � jour la date de derni�re op�ration
void ajouter(double somme) { 
  position.solde += somme; 
  position.derniereOperation = new Date(); }

// retire une somme � la position courante
// et met � jour la date de derni�re op�ration
void retirer(double somme) { 
  position.solde -= somme; 
  position.derniereOperation = new Date(); }

// renvoie la position courante
Position position() { return position; }
}

