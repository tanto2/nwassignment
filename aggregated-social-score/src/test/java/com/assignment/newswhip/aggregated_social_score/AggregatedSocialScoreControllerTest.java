package com.assignment.newswhip.aggregated_social_score;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.assignment.newswhip.aggregated_social_score.controller.AggregatedSocialScoreController;
import com.assignment.newswhip.aggregated_social_score.model.ArticleInfo;
import com.assignment.newswhip.aggregated_social_score.model.Report;
import com.assignment.newswhip.aggregated_social_score.service.AggregatedSocialScoreService;

public class AggregatedSocialScoreControllerTest {

	@Mock
	private AggregatedSocialScoreService scoreService;
	
	@InjectMocks
	private AggregatedSocialScoreController scoreController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(scoreController).build();
	}
	
	@Test
	void testSaveArticle() throws Exception {
		ArticleInfo article = new ArticleInfo(1L, "http://www.rte.ie/news/politics/2018/1004/1001034-cso/", 20, "ie");
		
		when(scoreService.saveArticle(any(ArticleInfo.class))).thenReturn(article);
		
		mockMvc.perform(post("/article")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
                    {
                        "url": "http://www.rte.ie/news/politics/2018/1004/1001034-cso/",
                        "engagement": 20,
                        "location": "ie"
                    }
                """))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.url").value("http://www.rte.ie/news/politics/2018/1004/1001034-cso/"));
	}
	
	@Test
    void testDeleteArticle() throws Exception {
        doNothing().when(scoreService).deleteArticle("http://www.rte.ie/news/politics/2018/1004/1001034-cso/");

        mockMvc.perform(delete("/article?url=http://www.rte.ie/news/politics/2018/1004/1001034-cso/"))
            .andExpect(status().isOk())
            .andExpect(content().string("Article http://www.rte.ie/news/politics/2018/1004/1001034-cso/ deleted successfully"));
    }
	
    @Test
    void testGetReportByCountry() throws Exception {
        List<Report> reports = Arrays.asList(
            new Report("rte.ie", 2, 40),
            new Report("example.org", 1, 30)
        );

        when(scoreService.getReportByCountry("ie")).thenReturn(reports);

        mockMvc.perform(get("/report/ie"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].domain").value("rte.ie"))
            .andExpect(jsonPath("$[0].urls").value(2));
    }
}
