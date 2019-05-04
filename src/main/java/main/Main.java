package main;

import network.Client;
import utils.Config;

public class Main {
	
	public static Client client;

	public static void main(String[] args) {
		client = new Client(Config.REMOTE_SERVER_ADDRESS, Config.REMOTE_SERVER_PORT, null);
		client.start();
	}
	




}
