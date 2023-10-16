package edu.postech.csed332.homework3;

import edu.postech.csed332.homework3.Event;
import org.jetbrains.annotations.NotNull;

/**
 * An observer that can observe subjects.
 */
interface Observer {
    /**
     * This method is called whenever the observed object is changed.
     *
     * @param caller the subject
     * @param arg    an argument passed to caller
     */
    void update(@NotNull Subject caller, @NotNull Event arg);
}