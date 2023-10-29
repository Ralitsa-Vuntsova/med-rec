import { DependencyList, useEffect } from "react";
import { useAsyncAction } from "./use-async-action";

export function useAsync<T>(
  action: () => Promise<T>,
  dependencies: DependencyList
) {
  const { perform, ...state } = useAsyncAction<T>(action, {
    data: undefined,
    error: undefined,
    loading: true,
  });

  useEffect(() => {
    perform();
  }, dependencies);

  return { ...state, perform };
}
