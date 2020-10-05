package reader;

import au.com.bytecode.opencsv.CSVReader;
import lombok.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReaderFromCsv {
    private String fileName;

    public ReaderFromCsv(final String fileName) {
        this.fileName = fileName;
    }

    public List<String[]> getLineList() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        CSVReader csvReader = new CSVReader(reader, ',', '"', 1);
        List<String[]> lineList = null;
        try {
            lineList = new ArrayList<>(csvReader.readAll());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineList;
    }
}
