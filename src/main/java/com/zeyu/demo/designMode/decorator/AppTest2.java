package com.zeyu.demo.designMode.decorator;


import java.io.*;

/**
 * 自定义文件读取类
 */
class MyBuilderRead extends Reader {
    Reader in;

    public MyBuilderRead(Reader in) {
        this.in = in;
    }

    /**
     * 自定义文件读取
     *
     * @return
     * @throws IOException
     */


    public String readLine() throws IOException {
        StringBuffer b = new StringBuffer("");
        int i = 0;
        while (true) {

            int read = in.read();
            if (i == 0) {
                i++;
                b.append("第" + i + "行:");
                b.append((char) read);
                continue;
            }
            if (read == '\r') {
                continue;
            }
            if (read == '\n') {
                i++;
                b.append("\n第" + i + "行:");
                continue;
            }
            if (read == -1) {
                break;
            }
            b.append((char) read);
        }
        return b.toString();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}

class MyBuilderWriter extends Writer {
    Writer writer;

    public MyBuilderWriter(Writer writer) {
        this.writer = writer;
    }

    public void write(String s) throws IOException {

    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
        ;
    }
}

public class AppTest2 {
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("D://1.txt");
        MyBuilderRead read = new MyBuilderRead(in);
        String s = read.readLine();
        System.err.println(s);
    }
}
