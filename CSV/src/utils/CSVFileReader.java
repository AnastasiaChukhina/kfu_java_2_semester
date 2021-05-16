package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader extends Reader{
    private String separator = ",";
    private BufferedReader reader;

    public CSVFileReader(Reader reader){
        this.reader = new BufferedReader(reader);
    }

    public String[][] readData(){
        List<String> lines = new ArrayList<>();
        String nextLine;
        try {
            while ((nextLine = reader.readLine()) != null){
                lines.add(nextLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "Problems with reading data.");
        }
        String[][] data = new String[lines.size()][];
        for(int i = 0; i < lines.size(); i++){
            data[i] = separateData(lines.get(i));
        }
        return data;
    }

    private String[] separateData(String line){
        return line.trim().split(separator);
    }

    public String getSeparator() {
        return separator;
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        return reader.read(target);
    }

    @Override
    public int read() throws IOException {
        return reader.read();
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        return reader.read(cbuf);
    }

    @Override
    public long skip(long n) throws IOException {
        return reader.skip(n);
    }

    @Override
    public boolean ready() throws IOException {
        return reader.ready();
    }

    @Override
    public boolean markSupported() {
        return reader.markSupported();
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        reader.mark(readAheadLimit);
    }

    @Override
    public void reset() throws IOException {
        reader.reset();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
    }
}
