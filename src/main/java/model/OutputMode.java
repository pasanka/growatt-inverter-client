package model;

public enum OutputMode {

    SBU("0"), SUB("3"), SOL("1");

    private final String mode;

    OutputMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
