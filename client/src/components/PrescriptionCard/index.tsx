import React from "react";
import { Card, CardActionArea, CardMedia } from "@material-ui/core";
import { Prescription } from "../../models/Prescription";
import { Link as RouterLink } from "react-router-dom";
import { makeStyles } from "@material-ui/core/styles";

export interface PrescriptionCardProps {
  prescription: Prescription;
}

const useStyles = makeStyles({
  card: {
    width: "250px",
    height: "250px",
  },
  media: {
    height: 0,
    paddingTop: "100%",
  },
});

export function PrescriptionCard({ prescription }: PrescriptionCardProps) {
  const classes = useStyles();

  return (
    <Card className={classes.card}>
      <CardActionArea
        component={RouterLink}
        to={`/prescriptions/${prescription.id}`}
        state={{ prescription }}
      >
        <CardMedia
          image={`/${prescription.pathName}`}
          title="Prescription"
          className={classes.media}
        />
      </CardActionArea>
    </Card>
  );
}
