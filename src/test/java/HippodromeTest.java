import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void emptyHorseException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horses.add(new Horse("" + i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new  Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }



    @Test
    public void getWinner() {
        Horse horse1 = new Horse("hor1",1, 4);
        Horse horse2 = new Horse("hor2",1, 3);
        Horse horse3 = new Horse("hor3",1, 2);
        Horse horse4 = new Horse("hor4",1, 1);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));

        assertEquals(horse1, hippodrome.getWinner());

    }

}
