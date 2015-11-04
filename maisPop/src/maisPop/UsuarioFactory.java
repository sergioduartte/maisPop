package maisPop;

import java.time.LocalDate;
import java.util.ArrayList;
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

	private String nome, email, senha, dataDeNascimento, caminhoImagem;
	private int pop;
	private Popularidade popularidade;
	private ArrayList<String> notificacoes;
	private ArrayList<String> solicitacoes;
	private ArrayList<String> amigos;
	private ArrayList<Postagem> mural;

	private final String ERRO_DE_CADASTRO = "Erro no cadastro de Usuarios. ";
	private final String ERRO_DE_ATUALIZACAO = "Erro na atualizacao de perfil. ";

	public static String USUARIO_NORMAL = "Normal";
	public static String CELEBRIDADE_POP = "CelebridadePop";
	public static String ICONE_POP = "IconePop";

	public Usuario criaUsuario(String nome, String email, String senha, String dataDeNascimento,
			String caminhoDaImagem) throws Exception {
		validaUsuario(nome, email, senha, dataDeNascimento, caminhoDaImagem);
		return new Usuario(nome, email, senha, dataDeNascimento, caminhoDaImagem);
	}
	
	public void validaUsuario(String nome, String email, String senha, String dataDeNasc, String imagem)
			throws Exception {
		validaNome(nome);
		validaEmail(email);
		validaDataDeNasc(dataDeNasc);
	}
	
	public LocalDate transformaData(String dataDeNascimento) {
		String[] dataSplit = dataDeNascimento.split("/");
		int dia = Integer.parseInt(dataSplit[0].trim());
		int mes = Integer.parseInt(dataSplit[1].trim());
		int ano = Integer.parseInt(dataSplit[2].trim());
		return LocalDate.of(ano, mes, dia);
	}
	
	private void validaDataDeNasc(String dataDeNasc) throws Exception {
		Pattern p = Pattern.compile("[\\d]{2}/[\\d]{2}/[\\d]{4}");
		Matcher m = p.matcher(dataDeNascimento);

		if (!m.matches() && this.dataDeNascimento == null) {
			throw new Exception(ERRO_DE_CADASTRO + "Formato de data esta invalida.");

		} else if (!m.matches() && this.dataDeNascimento != null) {
			throw new Exception(ERRO_DE_ATUALIZACAO + "Formato de data esta invalida.");
		} else {
			try {
				LocalDate data = transformaData(dataDeNascimento);
			} catch (Exception e) {
				if (this.dataDeNascimento != null) {
					throw new Exception(ERRO_DE_ATUALIZACAO + "Data nao existe.");
				} else {
					throw new Exception(ERRO_DE_CADASTRO + "Data nao existe.");
				}
			}
		}

	}

	private void validaNome(String nome) throws Exception {
		if ((this.nome != null) && (nome.trim().length() == 0)) {
			throw new Exception(ERRO_DE_ATUALIZACAO + "Nome dx usuarix nao pode ser vazio.");
		} else if (nome.trim().length() < 0) {
			throw new Exception(ERRO_DE_CADASTRO + "Nome dx usuarix nao pode ser vazio.");
		}
	}

	private void validaEmail(String email) throws Exception {
		Pattern p = Pattern.compile("[\\w\\d_\\.%\\+-]+@[\\w\\d\\.-]+\\.[\\w]{2,6}");
		Matcher m = p.matcher(email);

		if (this.email != null && !m.matches()) {
			throw new Exception(ERRO_DE_ATUALIZACAO + "Formato de e-mail esta invalido.");
		}
		if (!m.matches()) {
			throw new Exception(ERRO_DE_CADASTRO + "Formato de e-mail esta invalido.");
		}

	}
}
