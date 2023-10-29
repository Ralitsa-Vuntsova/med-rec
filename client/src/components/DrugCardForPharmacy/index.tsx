import React from "react";
import {
  Card,
  CardActionArea,
  CardContent,
  Typography,
} from "@material-ui/core";
import { Link as RouterLink } from "react-router-dom";
import { Drug } from "../../models/Drug";
import { makeStyles } from "@material-ui/core/styles";

export interface DrugCardForPharmacyProps {
  drug: Drug;
}

const useStyles = makeStyles({
  description: {
    fontWeight: "bold",
  },
  card: {
    height: "270px",
  }
});

export function DrugCardForPharmacy({ drug }: DrugCardForPharmacyProps) {
  const classes = useStyles();

  return (
    <Card>
      <CardActionArea component={RouterLink} to={`/drugs/${drug.id}`} className={classes.card}>
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            {drug.brandName}
          </Typography>

          {drug.dosageForm && (
            <Typography gutterBottom variant="body2" component="h2">
              <Typography className={classes.description}>
                Dosage:
              </Typography>{" "}
              {drug.dosageForm}
            </Typography>
          )}
        </CardContent>
      </CardActionArea>
    </Card>
  );
}
