import type { User } from "../models/User";
import { HttpService } from "./http-service";
import { LocalStorage } from "../lib/local-storage";

export interface RegisterInput {}

type ChangeUserHandler = (user: User | undefined) => void;

class AuthService {
  private handler: ChangeUserHandler | null = null;

  private readonly httpService = new HttpService();

  private readonly storage = new LocalStorage<{
    user: User;
    token: string;
  }>("currentUser");

  setHandler(handler: ChangeUserHandler | null) {
    this.handler = handler;
  }

  get persistedUser(): User | undefined {
    return this.storage.get()?.user;
  }

  get authToken(): string | undefined {
    return this.storage.get()?.token;
  }

  async register(
    username: string,
    password: string,
    name: string,
    lastName: string,
    email: string
  ) {
    await this.httpService.post("/auth/register", {
      username,
      password,
      name,
      lastName,
      email,
    });
  }

  async login(username: string, password: string) {
    const { user, token } = await this.httpService.post<{
      user: User;
      token: string;
    }>("/auth/login", {
      username,
      password,
    });

    this.storage.set({ user, token });
    this.handler?.(user);
  }

  logout() {
    this.storage.clear();
    this.handler?.(undefined);
  }
}

export const authService = new AuthService();
