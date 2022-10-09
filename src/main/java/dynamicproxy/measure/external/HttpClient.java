package dynamicproxy.measure.external;

public interface HttpClient {
    void initialize();

    String sendRequest(String requet);
}
