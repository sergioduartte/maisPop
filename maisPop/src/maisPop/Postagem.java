package maisPop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Postagem {

	private int popularidade;
	private String mensagem;
	private LocalDateTime data;
	private ArrayList<String> hashtags;
	private ArrayList<String> arquivos;
	private ArrayList<String> conteudo;

	private static String ERRO_DE_CRIACAO = "Nao eh possivel criar o post. ";

	public Postagem(String mensagem, String data) throws Exception {
		hashtags = new ArrayList<String>();
		arquivos = new ArrayList<String>();
		conteudo = new ArrayList<>();
		setMensagem(mensagem);
		validaMensagem();
		setPopularidade(0);
		setData(data);
	}

	private void setData(String data) {
		String[] dataSplit = data.split(" ");
		String[] dataTemp = dataSplit[0].split("/");
		int dia = Integer.parseInt(dataTemp[0].trim());
		int mes = Integer.parseInt(dataTemp[1].trim());
		int ano = Integer.parseInt(dataTemp[2].trim());

		String[] horaTemp = dataSplit[1].split(":");
		int hora = Integer.parseInt(horaTemp[0].trim());
		int minuto = Integer.parseInt(horaTemp[1].trim());
		int segundo = Integer.parseInt(horaTemp[2].trim());

		LocalDateTime saida = LocalDateTime.of(LocalDate.of(ano, mes, dia), LocalTime.of(hora, minuto, segundo));

		this.data = saida;
	}

	private void setPopularidade(int i) {
		this.popularidade = i;
	}

	private void setMensagem(String mensagem) throws Exception {
		this.mensagem = mensagem;
		StringBuilder mensagemPura = new StringBuilder();
		String[] msg = mensagem.split(" ");
		String padraoHashtag = "#\\w+";
		String padraoAudio = "<audio>.*?</audio>";
		String padraoVideo = "<video>.*?</video>";
		String padraoImagem = "<imagem>.*?</imagem>";

		for (int i = 0; i < msg.length; i++) {
			if (msg[i].matches(padraoAudio)) {
				conteudo.add(msg[i].trim());
				arquivos.add("$arquivo_audio:" + msg[i].replaceAll("</?audio>", ""));
			} else if (msg[i].matches(padraoVideo)) {
				conteudo.add(msg[i].trim());
				arquivos.add("$arquivo_video:" + msg[i].replaceAll("</?video>", ""));
			} else if (msg[i].matches(padraoImagem)) {
				conteudo.add(msg[i]);
				arquivos.add("$arquivo_imagem:" + msg[i].replaceAll("</?imagem>", ""));
			} else if (msg[i].matches(padraoHashtag)) {
				hashtags.add(msg[i]);
			} else {
				mensagemPura.append(msg[i] + " ");
			}
		}
		conteudo.add(0, mensagemPura.toString().trim());
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

	public String getData() {
		String saida = this.data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return saida;
	}

	public String getMensagemPura() throws Exception {
		return conteudo.get(0);
	}

	public String getMensagemSemHashtag() throws Exception {
		StringBuilder saida = new StringBuilder();
		for (String item : conteudo) {
			saida.append(item + " ");
		}
		return saida.toString().trim();
	}

	public String getHashtags() {
		StringBuilder saida = new StringBuilder();
		for (int i = 0; i < hashtags.size(); i++) {
			saida.append(hashtags.get(i));
			if (i != hashtags.size() - 1) {
				saida.append(",");
			}
		}
		return saida.toString();
	}

	public String getArquivosDaMensagem() {
		StringBuilder saida = new StringBuilder();
		for (int i = 0; i < arquivos.size(); i++) {
			saida.append(arquivos.get(i));
			if (i < arquivos.size() - 1) {
				saida.append(" ");
			}
		}

		return saida.toString();
	}

	public String getConteudo(int indice) throws Exception {
		try {
			if (indice == 0) {
				return conteudo.get(indice);
			} else {
				return arquivos.get(indice - 1);
			}
		} catch (Exception e) {
			throw new Exception("Item #" + indice + " nao existe nesse post, ele possui apenas " + conteudo.size()
					+ " itens distintos.");
		}
	}

	@Override
	public String toString() {
		String saida = this.mensagem;
		saida += " (" + getData() + ")";
		return saida;
	}

}
