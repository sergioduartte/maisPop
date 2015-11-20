package maisPop;

import java.util.List;

public interface Popularidade {

	public void curtirPost(Postagem p);

	public void rejeitaPost(Postagem p);

	public int getValorCurtida(Postagem p);

	public int getValorRejeita(Postagem p);

	public List<Postagem> fornecePosts(List<Postagem> mural);
}
