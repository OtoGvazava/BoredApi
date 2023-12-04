package api.request.boredapimock;

import api.endpoint.BoredApiMockEndpoints;
import api.request.Request;
import data.Configs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ActivitiesRequest extends Request {
    public ActivitiesRequest() {
        super(Configs.BORED_API_MOCK_HOST, Configs.BORED_API_MOCK_PORT, BoredApiMockEndpoints.ACTIVITIES);
    }

    public Response request() {
        return given().contentType(ContentType.JSON)
                .get(url)
                .then()
                .extract()
                .response();
    }
}
