import { render, screen } from "@testing-library/react";
import BaseLayout from "./BaseLayout";

describe("BaseLayout", () => {
  it("renders children correctly", () => {
    render(
      <BaseLayout>
        <div>Test Child</div>
      </BaseLayout>
    );
    expect(screen.getByText("Test Child")).toBeInTheDocument();
  });
});
