import { Injectable } from '@angular/core';
import { ApiService } from 'src/app/core';
import { Observable } from 'rxjs';
import { Banner } from 'src/app/core/_models/Banner';
const routes = {
  createBanner:() =>`/create`,
  updateBanner:(id: string) =>`/update/${id}`,
  deleteBanner:(id: string) =>`/delete/${id}`,
  allBanners:() => `/allBanners`,
  bannerWithId: (id: string) => `/getById/${id}`
};
@Injectable({
  providedIn: 'root'
})
export class BannerService {
  route_write = '/MS-BANNER-WRITE/banner/write';
  route_read = '/MS-BANNER-READ/banner/read';
  constructor(private api: ApiService) {}

  getAllBanners(): Observable<Banner[]> {
    return this.api.get<Banner[]>(this.route_read + routes.allBanners(), Banner);
  }
  getBanner(id: string): Observable<Banner> {
    return this.api.get<Banner>(this.route_read + routes.bannerWithId(id), Banner);
  }
  addBanner(banner: Banner): Observable<Banner> {
    return this.api.post<Banner>(this.route_write + routes.createBanner(), banner, Banner);
  }
  updateBanner(banner: Banner): Observable<Banner> {
    return this.api.put<Banner>(
      this.route_write + routes.updateBanner(banner.id),
      banner,
      Banner
    );
  }
  deleteBanner(id: string): Observable<Banner> {
    return this.api.delete<Banner>(
      this.route_write + routes.deleteBanner(id),
      Banner
    );
  }
}
