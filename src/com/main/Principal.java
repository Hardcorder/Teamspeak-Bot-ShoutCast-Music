package com.main;

import com.bot.*;

public class Principal 
{
	private static String ipTs = "Teamspeak Ip Adress";
	private static int portaTsQuery = 10011; // Port of Server Query
	private static int portaTs = 9987; // Normally this is the first teamspeak port
	private static String usernameTs = "Teste"; // Guest SErver Query Login
	private static String passwordTs = "Q6XH58ZL"; // guest Server Query Password
	private static String icecastStream = "http://ShoutCastIp:ShoutCastPort/currentsong?sid=1";

	public static void main(String[] args) 
	{
		BotShoutCastMusicBroadcast botShoutCast = new BotShoutCastMusicBroadcast(ipTs, portaTsQuery, portaTs, usernameTs, passwordTs, icecastStream);
		botShoutCast.conectar();
		botShoutCast.setNome("Bot Name[Htlv-team.com]");
		botShoutCast.iniciaEventos();
		botShoutCast.run();
	}
	
	public void run()
	{
		
	}
	
}
