import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ApiService } from '@app/core';
import { Admin } from '@app/core/_models/user/Admin';
import { Customer } from '@app/core/_models/user/Customer';
import { Provider } from '@app/core/_models/user/Provider';
import { Settings } from '@app/core/_models/Settings';

const routes = {
  connectAdmin: (login: string, password: string) =>
    `/connect/${login}/${password}`,
  getAllAdmin: () => `/allAdmins`,
  getAdmin: (id: string) => `/getById/${id}`,
  addAdmin: () => `/create`,
  updateAdmin: (id:string) => `/update/${id}`,

  getAllCustomers: () => `/allCustomers`,
  getCustomer: (id: string) => `/getById/${id}`,
  updateCustomer: (id:string) => `/update/${id}`,
  deleteCustomer: (id:string) => `/delete/${id}`,

  getAllProviders: () => `/allProviders`,
  newprovider: () => `/create`,
  getProvider: (id: string) => `/getById/${id}`,
  updateProvider: (id:string) => `/update/${id}`,
  deleteProvider: (id:string) => `/delete/${id}`,


  getAllSettings: () => `/allSettings`,
  updateSettings: (id:string) => `/update/${id}`,
  newSettings: () => `/create`,
  settingsWithId: (id: string) => `/getById/${id}`
};
@Injectable({
  providedIn: 'root'
})
export class UserService {
  route_admin_write='/MS-ADMIN-WRITE/admin/write';
  route_admin_read='/MS-ADMIN-READ/admin/read';

  route_customer_write='/MS-CUSTOMER-WRITE/customer/write';
  route_customer_read='/MS-CUSTOMER-READ/customer/read';
  
  route_provider_write='/MS-PROVIDER-WRITE/provider/write';
  route_provider_read='/MS-PROVIDER-READ/provider/read';
  
  route_settings_write='/MS-SETTINGS-WRITE/setting/write';
  route_settings_read='/MS-SETTINGS-READ/setting/read';

  constructor(private api: ApiService) {}
  
  /* Admin */
  getAdminByLoginAndPassword(
    login: string,
    password: string
  ): Observable<Admin> {
    return this.api.get<Admin>(
      this.route_admin_read + routes.connectAdmin(login, password),
      Admin
    );
  }
  getAdminById(id: string): Observable<Admin> {
    return this.api.get<Admin>(this.route_admin_read + routes.getAdmin(id), Admin);
  }
  getAllAdmins(): Observable<Admin[]> {
    return this.api.get<Admin[]>(this.route_admin_read + routes.getAllAdmin(), Admin);
  }
  addAdmin(admin: Admin): Observable<Admin> {
    return this.api.post<Admin>(this.route_admin_write + routes.addAdmin(), admin, Admin);
  }
  updateAdmin(id: string, admin: Admin): Observable<Admin> {
    return this.api.put<Admin>(this.route_admin_write + routes.updateAdmin(id), admin, Admin);
  }
  /* Customer */
  getAllCustomers(): Observable<Customer[]> {
    return this.api.get<Customer[]>(
      this.route_customer_read + routes.getAllCustomers(),
      Customer
    );
  }
  getCustomer(id: string): Observable<Customer> {
    return this.api.get<Customer>(
      this.route_customer_read + routes.getCustomer(id),
      Customer
    );
  }
  updateCustomer(id: string, customer: Customer): Observable<Customer> {
    return this.api.put<Customer>(
      this.route_customer_write + routes.updateCustomer(id),
      customer,
      Customer
    );
  }

  deleteCustomer(id: string): Observable<Customer> {
    return this.api.delete<Customer>(
      this.route_customer_write + routes.deleteCustomer(id),
      Customer
    );
  }
  /* Provider */
  getAllProviders(): Observable<Provider[]> {
    return this.api.get<Provider[]>(
      this.route_provider_read + routes.getAllProviders(),
      Provider
    );
  }
  addProvider(provider: Provider): Observable<Provider> {
    return this.api.post<Provider>(
      this.route_provider_write + routes.newprovider(),
      provider,
      Provider
    );
  }
  getProviderById(id: string): Observable<Provider> {
    return this.api.get<Provider>(
      this.route_provider_read + routes.getProvider(id),
      Provider
    );
  }
  updateProvider(id: string, provider: Provider): Observable<Provider> {
    return this.api.put<Provider>(
      this.route_provider_write + routes.updateProvider(id),
      provider,
      Provider
    );
  }
  deleteProvider(id: string): Observable<Provider> {
    return this.api.delete<Provider>(
      this.route_provider_write + routes.deleteProvider(id),
      Provider
    );
  }

  /* Settings */
  getSettingsById(id: string): Observable<Settings> {
    return this.api.get<Settings>(
      this.route_settings_read + routes.settingsWithId(id),
      Settings
    );
  }
  getSettings(): Observable<Settings> {
    return this.api.get<Settings>(this.route_settings_read + routes.getAllSettings(), Settings);
  }
  addSettings(settings: Settings): Observable<Settings> {
    return this.api.post<Settings>(
      this.route_settings_write + routes.newSettings(),
      settings,
      Settings
    );
  }
  updateSettings(settings: Settings): Observable<Settings> {
    return this.api.put<Settings>(
      this.route_settings_write + routes.updateSettings(settings.id),
      settings,
      Settings
    );
  }
}
