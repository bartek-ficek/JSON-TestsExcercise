package isa.jjdd.repository;

import isa.jjdd.models.LogData;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LogDataSet implements Collection<LogData> {
    private static LogDataSet INSTANCE;
    Set<LogData> setOfDataLogs = new HashSet<>();

    public LogDataSet getByComponentName(String componentName) {
        LogDataSet logDataSet = new LogDataSet();
        logDataSet.addAll(setOfDataLogs.stream().filter(e->e.getComponentName().equals(componentName)).collect(Collectors.toSet()));
        return logDataSet;
    }

    public LogDataSet getByDate(LocalDate logDate){
        LogDataSet logDataSet = new LogDataSet();
        logDataSet.addAll(setOfDataLogs.stream().filter(e->e.getTimestamp().equals(logDate)).collect(Collectors.toSet()));
        return logDataSet;
    }

    private LogDataSet() {
    }

    public static LogDataSet getInstance() {
        if (INSTANCE == null) {
            synchronized (LogDataSet.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LogDataSet();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public boolean add(LogData logData) {
        if (logData != null) {
            setOfDataLogs.add(logData);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(@Nonnull Collection<? extends LogData> c) {
        if (!c.isEmpty()) {
            for (LogData logData : c) {
                if (logData != null) {
                    setOfDataLogs.add(logData);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        setOfDataLogs.clear();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("No need to implement this method at this point");
    }

    @Override
    public boolean containsAll(@Nonnull Collection<?> c) {
        throw new UnsupportedOperationException("No need to implement this method at this point");
    }

    @Override
    public boolean isEmpty() {
        return setOfDataLogs.isEmpty();
    }

    @Override
    @Nonnull
    public Iterator<LogData> iterator() {
        return setOfDataLogs.iterator();
    }



    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("No need to implement this method at this point");
    }

    @Override
    public boolean removeAll(@Nonnull Collection<?> c) {
        throw new UnsupportedOperationException("No need to implement this method at this point");
    }

    @Override
    public boolean retainAll(@Nonnull Collection<?> c) {
        throw new UnsupportedOperationException("No need to implement this method at this point");
    }

    @Override
    public int size() {
        return setOfDataLogs.size();
    }

    @Override
    @Nonnull
    public Object[] toArray() {
        throw new UnsupportedOperationException("No need to implement this method at this point");
    }

    @Override
    @Nonnull
    public <T> T[] toArray(@Nonnull T[] a) {
        throw new UnsupportedOperationException("No need to implement this method at this point");
    }
}
