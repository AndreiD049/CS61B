package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature  {
    private static final double energyLossOnMove = 0.03;
    private static final double energyLossOnStay = 0.01;
    /** red color */
    private static final int r = 34;
    /** green color */
    private static final int g = 0;
    /** blue color */
    private static final int b = 231;

    /**
     * Create a clorus with some initial energy value
     */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    /**
     * Default clorus constructor sets energy to 1
     */
    public Clorus() {
        this(1);
    }

    @Override
    public void move() {
        energy -= energyLossOnMove;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        energy = energy / 2;
        return  new Clorus(energy);
    }

    @Override
    public void stay() {
        energy -= energyLossOnStay;
    }

    /**
     * Following actions are performed by Cloruses:
     * 1. If there are no empty cells around, just stay
     * 2. If there are plips around, attach a random one
     * 3. If energy is more or equal to 1, replicate
     * 4. otherwise, move in a random empty direction
     * @return the action to be performed
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        ArrayDeque<Direction> emptyDirections = new ArrayDeque<>();
        ArrayDeque<Direction> plipDirections = new ArrayDeque<>();
        for (Direction dir : neighbors.keySet()) {
            Occupant occupant = neighbors.get(dir);
            if (occupant.name().equals("empty")) {
                emptyDirections.add(dir);
            } else if (occupant.name().equals("plip")) {
                plipDirections.add(dir);
            }
        }
        // Rule 1
        if (emptyDirections.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (!plipDirections.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipDirections));
        }

        // Rule 3
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyDirections));
        }

        return new Action(Action.ActionType.MOVE, randomEntry(emptyDirections));
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }
}
