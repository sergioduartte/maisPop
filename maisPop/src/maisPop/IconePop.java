package maisPop;

public class IconePop implements Popularidade {

	public final int VALOR_POP = 50;
	public final String EPICWIN = "#epicwin";
	public final String EPICFAIL = "#epicfail";

	@Override
	public void curtirPost(Postagem p) {
		p.addHashTag(EPICWIN);
		p.addPopularidade(VALOR_POP);
	}

	@Override
	public void rejeitaPost(Postagem p) {
		p.addHashTag(EPICFAIL);
		p.diminuiPopularidade(VALOR_POP);
	}

}