package br.com.nextstep.bo;

import br.com.nextstep.beans.AudioVideo;
import br.com.nextstep.dao.AudioVideoDAO;

/**
 * Esta classe � respons�vel por conter as regras de neg�cio de tudo aquilo que diz respeito ao AudioVideo.
 * � ela que possui os m�todos que se comunicam com o Front-End e tamb�m com o DAO, para que estes n�o interajam diretamente.<br>
 * Todos os m�todos foram declarado como static para n�o haver necessidade de inst�nciar a classe na hora de usar estes m�todos.
 * @author Rogerio Pizzo dos Santos
 * @see br.com.nextstep.dao.AudioVideoDAO;
 * @see br.com.nextstep.beans.AudioVideo; 
 */

public class AudioVideoBO {

	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para a AudioVideo � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e apagar� uma AudioVideo.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.AudioVideoDAO;
 	 * @see br.com.nextstep.beans.AudioVideo;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static String apagarAudioVideo(int id) throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	}
	
	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para a AudioVideo � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e pesquisar� uma AudioVideo.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @return Retorna um AudioVideo contendo um ID e uma URL de �udio ou v�deo
	 * @see br.com.nextstep.dao.AudioVideoDAO;
 	 * @see br.com.nextstep.beans.AudioVideo;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static AudioVideo pesquisarAudioVideo(int id) throws Exception{
		if(id < 1) {
			//throw new RuntimeException("ID inv�lido");
			return new AudioVideo();
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();
		AudioVideo resposta = dao.getById(id);
		
		dao.fechar();
		
		return resposta;
	}
	
	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para a AudioVideo � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e atualizar� um �udio.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @param av Carrega a URL do �udio a ser atualizado.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.AudioVideoDAO;
 	 * @see br.com.nextstep.beans.AudioVideo;
 	 * @author Rogerio Pizzo dos Santos
	 * @throws Exception
	 */
	
	public static String atualizaAudio(int id, AudioVideo av)throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		if(av.getPathAudio() == null) {
			return "Arquivo inexistente de áudio";
		}
		
		if(av.getPathAudio().length() > 200) {
			return "O n�mero de caracteres excede 200";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.modifyAudio(id, av);
		dao.fechar();
		
		return "Arquivo de áudio Atualizado";
	}
	
	/**
	 * Este m�todo ir� verificar se o ID que foi inserido para a AudioVideo � v�lido de acordo com o estabelecido 
	 * no Banco de Dados, e atualizar� um v�deo.
	 * @param id Indica qual linha do Banco de Dados que ser� pesquisada.
	 * @param av Carrega a URL do v�deo a ser atualizado.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.AudioVideoDAO;
 	 * @see br.com.nextstep.beans.AudioVideo;
 	 * @author Rogerio Pizzo dos Santos
	 * @throws Exception
	 */
	
	public static String atualizaVideo(int id, AudioVideo av)throws Exception{
		if(id < 1) {
			return "ID inválido";
		}
		
		if(av.getPathVideo() == null) {
			return "Arquivo inexistente de vídeo";
		}
		
		if(av.getPathVideo().length() > 200) {
			return "O n�mero de caracteres excede 200";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.modifyVideo(id, av);
		dao.fechar();
		
		return "Arquivo de vídeo Atualizado";
	}
	
	/**
	 * Este m�todo ir� verificar se os dados que foram inseridos para a Vaga s�o v�lidos de acordo com o estabelecido 
	 * no Banco de Dados, e ir� inserir um �udio ou v�deo, onde:<br>
	 * PathAudio <= 200 and > 0<br>
	 * PathVideo <= 200 and > 0<br>
	 * @param vaga Este objeto deve conter todas as informa��es do Vaga.
	 * @return Retorna uma String dizendo qual o que ocorreu ao executar o m�todo.
	 * @see br.com.nextstep.dao.VagaDAO;
 	 * @see br.com.nextstep.beans.Vaga;
 	 * @author William Butler Poletto
	 * @throws Exception
	 */
	
	public static String novoAudioVideo(AudioVideo av) throws Exception{

		if(av.getPathVideo() == null || av.getPathAudio() == null) {
			return "Arquivo inexistente de áudio ou vídeo";
		}
		
		if(av.getPathAudio().length() > 200 || av.getPathVideo().length() > 200) {
			return "O n�mero de caracteres excede 200";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.add(av);
		dao.fechar();
		return "Cadastrado";
	}

}
