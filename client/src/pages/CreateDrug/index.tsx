import React, { FormEvent } from "react";
import { Button, Container, TextField, Typography } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import { useState } from "react";
import { useAsyncAction } from "../../hooks/use-async-action";
import { drugService } from "../../services/drug-service";
import { useNavigate } from "react-router-dom";
import { useCurrentUser } from "../../hooks/use-current-user";
import { Spinner } from "../../components/Spinner";

const useStyles = makeStyles({
  form: {
    display: "flex",
    flexDirection: "column",
    marginTop: "50px",
    gap: "20px",
    alignItems: "center",
  },
  field: {
    width: "300px",
  },
  button: {
    marginTop: "10px",
    width: "200px",
  },
});

export function CreateDrug() {
  const classes = useStyles();
  const user = useCurrentUser();
  const [fields, setFields] = useState({
    brandName: "",
  });
  const history = useNavigate();

  const {
    trigger: submit,
    loading,
    error,
    perform,
  } = useAsyncAction(async () => await drugService.createDrug(user!.id, fields.brandName));

  if (error) {
    <Typography color="error">Error</Typography>;
  }

  if (loading) {
    <Spinner />;
  }

  return (
    <Container
      maxWidth="xs"
    >
      <form  
        onSubmit={(event: FormEvent) => {
          event.preventDefault();
          submit();
          history("/");
          perform();
        }}
        className={classes.form}
      >
      <Typography variant="h5">Add a new drug</Typography>

        <TextField
          label="Brand name"
          value={fields.brandName}
          className={classes.field}
          onChange={(event) =>
            setFields({
              ...fields,
              brandName: event.target.value,
            })
          }
        />

        <Button
          type="submit"
          variant="contained"
          color="primary"
          className={classes.button}
        >
          Submit
        </Button>
      </form>
    </Container>
  );
}
