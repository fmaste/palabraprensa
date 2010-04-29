package palabraprensa.xmlrpc;

import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import palabraprensa.model.Blog;
import palabraprensa.model.User;

public class Request {

	public static Object make(User user, String method, Object[] params) throws Exception {
		return make("http://" + user.getName() + ".wordpress.com/xmlrpc.php", method, params);
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
