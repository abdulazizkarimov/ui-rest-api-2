package page;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddForm extends Form {
    private ITextBox projectNameInput = getElementFactory().getTextBox(By.xpath("//input[contains(@id, 'projectName')]"), "Project Name Input");
    private IButton saveBtn = getElementFactory().getButton(By.xpath("//button[contains(@type, 'submit')]"), "Save Button");
    private ILabel msgLabel = getElementFactory().getLabel(By.xpath("//div[contains(@class, 'alert-success')]"), "Message Label");

    public AddForm() {
        super(By.xpath("//form[contains(@id, 'addProjectForm')]"), "Add Form");
    }

    public void enterProjectName(String projectName) {
        projectNameInput.clearAndType(projectName);
    }

    public void save() {
        saveBtn.click();
    }

    public boolean isSaved(String projectName) {
        String s1 = "Project " + projectName + " saved";
        String s2 = msgLabel.getText();
        return s1.equals(s2) ? true : false;
    }
}
