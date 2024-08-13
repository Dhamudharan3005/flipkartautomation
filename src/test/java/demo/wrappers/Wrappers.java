package demo.wrappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import demo.TestCases;

import java.lang.reflect.Array;
import java.time.Duration;

public class Wrappers {
    static ChromeDriver driver;
    /*
     * Write your selenium wrappers here
     */
    public Wrappers(ChromeDriver driver){
        
        Wrappers.driver=driver;
    }
      
    
   public static
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
    



//Method to search text in search bar and click search
public static void Searchtext(String searchtxt,String boxpath){
    try {
        //finding search text box and search button
        WebElement search_box=driver.findElement(By.xpath(boxpath));
        WebElement searchbtn=driver.findElement(By.xpath("//button[@type='submit']"));
        
        //Making sure that searchbox is clear before typing,to use the method repeatedly
        search_box.clear();
       
        Thread.sleep(5000);
        //typing text inside search box
        search_box.sendKeys(searchtxt);
        //clicking on search button
        searchbtn.click();
        //waiting for url to change and returning the result
        // return wait.until(ExpectedConditions.urlContains(searchtxt));
        
        
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Exception while Searching for text "+e.getMessage());
        //Marking test failed due to excemption 
        // return false;
    }
}

public static boolean click_on_tab(String Tab_choice){
    try {
        List<WebElement> menu_tabs=driver.findElements(By.xpath("//div[@class='zg-M3Z']"));
        for (WebElement webElement : menu_tabs) {
            if(webElement.getText().contains(Tab_choice)){
                webElement.click();
                Thread.sleep(5000);
               return true;
            }
            
        }
        return false;
        
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Excemption while clicking on sortby tab"+e.getMessage());
        //Marking test failed due to excemption
        return false;
    }
}

//Method to print the count of items with specified rating 
public static int ratingscount(String rating){
    try {
        //Initializing result variable
        int result=0;
    //get the parent element of each product
    List<WebElement> Product_parent=driver.findElements(By.xpath("//div[@class='tUxRFH']"));
    //Iterate through each parent element 
    for (WebElement webElement : Product_parent) {
        //finding rating elemt of each parent element
        WebElement Product_rating=webElement.findElement(By.xpath(".//div[@class='XQDdHH']"));
        //getting their value as a int
        String rat=Product_rating.getText();
        //cheking for condition
        if(rat.contains(rating)){
            //incrementing result whenever specified condition is true
            result++;
        }
    }
    //returning result generated / Initialized value
    return result;
        
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Excemption while getting count of ratings "+e.getMessage());
        //Marking test faild due to excemption and no resluts generated
        return 0;
    }  
}
//Method to check discounts and retunr titles and discount percentage of products
public static void Check_Discount(int Ex_discount_percent){
    try {
         //get the parent element of each product
         List<WebElement> Product_parent=driver.findElements(By.xpath("//div[@class='yKfJKb row']"));
         //Iterate through each parent element 
         for (WebElement webElement : Product_parent) {
            //getting dicount text element
            WebElement dscnt_element=webElement.findElement(By.xpath(".//div[@class='UkUFwK']"));
            //splitting the discount percentage
            String[] temp=dscnt_element.getText().split("%");
            //getting discount as integer
            int discount_percentage=Integer.parseInt(temp[0]);
            //checking if discount percentage is more than expected percentage 
            if(discount_percentage>Ex_discount_percent){
                //finding the title of the products
                WebElement Product_Title=webElement.findElement(By.xpath(".//div[@class='KzDlHZ']"));
                //printing with details
                System.out.println(Product_Title.getText()+"and discount is :"+discount_percentage+"%");
            }
         }
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Exception while checking discount percentage"+e.getMessage());
    }
}

public static void reviewSort(int item) throws InterruptedException {
    try{
        int count = 0;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(
                        By.xpath("//div[@class='slAVV4']//div[@class='_5OesEi afFzxY']/span[@class='Wphh3N']")));
         //getting all reviews
        List<WebElement> reviewsList = driver
                .findElements(By.xpath("//div[@class='slAVV4']//div[@class='_5OesEi afFzxY']/span[@class='Wphh3N']"));
        List<String> reviewTextList = new ArrayList<>();
        //iterating throw the list
        for (WebElement reviewsListItem : reviewsList) {
            String reviewText = reviewsListItem.getText();
            reviewTextList.add(reviewText);
        }
        Collections.sort(reviewTextList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Remove parentheses and commas
                String num1 = s1.replaceAll("[^\\d]", "");
                String num2 = s2.replaceAll("[^\\d]", "");

                // Compare the numeric values
                return Integer.compare(Integer.parseInt(num2), Integer.parseInt(num1));
            }
        });
        for(String reviewstText : reviewTextList) {
            if (count < item) {
                WebElement imgUrl = driver.findElement(
                        By.xpath("//span[text()='" + reviewstText + "']//parent::div/preceding-sibling::a//img"));
                String imgUrlText = imgUrl.getAttribute("src");
                System.out.println("Url : " + imgUrlText);
                WebElement title = driver.findElement(By.xpath("//span[text()='" + reviewstText
                        + "']//parent::div[@class='_5OesEi afFzxY']//parent::div//a[@class='wjcEIp']"));
                String titleText = title.getText();
                System.out.println("title is : " + titleText);
                System.out.println("The number of reviews are : " + reviewstText);
                count++;
            }
        }
    }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Excemption while getting Title of Largest Reviewed items"+e.getMessage());
        }
    }



}
