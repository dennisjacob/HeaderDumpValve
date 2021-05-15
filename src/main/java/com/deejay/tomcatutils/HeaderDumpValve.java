package com.deejay.tomcatutils;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Tomcat Header Dump Valve


 */

public class HeaderDumpValve extends ValveBase {
    private static final Logger logger = Logger.getLogger(HeaderDumpValve.class.getName());

    public void invoke(Request request, Response response) throws IOException, ServletException {


        response.addHeader("Test-header","TestValue");

        Enumeration<String> headerNames = request.getHeaderNames();

        logger.info("Receiving request");

        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();

            logger.log(Level.INFO, "Header --> {0} Value --> {1}", new Object[]{header, request.getHeader(header)});
        }
        getNext().invoke(request, response);
    }

}