package org.tkalenko.chat.server;

import java.net.Socket;

import org.tkalenko.chat.common.MiscUtil;

public class ChatClient {
	private String user;
	private Socket socket;

	public ChatClient(final String user, final Socket socket) {
		if (MiscUtil.isEmpty(user))
			throw new IllegalArgumentException("missing user");
		this.user = user;
		this.socket = socket;
	}

	public String getUser() {
		return this.user;
	}

	public Socket getSocket() {
		return this.socket;
	}

	@Override
	public int hashCode() {
		return this.user.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatClient other = (ChatClient) obj;
		return this.user.equals(other.user);
	}

}
