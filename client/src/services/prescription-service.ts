import { Prescription } from "../models/Prescription";
import { HttpService } from "./http-service";

class PrescriptionService {
  private http = new HttpService();

  getPrescription(prescriptionId: number) {
    return this.http.get<Prescription>(`/qr/${prescriptionId}`);
  }

  getPrescriptions(userId: number | undefined) {
    return this.http.get<Prescription[]>(`/qr/user/${userId}`);
  }
}

export const prescriptionService = new PrescriptionService();
