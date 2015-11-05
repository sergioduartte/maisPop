package maisPop;

public class CelebridadePop implements Popularidade {

	public final int VALOR_POP = 25;
	public final int VALOR_ADICIONAL = 10;

	@Override
	public void curtirPost(Postagem p) {
		if (p.ehRecente()) {
			p.addPopularidade(VALOR_POP + VALOR_ADICIONAL);
		} else {
			p.addPopularidade(VALOR_POP);
		}
	}

	@Override
	public void rejeitaPost(Postagem p) {
		if (p.ehRecente()) {
			p.diminuiPopularidade(VALOR_POP + VALOR_ADICIONAL);
		} else {
			p.diminuiPopularidade(VALOR_POP);
		}
	}
	
	@Override
	public String toString() {
		return "Celebridade Pop";
	}

}
