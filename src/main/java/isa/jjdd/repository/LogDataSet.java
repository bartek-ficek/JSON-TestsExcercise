package isa.jjdd.repository;

import isa.jjdd.models.LogData;

import javax.annotation.Nonnull;
import java.util.*;

public class LogDataSet implements Collection<LogData> {
    private static LogDataSet INSTANCE;

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
        throw new UnsupportedOperationException("Method add is not implemented");
    }

    @Override
    public boolean addAll(@Nonnull Collection<? extends LogData> c) {
        throw new UnsupportedOperationException("Method addAll is not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Method clear is not implemented");
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
        throw new UnsupportedOperationException("Method isEmpty is not implemented");
    }

    @Override
    @Nonnull
    public Iterator<LogData> iterator() {
        throw new UnsupportedOperationException("Method iterator is not implemented");
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
        throw new UnsupportedOperationException("Method size is not implemented");
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
