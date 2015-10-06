package maisPop;

import maisPop.Controller;
import easyaccept.EasyAccept;

public class Facade {

	public static void main(String[] args) {
		String[] files = new String[] { "maisPop.Facade", "testes/scripts_de_teste/usecase_1.txt" };
		EasyAccept.main(files);
	}

	private Controller controlador;

	public Facade() {
		controlador = new Controller();
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataDeNasc) throws Exception {
		return controlador.cadastraUsuario(nome, email, senha, dataDeNasc);
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataDeNasc, String imagem)
			throws Exception {
		return controlador.cadastraUsuario(nome, email, senha, dataDeNasc, imagem);
	}

	public void fechaSistema() throws Exception {
		controlador.fechaSistema();
	}

	public String getInfoUsuario(String atributo) throws Exception {
		return controlador.getInfoUsuario(atributo);
	}

	public String getInfoUsuario(String atributo, String email) throws Exception {
		return controlador.getInfoUsuario(atributo, email);
	}

	public void iniciaSistema() {
		controlador.iniciaSistema();
	}

	public void login(String email, String senha) throws Exception {
		controlador.login(email, senha);
	}

	public void logout() throws Exception {
		controlador.logout();
	}

	public void removeUsuario(String idDoUsuario) throws Exception {
		controlador.removeUsuario(idDoUsuario);
	}
}
