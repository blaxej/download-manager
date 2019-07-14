package pl.sda;

import java.net.URL;
import java.nio.file.Path;

public interface TaggingDownloadManager {

    void downloadAndTag(URL source, Path target, String... tag);

    Iterable<Path> findByTag(String tag);
}
