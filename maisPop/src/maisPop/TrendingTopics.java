package maisPop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrendingTopics implements Serializable{

	private List<Tupla> trendingTopic;

	public TrendingTopics() {
		trendingTopic = new ArrayList<Tupla>();
	}

	public void add(Tupla hashtag) {
		trendingTopic.add(hashtag);
		Collections.sort(trendingTopic);
	}

	@Override
	public String toString() {
		Collections.sort(trendingTopic);
		return "Trending Topics:  (1) " + trendingTopic.get(0).getHashtag() + ": "
				+ trendingTopic.get(0).getQuantidade() + "; " + "(2) " + trendingTopic.get(1).getHashtag() + ": "
				+ trendingTopic.get(1).getQuantidade() + "; " + "(3) " + trendingTopic.get(2).getHashtag() + ": "
				+ trendingTopic.get(2).getQuantidade() + ";";
	}

	public boolean hasHashtag(String hashtag) {
		for (Tupla tupla : trendingTopic) {
			if (tupla.getHashtag().equals(hashtag)) {
				return true;
			}
		}
		return false;
	}

	public Tupla getHashtag(String hashtag) {
		for (Tupla tupla : trendingTopic) {
			if (tupla.getHashtag().equals(hashtag)) {
				return tupla;
			}
		}
		return null;
	}

	public int size() {
		return this.trendingTopic.size();
	}

}
