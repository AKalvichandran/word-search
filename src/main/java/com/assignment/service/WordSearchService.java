package com.assignment.service;

import rx.Observable;

import java.util.List;

/**
 * Created by kalvichandrana on 08/02/2018.
 */
public interface WordSearchService {

    /**
     * @param fileList
     * fileList will be loaded during the application initialization based on configuration directory
     * see configuration.yml for directory name
     * @param keyword
     * user key word. This may have multiple words.
     * @return the list of file name which matches the keyword
     */
    List<String> findFileList(List<String> fileList, String keyword);
}
