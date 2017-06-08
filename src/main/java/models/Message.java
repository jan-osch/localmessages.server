package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class Message {
    private String payload;
    private LocalDateTime createdAt;
    private Double latitude;
    private Double longitude;
    private List<Integer> receiversIds;
    private Integer senderId;
    private Boolean isPublic;
    private Integer id;

    public Message() {
    }


    public Message(String payload, LocalDateTime createdAt, Double latitude, Double longitude, List<Integer> receiversIds, Integer senderId, Boolean isPublic, Integer id) {
        this.payload = payload;
        this.createdAt = createdAt;
        this.latitude = latitude;
        this.longitude = longitude;
        this.receiversIds = receiversIds;
        this.senderId = senderId;
        this.isPublic = isPublic;
        this.id = id;
    }

    @JsonProperty
    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @JsonProperty
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty
    public List<Integer> getReceiversIds() {
        return receiversIds;
    }

    public void setReceiversIds(List<Integer> receiversIds) {
        this.receiversIds = receiversIds;
    }

    @JsonProperty
    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    @JsonProperty
    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    @JsonProperty
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
