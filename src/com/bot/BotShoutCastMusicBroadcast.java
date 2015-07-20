package com.bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDeletedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDescriptionEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelPasswordChangedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ServerEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

public class BotShoutCastMusicBroadcast implements BotGlobal
{

	private String ip;
	private int porta;
	private int portaTs;
	
	private String utilizador;
	private String password;
	private boolean conectado = false;
	private int botIdTs;
	
	private TS3Config config = new TS3Config();
	private TS3Query query;
	private TS3Api api;

	private String nome = "Htlv-team.com Bot";
	
	private String icecastStream = "http://188.93.234.224:8005/currentsong?sid=1";
	
	public BotShoutCastMusicBroadcast(String ip, int porta, int portaTs, String utilizador, String password, String icecastStream)
	{
		this.ip = ip;
		this.porta = porta;
		this.portaTs = portaTs;
		this.utilizador = utilizador;
		this.password = password;
		this.icecastStream = icecastStream;
	}
	
	@Override
	public void conectar() {
		// TODO Auto-generated method stub
		try
		{
			config.setHost(ip);
			config.setQueryPort(porta);
			config.setDebugLevel(Level.ALL);
			config.setLoginCredentials(utilizador, password);
			query = new TS3Query(config); 
			query.connect();
			api = query.getApi();
			api.selectVirtualServerByPort(portaTs);
			
			botIdTs = api.whoAmI().getId();
			
			conectado = true;
			
		}
		catch(Exception ex)
		{
			System.out.println("Erro ao conectar-se ao teamspeak ==>"+ex.toString());
			desconectar();
		}
		
	}

	@Override
	public void desconectar() {
		// TODO Auto-generated method stub
		if(isConectado())
		{
			query.exit();
			conectado = false;
		}
	}
	
	@Override
	public boolean isConectado() {
		// TODO Auto-generated method stub
		return conectado;
	}

	@Override
	public void setNome(String nome) {
		// TODO Auto-generated method stub
		api.setNickname(nome);
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.nome;
	}

	@Override
	public void enviaMensagemGlobal(String mensagem) {
		// TODO Auto-generated method stub
		if(isConectado())
		{
			api.sendServerMessage(mensagem);
		}
	}
	/**
	 * Retorna API
	 * @return
	 */
	public TS3Api getApi()
	{
		return api; 
	}
	@Override
	public void enviaMensagemSala(String mensagem) {
		// TODO Auto-generated method stub
		if(isConectado())
		{
			api.sendChannelMessage(mensagem);
		}
	}
	@Override
	public void enviaMensagemCliente(int idCliente, String mensagem) {
		// TODO Auto-generated method stub		
		api.sendPrivateMessage(idCliente, mensagem);
		
	}
	@Override
	public int getBotId() {
		// TODO Auto-generated method stub
		return this.botIdTs;
	}
	
	public void iniciaEventos()
	{
		api.registerEvent(TS3EventType.TEXT_CHANNEL, 0);
		api.registerEvent(TS3EventType.CHANNEL);
		api.registerAllEvents();
		// Register the event listener
		api.addTS3Listeners(new TS3Listener() {

			public void onTextMessage(TextMessageEvent e) {
				// Only react to channel messages not sent by the query itself
				if (e.getTargetMode() == TextMessageTargetMode.CHANNEL && e.getInvokerId() != botIdTs) {
					String message = e.getMessage().toLowerCase();

					if (message.equals("!ping")) {
						// Answer "!ping" with "pong"
						api.sendChannelMessage("pong");
					} else if (message.startsWith("boas")) {
						// Greet whoever said hello
						// Message: "Hello <client name>!"
						api.sendChannelMessage("Boas " + e.getInvokerName() + "!");
					} else if (message.equals("!info"))
					{
						api.sendChannelMessage("Bot criado pela Equipa http://htlv-team.com");
					}
				}
			}

			@Override
			public void onChannelCreate(ChannelCreateEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChannelDeleted(ChannelDeletedEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChannelDescriptionChanged(
					ChannelDescriptionEditedEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChannelEdit(ChannelEditedEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChannelMoved(ChannelMovedEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChannelPasswordChanged(
					ChannelPasswordChangedEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onClientJoin(ClientJoinEvent arg0) {
				// TODO Auto-generated method stub
				enviaMusicaAtual();
			}

			@Override
			public void onClientLeave(ClientLeaveEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onClientMoved(ClientMovedEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onServerEdit(ServerEditedEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void enviaMusicaAtual()
	{
		enviaMensagemSala("[B][COLOR=#0537dc]Musica Atual:[/COLOR] [COLOR=#26c27a]"+getMusicaAtual()+"[/COLOR][/B]");
	}
	public String getMusicaAtual()
	{
		 URL icecast;
		try {
			icecast = new URL(icecastStream);
			URLConnection yc = icecast.openConnection();
	         BufferedReader in = new BufferedReader(new InputStreamReader(
	                 yc.getInputStream(), "ISO-8859-1"));
	         String inputLine;
	         StringBuilder a = new StringBuilder();
	         while ((inputLine = in.readLine()) != null)
	             a.append(inputLine);
	         in.close();
	         return  a.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Indefinida";
		}	
    }
	
	public void run()
	{
		System.out.println("Entrou no Run");
		Runnable executa = new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("A tentar fazer algo com thread..");
				// TODO Auto-generated method stub
				String musicaAtual = "";
				while(true)
				{
					System.out.println("A correr..");
					System.out.println("Musica: "+getMusicaAtual());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(musicaAtual.equals(getMusicaAtual()))
					{
					}
					else
					{
						musicaAtual = getMusicaAtual();
						enviaMusicaAtual();
					}
				}
			}
			
		};
		Thread procura = new Thread(executa);
		procura.start();

		
	}

}
