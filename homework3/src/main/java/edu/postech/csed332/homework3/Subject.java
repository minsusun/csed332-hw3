package edu.postech.csed332.homework3;

import edu.postech.csed332.homework3.Event;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A subject that maintains a set of observers
 */
public class Subject {

    private final List<Observer> observers = new ArrayList<>();

    /**
     * Adds an observer to the set of observers, provided that it is not already added.
     *
     * @param o an observer
     */
    void addObserver(@NotNull Observer o) {
        if (!observers.contains(o))
            observers.add(o);
    }

    /**
     * Notify all the observers to indicate that this object has changed.
     *
     * @param arg an argument passed to the notify method of the observers
     */
    void notifyObservers(@NotNull Event arg) {
        for (Observer o : observers)
            o.update(this, arg);
    }
}
