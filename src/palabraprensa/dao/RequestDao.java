package palabraprensa.dao;

import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import palabraprensa.model.Request;
import palabraprensa.model.constants.Wordpress;

public class RequestDao {
	
	public static Object[] makeRequest(Request request) throws Exception {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	    config.setServerURL(new URL(Wordpress.XML_RPC_SERVER));
	    XmlRpcClient client = new XmlRpcClient();
	    client.setConfig(config);	    
	    return (Object[]) client.execute(request.getMethod(), request.getParams());
	}
	
	public static Boolean makeBooleanRequest(Request request) throws Exception {		
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	    config.setServerURL(new URL(Wordpress.XML_RPC_SERVER));
	    XmlRpcClient client = new XmlRpcClient();
	    client.setConfig(config);	    
	    return (Boolean) client.execute(request.getMethod(), request.getParams());    
	}

}
