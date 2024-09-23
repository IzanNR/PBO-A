public class NumberDisplay {
    private int limit, value;

    public NumberDisplay(int rollOverLimit) {
        limit = rollOverLimit;
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayValue() {
        if(value < 10) {
            return "0" + value;
        } else {
            return "" + value;
        }
    }

    public void setValue(int replaceValue) {
        if((replaceValue >= 0) && (replaceValue < limit)) {
            value = replaceValue;
        }
    }

    public void increment() {
        value = (value + 1) % limit;
    }
}
