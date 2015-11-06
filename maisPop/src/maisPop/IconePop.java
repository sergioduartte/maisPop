package maisPop;

public class IconePop implements Popularidade {

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

}