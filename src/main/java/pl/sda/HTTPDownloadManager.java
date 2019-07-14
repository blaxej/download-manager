package pl.sda;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class HTTPDownloadManager implements DownloadManager {


    @Override
    public void download(URL url, Path path) {
        try (CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build()){
            HttpResponse response = closeableHttpClient.execute(new HttpGet(url.toURI()));
            try (InputStream content = response.getEntity().getContent()) {
                Files.copy(content, path);
            }
        } catch (IOException | URISyntaxException e) {
            throw new DownloadManagerException(String.format("Failed to download file %s", url), e);
        }

    }
}
