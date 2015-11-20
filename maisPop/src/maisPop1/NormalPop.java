package maisPop1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NormalPop implements Popularidade, Serializable {


	private static final long serialVersionUID = 1L;
	public static final int VALOR_POP = 10;
	public static final int NUMERO_POSTS = 2;


	@Override
	public int getValorCurtida(Postagem p) {
		return VALOR_POP;
	}

	@Override
	public void curtirPost(Postagem p) {
		p.addPopularidade(VALOR_POP);
	}

	@Override
	public void rejeitaPost(Postagem p) {
		p.diminuiPopularidade(VALOR_POP);
	}
	
	@Override
	public String toString() {
		return "Normal Pop";
	}

	@Override
	public int getValorRejeita(Postagem p) {
		return VALOR_POP;
	}

	@Override
	public List<Postagem> fornecePosts(List<Postagem> mural) {
		List<Postagem> postagens = new ArrayList<Postagem>();
		for (int i = 0; i < NUMERO_POSTS; i++) {
			postagens.add(mural.get(i));
		}
		return postagens;
	}

}
