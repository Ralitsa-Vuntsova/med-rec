import React from "react";
import {
  Box,
  Container,
  InputAdornment,
  TextField,
  Typography,
} from "@material-ui/core";
import { Search } from "@material-ui/icons";
import { useState } from "react";
import { useParams } from "react-router-dom";
import { DrugListForPatient } from "../../components/DrugListForPatient";
import { useAsync } from "../../hooks/use-async";
import { drugService } from "../../services/drug-service";
import { Spinner } from "../../components/Spinner";

export function PharmacyDrugs() {
  const { id } = useParams<{ id: string }>();
  const [search, setSearch] = useState("");

  const {
    data: drugs,
    loading,
    error,
  } = useAsync(
    () => drugService.getAllDrugsOfPharmacy(Number(id), search),
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
        <DrugListForPatient drugs={drugs ?? []} pharmacyId={Number(id)} />
      </Container>
    </>
  );
}
