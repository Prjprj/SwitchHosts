package reseau;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Classe permettant de récupérer le Hostname d'une machine
 * 
 * @author Prj
 * @version 0.1
 */
public class Hostname {

	/**
	 * Méthode permettant de récupérer le hostname d'une machine distante
	 * 
	 * @param ip
	 *            l'adresse ip en question
	 * @return retourne le nom de la machine
	 */
	public static String getHostname(String ip) {
		// Initialisation des variables
		String retour = "";
		byte[] adresseIp = new byte[4];
		String tempIp = ip;
		// Parsing de l'adresse IP
		for (int i = 0; i < 4; i = i + 1) {
			String s = ".";
			CharSequence cs = s.subSequence(0, s.length());
			if (tempIp.contains(cs)) {
				adresseIp[i] = (byte) Integer.parseInt(tempIp.substring(0,
						tempIp.indexOf('.', 0)));
				tempIp = tempIp.substring(tempIp.indexOf('.', 0) + 1);
			} else {
				adresseIp[i] = (byte) Integer.parseInt(tempIp);
			}
		}
		try {
			// Récupération du hostname
			retour = InetAddress.getByAddress(adresseIp).getHostName();
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
		// Renvoie du résultat
		return retour;
	}

}
