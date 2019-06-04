import { Injectable } from '@angular/core';
import { News } from '../../model/news';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../../authentication/service/auth.service';
import { retry } from 'rxjs/internal/operators/retry';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  newsDbEndPoint: string;
  apiKey: string;
  searchEndPoint: string;

  constructor(private service:HttpClient,private auth:AuthenticationService) { 
    this.newsDbEndPoint = 'https://newsapi.org/v2/top-headlines?country=in';
    this.apiKey = 'a77b40ce5a48447baf650457fa84f781';
    this.searchEndPoint = 'https://newsapi.org/v2/everything?q=';

  }

  pickNewsResults(response) {
    return response['articles'];
  }

  getHeadLines():Observable<Array<News>>{
    if(this.auth.isUserAdmin()){
      const url = `${this.newsDbEndPoint}&apikey=${this.apiKey}&page=1`;
      return this.service.get(url).pipe(
        retry(3),
        map(this.pickNewsResults)
      );
    }else{
      return this.service.get<Array<News>>('http://localhost:8081/user/headlines');
    }
  }

  search(searchKey:string):Observable<Array<News>>{
    if (searchKey.length > 0) {
      const url = `${this.searchEndPoint}${searchKey}&apikey=${this.apiKey}&language=en&page=1`;
      return this.service.get(url).pipe(
        retry(3),
        map(this.pickNewsResults)
      );
    }
  }

  addNews(news:News):Observable<News>{
    if(this.auth.isUserAdmin()){
      return this.service.post<News>('http://localhost:8081/addNews',news);
    }else{
      news.userId = this.auth.getUser();
      return this.service.post<News>('http://localhost:8081/addNewstoWatchList',news);
    }
  }

  updateNews(news:News):Observable<News>{
    if(this.auth.isUserAdmin()){
      return this.service.put<News>('http://localhost:8081/updateNews',news);
    }
  }

  deleteNews(news:News):Observable<News>{
    if(this.auth.isUserAdmin()){
      return this.service.delete<News>('http://localhost:8081/deleteNews/'+news.id);
    }else{
      news.userId = this.auth.getUser();
      return this.service.delete<News>('http://localhost:8081/deleteNewstoWatchList/'+news.id);
    }
  }

  getAdminWatchList():Observable<Array<News>>{
    if(this.auth.isUserAdmin()){
      return this.service.get<Array<News>>('http://localhost:8081/admin/watchList');
    }else{
      return this.service.get<Array<News>>('http://localhost:8081/user/watchList/'+this.auth.getUser());
    }
  }
}