package com.elsevier.assignment;

import com.elsevier.assignment.resources.WordSearchResource;
import com.elsevier.assignment.service.WordSearchService;
import com.elsevier.assignment.service.WordSearchServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class WordSearchApplication extends Application<WordSearchConfiguration> {

    public static void main(String[] args) throws Exception {
        new WordSearchApplication().run(args);
    }

    @Override
    public String getName() {
        return "word-search";
    }


    @Override
    public void initialize(Bootstrap<WordSearchConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<WordSearchConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(WordSearchConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(WordSearchConfiguration configuration, Environment environment) throws Exception {
        Path start = Paths.get(configuration.getBaseDirectory());
        final List<String> fileList = new LinkedList<>();
        try (Stream<Path> searchDirectory = Files.walk(start)) {
            searchDirectory
                    .filter(p -> p.toFile().isFile() && p.toFile().getName().endsWith(".txt"))
                    .forEach(p -> fileList.add(p.toFile().getAbsolutePath()));
        }
        final WordSearchService wordSearchService = new WordSearchServiceImpl();
        environment.jersey().register(new WordSearchResource(wordSearchService, fileList));
    }
}
