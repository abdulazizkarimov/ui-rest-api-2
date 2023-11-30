import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import base.BaseTest;
import model.Test;
import org.testng.Assert;
import page.AddForm;
import page.MainPage;
import page.ProjectPage;
import util.ApiUtil;
import util.BrowserUtil;
import util.Util;
import java.util.Date;
import java.util.List;

public class MyTest extends BaseTest {
    private ISettingsFile testd = new JsonSettingsFile("testd.json");

    @org.testng.annotations.Test
    public void myTestCase() {
        String variant = testd.getValue("/variant").toString();
        String login = testd.getValue("/login").toString();
        String password = testd.getValue("/password").toString();
        String myProject = testd.getValue("/myProject").toString();

        String token = ApiUtil.getToken(variant);
        Assert.assertNotEquals(token, null, "Token is null");

        String authUrl = Util.getAuthUrl(Util.getWebAddress(), login, password);
        BrowserUtil.goTo(authUrl);
        MainPage mainPage = new MainPage();
        BrowserUtil.sendTokenWithCookie(token);

        Assert.assertTrue(mainPage.state().isDisplayed(), "Main Page is not open");
        Assert.assertEquals(mainPage.getFooterVar(), variant, "Variant is incorrect");

        String id = mainPage.getProjectIdByName(myProject);
        mainPage.clickProjectByName(myProject);
        ProjectPage projectPage = new ProjectPage();

        List<Test> apiTests = ApiUtil.getAllTests(id);
        List<Test> webTests = projectPage.getAllTests();

        Assert.assertTrue(Util.isSortedByDate(webTests), "Test list is not sorted");
        Assert.assertTrue(Util.allMatch(apiTests, webTests), "Two lists do not match");

        projectPage.clickOnHomeLink();
        String ow = BrowserUtil.getWindowHandle();
        mainPage.clickOnAddBtn();

        for (String wh : BrowserUtil.getWindowHandles()) {
            if (!ow.contentEquals(wh)) {
                BrowserUtil.switchToWindow(wh);
                break;
            }
        }

        Date date = new Date();
        String projectName = "MyProject" + date.getTime();

        AddForm addForm = new AddForm();
        addForm.enterProjectName(projectName);
        addForm.save();
        Assert.assertTrue(addForm.isSaved(projectName), "Project is not saved");

        BrowserUtil.closeWindow();
        BrowserUtil.switchToWindow(ow);
        Assert.assertFalse(addForm.state().isDisplayed(), "Add Form is displayed");

        BrowserUtil.refresh();
        Assert.assertTrue(mainPage.projectExists(projectName), "Project is not in the list");

        mainPage.clickProjectByName(projectName);

        Test myTest = new Test("1234567", "My Test", "My Method", "Windows");
        String testId = ApiUtil.addTestToProject(projectName, myTest);
        ApiUtil.addLogToTest(testId, BrowserUtil.getLogs());
        ApiUtil.addScreenshotToTest(testId, BrowserUtil.getScreenshot().toString());

        Assert.assertTrue(projectPage.isAdded(), "Test is not added");
    }
}