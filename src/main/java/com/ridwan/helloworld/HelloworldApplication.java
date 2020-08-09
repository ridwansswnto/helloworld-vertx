package com.ridwan.helloworld;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;


public class HelloworldApplication extends AbstractVerticle {

	public static final String version = "1.0";

	@Override
	public void start() throws Exception {
		Router router = Router.router(vertx);

		//Config CORS
		router.route().handler(CorsHandler.create("*")
				.allowedMethod(HttpMethod.GET)
				.allowedHeader("Content-Type"));

		// hello endpoint
		router.get("/api/hello/:name").handler(ctx -> {
			ctx.response().end(hello(ctx.request().getParam("name")));
		});
		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}

	private String hello(String name) {
		String configGreeting = ApplicationConfiguration.load(config()).getString("GREETING");
		String greeting = configGreeting == null ? "Hello {name} from {hostname} with {version}" : configGreeting;
		Map<String, String> values = new HashMap<String, String>();
		values.put("name", name);
		values.put("hostname", System.getenv().getOrDefault("HOSTNAME", "unknown"));
		values.put("version", version);
		return new StrSubstitutor(values, "{", "}").replace(greeting);
	}

}

