package com.elsevier.assignment.service;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by kalvichandrana on 08/02/2018.
 */
public class WordSearchServiceImplTest {

    @Test
    public void findFileList() {
        //when
        List<String> fileList = new WordSearchServiceImpl().findFileList(getFileList(), "Brian Goetz");
        //then
        assertEquals(fileList.size(), 1);

    }

    @Test
    public void findFileListForFailureCase() {
        //when
        List<String> fileList = new WordSearchServiceImpl().findFileList(getFileList(), "dot net");
        //then
        assertEquals(fileList.size(), 0);

    }

    @Test
    public void findIfKeywordMatches() {
        //given
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = classLoader.getResource("search-directory/test2.txt").getPath();
        String searchWord = "specifically 10 SQL Oracle’s";

        //when
        String file = new WordSearchServiceImpl().findIfKeywordMatches(fileName, searchWord).toBlocking().single();

        //then
        assertEquals(file, fileName);
    }

    @Test
    public void findIfKeywordMatchesForFailureCases() {
        //given
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = classLoader.getResource("search-directory/test2.txt").getPath();
        String searchWord = "This is dummy word to test";

        //when
        String file = new WordSearchServiceImpl().findIfKeywordMatches(fileName, searchWord).toBlocking().single();

        //then
        assertEquals(file, "");
    }

    @Test
    public void isContainExactWord() {

        //when
        boolean check = new WordSearchServiceImpl().isContainExactWord("This is Java Line", "java");

        //then
        assertEquals(check, true);

    }

    @Test
    public void isContainExactWordForFailureCase() {

        //when
        boolean check = new WordSearchServiceImpl().isContainExactWord("This is not a Java Line", "javas");

        assertEquals(check, false);

    }

    protected List<String> getFileList() {

        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource("search-directory").getPath();
        Path start = Paths.get(path);
        final List<String> fileList = new LinkedList<>();
        try {
            try (Stream<Path> searchDirectory = Files.walk(start)) {
                searchDirectory
                        .filter(p -> p.toFile().isFile() && !(p.toFile().getName().startsWith(".")))
                        .forEach(p -> fileList.add(p.toFile().getAbsolutePath()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }
}
