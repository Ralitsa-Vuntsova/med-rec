export class BaseError implements Error {
  stack?: string | undefined;

  constructor(public name: string, public message: string) {
    this.stack = new Error().stack;
  }
}
