package com.logger;

public class LogRequest {
    private String stack;
    private String level;
    private String packageName;
    private String message;

    // Constructor
    public LogRequest(String stack, String level, String packageName, String message) {
        this.stack = stack;
        this.level = level;
        this.packageName = packageName;
        this.message = message;
    }

    // Getters & Setters
    public String getStack() { return stack; }
    public void setStack(String stack) { this.stack = stack; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) { this.packageName = packageName; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
