package Actions;

import impl.Pair;

public class Move extends Action {
    // Attributes
    private Pair pair;

    // Constructor
    public Move(Pair pair){
        this.pair = pair;
    }

    // Methods
    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }
}
