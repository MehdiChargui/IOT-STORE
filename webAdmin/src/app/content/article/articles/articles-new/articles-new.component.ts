import { Component, OnInit, ViewChild } from '@angular/core';
import { ImageService } from '@app/core/_services/image/image.service';
import { ArticleService } from '@app/core/_services/article/article.service';
import { CategoryService } from '@app/core/_services/category/category.service';
import { ColorsService, ColorScheme, ThemeColor, Logger } from '@app/core';
import { RedirectService } from '@app/core/services/redirect.service';
import { AlertStyle } from '@app/blocks/alerts/models/alert-style.enum';
import { faLongArrowAltRight } from '@fortawesome/free-solid-svg-icons';
import {
  Article,
  Media,
  CategoryTable,
  Feature,
  PriceHistory
} from '@app/core/_models/Article';
import { Category, SubCategory } from '@app/core/_models/Category/Category';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
const log = new Logger('Article');
import {
  faUserPlus,
  faFilter,
  faCircle,
  faTrash,
  faListUl,
  faTable,
  faUser,
  faEdit,
  faCheck
} from '@fortawesome/free-solid-svg-icons';
import {
  ColumnMode,
  SelectionType,
  DatatableComponent
} from '@swimlane/ngx-datatable';
@Component({
  selector: 'prx-articles-new',
  templateUrl: './articles-new.component.html',
  styleUrls: ['./articles-new.component.scss']
})
export class ArticlesNewComponent implements OnInit {
  @ViewChild(DatatableComponent, { static: false })
  datatable: DatatableComponent;
  ColumnMode = ColumnMode;
  SelectionType = SelectionType;
  icons = {
    add: faUserPlus,
    filter: faFilter,
    dot: faCircle,
    edit: faEdit,
    list: faListUl,
    table: faTable,
    details: faUser,
    delete: faTrash,
    ok: faCheck
  };
  editor = ClassicEditor;
  description = '';
  ColorScheme = ColorScheme;
  AlertStyle = AlertStyle;
  colors: ThemeColor[];
  longArrowAltRight = faLongArrowAltRight;
  isLoading: boolean;
  error: boolean;
  article = new Article();
  categories: Category[] = [];
  files: File[] = [];
  selectedSubCategory: any[] = [];
  table = [
    { name: 'Disponible' },
    { name: 'Non Disponible' },
    { name: 'Sur Commnade' }
  ];
  feature = new Feature();
  constructor(
    private imageService: ImageService,
    private articleService: ArticleService,
    private categoryService: CategoryService,
    private _colors: ColorsService,
    private _redirect: RedirectService
  ) {
    this.colors = this._colors.ThemeColors;
    this.isLoading = false;
    this.error = false;
  }
  ngOnInit(): void {
    this.getCategories();
  }

  getCategories() {
    this.categoryService.getAllCategorys().subscribe(data => {
      this.categories = data.filter(item => item.active === true);
    });
  }
  addFeature() {
    let feature = new Feature();
    feature.name = this.feature.name;
    feature.value = this.feature.value;
    this.feature = new Feature();
    this.article.features.push(feature);
    this.article.features = [...this.article.features];
    if (this.datatable) {
      this.datatable.offset = this.article.features.length / 3;
    }
  }

  deleteFeature(feature: Feature) {
    let id = this.article.features.findIndex(item => item.id === feature.id);
    this.article.features.splice(id, 1);
  }
  addCategory(subCategory: SubCategory) {
    this.categories.forEach(cat => {
      let c = cat.subCategories.findIndex(i => i.id === subCategory.id);
      if (c !== -1) {
        let item = new CategoryTable();
        item.category = cat.id;
        item.subCategory = subCategory.id;
        this.article.categories.push(item);
      }
    });
  }
  delete(event: any) {
    let id = this.article.categories.findIndex(
      item => item.subCategory === event.value.id
    );
    this.article.categories.splice(id, 1);
  }
  submit({ valid, value }: { valid: boolean; value: any }) {
    if (valid) {
      this.isLoading = true;
      this.files.forEach(file => this.uploadImage(file));
    }
  }
  uploadImage(file: File) {
    let image = file.type.startsWith('image/');
    this.imageService.uploadImage(file).subscribe(
      data => {
        if (data) {
          image
            ? this.article.images.push(new Media(data))
            : this.article.videos.push(new Media(data));
          if (
            this.article.images.length + this.article.videos.length ===
            this.files.length
          ) {
            console.log(1);
            this.addArticle();
          }
        } else {
          this.isLoading = false;
          log.debug(`error`);
          this.error = true;
        }
      },
      error => {
        this.isLoading = false;
        this.error = true;
        log.debug(`error: ${error}`);
      }
    );
  }
  addArticle() {
    this.error = false;
    let history = new PriceHistory(this.article.price);
    this.article.tax = this.article.tax / 100;
    this.article.priceHistory.push(history);
    console.log(this.article);
    this.articleService.addArticle(this.article).subscribe(
      data => {
        this.isLoading = false;
        if (data) {
          console.log(data);
          this._redirect.toArticle();
        } else {
          log.debug(`error`);
          this.error = true;
        }
      },
      error => {
        this.isLoading = false;
        this.error = true;
        log.debug(`error: ${error}`);
      }
    );
  }
  onSelect(event: any) {
    console.log(event);
    this.files.push(...event.addedFiles);
    console.log(this.files);
  }
  isImage(type: string) {
    return type.startsWith('image');
  }
  getFiles(type: string) {
    return this.files.filter(item => item.type.startsWith(type));
  }
  onRemove(event: any) {
    console.log(event);
    this.files.splice(this.files.indexOf(event), 1);
  }

  getCategory(categoryT: CategoryTable): CategoryTable {
    let text = new CategoryTable();
    let category = this.categories.find(
      item => item.id === categoryT.category
    );
    if (category) {
      let sub = category.subCategories.find(
        item => item.id === categoryT.subCategory
      );
      if (sub) {
        text.category = category.categoryName;
        text.subCategory = sub.name;
      }
    }
    return text;
  }
}
