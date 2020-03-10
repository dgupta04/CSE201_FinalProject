package game;

import java.io.Serializable;

public class User implements Serializable {

    private Map map;

    public User(){
        this.map = new Map();
    }
}
