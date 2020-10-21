package isa.jjdd.models;

import isa.jjdd.repository.LogDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private static final Logger LOGGER = LoggerFactory.getLogger("CONSOLE_OUT");
    private final List<LogData> logData;

    private Report(List<LogData> logData) {
        this.logData = logData;
    }

    public static Report getReportForComponent(String componentName) {
        ArrayList<LogData> list = new ArrayList<>((LogDataSet.getInstance().getByComponentName(componentName)));
        return new Report(list);
    }

    public static Report getReportForData(String date) {
        ArrayList<LogData> list = new ArrayList<>(LogDataSet.getInstance().getByDate(LocalDate.parse(date)));
        return new Report(list);
    }

    public void printReport() {
        for (LogData data : logData) {
            LOGGER.info("{}\n", data);
        }
    }


}
