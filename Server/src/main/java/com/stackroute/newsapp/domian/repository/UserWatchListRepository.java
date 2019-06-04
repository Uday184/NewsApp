package com.stackroute.newsapp.domian.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.stackroute.newsapp.domian.UsersFavourite;

public interface UserWatchListRepository extends JpaRepository<UsersFavourite, Integer> {
	
	public List<UsersFavourite> findByUserId(String userId);

}
