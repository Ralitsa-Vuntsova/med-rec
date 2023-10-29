import React from "react";
import { Container, Typography } from "@material-ui/core";
import { useAsync } from "../../hooks/use-async";
import { useCurrentUser } from "../../hooks/use-current-user";
import { prescriptionService } from "../../services/prescription-service";
import { PrescriptionList } from "../../components/PrescriptionList";
import { Spinner } from "../../components/Spinner";

export function PrescriptionPage() {
  const user = useCurrentUser();

  const {
    data: prescriptions,
    loading,
    error,
  } = useAsync(() => prescriptionService.getPrescriptions(user?.id), []);

  if (error) {
    <Typography color="error">Error</Typography>;
  }

  if (loading) {
    <Spinner />;
  }

  return (
    <Container maxWidth="md">
      <PrescriptionList prescriptions={prescriptions ?? []} />
    </Container>
  );
}
