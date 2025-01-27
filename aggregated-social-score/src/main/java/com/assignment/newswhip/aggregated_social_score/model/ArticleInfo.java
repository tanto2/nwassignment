package com.assignment.newswhip.aggregated_social_score.model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
//@NoArgsConstructor
@Table(name = "web_article_info")
@Entity
public class ArticleInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "url", nullable = false)
	private String url;
	
	@Column(name = "engagement", nullable = false)
	private int engagement;
	
	@Column(name = "location", nullable = false)
	private String location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getEngagement() {
		return engagement;
	}

	public void setEngagement(int engagement) {
		this.engagement = engagement;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
