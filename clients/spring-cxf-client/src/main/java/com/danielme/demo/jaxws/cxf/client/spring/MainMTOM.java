package com.danielme.demo.jaxws.cxf.client.spring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.danielme.demo.jaxws.cxf.ws.IFileTransferMTOMService;

public class MainMTOM {

    private static final Logger logger = Logger.getLogger(MainMTOM.class);

    public static void main(String[] args) throws Exception {
        // Initializes Spring Container
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        IFileTransferMTOMService fileTransferService = (IFileTransferMTOMService) applicationContext
                .getBean("fileTransferMTOMClient");

        File fileDownloaded = download(fileTransferService);
        upload(fileTransferService, fileDownloaded);

        applicationContext.close();
    }

    private static File download(IFileTransferMTOMService fileTransferService) throws IOException {
        DataHandler dataHandlerResponse = fileTransferService.downloadFileContent();
        File file = File.createTempFile("temp", ".pdf");
        // file.deleteOnExit();
        FileOutputStream outputStream = new FileOutputStream(file);
        dataHandlerResponse.writeTo(outputStream);
        outputStream.close();
        logger.info("path = " + file.getPath());
        return file;
    }

    private static void upload(IFileTransferMTOMService fileTransferService, File fileDownloaded) {
        DataHandler dataHandlerSend = new DataHandler(new FileDataSource(fileDownloaded));
        fileTransferService.uploadFile(dataHandlerSend);
    }

}