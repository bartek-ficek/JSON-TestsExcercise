package isa.jjdd;

import isa.jjdd.models.LogData;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class TestUtils {
    private static final Long MIN_TIMESTAMP = LocalDateTime.of(2020, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
    private static final Long MAX_TIMESTAMP = LocalDateTime.of(2020, 6, 30, 23, 59).toEpochSecond(ZoneOffset.UTC);

    private TestUtils() {
    }

    public static LogData getRandomLogData() {
        LogData logData = new LogData();

        logData.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));

        byte[] randomString = new byte[10];
        ThreadLocalRandom.current().nextBytes(randomString);
        logData.setComponentName(new String(randomString, StandardCharsets.UTF_8));

        long randomTimestamp = ThreadLocalRandom.current().nextLong(MIN_TIMESTAMP, MAX_TIMESTAMP);
        logData.setTimestamp(LocalDateTime.ofEpochSecond(randomTimestamp, 0, ZoneOffset.UTC));

        return logData;
    }

    public static Collection<LogData> getRandomLogDataSet(int size) {
        return IntStream.range(0, size).mapToObj(e -> getRandomLogData()).collect(Collectors.toSet());
    }
}
