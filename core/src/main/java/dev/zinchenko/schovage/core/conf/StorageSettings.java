package dev.zinchenko.schovage.core.conf;


public final class StorageSettings {
    private String basePath;

    public StorageSettings(String basePath) {
        this.basePath = basePath;
    }

    public StorageSettings() {
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
