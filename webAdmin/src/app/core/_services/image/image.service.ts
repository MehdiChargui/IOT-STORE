import { Injectable } from '@angular/core';
import { ApiService } from '@app/core';
import { Observable } from 'rxjs';
import { Image } from '@app/core/_models/Image';

const routes = {
  image: () => `/image`,
  imageWithId: (id: string) => `/image/${id}`,
  upload: () => `/upload`
};
@Injectable({
  providedIn: 'root'
})
export class ImageService {
  route = '/MARKETGALLERYSERVICE';
  constructor(private api: ApiService) {}

  getAllImages(): Observable<Image[]> {
    return this.api.get<Image[]>(this.route + routes.image(), Image);
  }
  getImage(id: string): Observable<Image> {
    return this.api.get<Image>(this.route + routes.imageWithId(id), Image);
  }
  addImage(image: Image): Observable<Image> {
    return this.api.post<Image>(this.route + routes.image(), image, Image);
  }
  updateImage(image: Image): Observable<Image> {
    return this.api.put<Image>(
      this.route + routes.imageWithId(image._id),
      image,
      Image
    );
  }
  deleteImage(id: string): Observable<Image> {
    return this.api.delete<Image>(this.route + routes.imageWithId(id), Image);
  }
  uploadImage(image: any): Observable<any> {
    const formdata: FormData = new FormData();

    formdata.append('file', image);
    return this.api.postImage<any>(this.route + routes.upload(), formdata);
  }
}
