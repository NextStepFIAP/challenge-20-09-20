package br.com.nextstep.bo;

import java.util.List;

import br.com.nextstep.beans.Recrutador;
import br.com.nextstep.dao.RecrutadorDAO;

/**
 * Esta classe � respons�vel por conter as regras de neg�cio de tudo aquilo que diz respeito ao Recrutador.
 * � ela que possui os m�todos que se comunicam com o Front-End e tamb�m com o DAO, para que estes n�o interajam diretamente.<br>
 * Todos os m�todos foram declarado como static para n�o haver necessidade de inst�nciar a classe na hora de usar estes m�todos.
 * @author Guilherme Rodriguero de Souza
 * @see br.com.nextstep.dao.RecrutadorDAO;
 * @see br.com.nextstep.beans.Recrutador; 
 */

public class RecrutadorBO {

	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para o Recrutador � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e apagar� um Recrutador.
	 * @param id Indica qual linha do Banco de Dados que ser� apagada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
 	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */

	public static String apagarRecrutador(int id) throws Exception{
		if(id < 1) {
			return "ID inv�lido";
		}
		
		RecrutadorDAO dao = new RecrutadorDAO();


		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	
	}
	
	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para o Recrutador � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisar� um Recrutador.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna um Recrutador totalmente preenchido.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
 	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */

	public static Recrutador mostraRecrutador(int id) throws Exception{
		if(id < 1) {
			return new Recrutador();
		}
		
		RecrutadorDAO dao = new RecrutadorDAO();
		Recrutador recrutador = dao.getById(id);
		
		dao.fechar();
		
		return recrutador;
	}
	
	/**
	 * Este m�todo ir� pesquisar uma lista de  Recrutadores.
	 * @return Retorna uma lista de Recrutadores totalmente preenchido.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
 	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */

	public static List<Recrutador> mostraRecrutador() throws Exception{
			
		RecrutadorDAO dao = new RecrutadorDAO();
			
		List<Recrutador> listaRecrutadores = dao.getAll();
		
		dao.fechar();
		
		return listaRecrutadores;
		
		
	}
	
	/**
	 * Este m�todo ir� verificar se o email e senha que foram inseridos para o Recrutador s�o v�lidos de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisar� uma Recrutador.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna um Candidato totalmente preenchido.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
	 * @author Eduardo Vin�cius Benigno da Costa
	 * @throws Exception
	 */

	public static boolean mostraLogin(String email,String senha) throws Exception{
		
		if(email == null) {
			return false;
		}
		
		RecrutadorDAO dao = new RecrutadorDAO();
		boolean isValidado = dao.getByLogin(email,senha);
		
		dao.fechar();
		
		return isValidado;
	}
	
	/**
	 * Este m�todo ir� verificar se o email e a senha que foram inseridos para o Recrutador s�o v�lidos de acordo com o estabelecido 
	 * no Banco de Dados, e atualizar� um Recrutador.
	 * @param email Indica qual Recrutador ter� sua senha alterada.
	 * @param novaSenha Indica a nova Senha que ser� cadastrada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
 	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */
	
	public static String atualizaSenha(String email, String senha, Recrutador recrutador) throws Exception{

		if(recrutador.getEmail() == null) {
			return "Email nulo";
		}
		
		if(recrutador.getSenha() == null) {
			return "Senha nula";
		}
				
		RecrutadorDAO dao = new RecrutadorDAO();

		dao.modifySenha(recrutador.getEmail(),recrutador.getSenha());
		
		return "Email atualizado";
				
	}

	/**
	 * Este m�todo ir� verificar se os dados que foram inseridos para o Recrutador s�o v�lidos de acordo com o estabelecido 
	 * no Banco de Dados, e cadastrar� um Recrutador, onde:<br>
	 * Nome <= 100 and > 0<br>
	 * Email <= 100 and > 0<br>
	 * Senha <= 16 and > 0<br> 
	 * @param recrutador Este objeto deve conter todas as informa��es do Recrutador.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.RecrutadorDAO;
 	 * @see br.com.nextstep.beans.Recrutador;
 	 * @author Guilherme Rodriguero de Souza
	 * @throws Exception
	 */
	
	public static String add(Recrutador recrutador) throws Exception {
		
		if(recrutador.getNome().length() > 100) {
			return "Nome excede 100 caracteres";
		}
		
		if(recrutador.getEmail().length() > 100) {
			return "Email excede 100 caracteres";
		}
		
		if(recrutador.getSenha().length() > 16) {
			return "Senha excede 16 caracteres";
		}
		
		if(recrutador.getNome() == null) {
			return "Nome inv�lido";
		}
		
		if(recrutador.getEmail() == null) {
			return "Email inv�lido";
		}
		
		if(recrutador.getSenha() == null) {
			return "Senha inv�lido";
		}
		
		recrutador.setEmail(recrutador.getEmail().toLowerCase());
		
		RecrutadorDAO dao = new RecrutadorDAO();
		dao.add(recrutador);
		dao.fechar();
		return "Cadastrado";
	}
}
