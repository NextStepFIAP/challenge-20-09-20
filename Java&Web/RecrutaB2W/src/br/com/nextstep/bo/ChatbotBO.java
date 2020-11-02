package br.com.nextstep.bo;

import br.com.nextstep.beans.Chatbot;
import br.com.nextstep.dao.ChatbotDAO;

/**
 * Esta classe � respons�vel por conter as regras de neg�cio de tudo aquilo que diz respeito ao Chatbot.
 * � ela que possui os m�todos que se comunicam com o Front-End e tamb�m com o DAO, para que estes n�o interajam diretamente.<br>
 * Todos os m�todos foram declarado como static para n�o haver necessidade de inst�nciar a classe na hora de usar estes m�todos.
 * @author Celso Lorensatto da Silva Filho
 * @see br.com.nextstep.dao.ChatbotDAO;
 * @see br.com.nextstep.beans.Chatbot; 
 */

public class ChatbotBO {

	/**
	 * Este m�todo ir� verificar se os dados que foram inseridos para o Chatbot s�o v�lidos de acordo com o estabelecido 
	 * no Banco de Dados, e ir� inserir um Chatbot, onde:<br>
	 * Respostas n�o pode ser nulo<br>
	 * @param candidato Este objeto deve conter todas as informa��es do Candidato.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.ChatbotDAO;
 	 * @see br.com.nextstep.beans.Chatbot;
	 * @author Celso Lorensatto da Silva Filho
	 * @throws Exception
	 */
	
	public static int novoChatbot(Chatbot chat) throws Exception{

		if(chat.getRespostas() == null) {
			return 0;
		}
		
		ChatbotDAO dao = new ChatbotDAO();

		int linhasAlteradas = dao.add(chat);
		dao.fechar();
		return linhasAlteradas;
	}

	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para o Chatbot � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisar� uma Chatbot.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna um Chatbot totalmente preenchido.
	 * @see br.com.nextstep.dao.ChatbotDAO;
 	 * @see br.com.nextstep.beans.Chatbot;
	 * @author Celso Lorensatto da Silva Filho
	 * @throws Exception
	 */
	
	public static Chatbot pesquisarChatbot(int id) throws Exception{
		if(id < 1) {
			//throw new RuntimeException("ID inv�lido");
			return new Chatbot();
		}
		
		ChatbotDAO dao = new ChatbotDAO();
		Chatbot chatbot = dao.getById(id);
		
		dao.fechar();
		
		return chatbot;
	}
	
	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para o Chatbot � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e atualizar� um Chatbot.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @param chat Carrega todas as informa��es do Chatbot a ser atualizado.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.ChatbotDAO;
 	 * @see br.com.nextstep.beans.Chatbot;
	 * @author Celso Lorensatto da Silva Filho
	 * @throws Exception
	 */
	
	public static int atualizaChatbot(int id, Chatbot chat)throws Exception{
		if(id < 1) {
			return 0;
		}
		
		if(chat.getRespostas() == null) {
			return 0;
		}
		
		ChatbotDAO dao = new ChatbotDAO();

		int linhasAlteradas = dao.modifyConversa(chat, id);
		dao.fechar();
		
		return linhasAlteradas;
	}
	
	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para o Chatbot � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e apagar� uma Chatbot.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.ChatbotDAO;
 	 * @see br.com.nextstep.beans.Chatbot;
	 * @author Celso Lorensatto da Silva Filho
	 * @throws Exception
	 */
	
	public static String apagarChatbot(int id) throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		ChatbotDAO dao = new ChatbotDAO();

		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	}
	
}
