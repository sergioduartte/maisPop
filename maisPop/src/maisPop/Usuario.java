package maisPop;

import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario implements Comparable<Usuario> {
	public static String NOME = "Nome";
	public static String EMAIL = "E-mail";
	public static String SENHA = "Senha";
	public static String DATA_DE_NASCIMENTO = "Data de Nascimento";
	public static String FOTO = "Foto";
	public static String MENSAGEM = "Mensagem";
	public static String DATA_DA_POSTAGEM = "Data";
	public static String HASHTAGS = "Hashtags";

	private String nome, email, senha, dataDeNascimento, caminhoImagem;
	private int pop;
	private Popularidade popularidade;
	private ArrayList<String> notificacoes;
	private ArrayList<String> solicitacoes;
	private ArrayList<String> amigos;
	private ArrayList<Postagem> mural;

	public Usuario(String nome, String email, String senha, String dataDeNasc,
			String imagem) throws Exception {
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setDataDeNascimento(dataDeNasc);
		setCaminhoImagem(imagem);
		this.amigos = new ArrayList<String>();
		this.notificacoes = new ArrayList<String>();
		this.solicitacoes = new ArrayList<String>();
		this.mural = new ArrayList<Postagem>();
		this.popularidade = new NormalPop();
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public ArrayList<String> getAmigos() {
		return amigos;
	}

	public ArrayList<String> getListaNotificacoes() {
		return notificacoes;
	}

	public ArrayList<String> getSolicitacoes() {
		return solicitacoes;
	}

	public int getNotificacoes() {
		return notificacoes.size();
	}

	public String getNextNotificacao() throws Exception {
		for (String string : notificacoes) {
			notificacoes.remove(string);
			return string;
		}
		throw new Exception("Nao ha mais notificacoes.");
	}

	public int getQtdAmigos() {
		return amigos.size();
	}

	public String getDataDeNascimento() {
		return transformaData(dataDeNascimento).toString();
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public void addAmigo(String usuarioEmail) {
		amigos.add(usuarioEmail);
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static LocalDate transformaData(String dataDeNascimento) {
		String[] dataSplit = dataDeNascimento.split("/");
		int dia = Integer.parseInt(dataSplit[0].trim());
		int mes = Integer.parseInt(dataSplit[1].trim());
		int ano = Integer.parseInt(dataSplit[2].trim());
		return LocalDate.of(ano, mes, dia);
	}

	public Postagem criaPost(String mensagem, String data) throws Exception {
		Postagem post = new Postagem(mensagem, data);
		return post;
	}

	public void adicionaNoMural(Postagem post) {
		mural.add(post);
	}

	public Postagem getPost(int post) {
		return mural.get(post);
	}

	public String getPost(String atributo, int post) throws Exception {
		Postagem p;
		try {
			p = mural.get(post);
		} catch (Exception e) {
			throw new Exception("indice do post fora do range. Erro: "
					+ e.getMessage());
		}
		if (atributo.equals(MENSAGEM)) {
			return p.getMensagemSemHashtag();
		} else if (atributo.equals(DATA_DA_POSTAGEM)) {
			return p.getData();
		} else if (atributo.equals(HASHTAGS)) {
			return p.getHashtags();
		} else {
			throw new Exception("atributo invalido.");
		}
	}

	public String getConteudoPost(int indice, int post) throws Exception {
		if (indice < 0) {
			throw new Exception(
					"Requisicao invalida. O indice deve ser maior ou igual a zero.");
		}
		Postagem p;
		p = mural.get(post);
		return p.getConteudo(indice);
	}

	public Popularidade getPopularidade() {
		return this.popularidade;
	}

	public int getPop() {
		return this.pop;
	}

	public void atualizaPop() {
		if (pop <= 500) {
			popularidade = new NormalPop();
		} else if (pop <= 1000) {
			popularidade = new CelebridadePop();
		} else {
			popularidade = new IconePop();
		}
	}

	@Override
	public int compareTo(Usuario usr) {
		if (this.getPop() > usr.getPop()) {
			return 1;
		} else if (this.getPop() < usr.getPop()) {
			return -1;
		} else if (this.getPop() == usr.getPop()) {
			return this.getEmail().compareTo(usr.getEmail());
		}
		return 0;
	}
}
