import React from "react";
import { CssBaseline } from "@material-ui/core";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { AppHeader } from "../AppHeader";
import { Login } from "../../pages/Login";
import { AppFooter } from "../AppFooter";
import { Library } from "../../pages/Library";
import { Register } from "../../pages/Register";
import { PharmacyDrugs } from "../../pages/PharmacyDrugs";
import { DrugPageForPatient } from "../../pages/DrugPageForPatient";
import { CreateDrug } from "../../pages/CreateDrug";
import { UserProvider } from "../../hooks/use-current-user";
import { DrugPageForPharmacy } from "../../pages/DrugPageForPharmacy";
import { SinglePrescription } from "../../pages/SinglePrescription";
import { PrescriptionPage } from "../../pages/PrescriptionPage";
import { PrivateRoute } from "../PrivateRoute";

export function App() {
  return (
    <BrowserRouter>
      <CssBaseline />
      <UserProvider>
        <AppHeader />
        <Routes>
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route
            path="/pharmacy/:id"
            element={<PrivateRoute component={PharmacyDrugs} />}
          />
          <Route
            path="/pharmacy/:pharmacyId/drugs/:drugId"
            element={<PrivateRoute component={DrugPageForPatient} />}
          />
          <Route
            path="/drugs/new"
            element={<PrivateRoute component={CreateDrug} />}
          />
          <Route
            path="/drugs/:drugId"
            element={<PrivateRoute component={DrugPageForPharmacy} />}
          />
          <Route
            path="/prescriptions/"
            element={<PrivateRoute component={PrescriptionPage} />}
          />
          <Route
            path="/prescriptions/:id"
            element={<PrivateRoute component={SinglePrescription} />}
          />
          <Route path="/" element={<PrivateRoute component={Library} />} />
          <Route path="*" element={<Navigate to="/" />} />{" "}
        </Routes>
        <AppFooter />
      </UserProvider>
    </BrowserRouter>
  );
}
