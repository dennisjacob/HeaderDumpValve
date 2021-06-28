## HeaderDumpValve
###  HeaderDumpValve is a Tomcat Valve implementation, extending the ValveBase class.

This Valve acts as another Valve in the Tomcat container Pipeline, intercepting the requests and dumps the headers to the standard out.
Instead of using the Logger, you can also use containerLog object.

Valve to be configured in the server.xml is given below

```
<Valve className="com.deejay.tomcatutils.HeaderDumpValve" enabled="true" />

```
