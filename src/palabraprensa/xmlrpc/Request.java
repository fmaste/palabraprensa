package palabraprensa.xmlrpc;

import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import palabraprensa.model.Blog;
import palabraprensa.model.User;

public class Request {
	private static final Logger logger = LoggerFactory.getLogger(Request.class);
	
	public static Object make(String wordpressRoot, User user, String method, Object[] params) throws Exception {
		String url;
		if (wordpressRoot.endsWith("/")) {
			url = wordpressRoot + "xmlrpc.php";
		} else {
			url = wordpressRoot + "/xmlrpc.php";
		}
		return make(url, method, params);
	}

	public static Object make(Blog blog, String method, Object[] params) throws Exception {
		return make(blog.getXmlrpcUrl(), method, params);
	}	
	
	public static Object make(String url, String method, Object[] params) throws Exception {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	    config.setServerURL(new URL(url));
	    XmlRpcClient client = new XmlRpcClient();
	    client.setConfig(config);	    
	    return client.execute(method, params);
	}
	
}
