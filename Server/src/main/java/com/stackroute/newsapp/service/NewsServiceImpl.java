package com.stackroute.newsapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.domian.News;
import com.stackroute.newsapp.domian.UsersFavourite;
import com.stackroute.newsapp.domian.repository.NewsRepository;
import com.stackroute.newsapp.domian.repository.UserWatchListRepository;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;

@Service
public class NewsServiceImpl implements NewsService {
	
	private NewsRepository newsRepo;
	
	private UserWatchListRepository watchListRepo;
	
	@Autowired
	public NewsServiceImpl(final NewsRepository newsRepo, final UserWatchListRepository watchListRepo) {
		super();
		this.newsRepo = newsRepo;
		this.watchListRepo = watchListRepo;
	}

	@Override
	public News addNewsByAdmin(News news) throws NewsAlreadyExistsException {
		final Optional<News> obj = newsRepo.findById(news.getId());
		if (obj.isPresent()) {
			throw new NewsAlreadyExistsException("can't save the news as its already exists");
		}
		return newsRepo.save(news);
	}

	@Override
	public News updateNewsByAdmin(News news) throws NewsNotFoundException {
		final Optional<News> obj = newsRepo.findById(news.getId());
		if (!obj.isPresent()) {
			throw new NewsNotFoundException("can't update the news as it doesn't exist");
		}
		News updateNews = newsRepo.findById(news.getId()).orElse(null);
		if (updateNews != null) {
			updateNews.setTitle(news.getTitle());
			updateNews.setContent(news.getContent());
			updateNews.setDescription(news.getDescription());
			newsRepo.save(updateNews);
		}
		return updateNews;
	}

	@Override
	public News deleteNewsByAdmin(int newsId) throws NewsNotFoundException {
		final Optional<News> obj = newsRepo.findById(newsId);
		if (!obj.isPresent()) {
			throw new NewsNotFoundException("can't delete the news as it doesn't exist");
		}
		News deleteNews = newsRepo.findById(newsId).orElse(null);
		if (deleteNews != null) {
			newsRepo.deleteById(newsId);
		}
		return deleteNews;
	}

	@Override
	public List<News> getWatchListForAdmin() {
		return newsRepo.findAll();
	}

	@Override
	public List<News> getNewsForUser() {
		return newsRepo.findAll();
	}

	@Override
	public UsersFavourite addNewsToWatchList(UsersFavourite news) throws NewsAlreadyExistsException {
		final Optional<UsersFavourite> obj = watchListRepo.findById(news.getId());
		if (obj.isPresent()) {
			throw new NewsAlreadyExistsException("can't add the news as it already exists");
		}
		return watchListRepo.save(news);
	}

	@Override
	public UsersFavourite updateUserNewsFromWatchlist(UsersFavourite news) throws NewsNotFoundException {
		final Optional<UsersFavourite> obj = watchListRepo.findById(news.getId());
		if (!obj.isPresent()) {
			throw new NewsNotFoundException("can't update the news as it is not present");
		}
		UsersFavourite updateNews = watchListRepo.findById(news.getId()).orElse(null);
		if (updateNews != null) {
			updateNews.setTitle(news.getTitle());
			updateNews.setContent(news.getContent());
			updateNews.setDescription(news.getDescription());
			watchListRepo.save(updateNews);
		}
		return updateNews;
	}

	@Override
	public UsersFavourite deleteUserNewsFromWatchList(int newsId) throws NewsNotFoundException {
		final Optional<UsersFavourite> obj = watchListRepo.findById(newsId);
		if (!obj.isPresent()) {
			throw new NewsNotFoundException("can not delete the news as it doesn't exist");
		}
		UsersFavourite deleteMovie = watchListRepo.findById(newsId).orElse(null);
		if (deleteMovie != null) {
			watchListRepo.deleteById(newsId);
		}
		return deleteMovie;
	}

	@Override
	public List<UsersFavourite> getWatchListForUser(String userId) {
		return watchListRepo.findByUserId(userId);
	}

}
