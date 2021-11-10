public class OffByN implements CharacterComparator {
    public int diff;
    public OffByN(int N) {
        diff = N;
    }

    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == diff) {
            return true;
        }
        return false;
    }
}
