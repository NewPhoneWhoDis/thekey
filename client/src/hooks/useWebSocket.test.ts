/*import { renderHook, act } from "@testing-library/react-hooks";
import useWebSocket from "./useWebSocket";
import { Client } from "@stomp/stompjs";
import { vi } from "vitest";

vi.mock("sockjs-client", () => {
  return {
    default: vi.fn().mockImplementation(() => {
      return {};
    }),
  };
});

vi.mock("@stomp/stompjs", () => {
  return {
    Client: vi.fn().mockImplementation(() => ({
      subscribe: vi.fn(),
      activate: vi.fn().mockImplementation(function () {
        this.onConnect();
      }),
      deactivate: vi.fn(),
    })),
  };
});

describe("useWebSocket hook", () => {
  it("should update data based on WebSocket messages", async () => {
    vi.useFakeTimers();

    const { result } = renderHook(() => useWebSocket("ws://localhost:8080/ws"));

    act(() => {
      vi.advanceTimersByTime(1000);
    });

    const mockClientInstance = vi.mocked(Client).mock.results[0].value;

    if (mockClientInstance.subscribe.mock.calls.length > 0) {
      act(() => {
        mockClientInstance.subscribe.mock.calls[0][1]({
          body: JSON.stringify({ word: 5 }),
        });
      });

      expect(result.current.data).toEqual({ word: 5 });
    } else {
      throw new Error("Subscribe method was not called");
    }

    vi.useRealTimers();
  });
});
*/
