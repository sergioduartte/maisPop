package maisPop;

public class NormalPop implements Popularidade {

	public final int VALOR_POP = 10;

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

}
