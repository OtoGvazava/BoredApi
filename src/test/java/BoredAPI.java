import api.endpoint.BoredApiMockEndpoints;
import api.request.boredapi.ActivityRequest;
import api.request.boredapimock.ActivitiesRequest;
import api.response.boredapi.ActivityResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.Configs;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class BoredAPI {
    ActivityRequest activityRequest;
    WireMockServer wireMockServer;
    Gson gson;
    ActivitiesRequest activitiesRequest;

    @BeforeClass
    public void setupMock() {
        activityRequest = new ActivityRequest();
        wireMockServer = new WireMockServer(Configs.BORED_API_MOCK_PORT);
        wireMockServer.start();
        gson = new Gson();
        activitiesRequest = new ActivitiesRequest();

        List<ActivityResponse> activities = new ArrayList<>(Collections.emptyList());

        while (activities.size() != 20) {
            Response response = activityRequest.request();
            if (response.statusCode() == HttpStatus.SC_OK) {
                activities.add(gson.fromJson(response.body().asString(), ActivityResponse.class));
            }
        }

        Type type = new TypeToken<List<ActivityResponse>>(){}.getType();
        String activitiesString = gson.toJson(activities, type);

        wireMockServer.stubFor(get(urlEqualTo(BoredApiMockEndpoints.ACTIVITIES))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(activitiesString)));
    }

    @Test
    public void getActivities() {
        String activitiesStr = activitiesRequest.request().body().asString();
        Type type = new TypeToken<List<ActivityResponse>>(){}.getType();
        List<ActivityResponse> activities = gson.fromJson(activitiesStr, type);

        activities = activities.stream().filter(activity -> activity.getPrice() > 0)
                .sorted(Comparator.comparingDouble(ActivityResponse::getAccessibility))
                .toList();

        activities.forEach(System.out::println);
    }

    @AfterClass
    public void tearDown() {
        wireMockServer.stop();
    }
}
