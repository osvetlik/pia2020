package cz.zcu.fav.pia.jsf.websocket.hello;

import cz.zcu.fav.pia.jsf.websocket.hello.data.Greeting;
import cz.zcu.fav.pia.jsf.websocket.hello.data.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static org.springframework.web.util.HtmlUtils.htmlEscape;

/**
 * @author Oto Šťáva
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000);
        return new Greeting("Hello, " + htmlEscape(message.getName()) + "!");
    }

}
