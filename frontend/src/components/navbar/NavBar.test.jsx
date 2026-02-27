import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";

import NavBar from "./NavBar";
import { AuthProvider } from "../../contexts/AuthContext";
import { MenuProvider } from "../../contexts/MenuContext";

describe("NavBar", () => {
  it("renders NavBar component", () => {
    render(
      <AuthProvider>
        <MenuProvider>
          <NavBar />
        </MenuProvider>
      </AuthProvider>,
    );

    //screen.debug();

    expect(screen.getByText("MetBook")).toBeInTheDocument();
    expect(screen.getByAltText("Stack of books")).toBeInTheDocument();
  });
});
