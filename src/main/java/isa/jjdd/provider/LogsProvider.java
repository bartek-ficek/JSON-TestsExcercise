package isa.jjdd.provider;

import isa.jjdd.models.LogData;

import javax.annotation.Nonnull;
import java.util.List;

abstract public class LogsProvider {
    @Nonnull
    abstract public List<LogData> loadLogs(@Nonnull String filePath);
}
