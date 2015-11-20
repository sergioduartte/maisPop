package maisPop1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Comparable<Usuario>, Serializable{
	
	private static final long serialVersionUID = 1L;
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

	public Usuario(String nome, String email, String senha, String dataDeNasc, String imagem) throws Exception {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataDeNascimento = dataDeNasc;
		this.caminhoImagem = imagem;
		this.pop = 0;
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

	public ArrayList<Postagem> getMural() {
		return this.mural;
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

	public Postagem getPost(int post) throws Exception {
		Postagem p;
		try {
			p = mural.get(post);
		} catch (Exception e) {
			throw new Exception("Post #" + post + " nao existe. Usuarix possui apenas " + getTotalPosts() + " post(s).");
		}
		return p;

	}

	public String getPost(String atributo, int post) throws Exception {
		Postagem p;
		try {
			p = mural.get(post);
		} catch (Exception e) {
			throw new Exception("indice do post fora do range. Erro: " + e.getMessage());
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
			throw new Exception("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		}
		Postagem p;
		p = mural.get(post);
		return p.getConteudo(indice);
	}

	public String getPopularidade() {
		return this.popularidade.toString();
	}

	public void curtirPost(Postagem p) {
		this.popularidade.curtirPost(p);
	}

	public void rejeitaPost(Postagem p) {
		this.popularidade.rejeitaPost(p);
	}

	public int getPop() {
		return this.pop;
	}

	public void adicionaPops(int i) {
		this.pop += i;
		atualizaPop();
	}

	public void diminuiPops(int i) {
		this.pop -= i;
		atualizaPop();

	}

	public void atualizaPop() {
		if (this.pop <= 500) {
			this.popularidade = new NormalPop();
		} else if (this.pop <= 1000) {
			this.popularidade = new CelebridadePop();
		} else if (this.pop > 1000) {
			this.popularidade = new IconePop();
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

	public int getTotalPosts() {
		return mural.size();
	}

	public int getValorCurtida(Postagem p) {
		return this.popularidade.getValorCurtida(p);
	}

	public int getValorRejeita(Postagem p) {
		return this.popularidade.getValorRejeita(p);
	}

	public Popularidade getTipoPopularidade() {
		return this.popularidade;
	}

	public String toString() {
		return this.nome + " " + this.pop;
	}
	
	public List<Postagem> fornecePosts(){
		return this.popularidade.fornecePosts(this.mural);
	}
}
