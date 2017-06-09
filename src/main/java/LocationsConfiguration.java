import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class LocationsConfiguration extends Configuration {

    private Double distancePrivate;
    private Double distancePublic;

    public LocationsConfiguration() {
    }

    @JsonProperty
    public Double getDistancePrivate() {
        return distancePrivate;
    }

    @JsonProperty
    public void setDistancePrivate(Double distancePrivate) {
        this.distancePrivate = distancePrivate;
    }

    @JsonProperty
    public Double getDistancePublic() {
        return distancePublic;
    }

    @JsonProperty
    public void setDistancePublic(Double distancePublic) {
        this.distancePublic = distancePublic;
    }
}