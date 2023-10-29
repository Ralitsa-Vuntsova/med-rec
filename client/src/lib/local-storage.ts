export class LocalStorage<T> {
  constructor(private key: string) {}

  get(): T | undefined {
    const json = localStorage.getItem(this.key);

    return json ? JSON.parse(json) : undefined;
  }

  set(value: T) {
    localStorage.setItem(this.key, JSON.stringify(value));
  }

  clear() {
    localStorage.removeItem(this.key);
  }
}
