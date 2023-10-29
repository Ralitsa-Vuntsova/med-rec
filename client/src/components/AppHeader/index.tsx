import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import LocalHospitalIcon from "@material-ui/icons/LocalHospital";
import { Button, Link, Typography } from "@material-ui/core";
import { Link as RouterLink } from "react-router-dom";
import { useCurrentUser } from "../../hooks/use-current-user";
import { authService } from "../../services/auth-service";

const useStyles = makeStyles({
  title: {
    fontSize: "20px",
    color: "white",
  },
  prescription: {
    flexGrow: 1,
    marginLeft: "15px",
  },
  prescriptionLink: {
    color: "white",
  },
  icon: {
    marginRight: "10px",
  },
});

export function AppHeader() {
  const classes = useStyles();
  const user = useCurrentUser();
  const [anchorEl, setAnchorEl] = useState<Element | null>(null);

  function logout() {
    setAnchorEl(null);
    authService.logout();
  }

  return (
    <AppBar position="static">
      <Toolbar>
        <LocalHospitalIcon className={classes.icon} />
        <Link
          className={classes.title}
          underline="none"
          component={RouterLink}
          to="/"
        >
          MedRec
        </Link>

        {user && (
          <>
            <Typography variant="body1" className={classes.prescription}>
              {user.type === "patient" &&
                  <Link
                    underline="none"
                    component={RouterLink}
                    to="/prescriptions"
                    className={classes.prescriptionLink}
                  >
                    Prescriptions
                  </Link>
              }
            </Typography>

            <Button color="inherit" onClick={logout}>
              Logout
            </Button>
          </>
        )}
      </Toolbar>
    </AppBar>
  );
}
