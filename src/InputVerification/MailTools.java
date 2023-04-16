package InputVerification;

public class MailTools {

    public static boolean validateMailAddress(String mail) {

        // no input for mail
        if (mail == null) {
            return false;
        } else

        // email without @ is not vallid
        if (!mail.contains("@")) {
            return false;
        } else

        // no characters before @
        if (mail.split("@")[0].length() < 1) {
            return false;
        } else

        // no subdomain-tld delimiter
        if (mail.split("@")[1].split("\\.").length > 2) {
            return false;
        } else

        // no subdomain part
        if (mail.split("@")[1].split("\\.")[0].length() < 1) {
            return false;
        } else

        // no tld part
        if (mail.split("@")[1].split("\\.").length > 2) {
            return false;
        } else {
            return true;
        }
        // return true;

    }

}
