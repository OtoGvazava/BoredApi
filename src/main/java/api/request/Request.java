package api.request;

import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.URL;

public class Request {
    protected URL url;

    @SneakyThrows
    protected Request(String host, String endpoint) {
        url = URI.create(host + endpoint).toURL();
    }

    @SneakyThrows
    protected Request(String host, int port, String endpoint) {
        url = URI.create(host + ":" + port + endpoint).toURL();
    }
}
