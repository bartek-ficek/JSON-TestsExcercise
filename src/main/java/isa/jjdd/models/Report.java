package isa.jjdd.models;

import java.util.List;

public class Report {
    private List<LogData> logData;

    private Report(List<LogData> logData) {
        this.logData = logData;
    }
}
