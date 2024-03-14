package logic;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import io.cucumber.datatable.DataTable;

import framework.MySession;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.junit.Assert;
import framework.CreateDB;

public class databaseTest {

    WebDriver driver;
    CreateDB dbO;

    public databaseTest() throws InterruptedException, IOException {
        driver = MySession.getWebDriver();
        dbO = new CreateDB();

    }

    @Given("^user fetches data from \"([^\"]*)\"$")
    public void fetchData(String tName) throws Throwable, Exception {
        Statement st = dbO.dbConnection();
        ResultSet rs = st.executeQuery("select * from " + tName + ";");
        while (rs.next()) {
            Integer myEmpID = rs.getInt(1);
            String myName = rs.getString(2);
            String mygrade = rs.getString(3);
            System.out.println(myEmpID + " " + myName + " " + mygrade);
        }
        st.close();
    }

    @Given("^user adds new row into table \"([^\"]*)\" with values$")
    public void createData(String tName, DataTable createData) throws Throwable, Exception, InterruptedException {

        List<List<String>> data = createData.cells();
        int id = Integer.parseInt(data.get(1).get(0));
        String eName = data.get(1).get(1);
        String grade = data.get(1).get(2);
        Statement st = dbO.dbConnection();
        String query = "insert into " + tName + " values(" + id + ",'" + eName + "','" + grade + "');";
        System.out.println(query);
        st.execute(query);
        st.close();
    }
}
