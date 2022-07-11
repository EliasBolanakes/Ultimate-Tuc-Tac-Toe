package Model.Game;

public class Player {

    private String userName;
    private int wins;
    private int draws;
    private int gamesPlayed;

    Player(String userName,int wins,int draws,int gamesPlayed){

        this.userName=userName;
        this.wins=wins;
        this.draws=draws;
        this.gamesPlayed=gamesPlayed;
    }

    /**
     * @param result is ENUM with ordinal values of:
     *               ->LOSS = 0
     *               ->DRAW = 1
     *               ->WIN = 2
     */

    public void updateStats(int result){

        this.gamesPlayed++;

        if(result== GAME_RESULT.DRAW.ordinal())
            this.draws++;

        else if(result==GAME_RESULT.WIN.ordinal())
            this.wins++;
    }
}
