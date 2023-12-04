package api.request;

import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.URL;

public class Request {
    protected URL url;
    protected Gson gson;

    @SneakyThrows
    protected Request(String host, String endpoint) {
        url = URI.create(host + endpoint).toURL();
        gson = new Gson();
    }

    @SneakyThrows
    protected Request(String host, int port, String endpoint) {
        url = URI.create(host + ":" + port + endpoint).toURL();
        gson = new Gson();
    }
}
