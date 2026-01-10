package io.github.cciglesiasmartinez.microservice_template.infrastructure.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ranking")
public class RankingProperties {

	private int m = 10;
	private int k = 20;
	private Map<String, Double> editionWeight = new HashMap<>();
	private Map<String, Double> conditionWeight = new HashMap<>();
	private Map<String, Double> completenessWeight = new HashMap<>();
	private Topics topics = new Topics();
	
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public Map<String, Double> getEditionWeight() {
		return editionWeight;
	}
	public void setEditionWeight(Map<String, Double> editionWeight) {
		this.editionWeight = editionWeight;
	}
	public Map<String, Double> getConditionWeight() {
		return conditionWeight;
	}
	public void setConditionWeight(Map<String, Double> conditionWeight) {
		this.conditionWeight = conditionWeight;
	}
	public Map<String, Double> getCompletenessWeight() {
		return completenessWeight;
	}
	public void setCompletenessWeight(Map<String, Double> completenessWeight) {
		this.completenessWeight = completenessWeight;
	}
	public Topics getTopics() {
		return topics;
	}
	public void setTopics(Topics topics) {
		this.topics = topics;
	}
	
	public double resolveEditionWeight(String edition) {
        return resolveWeight(editionWeight, edition);
    }
	
	public double resolveConditionWeight(String condition) {
		return resolveWeight(conditionWeight, condition);
	}
	
	public double resolveCompletenessWeight(String completeness) {
        return resolveWeight(completenessWeight, completeness);
    }
	
	private double resolveWeight(Map<String, Double> weights, String key) {
		
		if (key == null) {
			return 0.0;
		}
		return weights.getOrDefault(key.toUpperCase(), 0.0);
	}
	
	public static class Topics {
		private String userItems = "user-items-events.v1";
		private String users = "users-events.v1";
        private String itemRarityUpdated = "item-rarity-updated.v1";
		
        public String getUserItems() {
            return userItems;
        }

        public void setUserItems(String userItems) {
            this.userItems = userItems;
        }

        public String getUsers() {
            return users;
        }

        public void setUsers(String users) {
            this.users = users;
        }

        public String getItemRarityUpdated() {
            return itemRarityUpdated;
        }

        public void setItemRarityUpdated(String itemRarityUpdated) {
            this.itemRarityUpdated = itemRarityUpdated;
        }
        
	}
	
	
}
