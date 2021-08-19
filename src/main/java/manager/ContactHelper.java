package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void openFormContact() {
        click(By.xpath("//a[.='ADD']"));
    }

    public void saveContact() {
        click(By.xpath("//button/b"));
    }

    public void fillFormContact(Contact contact) {
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"), contact.getLastname());
        type(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("[placeholder='description']"), contact.getDescription());
    }

    public boolean isContactAdded(String phone) {
        List<WebElement> contacts = wd.findElements(By.xpath("//h3"));
        for (WebElement el:contacts) {
            if(el.getText().contains(phone)){
                System.out.print(el.getText());
                return true;
            }

        }

        return false;
    }

    public void removeOneContact() {
        WebElement contact = wd.findElement(By.cssSelector(".contact-item_card__2SOIM"));
        contact.click();
        click(By.xpath("//button[.='Remove']"));
    }

    public void removeAllContacts() {
     // List<WebElement> contacts = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
//        for (WebElement el:contacts) {
//            el.click();
//            click(By.xpath("//button[.='Remove']"));
//        }

        while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size()!=0){
            removeOneContact();
            pause(2000);
        }
    }
}
