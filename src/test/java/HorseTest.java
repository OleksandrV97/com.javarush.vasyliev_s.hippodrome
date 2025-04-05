import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));

        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n\n\n\n"})
    public void blankNameException(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));

        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("white", 1, 1);

        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("white", nameValue);
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("white", 321, 1);
        assertEquals(321, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("white", 1, 345);
        assertEquals(345, horse.getDistance());
    }

    @Test
    public void zeroDistanceDefault() {
        Horse horse = new Horse("white", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveUsesGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("white", 321, 345).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0})
    void move(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("white", 321, 345);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();
            assertEquals(345 + 321 + random, horse.getDistance());
        }

    }
}

