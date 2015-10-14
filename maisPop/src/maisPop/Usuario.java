package maisPop;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Usuario {

	public static String NOME = "Nome";
	public static String EMAIL = "E-mail";
	public static String SENHA = "Senha";
	public static String DATA_DE_NASCIMENTO = "Data de Nascimento";
	public static String FOTO = "Foto";

	private String nome, email, senha, dataDeNascimento, caminhoImagem;
	private ArrayList<String> notificacoes;
	private ArrayList<String> amigos;

	private final String ERRO_DE_CADASTRO = "Erro no cadastro de Usuarios. ";
	private final String ERRO_DE_ATUALIZACAO = "Erro na atualizacao de perfil. ";

	public Usuario(String nome, String email, String senha, String dataDeNasc, String imagem) throws Exception {
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setDataDeNascimento(dataDeNasc);
		setCaminhoImagem(imagem);
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}
	
	public ArrayList<String> getAmigos(){
		return amigos;
	}

	public ArrayList<String> getListaNotificacoes() {
		return notificacoes;
	}
	
	public int getNotificacoes(){
		return notificacoes.size();
	}
	
	public String getNextNotificacao() throws Exception{
		for (String string : notificacoes) {
			return string;
		}
		throw new Exception("Nao ha mais notificacoes");
	}
	
	public int getQtdAmigos(){
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

	public void setDataDeNascimento(String dataDeNascimento) throws Exception {
		Pattern p = Pattern.compile("[\\d]{2}/[\\d]{2}/[\\d]{4}");
		Matcher m = p.matcher(dataDeNascimento);

		if (!m.matches() && this.dataDeNascimento == null) {
			throw new Exception(ERRO_DE_CADASTRO + "Formato de data esta invalida.");
			
		} else if (!m.matches() && this.dataDeNascimento != null){
			throw new Exception(ERRO_DE_ATUALIZACAO + "Formato de data esta invalida.");
		} else {
			try {
				LocalDate data = transformaData(dataDeNascimento);
			} catch (Exception e) {
				if (this.dataDeNascimento != null){
					throw new Exception( ERRO_DE_ATUALIZACAO + "Data nao existe.");
				} else {
				throw new Exception(ERRO_DE_CADASTRO + "Data nao existe.");
				}
			}
			this.dataDeNascimento = dataDeNascimento;
		}
	}

	public void setEmail(String email) throws Exception {
		Pattern p = Pattern.compile("[\\w\\d_\\.%\\+-]+@[\\w\\d\\.-]+\\.[\\w]{2,6}");
		Matcher m = p.matcher(email);
		
		if (this.email != null && !m.matches()){
			throw new Exception(ERRO_DE_ATUALIZACAO + "Formato de e-mail esta invalido.");
		}
		if (m.matches()) {
			this.email = email;
		} else {
			throw new Exception(ERRO_DE_CADASTRO + "Formato de e-mail esta invalido.");
		}
	}

	public void setNome(String nome) throws Exception {
		if ((this.nome != null) && (nome.trim().length() == 0) ){
			throw new Exception(ERRO_DE_ATUALIZACAO + "Nome dx usuarix nao pode ser vazio.");
		}
		if (nome.trim().length() > 0) {
			this.nome = nome;
		} else {
			throw new Exception(ERRO_DE_CADASTRO + "Nome dx usuarix nao pode ser vazio.");
		}
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate transformaData(String dataDeNascimento) {
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

}
