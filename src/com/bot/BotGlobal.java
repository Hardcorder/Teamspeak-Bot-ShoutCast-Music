package com.bot;

import com.github.theholywaffle.teamspeak3.TS3Api;

public interface BotGlobal 
{
	/**
	 * Conectar-se ao Teamspeak
	 */
	public void conectar();
	/**
	 * Desconectar-se do teamspeak
	 */
	public void desconectar();
	/**
	 * Returna True se o bot tiver conectado
	 * @return
	 */
	public boolean isConectado();
	
	/**
	 * Define o nome para o bot
	 * @param nome
	 */
	public void setNome(String nome);
	/**
	 * Retorna o Nome do bot
	 * @return
	 */
	public String getNome();
	
	/**
	 * Envia mensagem para toda a gente do teamspeak
	 * @param mensagem
	 */
	public void enviaMensagemGlobal(String mensagem);
	/**
	 * Envia mensagem para a sala atual
	 * @param mensagem
	 */
	public void enviaMensagemSala(String mensagem);
	
	/**
	 * Envia mensagem para um cliente
	 * @param idCliente
	 * @param mensagem
	 */
	public void enviaMensagemCliente(int idCliente, String mensagem);
	/**
	 * Retorna id do bot no teamspeak
	 * @return
	 */
	public int getBotId();
	/**
	 * Retorna TS3Api
	 * @return
	 */
	public TS3Api getApi();
}
