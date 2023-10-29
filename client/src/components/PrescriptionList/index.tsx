import React from "react";
import { Box } from "@material-ui/core";
import { Prescription } from "../../models/Prescription";
import { makeStyles } from "@material-ui/core/styles";
import { PrescriptionCard } from "../PrescriptionCard";

export interface PrescriptionListProps {
  prescriptions: Prescription[];
}

const useStyles = makeStyles({
  list: {
    display: "grid",
    gridTemplateColumns: "1fr 1fr 1fr",
    gap: "10px",
    marginTop: "50px",
  },
});

export function PrescriptionList({ prescriptions }: PrescriptionListProps) {
  const classes = useStyles();

  return prescriptions ? (
    <Box className={classes.list}>
      {prescriptions.map((prescription) => (
        <PrescriptionCard key={prescription.id} prescription={prescription} />
      ))}
    </Box>
  ) : null;
}
