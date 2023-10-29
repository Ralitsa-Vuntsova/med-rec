import React from "react";
import { Box } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import { DrugCardForPatient } from "../DrugCardForPatient";
import { Drug } from "../../models/Drug";

export interface DrugListForPatientProps {
  drugs: Drug[];
  pharmacyId: number;
}

const useStyles = makeStyles({
  list: {
    display: "grid",
    gridTemplateColumns: "1fr 1fr 1fr",
    gap: "10px",
  },
});

export function DrugListForPatient({
  drugs,
  pharmacyId,
}: DrugListForPatientProps) {
  const classes = useStyles();

  return drugs ? (
    <Box className={classes.list}>
      {drugs.map((drug) => (
        <DrugCardForPatient key={drug.id} drug={drug} pharmacyId={pharmacyId} />
      ))}
    </Box>
  ) : null;
}
