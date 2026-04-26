import { test, expect } from "@playwright/test";

// todo: add locales

test.describe("Find, loan, and return book", () => {
  let bookTitle;
  let bookAuthors;
  let bookPublishingYear;
  let bookGenre;

  // Login
  test.beforeEach(async ({ page }) => {
    await page.goto('http://localhost:5173/');
    await page.getByRole('button', { name: /login/i }).click();
    
    const modal = page.locator('div.fixed.inset-0');
    await expect(modal).toBeVisible();
    
    // Fill form
    await modal.getByPlaceholder('example@gmail.com').fill(process.env.TEST_EMAIL);
    await modal.getByPlaceholder('Enter your password').fill(process.env.TEST_PASSWORD);
    await modal.getByRole('button', { name: /login/i }).click();
    
    await expect(page.getByRole('button', { name: /logout/i })).toBeVisible();
  });

  test("Check first book info and loan book", async ({ page }) => {
    await page.goto(`http://localhost:5173/`, {
      waitUntil: "load",
    });

    // save book title to variable to be used in these tests
    bookTitle = await page
      .locator(".bg-white.rounded-lg")
      .first()
      .getByRole("heading")
      .textContent();

    // save book authors
    bookAuthors = await page
      .locator(".bg-white.rounded-lg")
      .first()
      .locator("p")
      .nth(0)
      .textContent();

    // save book publishing year
    bookPublishingYear = await page
      .locator(".bg-white.rounded-lg")
      .first()
      .locator("p")
      .nth(1)
      .textContent();

    // save book genre
    bookGenre = await page
      .locator(".bg-white.rounded-lg")
      .first()
      .locator("p")
      .nth(2)
      .textContent();

    //console.log(bookTitle, bookAuthors, bookPublishingYear, bookGenre)

    // click on book heading to open book modal
    await page.getByRole("heading", { name: bookTitle }).click();

    // check that book modal info matches book card info (title, authors, year, genre)
    await expect(
      page.locator("h1").filter({ hasText: bookTitle }),
    ).toBeVisible();

    await expect(
      page.locator("p").nth(1).filter({ hasText: bookAuthors }),
    ).toBeVisible();

    await expect(
      page.locator("p").nth(2).filter({ hasText: bookPublishingYear }),
    ).toBeVisible();

    await expect(
      page.locator("span").nth(2).filter({ hasText: bookGenre }),
    ).toBeVisible();

    // -- Step 2: Click loan button and handle alert --

    const modal = page.locator("div.fixed.inset-0");

    // Close modal
    if (await modal.isVisible()) {
      await page.getByRole("button", { name: "✕" }).click();
      await expect(modal).toBeHidden();
    }

    const firstBook = page.locator(".bg-white.rounded-lg").first();
    const loanButton = firstBook.getByRole("button", { name: /loan/i });

    // Check that loan button is visible
    await expect(loanButton).toBeVisible();

    // Listener for loan confirmation alert
    const dialogPromise = page.waitForEvent('dialog');

    // Click Loan
    await loanButton.click();

    // Verify alert message and close it
    const dialog = await dialogPromise;
    expect(dialog.message()).toContain(bookTitle + ' loaned');
    await dialog.accept();


    // next test

  });
});
