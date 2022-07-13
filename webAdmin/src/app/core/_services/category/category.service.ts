import { Injectable } from '@angular/core';
import { ApiService } from '@app/core';
import { Observable } from 'rxjs';
import { Category } from '@app/core/_models/Category/Category';

const routes = {
  createCategory:() =>`/create`,
  updateCategory:(id: string) =>`/update/${id}`,
  deleteCategory:(id: string) =>`/delete/${id}`,
  allCategory:() => `/allCategory`,
  categoryWithId: (id: string) => `/getById/${id}`
};
@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  route_write = '/MS-CATEGORY-WRITE/category/write';
  route_read = '/MS-CATEGORY-READ/category/read';
  constructor(private api: ApiService) {}

  getAllCategorys(): Observable<Category[]> {
    return this.api.get<Category[]>(this.route_read + routes.allCategory(), Category);
  }
  getCategory(id: string): Observable<Category> {
    return this.api.get<Category>(
      this.route_read + routes.categoryWithId(id),
      Category
    );
  }
  addCategory(category: Category): Observable<Category> {
    return this.api.post<Category>(
      this.route_write + routes.createCategory(),
      category,
      Category
    );
  }
  updateCategory(category: Category): Observable<Category> {
    return this.api.put<Category>(
      this.route_write + routes.updateCategory(category.id),
      category,
      Category
    );
  }
  deleteCategory(id: string): Observable<Category> {
    return this.api.delete<Category>(
      this.route_write + routes.deleteCategory(id),
      Category
    );
  }

}
