package page;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import model.Test;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class ProjectPage extends Form {
    private ILink homeLink = getElementFactory().getLink(By.xpath("//a[contains(@href, '/web/projects')]"), "Home Link");
    private List<ILabel> emptyCheck = getElementFactory().findElements(By.xpath("//span[contains(@class, 'glyphicon-exclamation-sign')]"), ElementType.LABEL);

    public ProjectPage() {
        super(By.xpath("//div[contains(@class, 'panel-heading')]"), "Project Page");
    }

    public List<Test> getAllTests() {
        List<Test> testList = new ArrayList<>();
        int startRow = 2;
        int endRow = 21;

        for (int i = startRow; i <= endRow; i++) {
            String str = "//table[contains(@class, 'table')]//tr[%d]//td[%d]";

            String duration = getElementFactory().getLabel(By.xpath(String.format(str, i, 6)), "").getText();
            String method = getElementFactory().getLabel(By.xpath(String.format(str, i, 2)), "").getText();
            String name = getElementFactory().getLink(By.xpath(String.format(str + "/a", i, 1)), "").getText();
            String startTime = getElementFactory().getLabel(By.xpath(String.format(str, i, 4)), "").getText();
            String endTime = getElementFactory().getLabel(By.xpath(String.format(str, i, 5)), "").getText();
            String status = getElementFactory().getLabel(By.xpath(String.format(str + "/span", i, 3)), "").getText();

            testList.add(new Test(duration, method, name, startTime, endTime, status));
        }

        return testList;
    }

    public void clickOnHomeLink() {
        homeLink.click();
    }

    public boolean isAdded() {
        int i = emptyCheck.size();
        return (i == 0) ? true : false;
    }
}