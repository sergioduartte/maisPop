package maisPop;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioFactory {

	public static String NOME = "Nome";
	public static String EMAIL = "E-mail";
	public static String SENHA = "Senha";
	public static String DATA_DE_NASCIMENTO = "Data de Nascimento";
	public static String FOTO = "Foto";
	public static String MENSAGEM = "Mensagem";
	public static String DATA_DA_POSTAGEM = "Data";
	public static String HASHTAGS = "Hashtags";

	private final String ERRO_DE_CADASTRO = "Erro no cadastro de Usuarios. ";

	public static String USUARIO_NORMAL = "Normal";
	public static String CELEBRIDADE_POP = "CelebridadePop";
	public static String ICONE_POP = "IconePop";

	public Usuario criaUsuario(String nome, String email, String senha, String dataDeNascimento, String caminhoDaImagem)
			throws Exception {
		validaUsuario(nome, email, senha, dataDeNascimento, caminhoDaImagem);
		return new Usuario(nome, email, senha, dataDeNascimento, caminhoDaImagem);
	}

	public void validaUsuario(String nome, String email, String senha, String dataDeNasc, String imagem)
			throws Exception {
		validaNome(nome);
		validaEmail(email);
		validaDataDeNasc(dataDeNasc);
	}

	private void validaDataDeNasc(String dataDeNasc) throws Exception {
		Pattern p = Pattern.compile("[\\d]{2}/[\\d]{2}/[\\d]{4}");
		Matcher m = p.matcher(dataDeNasc);

		if (!m.matches()) {
			throw new Exception(ERRO_DE_CADASTRO + "Formato de data esta invalida.");
		} else {
			try {
				LocalDate data = Usuario.transformaData(dataDeNasc);
			} catch (Exception e) {
				throw new Exception(ERRO_DE_CADASTRO + "Data nao existe.");
			}
		}

	}

	private void validaNome(String nome) throws Exception {
		if (nome.trim().length() <= 0) {
			throw new Exception(ERRO_DE_CADASTRO + "Nome dx usuarix nao pode ser vazio.");
		}
	}

	private void validaEmail(String email) throws Exception {
		Pattern p = Pattern.compile("[\\w\\d_\\.%\\+-]+@[\\w\\d\\.-]+\\.[\\w]{2,6}");
		Matcher m = p.matcher(email);

		if (!m.matches()) {
			throw new Exception(ERRO_DE_CADASTRO + "Formato de e-mail esta invalido.");
		}

	}
}
