package com.danielme.demo.jaxws.cxf.ws;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service(value = "fileTransferService")
public class FileTransferServiceImpl implements IFileTransferService {

    private static final Logger logger = Logger.getLogger(FileTransferServiceImpl.class);

    @Override
    public byte[] downloadFileContent() throws IOException {
        File file = new ClassPathResource("small.pdf").getFile();
        Path path = Paths.get(file.getPath());
        return Files.readAllBytes(path);
    }

    @Override
    public void uploadFile(byte[] fileContent) {
        logger.info("file uploaded, length " + fileContent.length);
    }

}
