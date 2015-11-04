package maisPop;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioFactory {

	private final String ERRO_DE_CADASTRO = "Erro no cadastro de Usuarios. ";

	public Usuario criaUsuario(String nome, String email, String senha, String dataDeNascimento, String caminhoDaImagem)
			throws Exception {
		validaUsuario(nome, email, senha, dataDeNascimento, caminhoDaImagem);
		return new Usuario(nome, email, senha, dataDeNascimento, caminhoDaImagem);
	}

	public void validaUsuario(String nome, String email, String senha, String dataDeNasc, String imagem)
			throws Exception {
		validaNome(ERRO_DE_CADASTRO, nome);
		validaEmail(ERRO_DE_CADASTRO, email);
		validaDataDeNasc(ERRO_DE_CADASTRO, dataDeNasc);
	}

	public void validaDataDeNasc(String msgErro, String dataDeNasc) throws Exception {
		Pattern p = Pattern.compile("[\\d]{2}/[\\d]{2}/[\\d]{4}");
		Matcher m = p.matcher(dataDeNasc);

		if (!m.matches()) {
			throw new Exception(msgErro + "Formato de data esta invalida.");
		} else {
			try {
				LocalDate data = Usuario.transformaData(dataDeNasc);
			} catch (Exception e) {
				throw new Exception(msgErro + "Data nao existe.");
			}
		}

	}

	public void validaNome(String msgErro, String nome) throws Exception {
		if (nome.trim().length() <= 0) {
			throw new Exception(msgErro + "Nome dx usuarix nao pode ser vazio.");
		}
	}

	public void validaEmail(String msgErro, String email) throws Exception {
		Pattern p = Pattern.compile("[\\w\\d_\\.%\\+-]+@[\\w\\d\\.-]+\\.[\\w]{2,6}");
		Matcher m = p.matcher(email);

		if (!m.matches()) {
			throw new Exception(msgErro + "Formato de e-mail esta invalido.");
		}

	}
}
