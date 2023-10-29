import React from "react";
import { Typography } from "@material-ui/core";
import { Patient } from "../../components/Patient";
import { Pharmacy } from "../../components/Pharmacy";
import { useCurrentUser } from "../../hooks/use-current-user";

export function Library() {
  const user = useCurrentUser();

  if (user && user.type === "patient") {
    return <Patient />;
  }

  if (user && user.type === "pharmacy") {
    return <Pharmacy pharmacyId={user.id} />;
  }

  return <Typography color="error">No data</Typography>;
}
