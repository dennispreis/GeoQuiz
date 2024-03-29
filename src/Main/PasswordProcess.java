package Main;

import DAOs.MyTeacherDao;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;

//source from: https://www.stubbornjava.com/posts/hashing-passwords-in-java-with-bcrypt
public class PasswordProcess
{

    private int logRounds;
    private int attempts = 0;
    private MyTeacherDao dbConnectorUser = new MyTeacherDao();
    private LocalDateTime currentAttempt;

    public PasswordProcess(int logRounds)
    {
        this.logRounds = logRounds;
    }

    public void setCurrentAttempt(LocalDateTime currentAttempt)
    {
        this.currentAttempt = currentAttempt;
    }

    public LocalDateTime getCurrentAttempt()
    {
        return currentAttempt.minusNanos(currentAttempt.getNano());
    }

    public String hash(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    private boolean verifyHash(String password, String hash)
    {
        return BCrypt.checkpw(password, hash);
    }

    public int bruteForceCheck(String username, String password, String hash)
    {

        LocalDateTime invalidTime;
        LocalDateTime lastSession = LocalDateTime
                .parse(dbConnectorUser.getLastDateTime(username));
        LocalDateTime temp = LocalDateTime
                .parse(dbConnectorUser.getInvalidDateTime(username));
        String nowToString = LocalDateTime.now().toString();
        String nowNoNano = nowToString.replaceAll(nowToString.substring(nowToString.indexOf('.'), nowToString.length()), "");

        if (currentAttempt.isAfter(temp))
        {
            dbConnectorUser.setInvalidDateTime(username, getCurrentAttempt()
                    .plusHours(2).toString());

            dbConnectorUser.setAttempt(username, 0);

            dbConnectorUser.setLastDateTime(username, getCurrentAttempt().toString());

            invalidTime = LocalDateTime.parse(dbConnectorUser.getInvalidDateTime(username));

        }
        else
        {
            invalidTime = temp;
        }

        int lastAttempt = dbConnectorUser.getAttempt(username);

        if (lastSession.isAfter(invalidTime))
        {
            lastAttempt = 0;
        }

        if (lastAttempt != 0)
        {
            attempts = lastAttempt;
        }

        if (lastSession.isBefore(invalidTime) && !(attempts < 5))
        {
            return -1;
        }

        if (verifyHash(password, hash))
        {
            dbConnectorUser.setAttempt(username, 0);
            dbConnectorUser.setLastDateTime(username, nowNoNano);
            dbConnectorUser.setInvalidDateTime(username, nowNoNano);

            return 1;
        }
        else
        {

            if (lastSession.isBefore(invalidTime) && attempts < 5)
            {
                attempts++;
                dbConnectorUser.setLastDateTime(username, nowNoNano);

                dbConnectorUser.setAttempt(username, attempts);

                return 0;
            }
            else if (lastSession.isBefore(invalidTime) && !(attempts < 5))
            {
                return -1;
            }

        }
        return -1;
    }

}
