import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";

import NavBar from "./NavBar";
import { MenuProvider } from "../../contexts/MenuContext";

describe("NavBar", () => {
  it("renders NavBar component", () => {
    render(
      <MenuProvider>
        <NavBar />
      </MenuProvider>,
    );

    screen.debug();

    expect(screen.getByText("MetBook")).toBeInTheDocument();
    expect(screen.getByAltText("Stack of books")).toBeInTheDocument();

  });
});
