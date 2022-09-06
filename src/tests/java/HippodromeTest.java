import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    @Test
    public void createHippodromeThrowExceptionWhenHorsesListIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void checkMessageExceptionWhenHorsesListIsNull() {
        try {
            Hippodrome hippodrome = new Hippodrome(null);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Horses cannot be null.", ex.getMessage());
        }
    }

    @Test
    public void createHippodromeThrowExceptionWhenHorsesListIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void checkMessageExceptionWhenHorsesListIsEmpty() {
        try {
            Hippodrome hippodrome = new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Horses cannot be empty.", ex.getMessage());
        }
    }

    @Test
    public void checkGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> hippodromeHorses = hippodrome.getHorses();
        for (int i = 0; i < 30; i++) {
            Assertions.assertEquals(horses.get(i), hippodromeHorses.get(i));
        }
    }

    @Test
    public void checkGetWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i != 10) {
                horses.add(new Horse("Horse_" + i, 20.0, 100 + i));
            } else {
                horses.add(new Horse("Winner", 50.0, 300.0));
            }
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        System.out.println(horses.get(10).getName());
        Assertions.assertEquals(horses.get(10), hippodrome.getWinner());
    }

    @Test
    public void checkMove() {
        ArrayList<Horse> horses = Mockito.spy(new ArrayList<Horse>());
        for (int i = 1; i <= 50; i++) {
            horses.add(Mockito.spy(new Horse("Horse" + i, i * 1.0d)));
        }
        Hippodrome hippodrome = Mockito.spy(new Hippodrome(horses));
        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }
}
