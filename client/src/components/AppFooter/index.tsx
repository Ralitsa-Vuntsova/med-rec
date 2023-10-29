import React from "react";
import { Box } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles({
  footer: {
    position: "fixed",
    left: 0,
    bottom: 0,
    width: "100%",
    color: "black",
    textAlign: "center",
    fontSize: "16px",
  },
});

export function AppFooter() {
  const classes = useStyles();

  return <Box className={classes.footer}>Adventure Squad 2022</Box>;
}
