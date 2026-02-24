import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import Footer from "./Footer";
import SocialMedia from './SocialMedia';
import Languages from './Languages';

describe("Footer", () => {
  it("renders footer component", () => {
    render(<Footer />);

    //screen.debug();

    expect(screen.getByAltText("MetBook logo")).toBeInTheDocument();
    expect(screen.getByText("Social Media")).toBeInTheDocument();
    expect(screen.getByText("Language")).toBeInTheDocument();
  });
});
