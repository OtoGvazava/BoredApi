package api.request.boredapi;

import api.endpoint.BoredApiEndpoints;
import api.request.Request;
import data.Configs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class ActivityRequest extends Request {

    public ActivityRequest() {
        super(Configs.BORED_API_HOST, BoredApiEndpoints.ACTIVITY);
    }

    public Response request() {
        return given().contentType(ContentType.JSON)
                .get(url)
                .then()
                .extract()
                .response();
    }
}
