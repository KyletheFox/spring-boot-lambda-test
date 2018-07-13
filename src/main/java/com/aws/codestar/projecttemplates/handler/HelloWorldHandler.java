package com.aws.codestar.projecttemplates.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws.codestar.projecttemplates.GatewayResponse;

/**
 * Handler for requests to Lambda function.
 */
public class HelloWorldHandler implements RequestHandler<Object, Object> {
	
	@Autowired
	RestTemplate restTemplate;
	
	static AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
			HelloWorldHandler.class.getPackage().getName());

	public HelloWorldHandler() {
		 ctx.getAutowireCapableBeanFactory().autowireBean(this);
	}

    public Object handleRequest(final Object input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        System.out.println(restTemplate.toString());
        String joke = restTemplate.getForObject("https://api.chucknorris.io/jokes/random", String.class);
        return new GatewayResponse(joke, headers, 200);
    }
}
