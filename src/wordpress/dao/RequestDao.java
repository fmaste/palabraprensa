package wordpress.dao;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import wordpress.model.Request;
import wordpress.model.constants.Wordpress;

public class RequestDao {
	
	public static Object[] makeRequest(Request request) throws Exception {
		
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	    config.setServerURL(new URL(Wordpress.XML_RPC_SERVER));
	    XmlRpcClient client = new XmlRpcClient();
	    client.setConfig(config);
	    
	    return (Object[]) client.execute(request.getMethod(), request.getParams());
	}
}
