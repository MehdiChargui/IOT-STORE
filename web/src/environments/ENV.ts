export class Env {
  static host: string;
  constructor() {}
  static getHost(): string {
    if (location.port == '4200' || location.port == '91') {
     // this.host = 'http://35.180.206.6:9004';
      this.host = 'http://localhost:9004';

    } else {
      if (location.hostname.includes('preprod')) {
        this.host = 'https://preprod.ms.tunisie-acca.five-consulting.com';
      } else {
        this.host = 'https://ms.tunisie-accastillage.com/';
      }
    }
    return this.host;
  }
}
