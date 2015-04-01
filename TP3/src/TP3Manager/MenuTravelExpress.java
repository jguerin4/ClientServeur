package TP3Manager;

public class MenuTravelExpress {
	public String getStyleMenu() {
		String str = "";
		str += "<!-- Palette couleur vert + rouge #a50026 #d73027 #f46d43 \n";
		str += "#fdae61 #fee08b #ffffbf \n";
		str += "#d9ef8b #a6d96a #66bd63 \n";
		str += "#1a9850 #006837 --> \n";
		str += "<style> \n";
		str += "body {background-color:#d9ef8b} \n";
		str += "h1 {color:#006837} \n";
		str += " p {color:#f46d43} \n";
		str += "ul#menu { \n";
		str += "padding: 0; \n";
		str += "} \n";
		str += "ul#menu li { \n";
		str += " display: inline; \n";
		str += "} \n";
		str += "ul#menu li a { \n";
		str += "background-color: #a50026; \n";
		str += "color: white; \n";
		str += "padding: 10px 20px; \n";
		str += "text-decoration: none; \n";
		str += " border-radius: 4px 4px 0 0; \n";
		str += " } \n";
		str += "ul#menu li a:hover { \n";
		str += "background-color: orange; \n";
		str += "} \n";
		str += "</style> \n";
		return str;
	}

	public String getMenu() {
		String str = "";
		str += "<ul id='menu'>\n";
		str += " <li><a href='/TP3/index.jsp'>Accueil</a></li>\n";
		str += " <li><a href='/TP3/inscription.jsp'>Inscription</a></li>\n";
		str += " <li><a href='/TP3/connexion.jsp'>Connexion</a></li>\n";
		str += " <li><a href='/TP3/itineraire.jsp'>Itinéraires de voyage</a></li>\n";
		str += " <li><a href='/TP3/preferences.jsp'>Préférences</a></li>\n";
		str += " <li><a href='/TP3/recherche.jsp'>Recherche et réservation</a></li>\n";
		str += " <li><a href='/TP3/voyages.jsp'>Gestion des Voyages</a></li>\n";
		str += " <li><a href='/TP3/deconnectionServlet'>Se déconnecter</a></li>\n";
		str += " </ul> \n";
		return str;
	}

	public String getMenuNonConnecte() {
		String str = "";
		str += "<ul id='menu'>\n";
		str += " <li><a href='/TP3/index.jsp'>Accueil</a></li>\n";
		str += " <li><a href='/TP3/inscription.jsp'>Inscription</a></li>\n";
		str += " <li><a href='/TP3/connexion.jsp'>Connexion</a></li>\n";
		str += " </ul> \n";
		return str;
	}
}
