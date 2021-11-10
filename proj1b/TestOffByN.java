import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    OffByN offByN = new OffByN(5);
    @Test
    public void testoffbyn() {
        assertTrue(offByN.equalChars('x', 'y'));
        assertTrue(offByN.equalChars('y', 'x'));
        assertFalse(offByN.equalChars('a', 'z'));
        assertFalse(offByN.equalChars('a', 'a'));
    }
}
