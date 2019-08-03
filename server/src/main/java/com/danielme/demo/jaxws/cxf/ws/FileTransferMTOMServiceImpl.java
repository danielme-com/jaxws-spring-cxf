package com.danielme.demo.jaxws.cxf.ws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service(value = "fileTransferMTOMService")
// @BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_MTOM_BINDING)
public class FileTransferMTOMServiceImpl implements IFileTransferMTOMService {

    private static final Logger logger = Logger.getLogger(FileTransferMTOMServiceImpl.class);

    @Override
    public DataHandler downloadFileContent() throws IOException {
        File file = new ClassPathResource("small.pdf").getFile();
        return new DataHandler(new FileDataSource(file));
    }

    @Override
    public void uploadFile(DataHandler dataHandler) {
        try {
            File file = File.createTempFile("upload", ".pdf");
            file.deleteOnExit();
            FileOutputStream outputStream = new FileOutputStream(file);
            dataHandler.writeTo(outputStream);
            outputStream.close();
            logger.info("file saved in = " + file.getPath());
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

}
