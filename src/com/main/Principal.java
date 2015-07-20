package com.main;

import com.bot.*;

public class Principal 
{
	private static String ipTs = "ts.htlv-team.com";
	private static int portaTsQuery = 10011;
	private static int portaTs = 9987;
	private static String usernameTs = "Teste";
	private static String passwordTs = "Q6XH58ZL";
	private static String icecastStream = "http://188.93.234.224:8005/currentsong?sid=1";

	public static void main(String[] args) 
	{
		/*
		Bot bot = new Bot("ts.htlv-team.com", 10011, 9987, "Admin", "9RsrXtH4");
		bot.conectar();
		bot.setNome("Htlv1");
		bot.enviaMensagemSala("Teste htlv-team.com bot");
		bot.iniciaEventos();
		*/
		BotShoutCastMusicBroadcast botShoutCast = new BotShoutCastMusicBroadcast(ipTs, portaTsQuery, portaTs, usernameTs, passwordTs, icecastStream);
		botShoutCast.conectar();
		botShoutCast.setNome("Htlv-team.com Radio");
		botShoutCast.iniciaEventos();
		botShoutCast.run();
	}
	
	public void run()
	{
		
	}
	
}
