package com.stackroute.newsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newsapp.domian.News;
import com.stackroute.newsapp.domian.UsersFavourite;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.service.NewsService;

@CrossOrigin
@RestController
public class NewsController {
	
	private NewsService newsService;
	
	@Autowired
	public NewsController(final NewsService newsService) {
		this.newsService = newsService;
	}
	
	@PostMapping("/addNews")
	public ResponseEntity<?> addNews(@RequestBody final News news) {
		ResponseEntity<?> response;
		try {
			newsService.addNewsByAdmin(news);
			response = new ResponseEntity<News>(news, HttpStatus.CREATED);
		} catch (NewsAlreadyExistsException e) {
			response = new ResponseEntity<String>("{ \"message\": \""+ e.getMessage() + "\"}"
					, HttpStatus.CONFLICT);
		}
		return response;
	}
	
	@PutMapping(path = "/updateNews")
	public ResponseEntity<?> updateNews(@RequestBody News news) {
		ResponseEntity<?> response;
		try {
			final News fetchedNews = newsService.updateNewsByAdmin(news);
			response = new ResponseEntity<News>(fetchedNews, HttpStatus.OK);
		} catch (NewsNotFoundException e) {
			response = new ResponseEntity<String>("{ \"message\": \""+ e.getMessage() + "\"}"
					, HttpStatus.CONFLICT);
		}
		return response;
	}
	
	@PostMapping(path = "/addNewstoWatchList")
	public ResponseEntity<?> addUserNewsToWatchlist(@RequestBody UsersFavourite news) {
		ResponseEntity<?> response;
		try {
			newsService.addNewsToWatchList(news);
			response = new ResponseEntity<UsersFavourite>(news, HttpStatus.CREATED);
		} catch (NewsAlreadyExistsException e) {
			response = new ResponseEntity<String>("{ \"message\": \""+ e.getMessage() + "\"}"
					, HttpStatus.CONFLICT);
		}
		return response;
		
	}
	@DeleteMapping(value = "/deleteNews/{newsId}")
	public ResponseEntity<?> deleteNews(@PathVariable final int newsId) {
		ResponseEntity<?> response;
		try {
			/*newsService.deleteNewsByAdmin(newsId);
			response = new ResponseEntity<String>("news deleted successfully", HttpStatus.OK);*/
			response = new ResponseEntity<>(newsService.deleteNewsByAdmin(newsId), HttpStatus.OK);
		} catch (NewsNotFoundException e) {
			response = new ResponseEntity<String>("{ \"message\": \""+ e.getMessage() + "\"}"
					, HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@GetMapping("/admin/watchList")
	public ResponseEntity<?> getWatchlistForAdmin() {
		ResponseEntity<List<News>> response = new ResponseEntity<>(newsService.getWatchListForAdmin(), HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/user/headlines")
	public ResponseEntity<?> getNewsForUser() {
		ResponseEntity<List<News>> response = new ResponseEntity<>(newsService.getNewsForUser(), HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/deleteNewstoWatchList/{newsId}")
	public ResponseEntity<?> deleteUserNewsFromWatchlist(@PathVariable final int newsId) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(newsService.deleteUserNewsFromWatchList(newsId), HttpStatus.OK);
		} catch (NewsNotFoundException e) {
			response = new ResponseEntity<String>("{ \"message\": \""+ e.getMessage() + "\"}"
					, HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@GetMapping("/user/watchList/{userId}")
	public ResponseEntity<?> getUserWatchList(@PathVariable final String userId) {
		ResponseEntity<List<UsersFavourite>> response = new ResponseEntity<>(newsService.getWatchListForUser(userId), HttpStatus.OK);
		return response;
	}

}
