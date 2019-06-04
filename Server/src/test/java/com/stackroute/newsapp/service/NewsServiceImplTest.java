package com.stackroute.newsapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.newsapp.domian.News;
import com.stackroute.newsapp.domian.UsersFavourite;
import com.stackroute.newsapp.domian.repository.NewsRepository;
import com.stackroute.newsapp.domian.repository.UserWatchListRepository;

public class NewsServiceImplTest {
	
	@Mock
	private NewsRepository newsRepo;
	
	@Mock
	private UserWatchListRepository watchListRepo;
	
	@InjectMocks
	private NewsServiceImpl serviceImpl;
	
	private transient News news;
	transient Optional<News> options;
	
	private transient UsersFavourite userFavNews;
	transient Optional<UsersFavourite> userFavOptions;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		news = new News(1, "uday", "title", "desc", "www.bbc.com", "www.image.com", "5/5/2019", "abc content", "uday184");
		options = Optional.of(news);
		
		userFavNews = new UsersFavourite(2, "Dan Brown", "Da Vinci Code", "awesome", "www.nytime.com", "www.abc.com", "04/01", "book", "davinci01");
		userFavOptions = Optional.of(userFavNews);
	}
	
	@Test
	public void testAddNewsByAdmin() throws Exception {
		when(newsRepo.save(news)).thenReturn(news);
		News result = serviceImpl.addNewsByAdmin(news);
		assertEquals(1, result.getId());
		verify(newsRepo, times(1)).save(news);
	}
	
	@Test
	public void testUpdateNewsByAdmin() throws Exception {
		when(newsRepo.findById(1)).thenReturn(options);
		when(newsRepo.save(news)).thenReturn(news);
		News result = serviceImpl.updateNewsByAdmin(news);
		assertEquals("uday", result.getAuthor());
	}
	
	@Test
	public void testAddNewsToUserWatchList() throws Exception {
		when(watchListRepo.save(userFavNews)).thenReturn(userFavNews);
		UsersFavourite result = serviceImpl.addNewsToWatchList(userFavNews);
		assertEquals(2, result.getId());
		verify(watchListRepo, times(1)).save(userFavNews);
	}
	
	@Test
	public void testUpdateUserNewsFromWatchlist() throws Exception {
		when(watchListRepo.findById(2)).thenReturn(userFavOptions);
		when(watchListRepo.save(userFavNews)).thenReturn(userFavNews);
		UsersFavourite result = serviceImpl.updateUserNewsFromWatchlist(userFavNews);
		assertEquals("Dan Brown", result.getAuthor());
	}

}
