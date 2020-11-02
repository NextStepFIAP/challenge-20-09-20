package br.com.nextstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.nextstep.beans.Recrutador;
import br.com.nextstep.conexao.ConectaBanco;
import br.com.nextstep.util.PadraoDAO;

/**
 * Nesta classe, por meio do Design Pattern Data Access Object, manipularemos a tabela T_RBW_RECRUTADOR, a qual possui CD_RECRUTADOR como chave prim�ria, que ser� vinculada a um Candidato.
 * Todos os Recrutadores cadastrados no sistema est�o armazenados nesta tabela.
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

public class RecrutadorDAO implements PadraoDAO<Recrutador> {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 * O m�todo construtor atua estabelecendo uma conex�o com o  Banco de Dados
	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */
	
	public RecrutadorDAO() throws Exception{
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
	 * M�todo para inserir uma linha na tabela T_RBW_RECRUTADOR.<br>
	 * A coluna CD_RECRUTADOR � a chave prim�ria e est� sendo inserida atrav�s de uma SEQUENCE.<br>
	 * Esta tabela n�o possui colunas opcionais.<br>
	 * A quantidade m�xima de caracteres das colunas VARCHAR s�o:<br>
	 * NM_RECRUTADOR = 100<br>
	 * DS_EMAIL = 100<br>
	 * NM_SENHA = 16<br>
	 * @param objeto Este objeto deve conter os dados do Recrutador.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Guilhere Rodriguero de Souza
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Recrutador
	 * @see br.com.nextstep.bo.RecrutadorBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public int add(Recrutador objeto) throws Exception{

		stmt = con.prepareStatement("INSERT INTO T_RBW_RECRUTADOR (CD_RECRUTADOR, NM_RECRUTADOR, DS_EMAIL, NM_SENHA) VALUES (SQ_RBW_RECRUTADOR.NEXTVAL, ?, ?, ?)");

		stmt.setString(1, objeto.getNome());
		stmt.setString(2, objeto.getEmail());
		stmt.setString(3, objeto.getSenha());

		return stmt.executeUpdate();
	}
	
	/**
	 * M�todo para deletar uma linha na tabela T_RBW_RECRUTADOR.<br>
	 * A linha que ser� deletada deve ser indicada pelo seu ID. 
	 * @param id ID da linha do Banco de Dados que ser� removida.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author William Butler Polleto
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Recrutador
	 * @see br.com.nextstep.bo.RecrutadorBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public int deleteById(int id) throws Exception{
	
		PreparedStatement stmt = con.prepareStatement("DELETE FROM T_RBW_RECRUTADOR WHERE CD_RECRUTADOR=?");
		stmt.setInt(1, id);
		
		return stmt.executeUpdate();
		
	}
	
	/**
	 * M�todo para atualizar uma linha na tabela T_RBW_RECRUTADOR.<br>
	 * Este m�todo tem como fun��o alterar o email de um Recrutador j� existente.
	 * @param email Email do Recrutador que deseja alterar sua Senha.
	 * @param senha Nova senha do Recrutador.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Celso Lorensatto da Silva Filho
	 * @see br.com.nextstep.beans.Recrutador
	 * @see br.com.nextstep.bo.RecrutadorBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	public int modifySenha(String email, String senha) throws Exception {
		
		stmt = con.prepareStatement("UPDATE T_RBW_RECRUTADOR SET NM_SENHA=? WHERE DS_EMAIL=?");
		stmt.setString(1, senha);
		stmt.setString(2, email);
		
		return stmt.executeUpdate();
	}
	
	/**
	 * M�todo para buscar uma linha na tabela T_RBW_RECRUTADOR.<br>
	 * Este m�todo tem como fun��o buscar e mostrar um Recrutador com todas as suas informa��es.
	 * @param id ID da linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna um objeto Recrutador preenchido.
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Recrutador
	 * @see br.com.nextstep.bo.RecrutadorBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public Recrutador getById(int id) throws Exception{
		
		stmt = con.prepareStatement("SELECT * FROM T_RBW_RECRUTADOR WHERE CD_RECRUTADOR=?");
		stmt.setInt(1, id);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			
			return new Recrutador(
					
					rs.getInt("CD_RECRUTADOR"),
					rs.getString("NM_RECRUTADOR"),
					rs.getString("DS_EMAIL"),
					rs.getString("NM_SENHA")
	
					);
		}
		
		return new Recrutador();
	}

	/**
	 * M�todo para buscar um Recrutador na tabela T_RBW_RECRUTADOR, com base no seu Email e Senha
	 * @param email Email do Recrutador
	 * @param senha Senha do Recrutador
	 * @return Retorna verdadeiro caso este login exista, e falso se n�o existir
	 * @throws Exception
	 */
	
	public Recrutador getByLogin(String email, String senha) throws Exception{
		
		stmt = con.prepareStatement("SELECT * FROM T_RBW_RECRUTADOR WHERE DS_EMAIL='" + email +  "' AND NM_SENHA='"+ senha +"'");
	
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Recrutador(
					
					rs.getInt("CD_RECRUTADOR"),
					rs.getString("NM_RECRUTADOR"),
					rs.getString("DS_EMAIL"),
					rs.getString("NM_SENHA")
	
					);
					
		}
		return new Recrutador();
	}
	
	/**
	 * M�todo para buscar todas as linhas na tabela T_RBW_RECRUTADOR.<br>
	 * Este m�todo tem como fun��o buscar e retornar uma lista de recrutadores existentes no Banco de Dados.
	 * @return Retorna uma lista de objetos Vaga com todos os recrutadores presentes no Banco de Dados.
	 * @author William Butler Polleto
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Recrutador
	 * @see br.com.nextstep.bo.RecrutadorBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public List<Recrutador> getAll() throws Exception {

		stmt = con.prepareStatement("SELECT * FROM T_RBW_RECRUTADOR");
	
		rs = stmt.executeQuery();
		
		List<Recrutador> listaUsuarios = new ArrayList<Recrutador>();
		
		while(rs.next()) {
			
			Recrutador usuario = new Recrutador(
					
					rs.getInt("CD_RECRUTADOR"),
					rs.getString("NM_RECRUTADOR"),
					rs.getString("DS_EMAIL"),
					rs.getString("NM_SENHA")
	
					);
			
			listaUsuarios.add(usuario);

		}
		
		return listaUsuarios;
	}	
}
