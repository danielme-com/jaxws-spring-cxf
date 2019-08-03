package com.danielme.demo.jaxws.cxf.ws;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

@WebService
@MTOM
public interface IFileTransferMTOMService {

    DataHandler downloadFileContent() throws IOException;

    void uploadFile(DataHandler dataHandler);
}
