package br.com.nextstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.nextstep.beans.Vaga;
import br.com.nextstep.conexao.ConectaBanco;
import br.com.nextstep.util.PadraoDAO;

/**
 * Nesta classe, por meio do Design Pattern Data Access Object, manipularemos a tabela T_RBW_VAGA, a qual possui CD_VAGA como chave prim�ria, que ser� vinculada a um Candidato.<br>
 * Toda vaga cadastrada ser� armazenada nesta tabela, contendo as seguintes informa��es: <br>
 * Nome da Vaga, Descri��o da Vaga e Sal�rio da Vaga.<br>
 * 
 * Criamos tr�s atributos para armazenar os componentes do JDBC.
 * 
 * @author Celso Lorensatto da Silva Filho
 * @author Rogerio Pizzo dos Santos
 * @author Eduardo Vin�cius Benigno da Costa
 * @version 1.0
 * @see br.com.nextstep.beans.Vaga 
 * @see br.com.nextstep.bo.VagaBO
 * @see br.com.nextstep.util.PadraoDAO
 */

public class VagaDAO implements PadraoDAO<Vaga> {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 * O m�todo construtor atua estabelecendo uma conex�o com o  Banco de Dados
	 * @author Rogerio Pizzo dos Santos
	 * @throws Exception
	 */
	
	public VagaDAO() throws Exception{

		con =  ConectaBanco.conectar();
	}
	
	/**
	 * Este m�todo efetua o desligamento de conex�o com o Banco de Dados.
	 * @author Rogerio Pizzo dos Santos
	 * @see br.com.nextstep.util.PadraoDAO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public void fechar() throws Exception{
		con.close();
	}

	/**
	 * M�todo para inserir uma linha na tabela T_RBW_VAGA.<br>
	 * A coluna CD_VAGA � a chave prim�ria e est� sendo inserida atrav�s de uma SEQUENCE.<br>
	 * A coluna VL_SALARIO � a �nica que aceita valores opcionais.<br>
	 * A quantidade m�xima de caracteres das colunas VARCHAR s�o:<br>
	 * NM_VAGA = 50<br>
	 * DS_VAGA = 400<br>
	 * @param objeto Este objeto deve conter a URL do �udio ou v�deo.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Celso Lorensatto da Silva Filho
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Vaga
	 * @see br.com.nextstep.bo.VagaBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public int add(Vaga objeto) throws Exception{

		con = ConectaBanco.conectar();

		stmt = con.prepareStatement("INSERT INTO T_RBW_VAGA (NR_VAGA, NM_VAGA, DS_VAGA, VL_SALARIO) VALUES (SQ_RBW_VAGA.NEXTVAL, ?, ?, ?)");
		stmt.setString(1, objeto.getNomeVaga());
		stmt.setString(2, objeto.getDescVaga());
		stmt.setDouble(3, objeto.getSalario());
		
		return stmt.executeUpdate();
	}
	
	/**
	 * M�todo para deletar uma linha na tabela T_RBW_VAGA.<br>
	 * A linha que ser� deletada deve ser indicada pelo seu ID. 
	 * @param id ID da linha do Banco de Dados que ser� removida.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Rogerio Pizzo dos Santos
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Vaga
	 * @see br.com.nextstep.bo.VagaBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public int deleteById(int id) throws Exception{
		Connection con = ConectaBanco.conectar();
		
		PreparedStatement stmt = con.prepareStatement("DELETE FROM T_RBW_VAGA WHERE NR_VAGA=?");
		stmt.setInt(1, id);
		
		return stmt.executeUpdate();
		
	}
	
	/**
	 * M�todo para atualizar uma linha na tabela T_RBW_VAGA.<br>
	 * Este m�todo tem como fun��o alterar a descri��o de uma vaga j� existente.
	 * @param desc Nova descri��o da Vaga.
	 * @param numero ID da Vaga.
	 * @return Retorna a quantidade de linhas modificadas
	 * @author Rogerio Pizzo dos Santos
	 * @see br.com.nextstep.beans.Vaga
	 * @see br.com.nextstep.bo.VagaBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	public int modifyDesc(String desc, int numero) throws Exception {
		stmt = con.prepareStatement("UPDATE T_RBW_VAGA SET DS_VAGA=? WHERE NR_VAGA=?");
		stmt.setString(1, desc);
		stmt.setInt(2, numero);
		
		return stmt.executeUpdate();
	}
	
	/**
	 * M�todo para buscar uma linha na tabela T_RBW_VAGA.<br>
	 * Este m�todo tem como fun��o buscar e mostrar uma Vaga com todas as suas informa��es.
	 * @param id ID da linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna um objeto Vaga preenchido.
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Vaga
	 * @see br.com.nextstep.bo.VagaBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public Vaga getById(int id) throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_RBW_VAGA WHERE NR_VAGA=?");
		stmt.setInt(1, id);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			
			return new Vaga(
					
					rs.getInt("NR_VAGA"),
					rs.getString("NM_VAGA"),
					rs.getString("DS_VAGA"),
					rs.getDouble("VL_SALARIO")
					
					);
		}
		
		return new Vaga();
	}

	/**
	 * M�todo para buscar todas as linhas na tabela T_RBW_VAGA.<br>
	 * Este m�todo tem como fun��o buscar e retornar uma lista de vagas existentes no Banco de Dados.
	 * @return Retorna uma lista de objetos Vaga com todas as vagas presentes no Banco de Dados.
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @see br.com.nextstep.util.PadraoDAO
	 * @see br.com.nextstep.beans.Vaga
	 * @see br.com.nextstep.bo.VagaBO
	 * @since 1.0
	 * @throws Exception
	 */
	
	@Override
	public List<Vaga> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
