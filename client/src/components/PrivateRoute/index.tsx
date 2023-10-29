import { Navigate } from "react-router-dom";
import { useCurrentUser } from "../../hooks/use-current-user";

interface Props {
  component: React.ComponentType;
  path?: string;
}

export const PrivateRoute: React.FC<Props> = ({
  component: RouteComponent,
}) => {
  const user = useCurrentUser();

  if (user) {
    return <RouteComponent />;
  }

  return <Navigate to="/login" />;
};
