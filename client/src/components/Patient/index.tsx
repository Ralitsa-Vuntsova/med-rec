import React from "react";
import {
  Box,
  Container,
  InputAdornment,
  TextField,
  Typography,
} from "@material-ui/core";
import { PharmacyList } from "../PharmacyList";
import { Search } from "@material-ui/icons";
import { useState } from "react";
import { useAsync } from "../../hooks/use-async";
import { drugService } from "../../services/drug-service";
import { Spinner } from "../Spinner";

export function Patient() {
  const [search, setSearch] = useState("");

  const {
    data: pharmacyList,
    loading,
    error,
  } = useAsync(() => drugService.getPharmacies(search), [search]);

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
          onChange={(event) => setSearch(event.target.value)}
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
        <PharmacyList pharmacies={pharmacyList ?? []} />
      </Container>
    </>
  );
}
