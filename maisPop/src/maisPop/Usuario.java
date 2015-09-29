package maisPop;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {

	private String nome, email, senha, dataDeNascimento, caminhoImagem;

	private final String ERRO_DE_CADASTRO = "Erro no cadastro de Usuarios. ";
	
	public static String NOME = "Nome";
	public static String EMAIL = "Email";
	public static String SENHA = "Senha";
	public static String DATA_DE_NASCIMENTO = "Data de Nascimento";
	public static String FOTO = "Foto";
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		if (nome.trim().length() > 0) {
			this.nome = nome;
		} else {
			throw new Exception(ERRO_DE_CADASTRO + "Nome dx usuarix nao pode ser vazio.");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		Pattern p = Pattern.compile("[\\w\\d_\\.%\\+-]+@[\\w\\d\\.-]+\\.[\\w]{2,6}");
		Matcher m = p.matcher(email);

		if (m.matches()) {
			this.email = email;
		} else {
			throw new Exception(ERRO_DE_CADASTRO + "Formato de e-mail esta invalido.");
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) throws Exception {		
		Pattern p = Pattern.compile("[\\d]{2}/[\\d]{2}/[\\d]{4}");
		Matcher m = p.matcher(dataDeNascimento);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		
		Date resultado;
		if (!m.matches()) {
			throw new Exception(ERRO_DE_CADASTRO + "Formato de data esta invalida.");
		} else {
			try {
				resultado = df.parse(dataDeNascimento);
			} catch (Exception e) {
				throw new Exception(ERRO_DE_CADASTRO + "Data nao existe.");
			}
			this.dataDeNascimento = dataDeNascimento;
		}
			
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public Usuario(String nome, String email, String senha, String dataDeNasc, String imagem) throws Exception {
		// TODO Auto-generated constructor stub
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setDataDeNascimento(dataDeNasc);
		setCaminhoImagem(imagem);
	}

}
