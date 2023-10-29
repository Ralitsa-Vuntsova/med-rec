import React from "react";
import { User } from "../../models/User";
import { PharmacyCard } from "../PharmacyCard";

export interface PharmacyListProps {
  pharmacies: User[];
}

export function PharmacyList({ pharmacies }: PharmacyListProps) {
  return pharmacies ? (
    <>
      {pharmacies.map((pharmacy) => (
        <PharmacyCard key={pharmacy.id} pharmacy={pharmacy} />
      ))}
    </>
  ) : null;
}
