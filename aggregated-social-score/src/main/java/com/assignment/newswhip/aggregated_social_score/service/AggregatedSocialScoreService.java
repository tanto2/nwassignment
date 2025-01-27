package com.assignment.newswhip.aggregated_social_score.service;

import java.util.List;

import com.assignment.newswhip.aggregated_social_score.model.ArticleInfo;
import com.assignment.newswhip.aggregated_social_score.model.Report;

public interface AggregatedSocialScoreService {
	
	public List<ArticleInfo> getAllUrls();
	
	public ArticleInfo saveArticle(ArticleInfo articleInfo);
		 	
	public void deleteArticle(String url);

	public List<Report> getReportByCountry(String location);
	

}
