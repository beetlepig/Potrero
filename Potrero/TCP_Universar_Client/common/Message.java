package common;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

	private static final long serialVersionUID = 15L;

	private String type, textMessage;
	private int data;
	private int userId;
	private int destinatioId;
	private Object object;

	// -------- ID USUSARIO ----------

	public Message(int userId, String type, String textMessage) {
		this.type = type;
		this.textMessage = textMessage;
		this.userId = userId;
	}// message with a textual message

	public Message(int userId, String type, Object obj) {
		this.type = type;
		this.object = obj;
		this.userId = userId;
	}// message with a Object

	public Message(int userId, String type, int data) {
		this.type = type;
		this.data = data;
		this.userId = userId;
	}// message with a data number

	// -------- ID USUSARIO + ID DESTINO----------

	public Message(int userId, int destinatioId, String type, String textMessage) {
		this.type = type;
		this.textMessage = textMessage;
		this.userId = userId;
		this.destinatioId = destinatioId;
	}// message with specific destination and a textual message

	public Message(int userId, int destinatioId, String type, Object obj) {
		this.type = type;
		this.object = obj;
		this.userId = userId;
		this.destinatioId = destinatioId;
	}// message with specific destination and a Object

	public Message(int userId, int destinatioId, String type, int data) {
		this.type = type;
		this.data = data;
		this.userId = userId;
		this.destinatioId = destinatioId;
	}// message with specific destination and a data number

	// ------------------ GETTERS -------------------

	public String getType() {
		return type;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public int getData() {
		return data;
	}

	public int getUserId() {
		return userId;
	}

	public int getDestinatioId() {
		return destinatioId;
	}

	public Object getObject() {
		return object;
	}

}// MESSAGE
