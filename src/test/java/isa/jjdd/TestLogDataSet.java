package isa.jjdd;

import isa.jjdd.models.LogData;
import isa.jjdd.repository.LogDataSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static isa.jjdd.TestUtils.getRandomLogData;
import static isa.jjdd.TestUtils.getRandomLogDataSet;
import static org.junit.jupiter.api.Assertions.*;

class TestLogDataSet {
    private static final int RANDOM_LOG_DATA_SET_SIZE = 100;

    private static LogDataSet logDataSet;

    @BeforeAll
    static void setup() {
        logDataSet = LogDataSet.getInstance();
    }

    @BeforeEach
    void clearRepository() {
        logDataSet.clear();
    }

    @Test
    void getInstance_notNull() {
        assertNotNull(LogDataSet.getInstance());
    }

    @Test
    void getInstance_returnSingletonObject() {
        assertEquals(logDataSet, LogDataSet.getInstance(), "Singleton pattern is not provided");
    }

    @Test
    void add_addNewObject_sizeIncrementedByOne() {
        int sizeBeforeAdd = logDataSet.size();

        logDataSet.add(getRandomLogData());

        int sizeAfterAdd = logDataSet.size();
        assertEquals(sizeBeforeAdd + 1, sizeAfterAdd, "Size should be incremented by one");
    }

    @Test
    void add_addNullData_notAdded() {
        int sizeBeforeAdd = logDataSet.size();

        logDataSet.add(null);

        int sizeAfterAdd = logDataSet.size();
        assertEquals(sizeBeforeAdd, sizeAfterAdd, "Size should not change, null values are not supported");
    }

    @Test
    void add_addSameDataTwice_addedOnlyOnce() {
        int sizeBeforeAdd = logDataSet.size();

        LogData logData = getRandomLogData();
        logDataSet.add(logData);

        int sizeAfterAdd = logDataSet.size();
        assertEquals(sizeBeforeAdd + 1, sizeAfterAdd, "Size should be incremented by one");

        logDataSet.add(logData);

        int sizeAfterSecondAdd = logDataSet.size();
        assertEquals(sizeAfterAdd, sizeAfterSecondAdd, "Size should not change, item was already added");
    }

    @Test
    void addAll_uniqueSetOfObjects_sizeEqualsAddedCollectionSize() {
        Collection<LogData> randomLogDataSet = getRandomLogDataSet(RANDOM_LOG_DATA_SET_SIZE);
        int expectedSize = randomLogDataSet.size();

        logDataSet.addAll(randomLogDataSet);
        int actualSize = logDataSet.size();

        assertEquals(expectedSize, actualSize, "Size should be the same as the collection added");
    }

    @Test
    void addAll_listWithNullValues_nullValuesNotAdded() {
        Collection<LogData> randomLogDataSet = getRandomLogDataSet(RANDOM_LOG_DATA_SET_SIZE);
        int expectedSize = randomLogDataSet.size();

        List<LogData> listWithNullValues = new ArrayList<>();
        listWithNullValues.addAll(randomLogDataSet);
        listWithNullValues.addAll(Collections.nCopies(RANDOM_LOG_DATA_SET_SIZE, null));
        Collections.shuffle(listWithNullValues);

        logDataSet.addAll(listWithNullValues);
        int actualSize = logDataSet.size();

        assertEquals(expectedSize, actualSize, "Size should be the same as the collection unique items");
    }

    @Test
    void addAll_listOfRedundantObjects_onlyUniqueObjectsAdded() {
        Collection<LogData> randomLogDataSet = getRandomLogDataSet(RANDOM_LOG_DATA_SET_SIZE);
        int expectedSize = randomLogDataSet.size();

        List<LogData> listOfRedundantData = new ArrayList<>();
        listOfRedundantData.addAll(randomLogDataSet);
        listOfRedundantData.addAll(randomLogDataSet);
        Collections.shuffle(listOfRedundantData);

        logDataSet.addAll(listOfRedundantData);
        int actualSize = logDataSet.size();

        assertEquals(expectedSize, actualSize, "Size should be the same as the collection of unique items");
    }

    @Test
    void clear_allItemsRemoved() {
        Collection<LogData> randomLogDataSet = getRandomLogDataSet(RANDOM_LOG_DATA_SET_SIZE);
        int expectedSize = randomLogDataSet.size();

        logDataSet.addAll(randomLogDataSet);
        int sizeAfterAdd = logDataSet.size();

        assertEquals(expectedSize, sizeAfterAdd, "Items were not added to the collection");

        logDataSet.clear();
        @SuppressWarnings("ConstantConditions")
        int sizeAfterClear = logDataSet.size();

        assertEquals(0, sizeAfterClear, "Items were not cleared from the collection");
    }

    @Test
    void isEmpty_sizeChanges_valueMatchSize() {
        logDataSet.addAll(getRandomLogDataSet(RANDOM_LOG_DATA_SET_SIZE));
        assertFalse(logDataSet.isEmpty(), "Collection should not be empty");

        logDataSet.clear();
        //noinspection ConstantConditions
        assertTrue(logDataSet.isEmpty(), "Collection should be empty");
    }

    @Test
    void iterator_notNull() {
        logDataSet.addAll(getRandomLogDataSet(RANDOM_LOG_DATA_SET_SIZE));

        for (LogData ignored : logDataSet) {
            break;
        }
    }

    @Test
    void getByComponentName_useNullParameter_returnEmptyLogDataSet() {
        logDataSet.addAll(getRandomLogDataSet(RANDOM_LOG_DATA_SET_SIZE));
        assertTrue(logDataSet.getByComponentName(null).isEmpty(), "Collection should be empty");
    }

    @Test
    void getByComponentName_useProperParameter_returnRightLogDataSet() {
        String wrongComponentName = "unavailableName";
        int sizeOfSetWithWrongNames = 10;

        for (int i = 0; i < sizeOfSetWithWrongNames; i++) {
            LogData logData = getRandomLogData();
            logData.setComponentName(wrongComponentName);
            logDataSet.add(logData);
        }

        String correctComponentName = "availableName";
        int sizeOfSetWithCorrectNames = 11;

        for (int i = 0; i < sizeOfSetWithCorrectNames; i++) {
            LogData logData = getRandomLogData();
            logData.setComponentName(correctComponentName);
            logDataSet.add(logData);
        }

        assertEquals(logDataSet.getByComponentName(correctComponentName).size(),
                sizeOfSetWithCorrectNames, "Collection should include " + sizeOfSetWithCorrectNames + " objects.");
    }

    @Test
    void getByComponentName_useWrongParameter_returnEmptyLogDataSet() {
        String wrongComponentName = "unavailableName";
        int sizeOfSetWithWrongNames = 10;

        for (int i = 0; i < sizeOfSetWithWrongNames; i++) {
            LogData logData = getRandomLogData();
            logData.setComponentName(wrongComponentName);
            logDataSet.add(logData);
        }

        String correctComponentName = "availableName";

        assertTrue(logDataSet.getByComponentName(correctComponentName).isEmpty(), "Collection should be empty.");
    }


    @Test
    void getByDate_useNullParameter_returnEmptyLogDataSet() {
        logDataSet.addAll(getRandomLogDataSet(RANDOM_LOG_DATA_SET_SIZE));
        assertTrue(logDataSet.getByDate(null).isEmpty(), "Collection should be empty");
    }

    @Test
    void getByDate_useProperParameter_returnRightLogDataSet() {
        LocalDateTime wrongDate = LocalDateTime.of(2012, 7, 12, 13, 45, 0);
        int sizeOfSetWithWrongDates = 10;

        for (int i = 0; i < sizeOfSetWithWrongDates; i++) {
            LogData logData = getRandomLogData();
            logData.setTimestamp(wrongDate);
            logDataSet.add(logData);
        }

        LocalDateTime correctDate = LocalDateTime.of(2019, 8, 13, 14, 50, 0);
        int sizeOfSetWithCorrectDates = 11;

        for (int i = 0; i < sizeOfSetWithCorrectDates; i++) {
            LogData logData = getRandomLogData();
            logData.setTimestamp(correctDate);
            logDataSet.add(logData);
        }

        assertEquals(logDataSet.getByDate(correctDate.toLocalDate()).size(),
                sizeOfSetWithCorrectDates, "Collection should include " + sizeOfSetWithCorrectDates + " objects.");
    }

    @Test
    void getByDate_useWrongParameter_returnEmptyLogDataSet() {
        LocalDateTime wrongDate = LocalDateTime.of(2012, 7, 12, 13, 45, 0);
        int sizeOfSetWithWrongDates = 10;

        for (int i = 0; i < sizeOfSetWithWrongDates; i++) {
            LogData logData = getRandomLogData();
            logData.setTimestamp(wrongDate);
            logDataSet.add(logData);
        }
        LocalDateTime correctDate = LocalDateTime.of(2019, 8, 13, 14, 50, 0);

        assertTrue(logDataSet.getByDate(correctDate.toLocalDate()).isEmpty(), "Collection should be empty.");
    }
}
