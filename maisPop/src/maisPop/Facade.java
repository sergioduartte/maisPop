package maisPop;

import easyaccept.EasyAccept;

public class Facade {

	private Controller controlador;
	private Serializador serializador;

	public static void main(String[] args) {
		String[] files = new String[] { "maisPop.Facade", "testes/scripts_de_teste/usecase_1.txt",
				"testes/scripts_de_teste/usecase_2.txt", "testes/scripts_de_teste/usecase_3.txt",
				"testes/scripts_de_teste/usecase_4.txt", "testes/scripts_de_teste/usecase_5.txt",
				"testes/scripts_de_teste/usecase_6.txt", "testes/scripts_de_teste/usecase_7.txt",
				"testes/scripts_de_teste/usecase_8.txt", "testes/scripts_de_teste/usecase_9.txt",
				"testes/scripts_de_teste/usecase_10.txt" };
		EasyAccept.main(files);
	}

	public Facade() {
		serializador = new Serializador();
		iniciaSistema();
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataDeNasc) throws Exception {
		return controlador.cadastraUsuario(nome, email, senha, dataDeNasc);
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataDeNasc, String imagem)
			throws Exception {
		return controlador.cadastraUsuario(nome, email, senha, dataDeNasc, imagem);
	}

	public void fechaSistema() throws Exception {
		serializador.fechaSistema(controlador);
	}
	
	public void iniciaSistema() {
		controlador = serializador.iniciaSistema();
		if (controlador == null) {
			controlador = new Controller();
		}
	}
	
	public String getInfoUsuario(String atributo) throws Exception {
		return controlador.getInfoUsuario(atributo);
	}

	public String getInfoUsuario(String atributo, String email) throws Exception {
		return controlador.getInfoUsuario(atributo, email);
	}

	public void login(String email, String senha) throws Exception {
		controlador.login(email, senha);
	}

	public void logout() throws Exception {
		controlador.logout();
	}

	public void removeUsuario(String idDoUsuario) throws Exception {
		controlador.removeUsuario(idDoUsuario);
	}

	public void atualizaPerfil(String atributo, String valor) throws Exception {
		controlador.atualizaPerfil(atributo, valor);
	}

	public void atualizaPerfil(String atributo, String novaSenha, String velhaSenha) throws Exception {
		controlador.atualizaPerfil(atributo, novaSenha, velhaSenha);
	}

	public void criaPost(String mensagem, String data) throws Exception {
		controlador.criaPost(mensagem, data);
	}

	public void adicionaAmigo(String usuarioEmail) throws Exception {
		controlador.adicionaAmigo(usuarioEmail);
	}

	public void aceitaAmizade(String usuarioEmail) throws Exception {
		controlador.aceitaAmizade(usuarioEmail);
	}

	public void rejeitaAmizade(String usuarioEmail) throws Exception {
		controlador.rejeitaAmizade(usuarioEmail);
	}

	public void removeAmigo(String usuarioEmail) throws Exception {
		controlador.removeAmigo(usuarioEmail);
	}

	public String getNextNotificacao() throws Exception {
		return controlador.getNextNotificacao();
	}

	public int getQtdAmigos() {
		return controlador.getQtdAmigos();
	}

	public int getNotificacoes() {
		return controlador.getNotificacoes();
	}

	public Postagem getPost(int post) throws Exception {
		return controlador.getPost(post);
	}

	public String getPost(String atributo, int post) throws Exception {
		return controlador.getPost(atributo, post);
	}

	public String getConteudoPost(int indice, int post) throws Exception {
		return controlador.getConteudoPost(indice, post);
	}

	public void curtirPost(String usuarioEmail, int post) throws Exception {
		controlador.curtirPost(usuarioEmail, post);
	}

	public void rejeitarPost(String usuarioEmail, int post) throws Exception {
		controlador.rejeitaPost(usuarioEmail, post);
	}

	public void adicionaPops(int i) {
		controlador.adicionaPops(i);
	}

	public String getPopularidade() {
		return controlador.getPopularidade();
	}

	public int getPopsUsuario() {
		return controlador.getPopsUsuario();
	}

	public int getPopsUsuario(String usuarioEmail) throws Exception {
		return controlador.getPopsUsuario(usuarioEmail);
	}

	public int getPopsPost(int post) throws Exception {
		return controlador.getPopsPost(post);
	}

	public int qtdCurtidasDePost(int post) throws Exception {
		return controlador.getCurtidasDePost(post);
	}

	public int qtdRejeicoesDePost(int post) throws Exception {
		return controlador.getRejeitadasDePost(post);
	}

	public String atualizaRanking() {
		return controlador.atualizaRanking();
	}

	public String atualizaTrendingTopics() {
		return controlador.atualizaTrendingTopics();
	}
	
	public int getTotalPosts(){
		return controlador.getTotalPosts();
	}
}
