package InputVerification;

public class MailTools {

    public static boolean validateMailAddress(String mail) {

        // no input for mail
        if (mail == null) {
            return false;
        }

        // email without @ is not vallid
        if (!mail.contains("@")) {
            return false;
        }

        // no characters before @
        if (mail.split("@")[0].length() < 1) {
            return false;
        }

        // no subdomain-tld delimiter
        if (mail.split("@")[1].split("\\.").length > 2) {
            return false;
        }

        // no subdomain part
        if (mail.split("@")[1].split("\\.")[0].length() < 1) {
            return false;
        }

        // no tld part
        return mail.split("@")[1].split("\\.").length >= 2;
    }

}
