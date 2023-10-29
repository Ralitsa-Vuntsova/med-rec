export interface Drug {
  id: number;
  brandName: string;
  genericName: string | undefined;
  substanceName: string | undefined;
  manufacturerName: string | undefined;
  dosageForm: string | undefined;
  route: string | undefined;
  marketingStatus: string | undefined;
}
