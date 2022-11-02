public enum UserRole {
    MENEGER,
    USER,
    DIRECTOR;
    private String appName;
    void UseRole(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return this.appName;
    }

}
