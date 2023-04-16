package domain;

public enum Level {
    BEGINNER, GEVORDERD, EXPERT;

    public static Level returnEnum(String value) {
        if (value.equals("BEGINNER")) {
            return Level.BEGINNER;
        }
        if (value.equals("GEVORDERD")){
            return Level.GEVORDERD;
        }
        if (value.equals("EXPERT")){
            return Level.EXPERT;
        }
        return null;
    }
}
