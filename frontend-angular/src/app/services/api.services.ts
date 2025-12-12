import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  get<T>(path: string, params?: any, headers?: HttpHeaders): Observable<T> {
    const url = `${this.baseUrl}/${path}`;
    let httpParams = new HttpParams();
    if (params) {
      Object.keys(params).forEach(key => {
        const val = params[key];
        if (val !== undefined && val !== null) {
          httpParams = httpParams.set(key, val);
        }
      });
    }
    return this.http.get<T>(url, { params: httpParams, headers });
  }

  post<T>(path: string, body: any, headers?: HttpHeaders): Observable<T> {
    const url = `${this.baseUrl}/${path}`;
    return this.http.post<T>(url, body, { headers });
  }

  put<T>(path: string, body: any, headers?: HttpHeaders): Observable<T> {
    const url = `${this.baseUrl}/${path}`;
    return this.http.put<T>(url, body, { headers });
  }

  delete<T>(path: string, headers?: HttpHeaders): Observable<T> {
    const url = `${this.baseUrl}/${path}`;
    return this.http.delete<T>(url, { headers });
  }
}
