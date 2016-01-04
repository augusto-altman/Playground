import org.apache.sshd.server.PasswordAuthenticator;
import org.apache.sshd.server.session.ServerSession;

public class Authenticator implements PasswordAuthenticator {

	@Override
	public boolean authenticate(String username, String password, ServerSession arg2) {
		return "tito".equals(username) && "7170".equals(password);
	}

}
