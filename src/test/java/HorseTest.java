import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    public void nullNameException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1));

        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n\n\n\n"})
    public void blankNameException(String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1,1));

        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void getName() throws NoSuchFieldException {
        Horse horse = new Horse("white", 1, 1);

        Horse.class.getDeclaredField("name");
    }

}

