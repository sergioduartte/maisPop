package maisPop;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {

	public static String NOME = "Nome";
	public static String EMAIL = "Email";
	public static String SENHA = "Senha";
	public static String DATA_DE_NASCIMENTO = "Data de Nascimento";
	public static String FOTO = "Foto";

	private String nome, email, senha, dataDeNascimento, caminhoImagem;

	private final String ERRO_DE_CADASTRO = "Erro no cadastro de Usuarios. ";

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

		if (!m.matches()) {
			throw new Exception(ERRO_DE_CADASTRO + "Formato de data esta invalida.");
		} else {
			try {
				LocalDate data = transformaData(dataDeNascimento);
			} catch (Exception e) {
				throw new Exception(ERRO_DE_CADASTRO + "Data nao existe.");
			}
			this.dataDeNascimento = dataDeNascimento;
		}
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

	public void setNome(String nome) throws Exception {
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

}
