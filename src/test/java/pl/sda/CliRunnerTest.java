package pl.sda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;

public class CliRunnerTest {

    @DisplayName("given args" +
            "when CliRunner run" +
            "then args are passed to tagging downlad manager")
    @Test
    void shouldPassArguments() throws MalformedURLException {
        //given
        TaggingDownloadManager taggingDownloadManager = mock(TaggingDownloadManager.class);
        CliRunner cliRunner = new CliRunner(taggingDownloadManager);
        URL expectedURL = new URL("http://localhost/file");
        Path expectedPath = Paths.get("path", "to", "file");
        String[] expectedTags = {"tag0", "tag1", "tag2"};
        String[] args = {
                "--url=http://localhost/file",
                "--path=path/to/file",
                "--tags=tag0,tag1,tag2"};

        //when
        cliRunner.run(args);

        //then
        verify(taggingDownloadManager, times(1))
                .downloadAndTag(expectedURL, expectedPath, expectedTags);
    }
}
