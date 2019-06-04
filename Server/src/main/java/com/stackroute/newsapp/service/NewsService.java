package com.stackroute.newsapp.service;

import java.util.List;

import com.stackroute.newsapp.domian.News;
import com.stackroute.newsapp.domian.UsersFavourite;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;

public interface NewsService {
	
	//operations for admin
	public News addNewsByAdmin(News news) throws NewsAlreadyExistsException;
	
	public News updateNewsByAdmin(News news) throws NewsNotFoundException;
	
	public News deleteNewsByAdmin(int newsId) throws NewsNotFoundException;
	
	public List<News> getWatchListForAdmin();
	
	//operations for user
	public List<News> getNewsForUser();
	
	public UsersFavourite addNewsToWatchList(UsersFavourite news) throws NewsAlreadyExistsException;
	
	public UsersFavourite updateUserNewsFromWatchlist(UsersFavourite news) throws NewsNotFoundException;
	
	public UsersFavourite deleteUserNewsFromWatchList(int newsId) throws NewsNotFoundException;
	
	public List<UsersFavourite> getWatchListForUser(String userId);

}
