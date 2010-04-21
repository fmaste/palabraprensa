package wordpress.factory;

import wordpress.model.Request;

public class RequestFactory {

	public static Request create(String method, Object[] params) {
		if (method == null || params == null) {
			return null;
		}
		Request request = new Request();
		request.setMethod(method);
		request.setParams(params);
		return request;
	}
}
