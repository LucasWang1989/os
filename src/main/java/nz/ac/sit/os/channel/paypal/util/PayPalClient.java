package nz.ac.sit.os.channel.paypal.util;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Iterator;

@Component
public class PayPalClient {
	@Value("${PAYPAL_CLIENT_ID}")
	private String clientId = "AdTn2zuHD-OtbdQR1zlP0j1wetpySRAeZRAQMDSG7QB0J3uc3nk769_YychiLAKjxQwjbUmrPBI_f2S_";
	@Value("${PAYPAL_CLIENT_SECRET}")
	private String clientSecret = "EBt5qlxE2gZ__wUhpwvM1pOpYi3qdI9OAE6fxHNGGrJzVRHL6ocjkqeP7u9WqYnE_MW_YSPpsfD6X9xu";

	PayPalHttpClient client = null;

	private PayPalClient() {
		/**
		 * Setting up PayPal SDK environment with PayPal Access credentials. For demo
		 * purpose, we are using SandboxEnvironment. In production this will be
		 * LiveEnvironment.
		 */
		PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId, clientSecret);
//	System.getProperty("PAYPAL_CLIENT_ID") != null ? System.getProperty("PAYPAL_CLIENT_ID")
//			: "<<PAYPAL-CLIENT-ID>>",
//			System.getProperty("PAYPAL_CLIENT_SECRET") != null ? System.getProperty("PAYPAL_CLIENT_SECRET")
//			: "<<PAYPAL-CLIENT-SECRET>>"

		/**
		 * PayPal HTTP client instance with environment which has access credentials
		 * context. This can be used invoke PayPal API's provided the credentials have
		 * the access to do so.
		 */
		client = new PayPalHttpClient(environment);
	}

	/**
	 * Method to get client object
	 *
	 * @return PayPalHttpClient client
	 */
	public PayPalHttpClient client() {
		return this.client;
	}

	/**
	 * Method to pretty print a response
	 *
	 * @param jo  JSONObject
	 * @param pre prefix (default="")
	 * @return String pretty printed JSON
	 */
	public String prettyPrint(JSONObject jo, String pre) {
		Iterator<?> keys = jo.keys();
		StringBuilder pretty = new StringBuilder();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			pretty.append(String.format("%s%s: ", pre, StringUtils.capitalize(key)));
			if (jo.get(key) instanceof JSONObject) {
				pretty.append(prettyPrint(jo.getJSONObject(key), pre + "\t"));
			} else if (jo.get(key) instanceof JSONArray) {
				int sno = 1;
				for (Object jsonObject : jo.getJSONArray(key)) {
					pretty.append(String.format("\n%s\t%d:\n", pre, sno++));
					pretty.append(prettyPrint((JSONObject) jsonObject, pre + "\t\t"));
				}
			} else {
				pretty.append(String.format("%s\n", jo.getString(key)));
			}
		}
		return pretty.toString();
	}
}
