package maisPop;

import easyaccept.EasyAccept;

public class Facade {
	public static void main(String[] args) {
		String[] files = new String[] { "maisPop.Facade", "testes/scripts_de_teste/usecase_1.txt" };
		EasyAccept.main(files);
	}

	public void iniciaSistema() {
		// TODO ler arquivos serializados.
	}

	public void fechaSistema() {
		// TODO serializar arquivos.
	}

	public Usuario cadastraUsuario(String nome, String email, String senha, String dataDeNasc) throws Exception {
		return cadastraUsuario(nome, email, senha, dataDeNasc, "imagemPadrao");
	}

	public Usuario cadastraUsuario(String nome, String email, String senha, String dataDeNasc, String imagem)
			throws Exception {
		return new Usuario(nome, email, senha, dataDeNasc, imagem);
	}
	
	public String getInfoUsuario(String atributo, Usuario usr) throws Exception{
		String saida = "";
		if (atributo.equals(Usuario.NOME)){
			saida = usr.getNome();
		} else if (atributo.equals(Usuario.EMAIL)){
			saida = usr.getEmail();
		} else if (atributo.equals(Usuario.SENHA)){
			throw new Exception("A senha dx usuarix eh protegida.");
		} else if (atributo.equals(Usuario.DATA_DE_NASCIMENTO)){
			//TODO colocar para o formato correto
		} else if (atributo.equals(Usuario.FOTO)){
			saida = usr.getCaminhoImagem();
		}
		return saida;
	}
}
