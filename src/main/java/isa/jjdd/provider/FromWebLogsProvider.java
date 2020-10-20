package isa.jjdd.provider;

import isa.jjdd.models.LogData;

import javax.annotation.Nonnull;
import java.util.List;

public class FromWebLogsProvider extends LogsProvider {
    @Nonnull
    @Override
    public List<LogData> loadLogs(@Nonnull String filePath) {
        return null;
    }
}
