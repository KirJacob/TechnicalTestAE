package api;

import okhttp3.Response;
import org.apache.commons.httpclient.util.URIUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import helpers.AccessData;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;

public class Requests extends Helpers implements AccessData {

    public static JSONObject getHomeStatuses(int index) throws IOException {
        System.out.println(">>> Getting HOME statuses");

        Response response = Helpers.request.get(apiURL + "/1.1/" + "statuses/home_timeline.json");
        String responseBody = response.body().string();

        if (response.code() == 200) {
            System.out.println(response.code() + " " + response.message());
            System.out.println("-----------------------");
            JSONArray responseJSON = new JSONArray(responseBody);
            JSONObject result = responseJSON.getJSONObject(index);

            System.out.println("id_str=" + result.getString("id_str"));
            System.out.println("created_at=" + result.getString("created_at"));
            System.out.println("text=" + result.getString("text"));
            System.out.println("-----------------------");

            return result;
        } else {
            failTest(responseBody, response.code(), response.message());
            return new JSONObject(responseBody + "\n" + response.code() + "\n" + response.message());
        }
    }

    public static JSONObject updateStatus(String text) throws  IOException{
        System.out.println(">>> Create status with defined text");

        String textConverted = URIUtil.encodeQuery(text);
        Response response = Helpers.request.post(apiURL + "/1.1/statuses/update.json?status=" +  textConverted, "");
        String responseBody = response.body().string();
        Assert.assertEquals(response.code(),200);

        if (response.code() == 200) {
            JSONObject result = new JSONObject(responseBody);
            System.out.println(response.code() + " " + response.message());
            System.out.println("-----------------------");
            System.out.println("id_str=" + result.getString("id_str"));
            System.out.println("created_at=" + result.getString("created_at"));
            System.out.println("text=" + result.getString("text"));
            System.out.println("-----------------------");
            return result;
        } else {
            failTest(responseBody, response.code(), response.message());
            return new JSONObject(responseBody + "\n" + response.code() + "\n" + response.message());
        }
    }

    public static JSONObject removeStatus(String id_str) throws IOException {
        System.out.println(">>> Remove status with id=" + id_str);

        Response response = Helpers.request.post(apiURL + "/1.1/statuses/destroy/" + id_str + ".json", "");
        String responseBody = response.body().string();
        JSONObject result = new JSONObject(responseBody);
        if (response.code() != 200) {
            result = new JSONObject(responseBody + "\n" + response.code() + "\n" + response.message());
        }
        return result;
    }

    public static JSONObject getSpecificStatus(String id_str) throws IOException {
        System.out.println(">>> Get status with id=" + id_str);

        Response response = Helpers.request.get(apiURL + "/1.1/statuses/show.json?id=" + id_str);
        String responseBody = response.body().string();
        System.out.println(responseBody);
        JSONObject result =  new JSONObject(responseBody);
        if (response.code() != 200) {
            result = new JSONObject(responseBody + "\n" + response.code() + "\n" + response.message());
        }
        return result;
    }

    public static JSONObject duplicateStatus(String text) throws  IOException{
        System.out.println(">>> Duplicate status with defined text");

        String textConverted = URIUtil.encodeQuery(text);
        Response response = Helpers.request.post(apiURL + "/1.1/statuses/update.json?status=" +  textConverted, "");
        String responseBody = response.body().string();
        JSONObject result = new JSONObject(responseBody);

        Assert.assertEquals(response.code(),403);
        if (response.code() != 200) {
            result = new JSONObject(responseBody + "\n" + response.code() + "\n" + response.message());
        }
        return result;
    }
    public static ArrayList getUserStatusesCollection() throws IOException {
        System.out.println(">>> Getting All statuses id's");

        ArrayList result = new ArrayList();

        Response response = Helpers.request.get(apiURL + "/1.1/" + "statuses/user_timeline.json");
        String responseBody = response.body().string();
        JSONArray responseJSON = new JSONArray(responseBody);

        int size = responseJSON.length();
        String tempId = null;

        for (int i = 0; i < size; i++){
            tempId = responseJSON.getJSONObject(i).getString("id_str");
            result.add(tempId);
        }

        return result;
    }

    public static JSONObject getUserStatuses(int index) throws IOException {
        System.out.println(">>> Getting USER statuses");

        Response response = Helpers.request.get(apiURL + "/1.1/" + "statuses/user_timeline.json");
        String responseBody = response.body().string();

        if (response.code() == 200) {
            System.out.println(response.code() + " " + response.message());
            System.out.println("-----------------------");
            JSONArray responseJSON = new JSONArray(responseBody);
            JSONObject result = responseJSON.getJSONObject(index);

            System.out.println("id_str=" + result.getString("id_str"));
            System.out.println("created_at=" + result.getString("created_at"));
            System.out.println("text=" + result.getString("text"));
            System.out.println("-----------------------");

            return result;
        } else {
            failTest(responseBody, response.code(), response.message());
            return new JSONObject(responseBody + "\n" + response.code() + "\n" + response.message());
        }
    }
}
