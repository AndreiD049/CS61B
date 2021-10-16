package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TestClorus {
    @Test
    public void testConstructor() {
        Clorus c1 = new Clorus(1.5);
        assertEquals(c1.energy(), 1.5, 0.01);
        Clorus c2 = new Clorus();
        assertEquals(c2.energy(), 1, 0.01);
    }

    @Test
    public void testColor() {
        Clorus c1 = new Clorus(1.5);
        assertEquals(new Color(34, 0, 231), c1.color());
    }

    @Test
    public void testEnergyLoss() {
        Clorus c1 = new Clorus(1.5);
        assertEquals(c1.energy(), 1.5, 0.01);
        c1.move();
        assertEquals(c1.energy(), 1.47, 0.001);
        c1.stay();
        assertEquals(c1.energy(), 1.46, 0.001);
    }

    @Test
    public void testAttack() {
        Plip p = new Plip(1);
        Clorus c1 = new Clorus();
        assertEquals(c1.energy(), 1, 0.01);
        c1.attack(p);
        assertEquals(c1.energy(), 2, 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c1 = new Clorus(1.5);
        assertEquals(c1.energy(), 1.5, 0.01);
        Clorus c2 = c1.replicate();
        assertEquals(c1.energy(), 0.75, 0.01);
        assertEquals(c2.energy(), 0.75, 0.01);
        assertNotSame(c1, c2);
    }

    @Test
    public void testChooseStay() {
        HashMap<Direction, Occupant> allOccupied = new HashMap<>();
        allOccupied.put(Direction.TOP, new Plip());
        allOccupied.put(Direction.BOTTOM, new Plip());
        allOccupied.put(Direction.LEFT, new Plip());
        allOccupied.put(Direction.RIGHT, new Plip());
        Clorus c = new Clorus(2);
        Action a = c.chooseAction(allOccupied);
        assertEquals(a, new Action(Action.ActionType.STAY));
    }

    @Test
    public void testChooseAttack() {
        HashMap<Direction, Occupant> map = new HashMap<>();
        map.put(Direction.TOP, new Plip());
        map.put(Direction.BOTTOM, new Empty());
        map.put(Direction.LEFT, new Empty());
        map.put(Direction.RIGHT, new Empty());
        Clorus c = new Clorus();
        Action a = c.chooseAction(map);
        assertEquals(a, new Action(Action.ActionType.ATTACK, Direction.TOP));
    }

    @Test
    public void testChooseReplicate() {
        HashMap<Direction, Occupant> map = new HashMap<>();
        map.put(Direction.TOP, new Impassible());
        map.put(Direction.BOTTOM, new Empty());
        map.put(Direction.LEFT, new Empty());
        map.put(Direction.RIGHT, new Empty());
        Clorus c = new Clorus(1.1);
        Action a = c.chooseAction(map);
        assertNotEquals(a, new Action(Action.ActionType.STAY));
        assertEquals(a.type, Action.ActionType.REPLICATE);
        assertNotEquals(a.dir, Direction.TOP);
    }

    @Test
    public void testChooseMove() {
        HashMap<Direction, Occupant> map = new HashMap<>();
        map.put(Direction.TOP, new Empty());
        map.put(Direction.BOTTOM, new Impassible());
        map.put(Direction.LEFT, new Empty());
        map.put(Direction.RIGHT, new Empty());
        Clorus c = new Clorus(0.99);
        Action a = c.chooseAction(map);
        assertEquals(a.type, Action.ActionType.MOVE);
        assertNotEquals(a.dir, Direction.BOTTOM);
    }

}
