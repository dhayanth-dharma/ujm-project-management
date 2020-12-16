package process_socket;

import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;
@CrossOrigin
@Controller
public class Process_socket_controller {

	@CrossOrigin
	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Initiation greeting(Response_model message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Initiation("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
