package bank;

import java.util.*;

/**
* Cette classe gère un seul compte
*/
public class Compte {

// La position courante du compte
private Position position;

// Constructeur
Compte(double solde_initial) {
  // on crée la position courante du compte
  position = new Position(solde_initial);
}

// ajoute une somme à la position courante
// et met à jour la date de dernière opération
void ajouter(double somme) { 
  position.solde += somme; 
  position.derniereOperation = new Date(); }

// retire une somme à la position courante
// et met à jour la date de dernière opération
void retirer(double somme) { 
  position.solde -= somme; 
  position.derniereOperation = new Date(); }

// renvoie la position courante
Position position() { return position; }
}

