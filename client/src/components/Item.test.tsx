import { render, screen } from "@testing-library/react";
import Item from "./Item";

describe("Item", () => {
  it("displays word and count", () => {
    render(<Item word="test" count={3} />);
    expect(
      screen.getByText("Current word is: test and its count is 3")
    ).toBeInTheDocument();
  });
});
