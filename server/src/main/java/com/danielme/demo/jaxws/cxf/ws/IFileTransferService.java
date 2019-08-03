package com.danielme.demo.jaxws.cxf.ws;

import java.io.IOException;

import javax.jws.WebService;

@WebService
public interface IFileTransferService {

    byte[] downloadFileContent() throws IOException;

    void uploadFile(byte[] fileContent);
}
