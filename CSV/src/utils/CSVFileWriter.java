package utils;

import java.io.IOException;
import java.io.Writer;

public class CSVFileWriter extends Writer {
    private String[][] data;
    private Writer writer;

    public CSVFileWriter(Writer writer, String[][] data){
        this.writer = writer;
        this.data = data;
    }

    public void writeData(){
        for(int i = 0; i < data.length; i++){
            try {
            for(int j = 0; j < data[i].length; j++){
                writer.write(String.valueOf(data[i][j]));
                if(j != data[i].length-1) {
                    writer.write(", ");
                }
            }
            writer.write('\n');
            writer.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage() + "Problems with writing data");
            }
        }
    }

    @Override
    public void write(int c) throws IOException {
        writer.write(c);
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        writer.write(cbuf);
    }

    @Override
    public void write(String str) throws IOException {
        writer.write(str);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        writer.write(str, off, len);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        return writer.append(csq);
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        return writer.append(csq, start, end);
    }

    @Override
    public Writer append(char c) throws IOException {
        return writer.append(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        writer.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
