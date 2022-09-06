import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {

    @Disabled("Тест проверен. При необходимости запустить")
    @Timeout(value = 22)
    @Test
    public void checkTimeForTest() {
        String[] str = new String[2];
        try {
            Main.main(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
