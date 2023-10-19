package edu.postech.csed332.homework3;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * A cell that has a number and a set of possibilities. A cell may have a number of observers,
 * and notifies events to the observers.
 */
public class Cell extends Subject {
    //TODO: add private members variables and private methods as needed
    Integer number = null;
    Set<Integer> possibility = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    Set<Group> groups = new HashSet<>();

    /**
     * Creates an empty cell with a given type. Initially, no number is assigned.
     */
    public Cell() {
        //TODO: implement this
    }

    /**
     * Returns the number of this cell.
     *
     * @return the number; Optional.empty() if no number assigned
     */
    @NotNull
    public Optional<Integer> getNumber() {
        //TODO: implement this
        return Optional.ofNullable(this.number);
    }

    /**
     * Sets a number of this cell and notifies NumberEvent(number, true), provided that the cell has 
     * previously no number and the given number to be set is in the set of possibilities.
     *
     * @param number the number
     * @return true if the number is set
     */
    public boolean setNumber(int number) {
        //TODO: implement this
        if (this.number == null && this.containsPossibility(number)) {
            this.number = number;
            this.notifyObservers(new NumberEvent(number, true));
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Removes the number of this cell and notifies NumberEvent(number, false), provided that the cell 
     * has a number.
     *
     * @return true if the number is removed
     */
    public boolean unsetNumber() {
        //TODO: implement this
        if (this.number == null) {
            return false;
        }
        else {
            int number = this.number;
            this.number = null;
            this.notifyObservers(new NumberEvent(number, false));
            return true;
        }
    }

    /**
     * Adds a group for this cell. This methods should also call addObserver(group).
     *
     * @param group a group
     */
    public void addGroup(@NotNull Group group) {
        addObserver(group);

        //TODO: implement this
        this.groups.add(group);
    }

    /**
     * Returns true if a given number is in the set of possibilities
     *
     * @param n a number
     * @return true if n is in the set of possibilities
     */
    public boolean containsPossibility(int n) {
        //TODO: implement this
        return this.possibility.contains(n);
    }

    /**
     * Returns true if the cell has no possibility
     *
     * @return true if the set of possibilities is empty
     */
    public boolean hasNoPossibility() {
        //TODO: implement this
        return this.possibility.isEmpty();
    }

    /**
     * Add the possibility of a given number, if possible, and notify ActivationEvent(true) if the set of
     * possibilities, previously empty, becomes non-empty. Also, if this number is already used by another
     * cell in the same group with this cell, the number cannot be added to the set of possibilities.
     *
     * @param number the number
     */
    public void addPossibility(int number) {
        //TODO: implement this
        if (!this.containsPossibility(number)) {
            if (this.groups.stream().map(group -> group.isAvailable(number)).toList().stream().allMatch(b -> b)) {
                this.possibility.add(number);
                if (this.possibility.size() == 1) {
                    notifyObservers(new ActivationEvent(true));
                }
            }
        }
    }

    /*
     * Remove the possibility of a given number, and notify ActivationEvent(false) if the set of
     * possibilities becomes empty.
     *
     * @param number the number
     */
    public void removePossibility(int number) {
        //TODO: implement this
        if (this.containsPossibility(number)) {
            this.possibility.remove(number);
            if (this.hasNoPossibility()) {
                this.notifyObservers(new ActivationEvent(false));
            }
        }
    }
}
