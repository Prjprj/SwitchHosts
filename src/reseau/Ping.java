package reseau;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Classe contenant une m�thode permettant d'effectuer un ping sur une machine
 * distante
 * 
 * @author Prj
 * @version 0.1
 */
public class Ping {

	/**
	 * M�thode permettant d'effectuer un ping sur une adresse
	 * 
	 * @param host
	 *            l'adresse en question
	 * @param timeOut
	 *            la dur�e en secondes pour que le ping soit valide
	 * @return retourne true si la machine a r�pondu en moins de timeOut
	 *         secondes, fasle sinon
	 */
	public static boolean executePing(String host, int timeOut) {
		// Initialisation des variables
		boolean retour = false;
		timeOut = timeOut * 1000;
		try {
			// Ex�cution du ping
			retour = InetAddress.getByName(host).isReachable(timeOut);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		// Renvoie du r�sultat
		return retour;
	}
}
