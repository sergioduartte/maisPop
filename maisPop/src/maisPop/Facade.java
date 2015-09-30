package maisPop;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAccept;

public class Facade {
	
	private List<Usuario> bancoDeUsuarios;
	
	public Facade() {
		bancoDeUsuarios = new ArrayList<Usuario>();
	}
	
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

	public String cadastraUsuario(String nome, String email, String senha, String dataDeNasc) throws Exception {
		return cadastraUsuario(nome, email, senha, dataDeNasc, "resources/default.jpg");
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataDeNasc, String imagem)
			throws Exception {
		Usuario usr = new Usuario(nome, email, senha, dataDeNasc, imagem);
		bancoDeUsuarios.add(usr);
		return usr.getEmail();
	
	}
	
	public String getInfoUsuario(String atributo, String usuario) throws Exception{
		Usuario usr = retornaUsuarioPorEmail(usuario);
		String data = usr.getDataDeNascimento();
		
		String saida = "";
		if (atributo.equals(Usuario.NOME)){
			saida = usr.getNome();
		} else if (atributo.equals(Usuario.EMAIL)){
			saida = retornaUsuarioPorEmail(atributo).getEmail();
		} else if (atributo.equals(Usuario.SENHA)){
			throw new Exception("A senha dx usuarix eh protegida.");
		} else if (atributo.equals(Usuario.DATA_DE_NASCIMENTO)){
			saida = data;
		} else if (atributo.equals(Usuario.FOTO)){
			saida = usr.getCaminhoImagem();
		}
		return saida;
	}

	private Usuario retornaUsuarioPorEmail(String usuarioEmail) throws Exception{
		for (Usuario usuario : bancoDeUsuarios) {
			if (usuario.getEmail().equals(usuarioEmail)){
				return usuario;
			}
		}
		throw new Exception("Um usuarix com email " + usuarioEmail + " nao esta cadastradx.");
	}

	}

