package com.assignment.newswhip.aggregated_social_score.service;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.newswhip.aggregated_social_score.model.ArticleInfo;
import com.assignment.newswhip.aggregated_social_score.model.Report;
import com.assignment.newswhip.aggregated_social_score.repository.AggregatedSocialScoreRepository;

@Service
public class AggregatedSocialScoreServiceImpl implements AggregatedSocialScoreService{
	
	@Autowired
	private AggregatedSocialScoreRepository scoreRepository;
	
	@Override
	public List<ArticleInfo> getAllUrls() {
		// TODO Auto-generated method stub
		return scoreRepository.findAll();
	}

	@Override
	public ArticleInfo saveArticle(ArticleInfo articleInfo) {
		return scoreRepository.save(articleInfo);
	}
	
	@Override
	public void deleteArticle(String url) {
		scoreRepository.deleteByUrl(url);
	}

	@Override
	public List<Report> getReportByCountry(String location) {
		List<ArticleInfo> articles = scoreRepository.findByLocation(location);
		 
		return articles.stream()
				.collect(Collectors.groupingBy(
						article -> getDomainFromUrl(article.getUrl()),
						Collectors.collectingAndThen(
								Collectors.toList(), 
								list -> new Report(
										getDomainFromUrl(list.get(0).getUrl()),
										list.size(),
										list.stream().mapToInt(ArticleInfo::getEngagement).sum()
								)
						)
					))
					.values()
					.stream()
					.sorted(Comparator.comparing(Report::aggSocialScore).reversed())
					.collect(Collectors.toList());
		
	}
	
	private String getDomainFromUrl(String url) {
		try {
			return new URI(url).getHost();
		} catch (Exception e) {
			throw new RuntimeException("Invalid URL: " + url);
		}
	}
}
