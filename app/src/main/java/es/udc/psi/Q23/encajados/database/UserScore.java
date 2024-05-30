package es.udc.psi.Q23.encajados.database;

public class UserScore {

    private String username;
    private long score;

    public UserScore() {
        // Constructor vacío necesario para Firebase
    }

    public UserScore(String username, long score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public long getScore() {
        return score;
    }
}
