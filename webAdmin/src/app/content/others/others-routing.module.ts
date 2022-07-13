import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { extract } from '@app/core';
import { GalleryListComponent } from './gallery/gallery-list/gallery-list.component';
import { GalleryNewComponent } from './gallery/gallery-new/gallery-new.component';
import { BannerListComponent } from './banner/banner-list/banner-list.component';
import { BannerNewComponent } from './banner/banner-new/banner-new.component';
import { BannerEditComponent } from './banner/banner-edit/banner-edit.component';
const routes: Routes = [
  // Module is lazy loaded, see app-routing.module.ts
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'gallery',
    component: GalleryListComponent,
    data: { title: extract('Gallery') }
  },
  {
    path: 'gallery/new',
    component: GalleryNewComponent,
    data: { title: extract('Nouveau Image') }
  },
  {
    path: 'banner',
    component: BannerListComponent,
    data: { title: extract('Liste bannières') }
  },
  {
    path: 'banner/new',
    component: BannerNewComponent,
    data: { title: extract('Nouvelle bannière') }
  },
  {
    path: 'banner/edit/:id',
    component: BannerEditComponent,
    data: { title: extract('MAJ  bannière') }
  },
  // Fallback when no prior route is matched
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OthersRoutingModule {
  static declarations = [
    GalleryListComponent,
    GalleryNewComponent,
    BannerListComponent,
    BannerEditComponent,
    BannerNewComponent
  ];
}
