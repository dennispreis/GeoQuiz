import DAOs.MyUserDao;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;

//source from: https://www.stubbornjava.com/posts/hashing-passwords-in-java-with-bcrypt
public class PasswordProcess {

    private int logRounds;
    private int attempts = 0;
    private MyUserDao dbConnectorUser = new MyUserDao();
    private LocalDateTime currentAttempt = LocalDateTime.now();

    public PasswordProcess(int logRounds) {
        this.logRounds = logRounds;
    }

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    private boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public int bruteForceCheck(String username, String password, String hash) {

        LocalDateTime firstAttempt = LocalDateTime.parse(dbConnectorUser.getDateTime(username));
        LocalDateTime validAttempt = firstAttempt.plusHours(2).minusNanos(firstAttempt.getNano());


        if (verifyHash(password, hash)) {
            dbConnectorUser.setDateTime(username, currentAttempt.minusNanos(currentAttempt.getNano()).toString());
            return 1;
        } else {
            if (currentAttempt.isBefore(validAttempt) && attempts < 5) {
                attempts++;
                currentAttempt = LocalDateTime.now();
                return 0;
            }
        }

        return -1;
    }

}
