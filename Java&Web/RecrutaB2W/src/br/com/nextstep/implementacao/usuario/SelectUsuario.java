package br.com.nextstep.implementacao.usuario;

import br.com.nextstep.dao.UsuarioDAO;
import br.com.nextstep.util.Resume;

public class SelectUsuario {
	
	public static void retornarDados(UsuarioDAO dao) throws Exception{
		
		String cpf = Resume.s("Digite o CPF do Usu�rio: ");
		System.out.println(dao.getById(cpf)); // AQUI DEVEMOS FAZER UM M�TODO DE DELETE NO DAO QUE TENHA COMO PAR�METRO UMA STRING
		
		dao.fechar();
	}

}
