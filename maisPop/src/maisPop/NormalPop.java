package maisPop;

public class NormalPop implements Popularidade {

	public final int VALOR_POP = 25;

	@Override
	public void curtirPost(Postagem p) {
		p.addPopularidade(VALOR_POP);
	}

	@Override
	public void rejeitaPost(Postagem p) {
		p.diminuiPopularidade(VALOR_POP);
	}

}
