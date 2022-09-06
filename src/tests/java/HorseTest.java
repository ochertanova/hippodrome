import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HorseTest {


    @Test
    public void createHorseThrowExceptionWhenNameIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 100.5, 200.0));
    }

    @Test
    public void checkMessageExceptionWhenNameIsNull() {
        try {
            Horse horse = new Horse(null, 100.0, 200.0);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Name cannot be null.", ex.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void createHorseThrowExceptionWhenNameIsNotCorrect(String argument) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(argument, 100.5, 200.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void checkMessageExceptionWhenNameIsNotCorrect(String argument) {
        try {
            Horse horse = new Horse(argument, 100.0, 200.0);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Name cannot be blank.", ex.getMessage());
        }
    }

    @Test
    public void createHorseThrowExceptionWhenSpeedIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("horse", -1.0, 200.0));
    }


    @Test
    public void createHorseThrowExceptionWhenNameDistanceIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("horse", 100.5, -200.0));
    }

    @Test
    public void checkMessageExceptionWhenSpeedIsNegative() {
        try {
            Horse horse = new Horse("horse", -100.0, 200.0);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Speed cannot be negative.", ex.getMessage());
        }
    }

    @Test
    public void checkMessageExeptionWhenNameDistabceIsNegative() {
        try {
            Horse horse = new Horse("horse", 100.0, -200.0);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Distance cannot be negative.", ex.getMessage());
        }
    }

    @Test
    public void checkGetName() {
        Horse horse = new Horse("Horse", 100.0, 200.0);
        Assertions.assertEquals("Horse", horse.getName());
    }

    @Test
    public void checkGetSpeed() {
        Horse horse = new Horse("Horse", 100.0, 200.0);
        Assertions.assertEquals(100.0, horse.getSpeed());
    }

    @Test
    public void checkGetDistance() {
        Horse horse = new Horse("Horse", 100.0, 200.0);
        Assertions.assertEquals(200.0, horse.getDistance());
    }

    @Test
    public void checkGetDistanceIfDistanceIsNull() {
        Horse horse = new Horse("Horse", 100.0);
        Assertions.assertEquals(0, horse.getDistance());
    }

    @Test
    public void checkParamInGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Horse", 1.1d, 5d);
            horse.move();
            horseMockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }

    @Test
    public void checkGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2d, 0.9d)).thenReturn(0.5d);
            Horse horse = new Horse("Horse", 1.1d, 5d);
            horse.move();
            Assertions.assertEquals(5.55d, horse.getDistance());
        }
    }
}
