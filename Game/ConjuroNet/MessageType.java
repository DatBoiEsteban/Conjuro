package ConjuroNet;

public enum MessageType {
    PLAYER_NAME(0),
    CARDS_SELECTED(1);


    private int value;

    MessageType(int pValue) {
        this.value = pValue;
    }
    MessageType(String pValue) {
        this.value = Integer.parseInt(pValue);
    }
}
