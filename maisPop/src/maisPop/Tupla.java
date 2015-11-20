package maisPop;

import java.io.Serializable;

public class Tupla implements Comparable<Tupla>, Serializable {

	private String hashtag;
	private int quantidade;

	public Tupla(String hashtag) {
		this.hashtag = hashtag;
		this.quantidade = 1;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void addQuantidade() {
		this.quantidade += 1;
	}

	public String getHashtag() {
		return hashtag;
	}

	@Override
	public int compareTo(Tupla outra) {
		if (this.getQuantidade() > outra.getQuantidade()) {
			return -1;
		} else if (this.getQuantidade() < outra.getQuantidade()) {
			return 1;
		} else if (this.getQuantidade() == outra.getQuantidade()) {
			return outra.getHashtag().compareToIgnoreCase(this.hashtag);
		}
		return 0;
	}

}
