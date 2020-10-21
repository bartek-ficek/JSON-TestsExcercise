package isa.jjdd.provider;

import com.google.gson.Gson;
import isa.jjdd.models.ComponentsLogs;
import isa.jjdd.models.Log;
import isa.jjdd.models.LogData;
import isa.jjdd.models.LogsDB;

import javax.annotation.Nonnull;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FromWebLogsProvider extends LogsProvider {

    @Nonnull
    @Override
    public List<LogData> loadLogs(@Nonnull String filePath) {

        File file = new File(filePath);
        InputStream logsFileAsStream = null;
        try {
            logsFileAsStream = new FileInputStream(file);
        } catch (FileNotFoundException | RuntimeException e) {
            e.printStackTrace();
        }
        return parseJsonToListOfLogData(logsFileAsStream);
    }

    @Nonnull
    private List<LogData> parseJsonToListOfLogData(InputStream jsonDataAsStream) {
        String jsonAsString = convertStreamToString(jsonDataAsStream);
        return extractListOfLogDataFromJson(jsonAsString);
    }

    private String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        try {
            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            return stringBuilder.toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nonnull
    private List<LogData> extractListOfLogDataFromJson(String jsonAsString) {
        Gson gson = new Gson();
        LogsDB mainObjectFromJson = gson.fromJson(jsonAsString, LogsDB.class);
        List<LogData> listOfLogs = new ArrayList<>();

        for (ComponentsLogs componentLog : mainObjectFromJson.getComponents_logs()) {
            List<Log> logList = componentLog.getLogs();
            for (Log log : logList) {
                LogData logData = new LogData();
                logData.setId(log.getId());
                logData.setMessage(log.getMessage());
                logData.setTimestamp(LocalDateTime.parse(log.getTimestamp()));
                logData.setComponentName(componentLog.getComponent_name());
                listOfLogs.add(logData);
            }
        }
        return listOfLogs;
    }
}
