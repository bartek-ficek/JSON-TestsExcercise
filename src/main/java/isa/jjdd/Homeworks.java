package isa.jjdd;

import isa.jjdd.models.LogData;
import isa.jjdd.provider.LogsProvider;
import isa.jjdd.repository.LogDataSet;

import java.util.List;

public class Homeworks {
    public static void main(String[] args) {
        LogsProvider logsProvider = null;
        String filePath = "";

        List<LogData> logs = logsProvider.loadLogs(filePath);
        LogDataSet.getInstance().addAll(logs);

        // TODO: generate report for database component
        // print report for database component

        // TODO: generate report from 18.09
        // print report from 18.09
    }
}
