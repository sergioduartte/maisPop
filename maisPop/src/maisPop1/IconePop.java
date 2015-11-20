package maisPop1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IconePop implements Popularidade, Serializable{

	private static final long serialVersionUID = 1L;
	private static final int NUMERO_POSTS = 6;
	public final int VALOR_POP = 50;
	public final String EPICWIN = "#epicwin";
	public final String EPICFAIL = "#epicfail";

	@Override
	public void curtirPost(Postagem p) {
		p.addHashTag(EPICWIN);
		p.addPopularidade(getValorCurtida(p));
	}

	@Override
	public void rejeitaPost(Postagem p) {
		p.addHashTag(EPICFAIL);
		p.diminuiPopularidade(getValorRejeita(p));
	}
	
	@Override
	public String toString() {
		return "Icone Pop";
	}

	@Override
	public int getValorCurtida(Postagem p) {
		return VALOR_POP;
	}

	@Override
	public int getValorRejeita(Postagem p) {
		return VALOR_POP;
	}
	
	public List<Postagem> fornecePosts(List<Postagem> mural) {
		List<Postagem> postagens = new ArrayList<Postagem>();
		for (int i = 0; i < NUMERO_POSTS; i++) {
			postagens.add(mural.get(i));
		}
		return postagens;
	}

}