import { Config } from "../config";
import { BaseError } from "../lib/base-error";
import { authService } from "./auth-service";

export class HttpError extends BaseError {
  constructor(
    message: string,
    public readonly statusCode: number,
    public readonly body: any
  ) {
    super("HttpError", message);
  }
}

export class HttpService {
  async post<T>(path: string, data: any): Promise<T> {
    return this.request<T>("POST", path, { body: data });
  }

  async get<T>(path: string, params?: Record<string, string>): Promise<T> {
    return this.request<T>("GET", path, { params });
  }

  async delete<T>(path: string, params?: Record<string, string>): Promise<T> {
    return this.request<T>("DELETE", path, { params });
  }

  private async request<T>(
    method: string,
    path: string,
    {
      body,
      params,
    }: {
      body?: any;
      params?: Record<string, string>;
    }
  ): Promise<T> {
    const { authToken } = authService;
    const url = new URL(path, Config.serverUrl);
    url.search = new URLSearchParams(params).toString();

    const response = await fetch(url.toString(), {
      method,
      headers: {
        ...(body ? { "Content-Type": "application/json" } : undefined),
        ...(authToken ? { Authorization: `Bearer ${authToken}` } : undefined),
      },
      body: body ? JSON.stringify(body) : undefined,
    });

    // error handling - different status codes

    if (response.status !== 200) {
      let body;
      try {
        body = await response.json();
      } catch {
        body = await response.text();
      }

      const message =
        body?.message?.toString() || body.toString() || "Something went wrong";

      throw new HttpError(message, response.status, body);
    }

    return response.json();
  }
}
