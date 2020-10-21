package isa.jjdd.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class LogData {
    private long id;
    private LocalDateTime timestamp;
    private String componentName;
    private String message;

    public LogData() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LogData{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", componentName='" + componentName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogData logData = (LogData) o;
        return id == logData.id &&
                timestamp.equals(logData.timestamp) &&
                componentName.equals(logData.componentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, componentName);
    }
}
