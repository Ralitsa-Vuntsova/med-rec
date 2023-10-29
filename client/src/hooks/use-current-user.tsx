import React, {
  createContext,
  ReactNode,
  useContext,
  useEffect,
  useState,
} from "react";
import { authService } from "../services/auth-service";
import type { User } from "../models/User";

const UserContext = createContext<User | undefined>(undefined);

export interface UserProviderProps {
  children: ReactNode;
}

export function UserProvider({ children }: UserProviderProps) {
  const [user, setUser] = useState(authService.persistedUser);

  useEffect(() => {
    authService.setHandler(setUser);

    return () => {
      authService.setHandler(null);
    };
  }, []);

  return <UserContext.Provider value={user}>{children}</UserContext.Provider>;
}

export function useCurrentUser() {
  return useContext(UserContext);
}
