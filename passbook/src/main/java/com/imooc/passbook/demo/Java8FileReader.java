package com.imooc.passbook.demo;

import javax.net.ssl.StandardConstants;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Java8FileReader {
    public static void main(String[] args) throws IOException {

        Files.lines(Paths.get("F:\\wq.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
    }
}
