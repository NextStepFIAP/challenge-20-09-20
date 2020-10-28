package br.com.nextstep.bo;

import java.util.List;

import br.com.nextstep.beans.Candidato;
import br.com.nextstep.dao.CandidatoDAO;

public class CandidatoBO {

	public static String apagarCandidato(int id) throws Exception{
		if(id < 1) {
			return "ID inv�lido";
		}
		
		CandidatoDAO dao = new CandidatoDAO();

		dao.deleteById(id);
		dao.fechar();
		
		return "Apagou";
	
	}
	
	public static Candidato mostraCandidato(int id) throws Exception{
		if(id < 1) {
			return new Candidato();
		}
		
		CandidatoDAO dao = new CandidatoDAO();
		Candidato candidato = dao.getById(id);
		
		dao.fechar();
		
		return candidato;
	}
	
	public static List<Candidato> mostraCandidato() throws Exception{
			
		CandidatoDAO dao = new CandidatoDAO();
			
		List<Candidato> listaCandidatos = dao.getAll();
		
		dao.fechar();
		
		return listaCandidatos;
		
		
	}
	
	public static String atualizaEmail(int id, Candidato candidato) throws Exception{
		if(id < 1) {
			return "ID inv�lido";
		}
		
		if(candidato.getEmail() == null) {
			return "Email nulo";
		}
				
		CandidatoDAO dao = new CandidatoDAO();

		dao.modifyEmail(id, candidato.getEmail());
		
		return "Email atualizado";
	
				
	}
	
	public static String add(Candidato candidato) throws Exception {

		if(candidato.getCpf().length() < 11) {
			return "CPF inv�lido";
		} else if (candidato.getVaga() == null) {
			return "Uma vaga � necess�ria para adicionar o candidato";
		}
				
		CandidatoDAO dao = new CandidatoDAO();

		dao.add(candidato);
		dao.fechar();
		return "Cadastrado";

	}

}
