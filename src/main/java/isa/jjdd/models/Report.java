package isa.jjdd.models;

import isa.jjdd.repository.LogDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private static final Logger LOGGER = LoggerFactory.getLogger("CONSOLE_OUT");
    private List<LogData> logData;

    private Report(List<LogData> logData) {
        this.logData = logData;
    }

    public static Report getReportForComponent(String componentName) {
        ArrayList<LogData> list = new ArrayList();
        list.addAll((LogDataSet.getInstance().getByComponentName(componentName)));
        Report report = new Report(list);
        return report;
    }

    public static Report getReportForData(String date) {
        ArrayList<LogData> list = new ArrayList<>(LogDataSet.getInstance().getByDate(LocalDate.parse(date)));
        Report report = new Report(list);
        return report;
    }

    public void printReport() {
        for (LogData logData : logData) {
            LOGGER.info("{}\n", logData);
        }
    }


}
