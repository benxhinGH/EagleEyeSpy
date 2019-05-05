package main;

import network.Client;
import utils.Config;

public class Main {
	
	public static Client client;

	public static void main(String[] args) {
		client = new Client("192.168.1.3", Config.REMOTE_SERVER_PORT, null);
		client.start();
	}
	




}
