package pl.sda;

import java.net.URL;
import java.nio.file.Path;

public interface DownloadManager {

    void download(URL url, Path path) throws DownloadManagerException;
}
