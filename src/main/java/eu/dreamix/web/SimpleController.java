package eu.dreamix.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.dreamix.config.ApplicationProperties;
import eu.dreamix.dto.GreetingInfo;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/greeting")
public class SimpleController {

	private final static Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);
	private final static String VERSION = "4.0.0";

	private final ApplicationProperties appConfig;

	@Autowired
	public SimpleController(ApplicationProperties appConfig) {
		this.appConfig = appConfig;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GreetingInfo> getGreeting() {
		LOGGER.debug("Received request to display greeting information.");
		GreetingInfo greeting = new GreetingInfo(appConfig.getGreetingMessage(), VERSION , appConfig.getServerHostName());
		return new ResponseEntity<>(greeting, HttpStatus.OK);
	}
}
