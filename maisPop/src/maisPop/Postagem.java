package maisPop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Postagem {

	private int popularidade;
	private String mensagem;
	private Date data;
	private ArrayList<String> mensagemPura;
	private ArrayList<String> hashtags;
	private ArrayList<String> arquivos;

	// tipos de saida
	private static String COMPLETA = "MensagemCompleta";
	private static String SEM_ARQUIVOS = "MensagemSemArquivos"; // e com hashtags
	private static String SEM_HASHTAG = "MensagemSemHashtag"; // e com arquivos
	private static String PURA = "MensagemPura";
	private static String DATA = "Data";

	private static String ERRO_DE_CRIACAO = "Nao eh possivel criar o post. ";

	public Postagem(String mensagem, String data) throws Exception {
		hashtags = new ArrayList<String>();
		arquivos = new ArrayList<String>();
		mensagemPura= new ArrayList<String>();
		setMensagem(mensagem);
		validaMensagem();
		setPopularidade(0);
		setData(data);
	}

	private void setData(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.data = sdf.parse(data);
	}

	private void setPopularidade(int i) {
		this.popularidade = i;
	}

	private void setMensagem(String mensagem) throws Exception {
		this.mensagem = mensagem;
		
		String[] msg = mensagem.split(" ");
		String padraoHashtag = "#\\w+";
		String padraoAudio = "<audio>.*?</audio>";
		String padraoVideo = "<video>.*?</video>";
		String padraoImagem = "<imagem>.*?</imagem>";
		
		for (int i = 0; i < msg.length; i++) {
			if (	msg[i].matches(padraoAudio) || 
					msg[i].matches(padraoVideo) ||
					msg[i].matches(padraoImagem)){
				arquivos.add(msg[i]);
			} else if(msg[i].matches(padraoHashtag)){
				hashtags.add(msg[i]);	
			} else{
				mensagemPura.add(msg[i].trim());
			}
		}
	}

	private void validaMensagem() throws Exception {
		String msg = getMensagemPura();
		if (msg.length() >= 200) {
			throw new Exception(ERRO_DE_CRIACAO + "O limite maximo da mensagem sao 200 caracteres.");
		} else {
			String[] msgTemp = this.mensagem.split(" ");
			for (int i = 0; i < msgTemp.length - 1; i++) {
				if (msgTemp[i].charAt(0) == '#') {
					if (msgTemp[i + 1].charAt(0) != '#') {
						throw new Exception(ERRO_DE_CRIACAO + "As hashtags devem comecar com '#'. Erro na hashtag: '"
								+ msgTemp[i + 1] + "'.");
					}
				}
			}
		}
	}

/*	private String retornaPorTipo(String tipoDeSaida) throws Exception {
		String saida = "";
		if (tipoDeSaida.equals(COMPLETA)) {
			saida = this.mensagem;
		} else if (tipoDeSaida.equals(SEM_ARQUIVOS)) {
			saida = getMensagemSemArquivos(mensagem);
		} else if (tipoDeSaida.equals(SEM_HASHTAG)) {
//			saida = getMensagemSemHashtags(mensagem);
		} else if (tipoDeSaida.equals(PURA)) {
			saida = getMensagemPura();
		} else if (tipoDeSaida.equals(DATA)) {
			saida = getData();
		}

		return saida;
	}*/

	private String getData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(data);
	}

	private String getMensagemPura() throws Exception {
		StringBuilder saida = new StringBuilder();
		for (int i = 0; i < mensagemPura.size(); i++) {
			if (i == mensagemPura.size()) {
				saida.append(mensagemPura.get(i));
			} else{
				saida.append(mensagemPura.get(i) + " ");
			}
		}
		return saida.toString();
	}

	private String getMensagemSemHashtags() throws Exception {
		String[] msgTemp = this.mensagem.split(" ");
		String saida = "";
		//TODO fazer um for varrendo as hashtags, se estiver na lista de msgTemp, replace("").
		/*for (int i = 0; i < msgTemp.length; i++) {
			if (msgTemp[i].charAt(0) != '#'){
				this.hashTags.append
			}
		}*/
		return saida.toString();
	}

	private String getMensagemSemArquivos(String mensagem) {
		//TODO implementar com regex
		return null;
	}
	@Override
	public String toString() {
		String saida = this.mensagem;
		saida += " (" + getData() + ")";
		return saida;
	}
}
