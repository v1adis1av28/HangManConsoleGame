package src;

public class Player {
    private Integer WinsCount;
    private Integer LosesCount;
    private Integer GamesCount;
    public Player()
    {
        this.WinsCount = 0;
        this.LosesCount = 0;
        this.GamesCount = 0;
    }
    public Integer GetWinsCount()
    {
        return WinsCount;
    }
    public Integer GetGamesCount()
    {
        return GamesCount;
    }
    public Integer GetLosesCount()
    {
        return LosesCount;
    }
    public void AddWin()
    {
        this.WinsCount++;
    }
    public void AddGame()
    {this.GamesCount++;}
    public void AddLose()
    {
        this.LosesCount++;
    }

}
