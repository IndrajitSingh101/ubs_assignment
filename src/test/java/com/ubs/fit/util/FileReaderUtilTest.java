package com.ubs.fit.util;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class FileReaderUtilTest {
    String FILE_PATH="src/test/resources/FILE.DAT.txt";
    @SneakyThrows
    @Test
    public void testGetStreamOfData() {
        try (Stream<String> fileStream = Files.lines(Paths.get(FILE_PATH))) {
            int noOfLines = (int) fileStream.count();
            assertEquals(FileReaderUtil.getStreamOfData(FILE_PATH).get().count(), noOfLines);
        }
    }
}
