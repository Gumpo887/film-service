package io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects;

public enum Format {

    DVD(480),
    BLURAY(1080),
    UHD_4K(2160);

    private final int maxResolution;

    Format(int maxResolution) {
        this.maxResolution = maxResolution;
    }

    public boolean isHighDefinition() {
        return maxResolution >= 1080;
    }

    public int maxResolution() {
        return maxResolution;
    }

}
