package br.com.nextstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.nextstep.beans.AudioVideo;
import br.com.nextstep.conexao.ConectaBanco;
import br.com.nextstep.util.PadraoDAO;

/**
 * Nesta classe, por meio do Design Pattern Data Access Object, manipularemos a tabela T_RBW_AUDIO_VIDEO, 
 * a qual possui CD_AUDIO_VIDEO como chave prim�ria, que ser� vinculada a um Candidato.
 * Todo envio de �udio ou v�deo efetuado pelos Candidatos, ser� armazenado nas suas respectivas colunas em formato de URL
 * 
 * Criamos tr�s atributos para armazenar os componentes do JDBC.
 * 
 * @author Guilherme Rodriguero de Souza
 * @author William Butler Poletto
 * @author Eduardo Vin�cius Benigno da Costa
 * @version 1.0
 * @see br.com.nextstep.beans.AudioVideo
 * @see br.com.nextstep.bo.AudioVideoBO
 * @see br.com.nextstep.util.PadraoDAO
 */
public class AudioVideoDAO implements PadraoDAO<AudioVideo> {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 * O m�todo construtor atua estabelecendo uma conex�o com o  Banco de Dados
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @throws Exception
	 */
	public AudioVideoDAO() throws Exception {
		super();
		
		con =  ConectaBanco.conectar();
	}
	
	/**
	 * Este m�todo efetua o desligamento de conex�o com o Banco de Dados.
	 * @author Guilherme Rodriguero de Souza
	 * @see br.com.nextstep.util.PadraoDAO
	 * @since 1.0
	 * @throws Exception
	 */
	@Override
	public void fechar() throws Exception{
		con.close();
	}
	
	/**
	 * M�todo para inserir uma linha na tabela T_RBW_AUDIO_VIDEO.<br>
	 * A coluna CD_AUDIO_VIDEO � a chave prim�ria e est� sendo inserida atrav�s de uma SEQUENCE.<br>
	 * As colunas FL_VIDEO e FL_AUDIO s�o opcionais pois o candidato pode escolher entre enviar um �udio ou um v�deo.<br>
	 * A quantidade m�xima de caracteres das colunas VARCHAR s�o:<br>
	 * FL_VIDEO = 200<br>
	 * FL_AUDIO = 200<br>
	 * @param objeto Este objeto deve conter a URL do �udio ou v�deo.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Guilhere Rodriguero de Souza
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.AudioVideo
	 * @see br.com.nextstep.bo.AudioVideoBO
	 * @since 1.0
	 * @throws Exception
	 */
	@Override
	public int add(AudioVideo objeto) throws Exception{
		con = ConectaBanco.conectar();
		
		stmt = con.prepareStatement("INSERT INTO T_RBW_AUDIO_VIDEO (CD_AUDIO_VIDEO, FL_VIDEO, FL_AUDIO) VALUES (SQ_RBW_AUDIO_VIDEO.NEXTVAL, ?, ?)");

		stmt.setString(1, objeto.getPathVideo());
		stmt.setString(2, objeto.getPathAudio());

		return stmt.executeUpdate();
	}
	
	/**
	 * M�todo para deletar uma linha na tabela T_RBW_AUDIO_VIDEO.<br>
	 * A linha que ser� deletada deve ser indicada pelo seu ID. 
	 * @param id ID da linha do Banco de Dados que ser� removida.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author William Butler Poletto
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.AudioVideo
	 * @see br.com.nextstep.bo.AudioVideoBO
	 * @since 1.0
	 * @throws Exception
	 */
	@Override
	public int deleteById(int id) throws Exception{
		Connection con = ConectaBanco.conectar();
		
		PreparedStatement stmt = con.prepareStatement("DELETE FROM T_RBW_AUDIO_VIDEO WHERE CD_AUDIO_VIDEO=?");
		stmt.setInt(1, id);
		
		return stmt.executeUpdate();
		
	}
	
	/**
	 * M�todo para atualizar uma linha na tabela T_RBW_AUDIO_VIDEO.<br>
	 * Este m�todo tem como fun��o alterar a URL de um v�deo j� existente.
	 * @param id ID da linha do Banco de Dados que ser� alterada.
	 * @param objeto Este objeto deve conter a URL do v�deo.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author William Butler Poletto
	 * @see br.com.nextstep.beans.AudioVideo
	 * @see br.com.nextstep.bo.AudioVideoBO
	 * @since 1.0
	 * @throws Exception
	 */
	public int modifyVideo(int id,AudioVideo objeto) throws Exception {
		stmt = con.prepareStatement("UPDATE T_RBW_AUDIO_VIDEO SET FL_VIDEO=? WHERE CD_AUDIO_VIDEO=?");

		stmt.setString(1, objeto.getPathVideo());
		stmt.setInt(2, id);
		
		return stmt.executeUpdate();
	}
	
	/**
	 * M�todo para atualizar uma linha na tabela T_RBW_AUDIO_VIDEO.<br>
	 * Este m�todo tem como fun��o alterar a URL de um �udio j� existente.
	 * @param id ID da linha do Banco de Dados que ser� alterada.
	 * @param objeto Este objeto deve conter a URL do �udio.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author William Butler Poletto
	 * @see br.com.nextstep.beans.AudioVideo
	 * @see br.com.nextstep.bo.AudioVideoBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	public int modifyAudio(int id,AudioVideo objeto) throws Exception {
		stmt = con.prepareStatement("UPDATE T_RBW_AUDIO_VIDEO SET FL_AUDIO=? WHERE CD_AUDIO_VIDEO=?");

		stmt.setString(1, objeto.getPathAudio());
		stmt.setInt(2, id);
		
		return stmt.executeUpdate();
	}
	
	/**
	 * M�todo para buscar uma linha na tabela T_RBW_AUDIO_VIDEO.<br>
	 * Este m�todo tem como fun��o buscar e mostrar a URL de um �udio ou v�deo j� existente.
	 * @param id ID da linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna um objeto AudioVideo preenchido com a URL.
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.AudioVideo
	 * @see br.com.nextstep.bo.AudioVideoBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public AudioVideo getById(int id) throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_RBW_AUDIO_VIDEO WHERE CD_AUDIO_VIDEO=?");
		stmt.setInt(1, id);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			
			return new AudioVideo(
					
					rs.getString("FL_AUDIO"),
					rs.getString("FL_VIDEO"),
					rs.getInt("CD_AUDIO_VIDEO")
										
					);
		}
		
		return new AudioVideo();
	}

	/**
	 * M�todo para buscar todas as linhas na tabela T_RBW_AUDIO_VIDEO.<br>
	 * Este m�todo tem como fun��o buscar e mostrar a URL de todos os �udios e v�deos existentes. 
	 * @return Retorna uma lista de objetos AudioVideo com todas as URLs presentes no Banco de Dados.
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.AudioVideo
	 * @see br.com.nextstep.bo.AudioVideoBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public List<AudioVideo> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
