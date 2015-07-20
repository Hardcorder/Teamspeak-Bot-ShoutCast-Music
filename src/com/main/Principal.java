package com.main;

import com.bot.*;

public class Principal 
{
	private static String ipTs = "Teamspeak Ip Adress";
	private static int portaTsQuery = 10011; // Port of Server Query
	private static int portaTs = 9987; // Normally this is the first teamspeak port
	private static String usernameTs = "Username"; // Guest SErver Query Login
	private static String passwordTs = "Password"; // guest Server Query Password
	private static String shoutcastStream = "http://ShoutCastIp:ShoutCastPort/currentsong?sid=StreamIdNormallyIs1";

	public static void main(String[] args) 
	{
		BotShoutCastMusicBroadcast botShoutCast = new BotShoutCastMusicBroadcast(ipTs, portaTsQuery, portaTs, usernameTs, passwordTs, shoutcastStream);
		botShoutCast.conectar();
		botShoutCast.setNome("Bot Name[Htlv-team.com]");
		botShoutCast.iniciaEventos();
		botShoutCast.run();
	}
	
	public void run()
	{
		
	}
	
}
