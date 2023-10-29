import React from "react";
import { Box, Container, Typography } from "@material-ui/core";
import { useParams } from "react-router-dom";
import { makeStyles } from "@material-ui/core/styles";
import { useAsync } from "../../hooks/use-async";
import { Spinner } from "../../components/Spinner";
import { drugService } from "../../services/drug-service";

const useStyles = makeStyles({
  container: {
    display: "flex",
    flexDirection: "column",
    marginTop: "50px",
    gap: "20px",
  },
  title: {
    alignSelf: "center",
  },
  button: {
    marginTop: "10px",
    width: "200px",
    alignSelf: "center",
  },
  description: {
    fontWeight: "bold",
  },
  box: {
    display: "flex",
    flexDirection: "column",
    gap: "10px",
  },
});

export function DrugPageForPatient() {
  const { pharmacyId } = useParams<{ pharmacyId: string }>();
  const { drugId } = useParams<{ drugId: string }>();
  const classes = useStyles();

  const {
    data: drug,
    loading,
    error,
  } = useAsync(
    () => drugService.getDrug(Number(pharmacyId), Number(drugId)),
    []
  );

  if (error) {
    <Typography color="error">Error</Typography>;
  }

  if (loading) {
    <Spinner />;
  }

  return drug ? (
    <Container maxWidth="md" className={classes.container}>
      <Typography className={classes.title} variant="h4">
        {drug.brandName}
      </Typography>

      <Box marginTop={5} className={classes.box}>
        {drug.genericName !== "NULL" && (
          <>
            <Typography className={classes.description}>Brand:</Typography>
            <Typography>
              {drug.genericName}
            </Typography>
          </>
        )}
        {drug.substanceName !== "NULL" && (
          <>
            <Typography className={classes.description}>Substance:</Typography>
            <Typography>
              {drug.substanceName}
            </Typography>
            </>
        )}
        {drug.manufacturerName !== "NULL" && (
          <>
            <Typography className={classes.description}>
              Manufacturer:
            </Typography>
            <Typography>
              {drug.manufacturerName}
            </Typography>
          </>
        )}
        {drug.dosageForm !== "NULL" && (
          <>
            <Typography className={classes.description}>Dosage:</Typography>
            <Typography>
              {drug.dosageForm}
            </Typography>
          </>
        )}
        {drug.route !== "NULL" && (
          <>
            <Typography className={classes.description}>Route:</Typography>
            <Typography>
              {drug.route}
            </Typography>
          </>
        )}
        {drug.marketingStatus !== "NULL" && (
          <>
            <Typography className={classes.description}>
              Marketing status:
            </Typography>
            <Typography>
              {drug.marketingStatus}
            </Typography>
          </>
        )}
      </Box>
    </Container>
  ) : null;
}
