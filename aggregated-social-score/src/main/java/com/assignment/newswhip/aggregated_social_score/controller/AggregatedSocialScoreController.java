package com.assignment.newswhip.aggregated_social_score.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.newswhip.aggregated_social_score.model.ArticleInfo;
import com.assignment.newswhip.aggregated_social_score.model.Report;
import com.assignment.newswhip.aggregated_social_score.service.AggregatedSocialScoreService;

@RestController
@RequestMapping("/")
public class AggregatedSocialScoreController {

	@Autowired
	private AggregatedSocialScoreService scoreService;

	// For getting all the articles 
	@GetMapping("/articles")
	public List<ArticleInfo> getAllUrls() {
		return scoreService.getAllUrls();
	}
	
	// Store information: POST /article
	@PostMapping("/article")
	public ResponseEntity<ArticleInfo> saveArticle(@RequestBody ArticleInfo articleInfo) {
		return ResponseEntity.ok(scoreService.saveArticle(articleInfo));
	}

	// Delete URL: DELETE /article?url={url}
	@DeleteMapping("/article")
	public ResponseEntity<String> deleteArticle(@RequestParam String url) {
		scoreService.deleteArticle(url);
		return ResponseEntity.ok("Article "+url+" deleted successfully");

	}

	// Query: GET /report/{countrycode}
	// Given a country, provide the aggregated social score for the domains
	// in the system ranked by social score
	@GetMapping("/report/{location}")
	public ResponseEntity<List<Report>> getReportByCountry(@PathVariable String location){
		return ResponseEntity.ok(scoreService.getReportByCountry(location));
	}
}
