package com.lior;

import com.badlogic.gdx.utils.Disposable;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger implements Disposable {

    private static Logger instance = new Logger();

    private SimpleDateFormat timeFormat = new SimpleDateFormat(
            "YYYY-MM-dd HH:mm:ss");

    private String msgFormat = "%s %s: %s";


    private LogLevel level = LogLevel.ALL;

    public enum LogLevel {
        NONE, INFO, ERROR, DEBUG, ALL
    }

    private Logger() {}

    public static Logger getInstance() {
        return instance;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void info(String tag, String message) {
        message(LogLevel.INFO, tag, message);
    }


    public void debug(String tag, String message) {
        message(LogLevel.DEBUG, tag, message);
    }


    private void message(LogLevel level, String tag, String message) {
        if (getLevel() == LogLevel.NONE) {
            return;
        }

        if (level.ordinal() <= getLevel().ordinal()) {
            String output = String.format(msgFormat, level, tag, message);

            if (level == LogLevel.ERROR) {
                error(output);
            } else {
                print(output);
            }
        }
    }

    private void print(String message) {
        Date now = new Date();
        String output = "[" + timeFormat.format(now) + "]: " + message;

        System.out.println(output);


    }

    private void error(String message) {
        Date now = new Date();
        String output = "[" + timeFormat.format(now) + "]: " + message;
        System.err.println(output);

    }

    @Override
    public void dispose() {

    }
}