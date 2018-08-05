package com.wecancodeit.reviewssite.fullstackreview;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReviewRestControllerTest {
	
	@Resource
	private TestRestTemplate restTemplate;
	
	@Test
	public void shouldBeOkForReviewTags() {
		ResponseEntity<String> response = restTemplate.getForEntity("/review/7/tags",  String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
	}
	
	@Test
	public void shouldBeOkForAddNewTag() throws URISyntaxException {
	
		URI putUri = new URI("/review/add-new-tag");
		
	
		String json = "{\"reviewId\":\"7\", \"tagName\":\"game\"}";
		
	
		RequestEntity<String> request = 
				RequestEntity.put(putUri) 
				.header("Content-Type", "application/json")
				.accept(MediaType.APPLICATION_JSON) 
				.body(json);
		

		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		
		
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
	}
	
	@Test 																						//THIS NEEDS ASSISTANCE FROM AARON!!!
	public void shouldBeOkForRemoveTagFromReview() throws URISyntaxException {
		
		URI deleteUri = new URI("/review/removetag");
		
		String json = "{\"reviewId\":\"7\", \"tagName\":\"game\"}";
				

		RequestEntity<Void> request = 
				RequestEntity
				.delete(deleteUri)
				.accept(MediaType.APPLICATION_JSON)
				.build();
		
		
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.BAD_REQUEST));
	}

}
