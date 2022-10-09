package dynamicproxy.measure;

import dynamicproxy.measure.external.DatabaseReader;
import dynamicproxy.measure.external.HttpClient;
import dynamicproxy.measure.impl.DatabaseReaderImpl;
import dynamicproxy.measure.impl.HttpClientImpl;
import dynamicproxy.measure.proxy.ProxyFactory;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HttpClient httpClient = ProxyFactory.createProxy(new HttpClientImpl());
        DatabaseReader databaseReader = ProxyFactory.createProxy(new DatabaseReaderImpl());

        useHttpClient(httpClient);
        System.out.println("=================================================");
        useDatabaseReader(databaseReader);
    }

    public static void useHttpClient(HttpClient httpClient) {
        httpClient.initialize();
        String response = httpClient.sendRequest("some request");
        System.out.println("Http response is : " + response);
    }

    public static void useDatabaseReader(DatabaseReader databaseReader) throws InterruptedException {
        int rowsInGamesTable = databaseReader.countRowsInTable("GamesTable");
        System.out.println("There are " + rowsInGamesTable + " rows in GamesTable");
        String[] data = databaseReader.readRow("SELECT * from GamesTable");
        System.out.println("Received " + String.join(" , ", data));
    }

}
