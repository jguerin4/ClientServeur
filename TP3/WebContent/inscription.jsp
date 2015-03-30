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
	 <li><a href="/TP3/itineraire.jsp">Itin�raires de voyage</a></li>
	 <li><a href="/TP3/preferences.jsp">Pr�f�rences</a></li>
	 <li><a href="/TP3/recherche.jsp">Recherche</a></li>
	 <li><a href="/TP3/voyages.jsp">Gestion des Voyages</a></li>
	</ul> 
	
	
<h1> Bienvenue dans la page inscription!</h1>


<form>
	Nom d'utilisateur:<br>
	<input type="text" name="user">
	<br>
	
	Mot de passe:<br>
	<input type="password" name="password">
	<br>
	
	Confirmez votre mot de passe:<br>
	<input type="password" name="confirmpassword">
	<br>
	<br>
	Pr�nom:<br>
	<input type="text" name="prenom">
	<br>
	Nom de famille:<br>
	<input type="text" name="nom">
	<br>
	Adresse courriel:<br>
	<input type="text" name="courriel">
	<br>
	Num�ro de t�l�phone:<br>
	<input type="text" name="telephone">
	<br>
	Date de naissance(YYYY/MM/JJ):<br>
	<input type="text" name="birthday">
	<br>
	<br>
	
	<input type="radio" name="fumeur" value="nonfumeur" checked>Je pr�f�re voyager dans un v�hicule sans fumeur 
	<br>
	<input type="radio" name="fumeur" value="ouifumeur">Je n'ai pas d'objection � voyager dans un v�hicule avec fumeurs 
	<br>
	<br>
	<input type="radio" name="animaux" value="nonanimaux" checked>Je pr�f�re voyager dans une voiture sans animaux 
	<br>
	<input type="radio" name="animaux" value="ouianimaux">Je n'ai pas d'objection � voyager avec des animaux 
	<br><br>
	<input type="submit" value="S'inscrire!">

</form>


</body>

</html> 


