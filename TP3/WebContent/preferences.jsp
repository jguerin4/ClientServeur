 <!DOCTYPE html>
<html lang="fr-CA">
<head>
  <title>Travel Express</title>
  
    <!-- Palette couleur vert + rouge #a50026    #d73027    #f46d43    
#fdae61    #fee08b    #ffffbf    
#d9ef8b    #a6d96a    #66bd63    
#1a9850    #006837 -->
  <style>
  body {background-color:#d9ef8b}
  h1   {color:#006837}
  p    {color:#f46d43}
  ul#menu {
   		padding: 0;
	}

	ul#menu li {
	    display: inline;
	}
	
		
	ul#menu li a {
	    background-color: #a50026;
	    color: white;
	    padding: 10px 20px;
	    text-decoration: none;
	    border-radius: 4px 4px 0 0;
	}
	
	ul#menu li a:hover {
   		background-color: orange;
	}
	</style>
</head>

<body>
	<ul id="menu">
	 <li><a href="/TP3/index.jsp">Accueil</a></li>
	 <li><a href="/TP3/inscription.jsp">Inscription</a></li>
	 <li><a href="/TP3/connexion.jsp">Connexion</a></li>
	 <li><a href="/TP3/itineraire.jsp">Itinéraires de voyage</a></li>
	 <li><a href="/TP3/preferences.jsp">Préférences</a></li>
	 <li><a href="/TP3/recherche.jsp">Recherche</a></li>
	 <li><a href="/TP3/voyages.jsp">Gestion des Voyages</a></li>
	</ul> 
	
<h1> Bienvenue dans la page préférences!</h1>
<h4> Vous pouvez ici modifier les préférences de votre compte</h4>

<form>
	<input type="radio" name="fumeur" value="nonfumeur" checked>Je préfère voyager dans un véhicule sans fumeur 
	<br>
	<input type="radio" name="fumeur" value="ouifumeur">Je n'ai pas d'objection à voyager dans un véhicule avec fumeurs 
	<br>
	<br>
	<input type="radio" name="animaux" value="nonanimaux" checked>Je préfère voyager dans une voiture sans animaux 
	<br>
	<input type="radio" name="animaux" value="ouianimaux">Je n'ai pas d'objection à voyager avec des animaux 
	<br><br>
	<input type="submit" value="Modifier">
</form>

<!-- Possibilité de modifier le mot de passe au besoin! -->

</body>

</html> 


