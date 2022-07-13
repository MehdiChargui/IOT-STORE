import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ApiService } from '@app/core';
import { Article } from '@app/core/_models/Article';

const routes = {
  createArticle:() =>`/create`,
  updateArticle:(id: string) =>`/update/${id}`,
  deleteArticle:(id: string) =>`/delete/${id}`,
  allArticles:() => '/allArticle',
  articleWithId: (id: string) => `/getById/${id}`
};
@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  route_write = '/MS-ARTICLE-WRITE/article/write';
  route_read = '/MS-ARTICLE-READ/article/read';
  constructor(private api: ApiService) {}

  getAllArticles(): Observable<Article[]> {
    return this.api.get<Article[]>(this.route_read + routes.allArticles(), Article);
  }
  getArticle(id: string): Observable<Article> {
    return this.api.get<Article>(
      this.route_read + routes.articleWithId(id),
      Article
    );
  }
  addArticle(article: Article): Observable<Article> {
    return this.api.post<Article>(
      this.route_write + routes.createArticle(),
      article,
      Article
    );
  }
  updateArticle(article: Article): Observable<Article> {
    return this.api.put<Article>(
      this.route_write + routes.updateArticle(article.id),
      article,
      Article
    );
  }
  deleteArticle(id: string): Observable<Article> {
    return this.api.delete<Article>(
      this.route_write + routes.deleteArticle(id),
      Article
    );
  }
}
