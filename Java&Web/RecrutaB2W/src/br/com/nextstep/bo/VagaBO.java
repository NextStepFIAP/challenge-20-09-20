package br.com.nextstep.bo;

import br.com.nextstep.beans.Vaga;
import br.com.nextstep.dao.VagaDAO;

/**
 * Esta classe � respons�vel por conter as regras de neg�cio de tudo aquilo que diz respeito � Vaga.
 * � ela que possui os m�todos que se comunicam com o Front-End e tamb�m com o DAO, para que estes n�o interajam diretamente.<br>
 * Todos os m�todos foram declarado como static para n�o haver necessidade de inst�nciar a classe na hora de usar estes m�todos.
 * @author William Butler Poletto
 * @see br.com.nextstep.dao.VagaDAO;
 * @see br.com.nextstep.beans.Vaga; 
 */

public class VagaBO {

	/**
	 * Este m�todo ir� verificar se os dados que foram inseridos para a Vaga s�o v�lidos de acordo com o estabelecido 
	 * no Banco de Dados, e ir� inserir uma Vaga, onde:<br>
	 * Sal�rio <= 99999.99 and > 0<br>
	 * DescVaga <= 400 and > 0<br>
	 * NomeVaga <= 50 and > 0<br>
	 * @param vaga Este objeto deve conter todas as informa��es do Vaga.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.VagaDAO;
 	 * @see br.com.nextstep.beans.Vaga;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static String novaVaga(Vaga vaga) throws Exception{

		if(vaga.getNomeVaga() == null) {
			return "Nome da vaga n�o foi preenchido";
		}
		
		if(vaga.getNomeVaga().length() > 50) {
			return "Nome da vaga ultrapassou os limites";
		}
		
		if(vaga.getSalario() < 0) {
			return "Insira valores positivos";
		}

		if(vaga.getSalario() > 99999.99) {
			return "N�o ultrapasse 5 d�gitos";
		}
		
		if(vaga.getDescVaga() == null) {
			return "Descri��o da Vaga n�o foi preenchida";
		}
		if(vaga.getDescVaga().length() > 400) {
			return "Descri��o da vaga ultrapassou os limites";
		}
		
		VagaDAO dao = new VagaDAO();

		dao.add(vaga);
		dao.fechar();
		return "Cadastrado";
	}

	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para a Vaga � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisar� uma Vaga.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna uma Vaga totalmente preenchida.
	 * @see br.com.nextstep.dao.VagaDAO;
 	 * @see br.com.nextstep.beans.Vaga;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static Vaga pesquisarVaga(int id) throws Exception{
		if(id < 1) {
			//throw new RuntimeException("ID inv�lido");
			return new Vaga();
		}
		
		VagaDAO dao = new VagaDAO();
		Vaga resposta = dao.getById(id);
		
		dao.fechar();
		
		return resposta;
	}
	
	/**
	 * Este m�todo ir� verificar se o ID e a descri��o que foram inseridos para a Vaga s�o v�lidso de acordo com o estabelecido 
	 * no Banco de Dados, e atualizar� uma Vaga.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @param novaDescricao Indica qual ser� a nova descri��o da Vaga.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.VagaDAO;
 	 * @see br.com.nextstep.beans.Vaga;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static String atualizarVaga(int id, String novaDescricao)throws Exception{
		if(id < 1) {
			return "ID inv�lido";
		}
		
		if(novaDescricao == null) {
			return "Descri��o inexistente";
		}
		
		if(novaDescricao.length() > 400) {
			return "Descri��o ultrapassou os limites de 400 caracteres";
		}
		
		VagaDAO dao = new VagaDAO();

		dao.modifyDesc(novaDescricao, id);
		dao.fechar();
		
		return "Conversa de chatbot atualizada";
	}
	
	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para a Vaga � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e apagar� uma Vaga.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.VagaDAO;
 	 * @see br.com.nextstep.beans.Vaga;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static String apagarVaga(int id) throws Exception{
		if(id < 1) {
			return "ID inv�lido";
		}
		
		VagaDAO dao = new VagaDAO();

		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	}
	
}
