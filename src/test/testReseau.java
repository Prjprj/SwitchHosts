package test;

import reseau.Ping;
import reseau.Hostname;

public class testReseau {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Ping.executePing(Hostname
				.getHostname("128.94.200.128"), 3));
	}

}
