package com.ubs.fit.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FileReaderUtil {
    //supplier to stream of rows in file
    public static Supplier<Stream<String>> getStreamOfData(String path)  {
        return ()-> {
            try {
                return Files.lines(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };

    }
}
