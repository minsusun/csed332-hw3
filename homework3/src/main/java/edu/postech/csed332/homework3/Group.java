package edu.postech.csed332.homework3;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * A group that observes a set of cells, and maintains the invariant: if one
 * of the member has a particular value, none of its other members can have
 * the value as a possibility.
 */
public class Group implements Observer {
    //TODO: add private member variables and methods as necessary
    List<Cell> cells = new ArrayList<>();

    /**
     * Creates an empty group.
     */
    Group() {
        //TODO: implement this
    }

    /**
     * Adds a cell to this group.
     *
     * @param cell a cell to be added
     */
    void addCell(@NotNull Cell cell) {
        //TODO: implement this
        cell.addGroup(this);
        this.cells.add(cell);
    }

    /**
     * Returns true if a given number is available in the group
     *
     * @param number a number
     * @return true if no cell in the group has a given number.
     */
    public boolean isAvailable(int number) {
        //TODO: implement this
        return this.cells.stream().noneMatch(cell -> cell.getNumber().equals(Optional.of(number)));
    }


    /**
     * Updates the possibility of each cell in this group, according to NumberEvent.
     * If one of the cells has a particular value, none of its other members can have
     * the value as a possibility.
     *
     * @param caller the subject
     * @param arg    an argument
     */
    @Override
    public void update(@NotNull Subject caller, @NotNull Event arg) {
        //TODO: implement this
        if (arg instanceof NumberEvent) {
            int number = ((NumberEvent) arg).number();
            boolean set =((NumberEvent) arg).set();
            if (set) {
                this.cells.forEach(cell -> cell.removePossibility(number));
            }
            else {
                this.cells.forEach(cell -> cell.addPossibility(number));
            }
        }
    }
}
