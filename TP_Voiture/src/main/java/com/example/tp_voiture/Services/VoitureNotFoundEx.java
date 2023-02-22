package com.example.tp_voiture.Services;

import java.security.PrivilegedActionException;

public class VoitureNotFoundEx extends Throwable {
    /**
     * Constructs a new throwable with the specified cause and a detail
     * message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}).
     * This constructor is useful for throwables that are little more than
     * wrappers for other throwables (for example, {@link
     * PrivilegedActionException}).
     *
     * <p>The {@link #fillInStackTrace()} method is called to initialize
     * the stack trace data in the newly created throwable.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public VoitureNotFoundEx(String cause) {
        super(cause);
    }
}
