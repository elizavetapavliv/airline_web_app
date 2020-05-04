package controller;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatEndpoint {

	@OnMessage
	public void onMessage(Session session, String message) {
		if (!session.getUserProperties().containsKey("sender")) {
			String[] info = message.split(": ");
			session.getUserProperties().put("sender", info[0]);
		} 
		else {
			String sender = (String) session.getUserProperties().get("sender");
			String receiver;
			if(sender.equals("Admin")) {
				String[] info = message.split(": ");
				receiver = info[0];
				message = sender + ": " + info[1];
			}
			else {
				receiver = "Admin";
				message = sender + ": " + message;
			}

			for (Session sessionReceiver : session.getOpenSessions()) {
				if (sessionReceiver.isOpen()) {
					if (sessionReceiver.getUserProperties().containsKey("sender")) {
						String sessionSenderName = (String) sessionReceiver.getUserProperties().get("sender");
						if (sessionSenderName.equals(receiver)) {
							try {
								sessionReceiver.getBasicRemote().sendText(message);
							} 
							catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

	}
}
