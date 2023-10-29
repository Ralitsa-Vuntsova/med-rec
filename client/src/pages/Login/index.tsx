import React, { FormEvent, useState } from "react";
import {
  Box,
  Button,
  Container,
  TextField,
  Typography,
  Link,
} from "@material-ui/core";
import { useNavigate } from "react-router-dom";
import { Link as RouteLink } from "react-router-dom";
import { authService } from "../../services/auth-service";

export function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<any>();

  const history = useNavigate();

  async function submit(event: FormEvent) {
    event.preventDefault();

    if (!username || !password) {
      return;
    }

    try {
      await authService.login(username, password);
    } catch (error) {
      setError(error);
      return;
    }

    history("/");
  }

  return (
    <Container maxWidth="xs">
      <form onSubmit={submit}>
        <Box display="grid" gridGap={7} marginTop={10}>
          <TextField
            name="username"
            value={username}
            onChange={(event) => setUsername(event.target.value)}
            label="Username"
            variant="outlined"
          />

          <TextField
            name="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            label="Password"
            variant="outlined"
            type="password"
          />

          {error && <Typography color="error">Error</Typography>}

          <Button type="submit" color="primary" variant="contained">
            Login
          </Button>

          <Typography>
            Don&apos;t have an account?{" "}
            <Link component={RouteLink} to="/register" underline="none">
              Register
            </Link>{" "}
          </Typography>
        </Box>
      </form>
    </Container>
  );
}
