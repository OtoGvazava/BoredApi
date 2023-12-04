package data;

import utils.PropsReader;

public class Configs {
    public static final String BORED_API_HOST = PropsReader.getInstance().getProperty("bored.api.host");
    public static final String BORED_API_MOCK_HOST = PropsReader.getInstance().getProperty("bored.mock.api.host");
    public static final Integer BORED_API_MOCK_PORT = Integer.parseInt(PropsReader.getInstance().getProperty("bored.mock.api.port"));
}
