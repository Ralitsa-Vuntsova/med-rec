import React from "react";
import { Box, CircularProgress } from "@material-ui/core";

export function Spinner() {
  return (
    <Box textAlign="center" marginTop={10}>
      <CircularProgress />
    </Box>
  );
}
