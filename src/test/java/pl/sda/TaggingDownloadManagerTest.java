package pl.sda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TaggingDownloadManagerTest {

    @DisplayName("given URL and tags" +
            "when file is downloaded" +
            "then file is tagged with given tags")
    @Test
    void shouldTagFile() throws MalformedURLException {
        //given
        DownloadManager downloadManager = mock(DownloadManager.class);
        TaggingDownloadManager taggingDownloadManager = new DelegatingTaggingManager(downloadManager);
        Path filePath = Paths.get("path", "file");
        URL sourceURL = new URL("http://localhost:8080/file");

        //when
        taggingDownloadManager.downloadAndTag(
                sourceURL, filePath
                , "cat", "jpg");

        //then
        assertThat(taggingDownloadManager.findByTag("cat")).contains(filePath);
        verify(downloadManager, times(1)).download(sourceURL, filePath);
    }
}