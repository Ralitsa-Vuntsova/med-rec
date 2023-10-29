import React from "react";
import {
  Box,
  TextField,
  InputAdornment,
  Container,
  Card,
  CardActionArea,
  Typography,
} from "@material-ui/core";
import { Search } from "@material-ui/icons";
import { useState } from "react";
import { Link as RouterLink } from "react-router-dom";
import AddCircleOutlineIcon from "@material-ui/icons/AddCircleOutline";
import { makeStyles } from "@material-ui/core/styles";
import { useAsync } from "../../hooks/use-async";
import { drugService } from "../../services/drug-service";
import { Spinner } from "../Spinner";
import { DrugListForPharmacy } from "../DrugListForPharmacy";

export interface PharmacyProps {
  pharmacyId: number;
}

const useStyles = makeStyles({
  card: {
    display: "flex",
    marginBottom: "20px",
    height: "50px",
    alignItems: "center",
    justifyContent: "center",
  },
  cardActionArea: {
    display: "flex",
    justifyItems: "center",
    alignItems: "center",
  },
});

export function Pharmacy({ pharmacyId }: PharmacyProps) {
  const [search, setSearch] = useState("");
  const classes = useStyles();

  const {
    data: drugs,
    loading,
    error,
  } = useAsync(
    () => drugService.getAllDrugsOfPharmacy(pharmacyId, search),
    [search]
  );

  if (error) {
    <Typography color="error">Error</Typography>;
  }

  if (loading) {
    <Spinner />;
  }

  return (
    <>
      <Box textAlign="center" marginTop={5} marginBottom={5}>
        <TextField
          placeholder="Search"
          variant="outlined"
          onChange={(event) => {
            console.log(event.target.value);
            setSearch(event.target.value);
          }}
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <Search />
              </InputAdornment>
            ),
          }}
        />
      </Box>

      <Container maxWidth="md">
        <Card className={classes.card}>
          <CardActionArea
            component={RouterLink}
            to={"/drugs/new"}
            className={classes.cardActionArea}
          >
            <AddCircleOutlineIcon />
          </CardActionArea>
        </Card>

        <DrugListForPharmacy drugs={drugs ?? []} />
      </Container>
    </>
  );
}
