package isa.jjdd.models;

import com.google.gson.Gson;

import java.time.LocalDateTime;

public class Log {
    private long id;
    private String timestamp;
    private String message;

    public Log(long id, String timestamp, String component_name, String message) {
    }

    public long getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

