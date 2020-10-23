package br.com.nextstep.implementacao.vaga;

import br.com.nextstep.dao.VagaDAO;
import br.com.nextstep.excecao.Excecao;
import br.com.nextstep.util.Resume;

public class TesteDelVaga {

	public static void main(String[] args) {
		try {
			VagaDAO dao = new VagaDAO();

			int apaga = Resume.i("Digite o ID da vaga que deseja apagar: ");
			
			if(dao.deleteById(apaga) == 0) {
				System.out.println("N�o Apagou");
			}else {
				System.out.println("Apagou");
			}
			dao.fechar();
			
		}catch(Exception e) {
			Excecao.tratarExcecao(e);
		}
	}
}
