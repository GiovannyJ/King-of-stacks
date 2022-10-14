
/**
 * Driver.java - test code for our Stack ADT
 */
public class Driver
{
    public static void main(String[] args)
    {
        KingofStacks game = new KingofStacks("Gio", "Nicole", 120);
        for(int i=0; i<121; i++){
            game.Round();
        }
        game.GameOver();
    }

}
