import { useEffect, useRef, useState } from "react";

export interface AsyncState<T> {
  data: T | undefined;
  loading: boolean;
  error: any | undefined;
}

export function useAsyncAction<T>(
  action: () => Promise<T>,
  initialState: AsyncState<T> = {
    data: undefined,
    loading: false,
    error: undefined,
  }
) {
  const [state, setState] = useState<AsyncState<T>>(initialState);
  const idRef = useRef(0);

  async function perform() {
    idRef.current += 1;
    const ourId = idRef.current;

    setState({ loading: true, data: undefined, error: undefined });

    try {
      const data = await action();

      if (idRef.current === ourId) {
        setState({ loading: false, data, error: undefined });
      }
    } catch (error) {
      if (idRef.current === ourId) {
        setState({ loading: false, data: undefined, error });
      }

      throw error;
    }
  }

  function trigger() {
    perform().catch(() => {
      // Intentionally ignored
    });
  }

  useEffect(() => {
    return () => {
      idRef.current += 1;
    };
  }, []);

  return {
    ...state,
    perform,
    trigger,
  };
}
