package pl.sda;

import lombok.Value;

import java.net.URL;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class DelegatingTaggingManager implements TaggingDownloadManager {

    private DownloadManager downloadManager;
    private Collection<TagPathWrapper> taggedFiles;

    public DelegatingTaggingManager(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
        taggedFiles = new ArrayList<>();
    }

    @Override
    public void downloadAndTag(URL source, Path target, String... tag) {
        downloadManager.download(source, target);
        taggedFiles.add(new TagPathWrapper(new HashSet<>(Arrays.asList(tag)), target));
    }

    @Override
    public Iterable<Path> findByTag(String tag) {
        return taggedFiles.stream().filter(tagPathWrapper -> tagPathWrapper.getTags()
                .contains(tag))
                .map(TagPathWrapper::getFilePath)
                .collect(Collectors.toList());
    }

    @Value
    private class TagPathWrapper {
        private Set<String> tags;
        private Path filePath;
    }
}
