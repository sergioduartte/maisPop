package maisPop;

public class CelebridadePop implements Popularidade {

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
}
