package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MessageList {

    private List<Message> result;
    private Integer total;
    private Integer offset;

    public MessageList(List<Message> result, Integer total, Integer offset) {
        this.result = result;
        this.total = total;
        this.offset = offset;
    }

    @JsonProperty
    public List<Message> getResult() {
        return result;
    }

    public void setResult(List<Message> result) {
        this.result = result;
    }

    @JsonProperty
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty
    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
