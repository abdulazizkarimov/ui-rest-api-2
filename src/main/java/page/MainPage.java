package page;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends Form {
    private ILabel versionLabel = getElementFactory().getLabel(By.xpath("//span[contains(text(), 'Version:')]"), "Version Label");
    private ILink addBtn = getElementFactory().getLink(By.xpath("//a[contains(@href, 'addProject')]"), "Add Button");
    private List<ILink> projectLinks = getElementFactory().findElements(By.xpath("//a[contains(@class, 'list-group-item')]"), ElementType.LINK);

    public MainPage() {
        super(By.xpath("//a[contains(@href, '/web/projects')]"), "Main Page");
    }

    public String getFooterVar() {
        String str = versionLabel.getText();
        return str.split(" ")[1];
    }

    public void clickProjectByName(String name) {
        getElementFactory().getLink(By.linkText(name), name + " Link").click();
    }

    public String getProjectIdByName(String name) {
        return getElementFactory().getLink(By.linkText(name), name + " Link").getHref().split("=")[1];
    }

    public void clickOnAddBtn() {
        addBtn.click();
    }

    public boolean projectExists(String projectName) {
        List<String> arr = new ArrayList<>();

        for (ILink link : projectLinks) {
            arr.add(link.getText());
        }

        return arr.contains(projectName) ? true : false;
    }
}