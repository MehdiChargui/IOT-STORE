export class Category {
  id: string;
  categoryName: string;
  image: string;
  active: boolean;
  subCategories: SubCategory[] = [];

  constructor() {}
}

export class SubCategory {
  id: string;
  name: string;
  active: boolean;
}
