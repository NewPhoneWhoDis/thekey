import "./App.css";
import BaseLayout from "./layouts/BaseLayout";
import useWebSocket from "./hooks/useWebSocket";
import Item from "./components/Item";

function App() {
  const { data, error, loading } = useWebSocket("http://localhost:8080/ws");

  return (
    <BaseLayout>
      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <h2 className="text-red-500">Error: {error.message}</h2>
      ) : (
        Object.entries(data).map(([word, count]) => (
          <Item key={word} word={word} count={count} />
        ))
      )}
    </BaseLayout>
  );
}

export default App;
