import { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import WordCount from "../interfaces/WordCount.interface";

interface WebSocketHook {
  data: WordCount;
  error: Error | null;
  loading: boolean;
}

const useWebSocket = (url: string): WebSocketHook => {
  const [data, setData] = useState<WordCount>({});
  const [error, setError] = useState<Error | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const client = new Client({
      webSocketFactory: () => new SockJS(url),
      onConnect: () => {
        setLoading(false);
        client.subscribe("/topic/wordcount", (message) => {
          try {
            const parsedData: WordCount = JSON.parse(message.body);
            setData(parsedData);
          } catch (e) {
            if (e instanceof Error) {
              console.error("Error parsing the data: ", e.message);
              setError(e);
            }
          }
        });
      },
      onStompError: (frame) => {
        setError(new Error("STOMP error: " + frame.body));
        setLoading(false);
      },
    });

    client.activate();

    return () => {
      client.deactivate();
    };
  }, [url]);

  return { data, error, loading };
};

export default useWebSocket;
