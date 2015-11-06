package maisPop;

public class CelebridadePop implements Popularidade {

	public final int VALOR_POP = 25;
	public final int VALOR_ADICIONAL = 10;
	
	@Override
<<<<<<< HEAD
	public int getValorCurtida(Postagem p) {
		if (p.ehRecente()) {
			return VALOR_POP + VALOR_ADICIONAL;
=======
	public void curtirPost(Postagem p) {
		if (p.ehRecente()) {
			p.addPopularidade(VALOR_POP + VALOR_ADICIONAL);
>>>>>>> 1d4838268ba371bdcb5e1c4a673103ceba5479be
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
<<<<<<< HEAD
	public String toString() {
		return "Celebridade Pop";
	}

	@Override
	public int getValorRejeita(Postagem p) {
		if (p.ehRecente()) {
			return VALOR_POP + VALOR_ADICIONAL;
=======
	public void rejeitaPost(Postagem p) {
		if (p.ehRecente()) {
			p.diminuiPopularidade(VALOR_POP + VALOR_ADICIONAL);
>>>>>>> 1d4838268ba371bdcb5e1c4a673103ceba5479be
		} else {
			return VALOR_POP;
		}
<<<<<<< HEAD
	}	
=======
	}

>>>>>>> 1d4838268ba371bdcb5e1c4a673103ceba5479be
}
