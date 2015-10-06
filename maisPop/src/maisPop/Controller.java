package maisPop;

import java.util.ArrayList;
import java.util.List;

public class Controller {

	private List<Usuario> bancoDeUsuarios;
	private boolean temUsuarioLogado;

	private Usuario usuarioLogado;

	public Controller() {
		bancoDeUsuarios = new ArrayList<Usuario>();
		temUsuarioLogado = false;
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

	public void fechaSistema() throws Exception {
		if (temUsuarioLogado) {
			throw new Exception("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
	}

	public String getInfoUsuario(String atributo) throws Exception {
		String saida = "";
		if (atributo.equals(Usuario.NOME)) {
			saida = usuarioLogado.getNome();
		} else if (atributo.equals(Usuario.EMAIL)) {
			saida = usuarioLogado.getEmail();
		} else if (atributo.equals(Usuario.SENHA)) {
			throw new Exception("A senha dx usuarix eh protegida.");
		} else if (atributo.equals(Usuario.DATA_DE_NASCIMENTO)) {
			saida = usuarioLogado.getDataDeNascimento();
		} else if (atributo.equals(Usuario.FOTO)) {
			saida = usuarioLogado.getCaminhoImagem();
		}
		return saida;
	}

	public String getInfoUsuario(String atributo, String email) throws Exception {
		Usuario usr = retornaUsuarioPorEmail(email);
		String data = usr.getDataDeNascimento();
		String saida = "";

		if (atributo.equals(Usuario.NOME)) {
			saida = usr.getNome();
		} else if (atributo.equals(Usuario.EMAIL)) {
			saida = retornaUsuarioPorEmail(atributo).getEmail();
		} else if (atributo.equals(Usuario.SENHA)) {
			throw new Exception("A senha dx usuarix eh protegida.");
		} else if (atributo.equals(Usuario.DATA_DE_NASCIMENTO)) {
			saida = data;
		} else if (atributo.equals(Usuario.FOTO)) {
			saida = usr.getCaminhoImagem();
		}
		return saida;
	}

	public void iniciaSistema() {
		// TODO ler arquivos serializados.
	}

	public Usuario loga(String email, String senha) throws Exception {
		Usuario usrTemp;
		try {
			usrTemp = retornaUsuarioPorEmail(email);
		} catch (Exception e) {
			throw new Exception("Nao foi possivel realizar login. " + e.getMessage());
		}
		if (usrTemp.getSenha().equals(senha)) {
			return usrTemp;
		} else {
			throw new Exception("Nao foi possivel realizar login. Senha invalida.");
		}
	}

	public void login(String email, String senha) throws Exception {
		if (!temUsuarioLogado) {
			this.usuarioLogado = loga(email, senha);
			setUsuarioLogado(true);
		} else {
			throw new Exception(
					"Nao foi possivel realizar login. Um usuarix ja esta logadx: " + usuarioLogado.getNome() + ".");
		}
	}

	public void logout() throws Exception {
		if (temUsuarioLogado) {
			setUsuarioLogado(false);
		} else {
			throw new Exception("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
		}
		if (!usuarioLogado.equals(null)) {
			usuarioLogado = null;
		}

	}

	public void removeUsuario(String idDoUsuario) throws Exception {
		try {
			bancoDeUsuarios.remove(retornaUsuarioPorEmail(idDoUsuario));
		} catch (Exception e) {
			throw new Exception("Impossivel remover usuario, usuario nao cadastrado.");
		}
	}

	public Usuario retornaUsuarioPorEmail(String usuarioEmail) throws Exception {
		for (Usuario usuario : bancoDeUsuarios) {
			if (usuario.getEmail().equals(usuarioEmail)) {
				return usuario;
			}
		}
		throw new Exception("Um usuarix com email " + usuarioEmail + " nao esta cadastradx.");
	}

	public void setUsuarioLogado(boolean b) {
		this.temUsuarioLogado = b;
	}

}
