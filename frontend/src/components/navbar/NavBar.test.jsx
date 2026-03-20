import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";

import NavBar from "./NavBar";
import { AuthProvider } from "../../contexts/AuthContext";
import { MenuProvider } from "../../contexts/MenuContext";
import { MemoryRouter } from "react-router";

describe("NavBar", () => {
  it("renders NavBar component", () => {
    render(
      <MemoryRouter>
        <AuthProvider>
          <MenuProvider>
            <NavBar />
          </MenuProvider>
        </AuthProvider>
      </MemoryRouter>,
    );

    //screen.debug();

    expect(screen.getByTestId("site-title")).toBeInTheDocument();
    expect(screen.getByTestId("book-logo")).toBeInTheDocument();
  });
});
