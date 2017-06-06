package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Message {
    private String payload;
    private Date createdAt;
    private Double latitude;
    private Double longitude;
    private List<Integer> receiversIds;
    private Integer senderId;
    private Boolean isPublic;
    private Integer id;

    public Message() {
    }

    @JsonProperty
    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @JsonProperty
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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
