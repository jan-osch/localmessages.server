package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserList {
    private List<User> result;
    private Integer total;
    private Integer offset;

    public UserList(List<User> result, Integer total, Integer offset) {
        this.result = result;
        this.total = total;
        this.offset = offset;
    }

    @JsonProperty
    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
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
