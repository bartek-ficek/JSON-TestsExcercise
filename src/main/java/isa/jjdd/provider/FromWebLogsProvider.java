package isa.jjdd.provider;

import com.google.gson.Gson;
import isa.jjdd.models.ComponentsLogs;
import isa.jjdd.models.Log;
import isa.jjdd.models.LogData;
import isa.jjdd.models.LogsDB;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parseJsonToLogData(logsFileAsStream);
    }

    @Nonnull
    private List<LogData> parseJsonToLogData(InputStream jsonDataAsStream) {

        String jsonAsString = convertStreamToString(jsonDataAsStream);

        Gson gson = new Gson();
        LogsDB logsDB = gson.fromJson(jsonAsString, LogsDB.class);

        List<LogData> listOfLogs = new ArrayList<>();
        for (ComponentsLogs logComponent : logsDB.getComponents_logs()) {
            List<Log> componentsLogs = logComponent.getLogs();

            for (Log log : componentsLogs) {
                LogData logData = new LogData(log.getId(), LocalDateTime.parse(log.getTimestamp()), logComponent.getComponent_name(), log.getMessage());
                listOfLogs.add(logData);
            }
        }
        return listOfLogs;
    }

    @Nonnull
    private String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        FromWebLogsProvider fromWebLogsProvider = new FromWebLogsProvider();
        System.out.println(fromWebLogsProvider.loadLogs("src/main/resources/logs.json"));
        System.out.println((fromWebLogsProvider.loadLogs("src/main/resources/logs.json")).size());
    }
}
