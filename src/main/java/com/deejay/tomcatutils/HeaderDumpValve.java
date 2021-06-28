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
*******   HeaderDumpValve is a Tomcat Valve implementation, extending the ValveBase class.   *****
*******   dennis.jacob@gmail.com   ******

This Valve acts as another Valve in the Tomcat container Pipeline, intercepting the requests and dumps the headers to the standard out.
Instead of using the Logger, you can also use containerLog object.

Valve to be configured in the server.xml is given below

<Valve className="com.deejay.tomcatutils.HeaderDumpValve" enabled="true" />

 */



public class HeaderDumpValve extends ValveBase {

    private static final Logger logger = Logger.getLogger(HeaderDumpValve.class.getName());

    private String enabled;

    public HeaderDumpValve() {
        super(true);
        this.enabled = "false";
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }



    public void invoke(Request request, Response response) throws IOException, ServletException {

        logger.log(Level.INFO, "Request received : " + request.getDecodedRequestURI());

        Enumeration<String> headerNames = request.getHeaderNames();


        if (enabled.equals("true")) {
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
                logger.log(Level.INFO, " [ " + header + " : " + request.getHeader(header) + " ] ");
            }
        }
        getNext().invoke(request, response);
        logger.log(Level.INFO, "Request processed" );
    }

}