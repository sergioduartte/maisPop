package maisPop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Usuario> bancoDeUsuarios;

	private Usuario usuarioLogado;

	private UsuarioFactory usuarioFactory;
	private TrendingTopics trendingTopics;

	private final String ERRO_DE_ATUALIZACAO = "Erro na atualizacao de perfil. ";

	public Controller() {
		bancoDeUsuarios = new ArrayList<Usuario>();
		usuarioFactory = new UsuarioFactory();
		trendingTopics = new TrendingTopics();
	}

	public void aceitaAmizade(String usuarioEmail) throws Exception {
		if (usuarioLogado == null) {
			throw new Exception("Nao eh possivel aceitar solicitacao. Nenhum usuarix esta logadx no +pop.");
		}
		Usuario usr = retornaUsuarioPorEmail(usuarioEmail);
		if (!temSolicitacao(usr)) {
			throw new Exception(usr.getNome() + " nao lhe enviou solicitacoes de amizade.");
		}
		usuarioLogado.addAmigo(usuarioEmail);
		usr.addAmigo(usuarioLogado.getEmail());
		adicionaNotificacao(usuarioEmail, usuarioLogado.getNome() + " aceitou sua amizade.");
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void adicionaAmigo(String usuarioEmail) throws Exception {
		if (usuarioLogado == null) {
			throw new Exception("Nao eh possivel enviar solicitacao. Nenhum usuarix esta logadx no +pop.");
		}
		String mensagem = usuarioLogado.getNome() + " quer sua amizade.";
		adicionaNotificacao(usuarioEmail, mensagem);
		adicionaSolicitacao(usuarioEmail, mensagem);
	}

	public void adicionaNotificacao(String usuarioEmail, String notificacao) throws Exception {
		Usuario usr = retornaUsuarioPorEmail(usuarioEmail);
		usr.getListaNotificacoes().add(notificacao);
	}

	public void adicionaPops(int i) {
		usuarioLogado.adicionaPops(i);
	}

	public void adicionaSolicitacao(String usuarioEmail, String solicitacao) throws Exception {
		Usuario usr = retornaUsuarioPorEmail(usuarioEmail);
		usr.getSolicitacoes().add(solicitacao);

	}

	public void atualizaPerfil(String atributo, String valor) throws Exception {
		if (usuarioLogado == null) {
			throw new Exception("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		}
		if (atributo.equals(Usuario.NOME)) {
			usuarioFactory.validaNome(ERRO_DE_ATUALIZACAO, valor);
			usuarioLogado.setNome(valor);
		} else if (atributo.equals(Usuario.EMAIL)) {
			usuarioFactory.validaEmail(ERRO_DE_ATUALIZACAO, valor);
			usuarioLogado.setEmail(valor);
		} else if (atributo.equals(Usuario.DATA_DE_NASCIMENTO)) {
			usuarioFactory.validaDataDeNasc(ERRO_DE_ATUALIZACAO, valor);
			usuarioLogado.setDataDeNascimento(valor);
		} else if (atributo.equals(Usuario.FOTO)) {
			usuarioLogado.setCaminhoImagem(valor);
		}
	}

	public void atualizaPerfil(String atributo, String novaSenha, String velhaSenha) throws Exception {
		if (usuarioLogado == null) {
			throw new Exception("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		}
		if (atributo.equals(Usuario.SENHA)) {
			if (velhaSenha.equals(usuarioLogado.getSenha())) {
				usuarioLogado.setSenha(novaSenha);
			} else {
				throw new Exception(ERRO_DE_ATUALIZACAO + "A senha fornecida esta incorreta.");
			}
		}
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataDeNasc) throws Exception {
		return cadastraUsuario(nome, email, senha, dataDeNasc, "resources/default.jpg");
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataDeNasc, String imagem)
			throws Exception {
		Usuario usr = usuarioFactory.criaUsuario(nome, email, senha, dataDeNasc, imagem);
		bancoDeUsuarios.add(usr);
		return usr.getEmail();
	}

	public void criaPost(String mensagem, String data) throws Exception {
		Postagem post = usuarioLogado.criaPost(mensagem, data);
		usuarioLogado.adicionaNoMural(post);
	}

	public void curtirPost(String usuarioEmail, int post) throws Exception {
		if (usuarioLogado == null) {
			throw new Exception("Nao eh possivel curtir o post. Nenhum usuarix esta logadx no +pop.");
		}
		usuarioLogado.atualizaPop();
		Usuario usr = retornaUsuarioPorEmail(usuarioEmail);
		Postagem p = usr.getPost(post);
		int curtida = usuarioLogado.getValorCurtida(p);
		usuarioLogado.curtirPost(p);
		usr.adicionaPops(curtida);

		adicionaNotificacao(usuarioEmail,
				usuarioLogado.getNome() + " curtiu seu post de " + usr.getPost(Usuario.DATA_DA_POSTAGEM, post) + ".");
	}

	public void rejeitaPost(String usuarioEmail, int post) throws Exception {
		if (usuarioLogado == null) {
			throw new Exception("Nao eh possivel rejeitar o post. Nenhum usuarix esta logadx no +pop.");
		}
		usuarioLogado.atualizaPop();
		Usuario usr = retornaUsuarioPorEmail(usuarioEmail);
		Postagem p = usr.getPost(post);
		int rejeita = usuarioLogado.getValorRejeita(p);
		usuarioLogado.rejeitaPost(p);
		usr.diminuiPops(rejeita);

		adicionaNotificacao(usuarioEmail,
				usuarioLogado.getNome() + " rejeitou seu post de " + usr.getPost(Usuario.DATA_DA_POSTAGEM, post) + ".");
	}

	public String getConteudoPost(int indice, int post) throws Exception {
		return usuarioLogado.getConteudoPost(indice, post);
	}

	public int getCurtidasDePost(int post) throws Exception {
		Postagem p = usuarioLogado.getPost(post);
		return p.getCurtidas();
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
		String saida = "";

		if (atributo.equals(Usuario.NOME)) {
			saida = usr.getNome();
		} else if (atributo.equals(Usuario.EMAIL)) {
			saida = retornaUsuarioPorEmail(atributo).getEmail();
		} else if (atributo.equals(Usuario.SENHA)) {
			throw new Exception("A senha dx usuarix eh protegida.");
		} else if (atributo.equals(Usuario.DATA_DE_NASCIMENTO)) {
			saida = usr.getDataDeNascimento();
		} else if (atributo.equals(Usuario.FOTO)) {
			saida = usr.getCaminhoImagem();
		}
		return saida;
	}

	public String getNextNotificacao() throws Exception {
		return usuarioLogado.getNextNotificacao();
	}

	public int getNotificacoes() {
		return usuarioLogado.getNotificacoes();
	}

	public int getPopsPost(int post) throws Exception {
		Postagem p = usuarioLogado.getPost(post);
		return p.getPopularidade();
	}

	public int getPopsUsuario() {
		return usuarioLogado.getPop();
	}

	public int getPopsUsuario(String usuarioEmail) throws Exception {
		if (usuarioLogado != null) {
			throw new Exception("Erro na consulta de Pops. Um usuarix ainda esta logadx.");
		}
		Usuario usr = retornaUsuarioPorEmail(usuarioEmail);
		return usr.getPop();
	}

	public String getPopularidade() {
		usuarioLogado.atualizaPop();
		return usuarioLogado.getPopularidade();
	}

	public Postagem getPost(int post) throws Exception {
		Postagem p = usuarioLogado.getPost(post);
		return p;
	}

	public String getPost(String atributo, int post) throws Exception {
		return usuarioLogado.getPost(atributo, post);
	}

	public int getQtdAmigos() {
		return usuarioLogado.getQtdAmigos();
	}

	public int getRejeitadasDePost(int post) throws Exception {
		Postagem p = usuarioLogado.getPost(post);
		return p.getRejeitadas();
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
		if (usuarioLogado == null) {
			this.usuarioLogado = loga(email, senha);
		} else {
			throw new Exception(
					"Nao foi possivel realizar login. Um usuarix ja esta logadx: " + usuarioLogado.getNome() + ".");
		}
	}

	public void logout() throws Exception {
		if (usuarioLogado == null) {
			throw new Exception("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
		}
		if (usuarioLogado != null) {
			usuarioLogado = null;
		}

	}

	public void rejeitaAmizade(String usuarioEmail) throws Exception {
		if (usuarioLogado == null) {
			throw new Exception("Nao eh possivel rejeitar solicitacao. Nenhum usuarix esta logadx no +pop.");
		}
		Usuario usr = retornaUsuarioPorEmail(usuarioEmail);
		if (!temSolicitacao(usr)) {
			throw new Exception(usr.getNome() + " nao lhe enviou solicitacoes de amizade.");
		}
		adicionaNotificacao(usuarioEmail, usuarioLogado.getNome() + " rejeitou sua amizade.");
	}

	public void removeAmigo(String usuarioEmail) throws Exception {
		if (usuarioLogado == null) {
			throw new Exception("Nao eh possivel adicionar amigo. Nenhum usuarix esta logadx no +pop.");
		}
		usuarioLogado.getAmigos().remove(usuarioEmail);
		Usuario usr = retornaUsuarioPorEmail(usuarioEmail);
		usr.getAmigos().remove(usuarioLogado.getEmail());
		adicionaNotificacao(usuarioEmail, usuarioLogado.getNome() + " removeu a sua amizade.");
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

	public boolean temSolicitacao(Usuario usr) {
		String mensagem = usr.getNome() + " quer sua amizade.";
		for (String solicitacao : usuarioLogado.getSolicitacoes()) {
			if (solicitacao.equals(mensagem)) {
				usuarioLogado.getSolicitacoes().remove(solicitacao);
				return true;
			}
		}
		return false;
	}

	public List<Usuario> getMaisPopulares() {
		List<Usuario> saida = new ArrayList<>();
		Collections.sort(bancoDeUsuarios);
		for (int i = 3; i > 0; i--) {
			saida.add(bancoDeUsuarios.get(bancoDeUsuarios.size() - i));
		}
		return saida;
	}

	public List<Usuario> getMenosPopulares() {
		List<Usuario> saida = new ArrayList<>();
		Collections.sort(bancoDeUsuarios);
		for (int i = 0; i < 3; i++) {
			saida.add(bancoDeUsuarios.get(i));
		}
		return saida;
	}

	public String atualizaRanking() {
		List<Usuario> maisPopulares = getMaisPopulares();
		List<Usuario> menosPopulares = getMenosPopulares();

		String saida = "Mais Populares: (1) " + maisPopulares.get(2) + "; (2) " + maisPopulares.get(1) + "; (3) "
				+ maisPopulares.get(0) + "; | ";
		saida += "Menos Populares: (1) " + menosPopulares.get(0) + "; (2) " + menosPopulares.get(1) + "; (3) "
				+ menosPopulares.get(2) + ";";
		return saida;
	}

	private void insereHashtagsNoTT(List<String> listaDeHashtags) {
		for (String hashtag : listaDeHashtags) {
			if (trendingTopics.hasHashtag(hashtag)) {
				trendingTopics.getHashtag(hashtag).addQuantidade();
			} else {
				trendingTopics.add(new Tupla(hashtag));
			}
		}
	}

	public String atualizaTrendingTopics() {

		this.trendingTopics = new TrendingTopics();

		for (Usuario usuario : bancoDeUsuarios) {
			for (Postagem p : usuario.getMural()) {
				insereHashtagsNoTT(p.getListaHashtags());
			}
		}

		return this.trendingTopics.toString();
	}

	public int getTotalPosts() {

		return usuarioLogado.getTotalPosts();

	}

}
