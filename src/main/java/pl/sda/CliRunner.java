package pl.sda;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class CliRunner {

    private TaggingDownloadManager taggingDownloadManager;

    public CliRunner(TaggingDownloadManager taggingDownloadManager) {

        this.taggingDownloadManager = taggingDownloadManager;
    }

    public void run(String[] args) {
        try {
            URL url = new URL(extractValue("url", args));
            Path path = Paths.get(extractValue("path", args));
            String[] tags = extractValue("tags", args).split(",");
            taggingDownloadManager.downloadAndTag(url, path, tags);
        } catch (MalformedURLException e) {
           throw new IllegalArgumentException("Bad URL");
        }
    }

    private String extractValue(String key, String[] args) {
        return Arrays.stream(args).filter(string -> string.contains("--" + key + "="))
                .findAny()
                .map(s -> s.split("=")[1])
                .orElseThrow(() -> new IllegalArgumentException("There's no valid argument."));
    }
}
