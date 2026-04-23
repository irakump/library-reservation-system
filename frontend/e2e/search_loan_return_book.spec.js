import { test, expect } from '@playwright/test';

// todo: add locales

test.describe('Find, loan, and return book', () => {

  let bookTitle;
  let bookAuthors;
  let bookPublishingYear;
  let bookGenre;

  test('Check first book info', async ({ page }) => {
    await page.goto(`http://localhost:5173/`, {
      waitUntil: 'load',
    });

    // save book title to variable to be used in these tests
    bookTitle = await page
      .locator('.bg-white.rounded-lg')
      .first()
      .getByRole('heading')
      .textContent();
    
    // save book authors
    bookAuthors = await page
      .locator('.bg-white.rounded-lg')
      .first()
      .locator('p').nth(0)
      .textContent();
    
    // save book publishing year
    bookPublishingYear = await page
      .locator('.bg-white.rounded-lg')
      .first()
      .locator('p').nth(1)
      .textContent();
    
    // save book genre
    bookGenre = await page
      .locator('.bg-white.rounded-lg')
      .first()
      .locator('p').nth(2)
      .textContent();
    
    //console.log(bookTitle, bookAuthors, bookPublishingYear, bookGenre)

    // click on book heading to open book modal
    await page
      .getByRole('heading', { name: bookTitle })
      .click();

    // check that book modal info matches book card info (title, authors, year, genre)
    await expect(
      page.locator('h1').filter({ hasText: bookTitle }),
    ).toBeVisible();

    await expect(
      page.locator('p').nth(1).filter({ hasText: bookAuthors }),
    ).toBeVisible();

    await expect(
      page.locator('p').nth(2).filter({ hasText: bookPublishingYear }),
    ).toBeVisible();

    await expect(
      page.locator('span').nth(2).filter({ hasText: bookGenre }),
    ).toBeVisible();
  });

  // next test
});
