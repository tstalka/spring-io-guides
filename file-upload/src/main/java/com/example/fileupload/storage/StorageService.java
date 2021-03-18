package com.example.fileupload.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file);

    Stream<String> loadAll();

    MultipartFile loadAsInputStream(String filename) throws IOException;

    void deleteAll();
}
