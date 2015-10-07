package maisPop;

import java.util.ArrayList;

public class Postagem {
	
	private int popularidade;
	private String dataHora;
	private String mensagem;
	private ArrayList<String> hashTags;
		
	private static String ERRO_DE_CRIACAO = "Nao eh possivel criar o post. ";

	public Postagem(String mensagem, String data) throws Exception {
		separaMsgDeHashtag(mensagem);
		if (mensagem.length() > 400){
			throw new Exception(ERRO_DE_CRIACAO + "O limite maximo da mensagem sao 200 caracteres.");
		}
	}

	private void separaMsgDeHashtag(String mensagem) {
		// TODO pesquisa as hashtags e separa para seus respectivos atributos.
		String[] msgHT = mensagem.split(" ");
//		for (int i = 0; i < msgHT.length; i++) {
//			if (msgHT[i][0].equals("#") && msgHT)
//			
//		}
			
//		}
		
		
	}
	
}
