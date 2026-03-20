import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import Footer from "./Footer";
import SocialMedia from './SocialMedia';
import Languages from './Languages';

describe("Footer", () => {
  it("renders footer component", () => {
    render(<Footer />);

    //screen.debug();

    expect(screen.getByTestId("footer-logo-img")).toBeInTheDocument();
    expect(screen.getByTestId("social-media-links-header")).toBeInTheDocument();
    expect(screen.getByTestId("footer-languages-header")).toBeInTheDocument();
  });
});
