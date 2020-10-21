package isa.jjdd;

import isa.jjdd.models.LogData;
import isa.jjdd.models.Report;
import isa.jjdd.provider.FromWebLogsProvider;
import isa.jjdd.provider.LogsProvider;
import isa.jjdd.repository.LogDataSet;

import java.io.FileNotFoundException;
import java.util.List;

public class Homeworks {
    public static void main(String[] args) throws FileNotFoundException {
        LogsProvider logsProvider = new FromWebLogsProvider();
        String filePath = "src/main/resources/logs.json";

        List<LogData> logs = logsProvider.loadLogs(filePath);
        LogDataSet.getInstance().addAll(logs);

        Report reportComponent = Report.getReportForComponent("database");
        reportComponent.printReport();

        Report reportDate = Report.getReportForData("2020-09-18");
        reportDate.printReport();


    }
}
