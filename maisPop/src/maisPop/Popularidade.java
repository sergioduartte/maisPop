package maisPop;

public interface Popularidade {

	public void curtirPost(Postagem p);

	public void rejeitaPost(Postagem p);

	public int getValorCurtida(Postagem p);

	public int getValorRejeita(Postagem p);

}
