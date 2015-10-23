package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logger {

    /**
     * Static string for loglevel information.
     */
    private static final String _debug = "[DEBUG] ";
    private static final String _info = "[INFO] ";
    private static final String _warning = "[WARNING] ";
    private static final String _error = "[ERROR] ";
    private static final String _MSG = " Message: ";

    /**
     * Enum to define log levels.
     */
    public static enum _outputLevel {
        DEBUG, INFO, WARNING, ERROR, NONE
    }

    /**
     * Output levels for console and log file.
     */
    private static _outputLevel _consoleOutputLevel;
    private static _outputLevel _logFileOutputLevel;

    /**
     * Path to log file.
     */
    private static Path _file;

    /**
     * The static logger instance.
     */
    @SuppressWarnings("unused")
    private static Logger _logger = new Logger();

    /**
     * Private constructor.
     */
    private Logger() {
        _consoleOutputLevel = _outputLevel.INFO;
        _logFileOutputLevel = _outputLevel.DEBUG;

        _file = Paths.get("log.txt");

        info("Logger initialised.");
    }

    /**
     * Internal function to append date and log to either console or log file
     * based on log level.
     *
     * @param message
     *            The message to log.
     * @param logLevel
     *            The log level of the message.
     */
    private static void log(String message, _outputLevel logLevel) {
        Date date = new Date();

        final String messageToLog = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ").format(date) + message + "\n";

        // Log to console
        if (_consoleOutputLevel.compareTo(logLevel) <= 0) {
            System.out.print(messageToLog);
        }

        // Log to logfile
        if (_logFileOutputLevel.compareTo(logLevel) <= 0) {
            try {
                Files.write(_file, messageToLog.getBytes("UTF-8"), StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                Logger.error("Failed to write message to log file.");
            }
        }
    }

    /**
     * Called by debug() info() warning() and error() methods. Provides the
     * class and method which called them.
     *
     * @return The method and class which called any of the above methods.
     */
    private static String getClassAndMethod() {
        String message;
        message = Thread.currentThread().getStackTrace()[3].getClassName();
        message += " -> " + Thread.currentThread().getStackTrace()[3].getMethodName();
        return message;
    }

    /**
     * Set console output level.
     *
     * @param outputLevel
     *            The output level for the console.
     */
    public static void setConsoleOutputLevel(_outputLevel outputLevel) {
        _consoleOutputLevel = outputLevel;
    }

    /**
     * Get console output level.
     * @return Current Console Output Level
     */
    public static _outputLevel getConsoleOutputLevel() {
        return _consoleOutputLevel;
    }

    /**
     * Set log file output level.
     *
     * @param outputLevel
     *            The output level for the log file.
     */
    public static void setLogFileOutputLevel(_outputLevel outputLevel) {
        _logFileOutputLevel = outputLevel;
    }

    /**
     * Get log file output level.
     * @return Current Log File Level
     */
    public static _outputLevel getLogFileOutputLevel() {
        return _logFileOutputLevel;
    }

    /**
     * Log debug messages.
     *
     * @param message
     *            The message to log.
     */
    public static void debug(final String message) {
        String messageToLog = _debug;

        messageToLog += getClassAndMethod();
        messageToLog += _MSG + message;

        log(messageToLog, _outputLevel.DEBUG);
    }

    /**
     * Log info messages.
     *
     * @param message
     *            The message to log.
     */
    public static void info(final String message) {
        String messageToLog = _info;

        messageToLog += getClassAndMethod();
        messageToLog += _MSG + message;

        log(messageToLog, _outputLevel.INFO);
    }

    /**
     * Log warning messages.
     *
     * @param message
     *            The message to log.
     */
    public static void warning(final String message) {
        String messageToLog = _warning;

        messageToLog += getClassAndMethod();
        messageToLog += _MSG + message;

        log(messageToLog, _outputLevel.WARNING);
    }

    /**
     * Log error messages.
     *
     * @param message
     *            The message to log.
     */
    public static void error(final String message) {
        String messageToLog = _error;

        messageToLog += getClassAndMethod();
        messageToLog += _MSG + message;

        log(messageToLog, _outputLevel.ERROR);
    }
}
