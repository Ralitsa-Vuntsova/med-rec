import React from "react";
import { Box } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import { Drug } from "../../models/Drug";
import { DrugCardForPharmacy } from "../DrugCardForPharmacy";

export interface DrugListForPharmacyProps {
  drugs: Drug[];
}

const useStyles = makeStyles({
  list: {
    display: "grid",
    gridTemplateColumns: "1fr 1fr 1fr",
    gap: "10px",
  },
});

export function DrugListForPharmacy({ drugs }: DrugListForPharmacyProps) {
  const classes = useStyles();

  return drugs ? (
    <Box className={classes.list}>
      {drugs.map((drug) => (
        <DrugCardForPharmacy key={drug.id} drug={drug} />
      ))}
    </Box>
  ) : null;
}
