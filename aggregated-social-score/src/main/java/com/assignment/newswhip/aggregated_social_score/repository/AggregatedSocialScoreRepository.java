package com.assignment.newswhip.aggregated_social_score.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.newswhip.aggregated_social_score.model.ArticleInfo;

import jakarta.transaction.Transactional;

public interface AggregatedSocialScoreRepository extends JpaRepository<ArticleInfo, Long> {

	@Transactional
	void deleteByUrl(String url);
	
	@Transactional
	List<ArticleInfo> findByLocation(String location);
}
