import React, { FormEvent, useState } from "react";
import {
  Box,
  Button,
  Container,
  TextField,
  Typography,
  Link,
} from "@material-ui/core";
import { Link as RouteLink, useNavigate } from "react-router-dom";
import { authService } from "../../services/auth-service";

// Only users will register

export function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [error, setError] = useState<any>();

  const history = useNavigate();

  function submit(event: FormEvent) {
    event.preventDefault();
    if (!username || !password || !name || !lastName || !email) {
      return;
    }

    try {
      authService.register(username, password, name, lastName, email);
    } catch (error) {
      setError(error);
      return;
    }

    history("/login");
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

          <TextField
            name="firstName"
            value={name}
            onChange={(event) => setName(event.target.value)}
            label="First Name"
            variant="outlined"
          />

          <TextField
            name="lastName"
            value={lastName}
            onChange={(event) => setLastName(event.target.value)}
            label="Last name"
            variant="outlined"
          />

          <TextField
            name="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            label="Email"
            variant="outlined"
          />

          {error && <Typography color="error">Error</Typography>}

          <Button type="submit" color="primary" variant="contained">
            Register
          </Button>

          <Typography>
            Already have an account?{" "}
            <Link component={RouteLink} to="/login" underline="none">
              Login
            </Link>
          </Typography>
        </Box>
      </form>
    </Container>
  );
}
