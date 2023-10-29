import { Drug } from "../models/Drug";
import { User } from "../models/User";
import { HttpService } from "./http-service";

class DrugService {
  private http = new HttpService();

  getDrug(pharmacyId: number, drugId: number) {
    return this.http.get<Drug>(`/pharmacy/${pharmacyId}/drugs/${drugId}`);
  }

  getPharmacies(search: string) {
    return this.http.get<User[]>("/pharmacy/", {
      name: search,
    });
  }

  getAllDrugsOfPharmacy(pharmacyId: number, search: string) {
    return this.http.get<Drug[]>(`/pharmacy/${pharmacyId}/drugs`, {
      name: search,
    });
  }

  createDrug(pharmacyId: number, brandName: string) {
    return this.http.post<Drug>(`/pharmacy/${pharmacyId}/drug`, brandName);
  }

  deleteDrug(pharmacyId: number, drugId: number) {
    this.http.delete(`/pharmacy/${pharmacyId}/drug/${drugId}`);
  }
}

export const drugService = new DrugService();
