package com.example.fileupload.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private Logger log = LoggerFactory.getLogger(StorageService.class);

    private final Map<String, MultipartFile> memoryStorage = new HashMap<>();

    @Override
    public void init() {
        log.info(" === Init Storage === ");
    }

    @Override
    public void store(MultipartFile file) {
        log.info(" === Store file: " + file.getName() + " === ");
        this.memoryStorage.put(file.getOriginalFilename(), file);
    }

    @Override
    public Stream<String> loadAll() {
        return this.memoryStorage.values().stream().map(file -> file.getOriginalFilename()).collect(Collectors.toList()).stream();
    }

    @Override
    public MultipartFile loadAsInputStream(String filename) {
        if (this.memoryStorage.containsKey(filename)) {
            return this.memoryStorage.get(filename);
        }
        throw new StorageFileNotFoundException();
    }

    @Override
    public void deleteAll() {
        this.memoryStorage.clear();
    }
}
