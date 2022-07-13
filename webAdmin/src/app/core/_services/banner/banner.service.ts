import { Injectable } from '@angular/core';
import { ApiService } from '@app/core';
import { Observable } from 'rxjs';
import { Ad } from '@app/core/_models/Ad';
import { Banner } from '@app/core/_models/Banner';
const routes = {
  //ad: () => `/ad`,
  //adWithId: (id: string) => `/ad/${id}`,
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
/*
  getAllAds(): Observable<Ad[]> {
    return this.api.get<Ad[]>(this.route + routes.ad(), Ad);
  }
  getAd(id: string): Observable<Ad> {
    return this.api.get<Ad>(this.route + routes.adWithId(id), Ad);
  }
  addAd(ad: Ad): Observable<Ad> {
    return this.api.post<Ad>(this.route + routes.ad(), ad, Ad);
  }
  updateAd(ad: Ad): Observable<Ad> {
    return this.api.put<Ad>(this.route + routes.adWithId(ad._id), ad, Ad);
  }
  deleteAd(id: string): Observable<Ad> {
    return this.api.delete<Ad>(this.route + routes.adWithId(id), Ad);
  }
  */
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
