package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import java.util.HashMap;
import java.util.Map;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final S3Client s3Client;

    public App() {
	    System.out.println("Initializing S3 Client");
	    s3Client = S3Client.create();
	    System.out.println("Done Initializing S3 Client");
    }

	public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("X-Custom-Header", "application/json");

		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
				.withHeaders(headers);
		String output = String.format("{ \"message\": \"hello world\" }");

		return response
				.withStatusCode(200)
				.withBody(output);
	}

}
