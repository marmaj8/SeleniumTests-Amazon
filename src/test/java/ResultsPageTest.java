import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ResultsPageTest extends BaseTest {
    final static String DEPARTMENT_BOOKS = "Books";
    final static String DEPARTMENT_COMPUTERS = "Computers";

    final static String SEARCH_TERM_MOUSE = "mouse";
    final static String SEARCH_TERM_BOARD = "keyboard";

    final static String CURRENCY_CODE_USD = "USD";
    final static String CURRENCY_SYMBOL_USD = "$";
    final static String CURRENCY_CODE_EUR = "EUR";
    final static String CURRENCY_SYMBOL_EUR = "EUR";


    @DataProvider(name = "basic-search-term-set")
    public Object[][] basicSearchTerms() {
        return new Object[][]
                {
                        {SEARCH_TERM_MOUSE},
                        {SEARCH_TERM_BOARD}
                };
    }

    @DataProvider(name = "search-term-in-department-set")
    public Object[][] departmentSearchTerms() {
        return new Object[][]
                {
                        {SEARCH_TERM_MOUSE, DEPARTMENT_COMPUTERS},
                        {SEARCH_TERM_MOUSE, DEPARTMENT_BOOKS}
                };
    }

    @DataProvider(name = "search-term-with-currency-set")
    public Object[][] basicSearchTermsWithCurrency() {
        return new Object[][]
                {
                        {SEARCH_TERM_MOUSE, CURRENCY_CODE_EUR, CURRENCY_SYMBOL_EUR},
                        {SEARCH_TERM_MOUSE, CURRENCY_CODE_USD, CURRENCY_SYMBOL_USD}
                };
    }

    @BeforeMethod
    public void open(){
        homePage.open()
                .dismissLanguageChangePopUp();
    }

    @Test(dataProvider = "basic-search-term-set")
    public void searchProductResultsNamesContainsSearchedString(String stringToFind) {
        final String stringToFindLowerCase = stringToFind.toLowerCase();

        homePage.typeSearchTerm(stringToFind)
                .startSearching();

        String searchedString = resultsPage.searchedTerm
                .getText()
                .trim();

        logger.info("Checking searched term");
        Assert.assertEquals(searchedString, "\"" + stringToFind + "\"");

        logger.info("Checking founded product names");
        resultsPage.productNames.stream()
                .filter(WebElement::isDisplayed)
                .forEach(element -> {
                    String text = element
                            .getText()
                            .trim();
                    String textLowerCase = text.toLowerCase();
                    softAssert.assertTrue(
                            textLowerCase.contains(stringToFindLowerCase),
                            "'" + stringToFind + "' is not in '" + text + "'");
                });
        softAssert.assertAll();
    }

    @Test(dataProvider = "search-term-in-department-set")
    public void searchProductInDepartmentResultsInSelectedDepartment(String stringToFind, String departmentToSearch) {
        homePage.selectDepartmentByText(departmentToSearch)
                .typeSearchTerm(stringToFind)
                .startSearching();

        logger.info("Checking first department name");
        String department = resultsPage.mainDepartment.getText();
        Assert.assertEquals(department, departmentToSearch);

        String mainClass = resultsPage.mainDepartment.getAttribute("class");

        logger.info("Checking main department is distinguished");
        resultsPage.subDepartments.forEach(element -> {
            String subClass = element.getAttribute("class");
            softAssert.assertNotEquals(subClass, mainClass, element + " have same style as main department");
        });
        softAssert.assertAll();
    }

    @Test(dataProvider = "search-term-with-currency-set")
    public void changeCurrencyCorrectCurrencyInResults(String stringToFind, String currencyCode, String currencySymbol) {
        homePage.typeSearchTerm(stringToFind)
                .startSearching();

        resultsPage.enterChangeLanguageCurrencyPage();

        languageCurrencySettingsPage.selectCurrencyByName(currencyCode)
                .saveLanguageCurrencySettings();

        logger.info("Checking currency symbols");
        resultsPage.currencySymbols.stream()
                .filter(WebElement::isDisplayed)
                .forEach(element -> {
                    String symbol = element.getText()
                            .trim();
                    softAssert.assertEquals(symbol, currencySymbol);
                });
        softAssert.assertAll();
    }
}
