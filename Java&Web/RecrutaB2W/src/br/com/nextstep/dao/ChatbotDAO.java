package br.com.nextstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import br.com.nextstep.beans.Chatbot;
import br.com.nextstep.conexao.ConectaBanco;
import br.com.nextstep.util.PadraoDAO;

/**
 * Nesta classe, por meio do Design Pattern Data Access Object, manipularemos a tabela T_RBW_CHATBOT, a qual possui CD_CHATBOT como chave prim�ria, que ser� vinculada a um Candidato.
 * Toda vez que um Candidato efetuar uma conversa completa com o Chatbot, esta ser� armazenada nesta tabela por meio desta classe.
 * 
 * Criamos tr�s atributos para armazenar os componentes do JDBC.
 * 
 * @author Guilherme Rodriguero de Souza
 * @author William Butler Poletto
 * @author Celso Lorensatto da Silva Filho
 * @version 1.0
 * @see br.com.nextstep.beans.Recrutador
 * @see br.com.nextstep.bo.RecrutadorBO
 * @see br.com.nextstep.util.PadraoDAO
 */

public class ChatbotDAO implements PadraoDAO<Chatbot> {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 * O m�todo construtor atua estabelecendo uma conex�o com o  Banco de Dados
	 * @author Celso Lorensatto da Silva Filho
	 * @throws Exception
	 */
	
	public ChatbotDAO() throws Exception{

		con =  ConectaBanco.conectar();
	}
	
	/**
	 * Este m�todo efetua o desligamento de conex�o com o Banco de Dados.
	 * @author Celso Lorensatto da Silva Filho
	 * @see br.com.nextstep.util.PadraoDAO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public void fechar() throws Exception{
		con.close();
	}
	
	/**
	 * M�todo para inserir uma linha na tabela T_RBW_CHATBOT.<br>
	 * A coluna CD_CHATBOT � a chave prim�ria e est� sendo inserida atrav�s de uma SEQUENCE.<br>
	 * Esta tabela n�o possui colunas opcionais.<br>
	 * Utilizamos do tipo CLOB para a coluna DS_CONVERSA pois � um tipo de dado que comporta um n�mero de caracteres 
	 * suficientes para armazenar uma conversa com o Chatbot.
	 * @param objeto Este objeto deve conter os dados do Recrutador.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Guilhere Rodriguero de Souza
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Chatbot
	 * @see br.com.nextstep.bo.ChatbotBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public int add(Chatbot chatbot) throws Exception{

		con = ConectaBanco.conectar();
		
		Gson gson = new Gson();
		String json = gson.toJson(chatbot.getRespostas());

		stmt = con.prepareStatement("INSERT INTO T_RBW_CHATBOT (CD_CHATBOT, DS_RESPOSTA) VALUES (SQ_RBW_CHATBOT.NEXTVAL, ?)");
		stmt.setString(1, json);

		return stmt.executeUpdate();
	}
	
	/**
	 * M�todo para deletar uma linha na tabela T_RBW_CHATBOT.<br>
	 * A linha que ser� deletada deve ser indicada pelo seu ID. 
	 * @param id ID da linha do Banco de Dados que ser� removida.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Guilherme Rodriguero de Souza
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Recrutador
	 * @see br.com.nextstep.bo.RecrutadorBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public int deleteById(int id) throws Exception{
		Connection con = ConectaBanco.conectar();
		
		PreparedStatement stmt = con.prepareStatement("DELETE FROM T_RBW_CHATBOT WHERE CD_CHATBOT=?");
		stmt.setInt(1, id);
		
		return stmt.executeUpdate();
		
	}
	
	/**
	 * M�todo para atualizar uma linha na tabela T_RBW_CHATBOT.<br>
	 * Este m�todo tem como fun��o alterar o conte�do de uma conversa j� existente.
	 * @param email Email do Recrutador que deseja alterar sua Senha.
	 * @param senha Nova senha do Recrutador.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Celso Lorensatto da Silva Filho
	 * @see br.com.nextstep.beans.Recrutador
	 * @see br.com.nextstep.bo.RecrutadorBO
	 * @since 1.0
	 * @throws Exception
	 */

	public int modifyConversa(Chatbot chatbot, int numero) throws Exception {
		
		Gson gson = new Gson();
		String json = gson.toJson(chatbot.getRespostas());
		
		stmt = con.prepareStatement("UPDATE T_RBW_CHATBOT SET DS_RESPOSTA=? WHERE CD_CHATBOT=?");
		stmt.setString(1, json);
		stmt.setInt(2, numero);
		
		return stmt.executeUpdate();
	}

	/**
	 * M�todo para buscar uma linha na tabela T_RBW_CHATBOT.<br>
	 * Este m�todo tem como fun��o buscar e mostrar uma conversa de determinado Candidato com o Chatbot, sendo acessado 
	 * apeas pelo Recrutador ou admin do sistema.
	 * @param id ID da linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna um objeto Recrutador preenchido.
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Chatbot
	 * @see br.com.nextstep.bo.ChatbotBO
	 * @since 1.0
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	@Override
	public Chatbot getById(int id) throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_RBW_CHATBOT WHERE CD_CHATBOT=?");
		stmt.setInt(1, id);
				
		rs = stmt.executeQuery();
		
		Gson gson = new Gson();

		if(rs.next()) {
		
			int cd_chatbot = rs.getInt("CD_CHATBOT");
			Map<String, String> respostas
				= gson.fromJson(rs.getString("DS_RESPOSTA"), Map.class);

			return new Chatbot(cd_chatbot, respostas);
		}
		
		
		return new Chatbot();
	}

	/**
	 * M�todo para buscar todas as linhas na tabela T_RBW_CHATBOT.<br>
	 * Este m�todo tem como fun��o buscar e retornar uma lista de todas conversas existentes no Banco de Dados.
	 * @return Retorna uma lista de objetos Chatbot com todas as conversas presentes no Banco de Dados.
	 * @author William Butler Polleto
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Chatbot
	 * @see br.com.nextstep.bo.ChatbotBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public List<Chatbot> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
