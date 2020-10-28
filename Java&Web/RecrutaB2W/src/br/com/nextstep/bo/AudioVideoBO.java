package br.com.nextstep.bo;

import br.com.nextstep.beans.AudioVideo;
import br.com.nextstep.dao.AudioVideoDAO;

public class AudioVideoBO {

	public static String apagarAudioVideo(int id) throws Exception{
		if(id < 1) {
			return "ID inv�lido";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	}
	
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
	
	public static String atualizaAudio(int id, AudioVideo av)throws Exception{
		if(id < 1) {
			return "ID inv�lidoo";
		}
		
		if(av.getPathAudio() == null) {
			return "Arquivo inexistente de �udio";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.modifyAudio(id, av);
		dao.fechar();
		
		return "Arquivo de �udio Atualizado";
	}
	
	public static String atualizaVideo(int id, AudioVideo av)throws Exception{
		if(id < 1) {
			return "ID inv�lido";
		}
		
		if(av.getPathAudio() == null) {
			return "Arquivo inexistente de v�deo";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.modifyVideo(id, av);
		dao.fechar();
		
		return "Arquivo de v�deo Atualizado";
	}
	
	public static String novoAudioVideo(AudioVideo av) throws Exception{

		if(av.getPathVideo() == null || av.getPathAudio() == null) {
			return "Arquivo inexistente de �udio ou v�deo";
		}
		
		AudioVideoDAO dao = new AudioVideoDAO();

		dao.add(av);
		dao.fechar();
		return "Cadastrado";
	}

}
