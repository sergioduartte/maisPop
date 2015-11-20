package maisPop1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CelebridadePop implements Popularidade, Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final int NUMERO_POSTS = 4;
	public final int VALOR_POP = 25;
	public final int VALOR_ADICIONAL = 10;
	
	@Override
	public int getValorCurtida(Postagem p) {
		if (p.ehRecente()) {
			return VALOR_POP + VALOR_ADICIONAL;
		} else {
			return VALOR_POP;
		}
	}	

	@Override
	public void curtirPost(Postagem p) {
		p.addPopularidade(getValorCurtida(p));
	}

	@Override
	public void rejeitaPost(Postagem p) {
		p.diminuiPopularidade(getValorCurtida(p));
	}
	
	@Override
	public String toString() {
		return "Celebridade Pop";
	}

	@Override
	public int getValorRejeita(Postagem p) {
		if (p.ehRecente()) {
			return VALOR_POP + VALOR_ADICIONAL;
		} else {
			return VALOR_POP;
		}
	}
	
	public List<Postagem> fornecePosts(List<Postagem> mural) {
		List<Postagem> postagens = new ArrayList<Postagem>();
		for (int i = 0; i < NUMERO_POSTS; i++) {
			postagens.add(mural.get(i));
		}
		return postagens;
	}
}
