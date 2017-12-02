package test;

import util.ParseurFichierConf;
import application.Constantes;

public class testParseurFichierConf {
	public static void main(String[] args) {
		System.out.println(ParseurFichierConf.existanceFichier(Constantes.nomFichierConf));
		System.out.println(ParseurFichierConf.getProperty("Test",Constantes.nomFichierConf));
	}

}
