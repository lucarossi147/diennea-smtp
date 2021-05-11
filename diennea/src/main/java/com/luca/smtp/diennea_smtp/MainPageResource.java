package com.luca.smtp.diennea_smtp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Path("/index")
public class MainPageResource {
    @GET
    @Produces({MediaType.TEXT_HTML})
    public InputStream hello() throws FileNotFoundException {
        File f = new File("/home/luca/Desktop/diennea/diennea-smtp/diennea/src/main/resources/index.html");
        return new FileInputStream(f);
    }
}
