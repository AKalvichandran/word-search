package com.elsevier.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by kalvichandrana on 08/02/2018.
 */
public class WordSearchServiceImpl implements WordSearchService {

    private final static String EMPTY_STRING = "";
    final static Logger logger = LoggerFactory.getLogger(WordSearchServiceImpl.class);


    @Override
    public List<String> findFileList(final List<String> fileList, final String keyword) {
        final List<String> fileMatches = new LinkedList<>();
        Observable.from(fileList)
                .flatMap(file -> findIfKeywordMatches(file, keyword))
                .subscribe(file -> {
                    if (!EMPTY_STRING.equals(file)) {
                        fileMatches.add(file);
                    }
                });
        return fileMatches;
    }

    protected Observable<String> findIfKeywordMatches(final String fileName, final String keywords) {
        Set<String> wordsMatchList = new HashSet<>();
        String[] keywordList = keywords.split(" ");
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.forEach(line -> {
                for (String keyword : keywordList) {
                    if (isContainExactWord(line, keyword)) {
                        wordsMatchList.add(keyword);
                    }
                }
            });
            lines.close();
        } catch (IOException exception) {
            logger.warn("Unable to process this file {},and Keywords {} exception message : {}", fileName, keywords, exception.getMessage());
        }
        return keywordList.length == wordsMatchList.size() ? Observable.just(fileName) : Observable.just(EMPTY_STRING);
    }

    protected boolean isContainExactWord(String line, String keyword) {
        String pattern = "\\b" + keyword.toUpperCase() + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line.toUpperCase());
        return m.find();
    }

}
