export interface User {
  id: number;
  type: "patient" | "pharmacy" | undefined;
  name: string | undefined;
  address: string | undefined;
}
