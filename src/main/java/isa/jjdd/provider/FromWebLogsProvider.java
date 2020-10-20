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
    public List<LogData> loadLogs(@Nonnull String filePath) throws FileNotFoundException {
        StringBuilder jsonLogs = new StringBuilder();
        File file = new File(filePath);
        InputStream jsonAsStream = new FileInputStream(file);
        Scanner scanner = new Scanner(jsonAsStream);
        while (scanner.hasNextLine()) {
            jsonLogs.append(scanner.nextLine());
        }
        String jsonAsString = jsonLogs.toString();

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

    public static void main(String[] args) throws FileNotFoundException {
        FromWebLogsProvider fromWebLogsProvider = new FromWebLogsProvider();
        System.out.println(fromWebLogsProvider.loadLogs("src/main/resources/logs.json"));
        System.out.println((fromWebLogsProvider.loadLogs("src/main/resources/logs.json")).size());
    }
}
