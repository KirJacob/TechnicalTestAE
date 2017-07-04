package tests.api;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static api.Requests.*;

public class MainApiTest extends BaseApiTest {

    @Test(priority = 1, enabled = true)
    public void getStatuses_apiTest() throws IOException{
        JSONObject result = getHomeStatuses(0);

        Assert.assertTrue(result.has("retweet_count"));
        Assert.assertTrue(result.has("created_at"));
        Assert.assertTrue(result.has("text"));
    }

    @Test(priority = 2, enabled = true)
    public void removeStatus_apiTest() throws IOException {
        //create status for removal and get its id
        String textFor = "this status will be removed 5";
        JSONObject statusForRemove = updateStatus(textFor);
        String id_str = statusForRemove.getString("id_str");

        //remove status
        removeStatus(id_str);

        //check status was really removed
        JSONObject result = getSpecificStatus(id_str);
        Assert.assertTrue(result.toString().contains("No status found with that ID."));
    }

    @Test(priority = 3, enabled = true)
    public void updateStatus_apiTest() throws IOException {
        String textFor = "Mars should be colonized till 2040";
        JSONObject result = updateStatus(textFor);

        Assert.assertTrue(result.has("text"));
        Assert.assertEquals(result.getString("text"), textFor);
    }

    @Test(priority = 4, enabled = true)
    public void statusDuplicationMessage_apiTest() throws IOException {
        //create status for duplication
        String textFor = "Mars should be colonized till 2080";
        updateStatus(textFor);
        JSONObject result = duplicateStatus(textFor);
        System.out.println(result);
        Assert.assertTrue(result.toString().contains("Status is a duplicate."));
    }

    @AfterClass(enabled = true)
    public void clean() throws IOException {
        cleanAllStatuses();
    }
}
