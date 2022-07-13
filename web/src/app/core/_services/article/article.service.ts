import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ApiService } from 'src/app/core';
import { Article } from 'src/app/core/_models/Article';
import { Review } from '../../_models/Review';

const routes = {
  article: () => `/create`,
  update: () => `/updateQty`,
  activeArticles: () => `/allArticleByActive`,
  articleWithId: (id: string) => `/getById/${id}`,
  updateArticle:(id:string)=>`/update/${id}`,
  deleteArticle:(id:string)=>`/delete/${id}`,
  articleByCategory: (id: string) => `/allArticleByCategory/${id}`,
  articleBySubCategory: (id: string) => `/allArticleBySubcategory/${id}`,
  articleInDiscount: () => `/allArticleByDiscount`,

  review: () => `/allReview`,
  reviewWithId: (id: string) => `/getById/${id}`,
  reviewByArticle: (id: string) => `/getByArticle/${id}`,
  addReview:()=>`/create`,
  updateReview:(id:string) =>`/update/${id}`
  
};
@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  route_write = '/MS-ARTICLE-WRITE/article/write';
  route_read = '/MS-ARTICLE-READ/article/read';
  route_review_write = '/MS-REVIEW-WRITE/review/write';
  route_review_read = '/MS-REVIEW-READ/review/read';
  
  constructor(private api: ApiService) {}

  getAllArticles(): Observable<Article[]> {
    return this.api.get<Article[]>(this.route_read + routes.activeArticles(), Article);
  }
  getArticlesByCategory(id:string): Observable<Article[]> {
    return this.api.get<Article[]>(this.route_read + routes.articleByCategory(id), Article);
  }
  getArticlesBySubCategory(id:string): Observable<Article[]> {
    return this.api.get<Article[]>(this.route_read + routes.articleBySubCategory(id), Article);
  }
  getArticlesInDiscount(): Observable<Article[]> {
    return this.api.get<Article[]>(this.route_read + routes.articleInDiscount(), Article);
  }
  getArticle(id: string): Observable<Article> {
    return this.api.get<Article>(
      this.route_read + routes.articleWithId(id),
      Article
    );
  }
  addArticle(article: Article): Observable<Article> {
    return this.api.post<Article>(
      this.route_write + routes.article(),
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
  //backend ko
  updateArticlesQty(array: any): Observable<boolean> {
    return this.api.put<boolean>(
      this.route_write + routes.update(),
      {data:array},
      Boolean
    );
  }
  deleteArticle(id: string): Observable<Article> {
    return this.api.delete<Article>(
      this.route_write + routes.deleteArticle(id),
      Article
    );
  }


  /* Review */

  getReviews(): Observable<Review[]> {
    return this.api.get<Review[]>(this.route_review_read + routes.review(), Review);
  }
  getArticleReviews(id:string): Observable<Review[]> {
    return this.api.get<Review[]>(this.route_review_read + routes.reviewByArticle(id), Review);
  }
  getReview(id: string): Observable<Review> {
    return this.api.get<Review>(
      this.route_review_read + routes.reviewWithId(id),
      Review
    );
  }
  addReview(article: Review): Observable<Review> {
    return this.api.post<Review>(
      this.route_review_write + routes.addReview(),
      article,
      Review
    );
  }
  updateReview(article: Review): Observable<Review> {
    return this.api.put<Review>(
      this.route_review_write + routes.updateReview(article.id),
      article,
      Review
    );
  }
}
