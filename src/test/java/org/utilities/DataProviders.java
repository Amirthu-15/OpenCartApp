package org.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public Object[][] getData() throws IOException {

		String path = "D:\\Greens_Technology\\SDET_QA\\OpenCartApp\\TestData\\OpenCart.xlsx";

		ExcelUtils excelUtils = new ExcelUtils(path);

		int rowCount = excelUtils.getRowCount("Sheet1");

		int cellCount = excelUtils.getCellCount("Sheet1", rowCount - 1);

		Object[][] loginData = new Object[rowCount - 1][cellCount];

		for (int i = 1; i < rowCount; i++) {

			for (int j = 0; j < cellCount; j++) {

				loginData[i - 1][j] = excelUtils.getCellData("Sheet1", i, j); // Sheet1, 1, 0
			}
		}

		return loginData;

	}

	/*
	 * Object[][] loginData = new Object[rowCount - 1][cellCount];
	 * 
	 * 
	 * This line **creates a 2-dimensional array** (also known as a matrix or table)
	 * in Java. It's specifically designed to hold all the **test data** (excluding
	 * the header row) from the Excel sheet, so TestNG can use it in a data-driven
	 * test.
	 * 
	 * ---
	 * 
	 * ### 🧠 First, What is `Object[][]`?
	 * 
	 * - In Java, a 2D array `Object[][]` is like a **table** with rows and columns.
	 * - Since TestNG’s `@DataProvider` expects a 2D `Object` array, this format is
	 * mandatory. - Each **row** of this array corresponds to one **test case**. -
	 * Each **column** in that row represents a different **parameter** (like email,
	 * password, expected result, etc.).
	 * 
	 * ---
	 * 
	 * ### 🧾 Example Excel Sheet (`rowCount = 3`, `cellCount = 3`):
	 * 
	 * | Email | Password | Expected | |-------------------|-----------|----------|
	 * | user1@example.com | pass123 | valid | | user2@example.com | wrongpass |
	 * invalid |
	 * 
	 * - `rowCount = 3` → 1 header + 2 data rows - `cellCount = 3` → 3 columns
	 * 
	 * ---
	 * 
	 * ### 📌 Why `rowCount - 1`?
	 * 
	 * Because the first row (`row 0`) is the **header** (`Email`, `Password`,
	 * `Expected`) and is **not test data**. So we subtract 1:
	 * 
	 * ```java Object[][] loginData = new Object[2][3]; // 2 rows, 3 columns
	 * 
	 * This means:
	 * 
	 * 2 test cases (rows): one for user1, one for user2
	 * 
	 * 3 parameters (columns): email, password, and expected result
	 * 
	 * Initially, the array is empty:
	 * 
	 * loginData = { { null, null, null }, { null, null, null } }
	 * 
	 * Then, in the nested loop:
	 * 
	 * for (int i = 1; i < rowCount; i++) { for (int j = 0; j < cellCount; j++) {
	 * loginData[i - 1][j] = excelUtils.getCellData("Sheet1", i, j); } }
	 * 
	 * Each call to getCellData(...) fetches one cell's value from Excel, and fills
	 * the array like:
	 * 
	 * loginData = { { "user1@example.com", "pass123", "valid" }, {
	 * "user2@example.com", "wrongpass", "invalid" } }
	 * 
	 * @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	 * public void testLogin(String email, String password, String expected) {
	 * 
	 * // First test run email = "user1@example.com" password = "pass123" expected =
	 * "valid"
	 * 
	 * // Second test run email = "user2@example.com" password = "wrongpass"
	 * expected = "invalid"
	 * 
	 * 
	 */

}
