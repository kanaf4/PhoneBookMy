package tests;

import manager.MyDataProvider;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTest extends TestBase{

@BeforeMethod
public void precondition(){
    if(app.userHelper().isLogged()){
        app.userHelper().logout();
    }
}



    @Test
    public void loginTestPositiveNewContact(){
      app.userHelper().click(By.xpath("//a[.='LOGIN']"));
        //app.userHelper().click(By.xpath(""));
        app.userHelper().type(By.xpath("//input[@placeholder='Email']"),"noa@gmail.com");
        app.userHelper().type(By.xpath("//input[@placeholder='Password']"),"Nnoa12345$");
        app.userHelper().click(By.xpath("//button[.=' Login']"));
        app.userHelper().pause(2000);
     /*   String loginNC=getText(By.xpath("//div//h1[.=' No Contacts here!']"));
        Assert.assertEquals(loginNC,"No Contacts here!");*/

        String loginS = app.userHelper().getText(By.xpath("//a[.='ADD']"));

        Assert.assertEquals(loginS,"ADD");

    }

    @Test
    public void loginTestWithWrongPassword(){
        User user= new User()
                .withEmail("noa@gmail.com")
                .withPassword("Nnoa12345");

        app.userHelper().openLoginRegForm();
        app.userHelper().fillLoginRegForm(user);
        app.userHelper().clickLoginButton();

        app.userHelper().acceptAlert();
        Assert.assertFalse(app.userHelper().isLogged());

    }

    @Test
    public void loginTestBase(){
        User user= new User()
                .withEmail("noa@gmail.com")
                .withPassword("Nnoa12345$");

        app.userHelper().openLoginRegForm();
        app.userHelper().fillLoginRegForm(user);
        app.userHelper().clickLoginButton();

        Assert.assertTrue(app.userHelper().isLogged());

    }
    @DataProvider
    public Iterator <Object[] > validDataLogin(){
        List<Object[]> list= new ArrayList<>();
        list.add(new Object[]{"noa@gmail.com","Nnoa12345$"});
        list.add(new Object[]{"sonya@gmail.com","Ss12345$"});
        list.add(new Object[]{"noa@gmail.com","Nnoa12345$"});

        return list.iterator();
    }




    @Test(dataProvider = "validDataLogin")
    public void loginTestDataProvider(String email, String password){
        User user= new User()
                .withEmail(email)
                .withPassword(password);
        app.userHelper().openLoginRegForm();
        app.userHelper().fillLoginRegForm(user);
        app.userHelper().clickLoginButton();
        app.userHelper().pause(2000);
        Assert.assertTrue(app.userHelper().isLogged());

    }
    @Test(dataProvider = "validLoginDataClassDP", dataProviderClass = MyDataProvider.class)
    public void loginTestDataProviderClass(String email, String password){
        User user= new User()
                .withEmail(email)
                .withPassword(password);
        app.userHelper().openLoginRegForm();
        app.userHelper().fillLoginRegForm(user);
        app.userHelper().clickLoginButton();
        app.userHelper().pause(2000);
        Assert.assertTrue(app.userHelper().isLogged());

    }
    @Test(dataProvider = "dataFileCSV", dataProviderClass = MyDataProvider.class)
    public void loginTestDP_CSV(User user){


        app.userHelper().openLoginRegForm();
        app.userHelper().fillLoginRegForm(user);
        app.userHelper().clickLoginButton();

        Assert.assertTrue(app.userHelper().isLogged());

    }
}
