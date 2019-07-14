package pl.sda;

public class Main {
    public static void main(String[] args) {
        new CliRunner(
                new DelegatingTaggingManager(
                        new HTTPDownloadManager())).run(args);
    }
}
