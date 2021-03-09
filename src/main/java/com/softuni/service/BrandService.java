package com.softuni.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface BrandService {
    void initBrands() throws IOException;

     String readAuthorsFileContent() throws IOException;
}
