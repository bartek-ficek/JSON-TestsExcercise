package isa.jjdd.models;

public class Log {
    private long id;
    private String timestamp;
    private String message;

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

