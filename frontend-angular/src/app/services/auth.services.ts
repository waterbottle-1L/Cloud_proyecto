import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable, tap } from 'rxjs';

interface LoginResponse {
  token: string;
  [key: string]: any;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private tokenKey = 'myapp_token';

  constructor(private api: ApiService) {}

  login(credentials: { email: string; password: string }): Observable<LoginResponse> {
    // Ajusta la ruta si tu API usa otro endpoint
    return this.api.post<LoginResponse>('auth/login', credentials).pipe(
      tap(res => {
        if (res && res.token) {
          localStorage.setItem(this.tokenKey, res.token);
        }
      })
    );
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}