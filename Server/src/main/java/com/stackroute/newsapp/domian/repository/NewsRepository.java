package com.stackroute.newsapp.domian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.newsapp.domian.News;

public interface NewsRepository extends JpaRepository<News, Integer>{
	
	public List<News> findByUserId(String userId);

}
