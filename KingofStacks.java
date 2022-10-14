import java.util.EmptyStackException;
import java.util.Random;

/**
 * Game made using any implementation StackInterfaces
 */
public class KingofStacks {
    private String player1; // player 1 name
    private String player2; // player 2 name
    private int n; // input amount of rounds
    private final static int MIN_N = 30; // min amount of rounds allowed
    private final static int MAX_N = 300; // max amount of rounds allowed
    private int roundNum = 0; // current round
    private StackInterface<Disk> stack1 = new VectorStack<Disk>(); // Stack interface objects
    private StackInterface<Disk> stack2 = new VectorStack<Disk>();
    private StackInterface<Disk> stack3 = new VectorStack<Disk>();
    
    // scores for player 1 and 2
    private int player1_score = 0;
    private int player2_score = 0;

    /**
     *  default construtor - creates King of stacks game with MIN_N
     *  @param player1_name - name of first player
     *  @param player2_name - name of second player
     */
    public KingofStacks(String player1_name, String player2_name) {
        this(player1_name, player2_name, MIN_N);
    }

    /**
     * paramaterized contrustor - creates King of stacks game with given amount of rounds
     * @param player1_name - name to be set for player 1
     * @param player2_name - name to be set for player 2
     * @param rounds       - amount of rounds to be played
     */
    public KingofStacks(String player1_name, String player2_name, int rounds) {
        // if the input is less than the min allowed then set n to the min
        if (rounds < MIN_N) {
            player1 = player1_name; // setting player names
            player2 = player2_name;
            n = MAX_N;
        } else if (rounds > MAX_N) { // if input is greater than max allowed then set n to max
            player1 = player1_name;
            player2 = player2_name;
            n = MAX_N;
        } else { // if input is a number in between the min and max set n to that number
            player1 = player1_name;
            player2 = player2_name;
            n = rounds;
        }
    }

    /**
     * Turn() - pushes a new disk obj to a stack
     * @param player - name of player whos turn it is
     * @param player_stack  - stack being interacted with during turn
     */
    public void Turn(String player, StackInterface<Disk> player_stack) {
        Disk playerdisk = new Disk(player); // create new disk with player name
        player_stack.push(playerdisk); // push that disk to the stack
    }

    /**
     * CheckGameOver() - helper method for round checks to see if the game can contiue
     *  @return - FLASE if the game is NOT over TRUE if it is over
     */
    private boolean CheckGameOver() {
        if (roundNum >= n) { // if the round number is greater or = to the amount set
            return true; // true the game is over
        }
        return false; // false the game is not over
    }

    /**PopTimer() - helper method for round checks the round and pops off of stack
    *@return - returns String of what stacks had values popped off
    *@throws - EmptyStackException if stack is selected but it empty return None string
    */
    private String PopTimer() {
        try {
            if (roundNum % 3 == 0 & roundNum % 5 == 0 & roundNum % 8 == 0) {// condition to pop all stacks at the same time
                stack1.pop();
                stack2.pop();
                stack3.pop();
                return "Stack 1 and Stack 2 and Stack 3";
            } else if (roundNum % 3 == 0 & roundNum % 5 == 0) { // condiiton to pop stack 1 and 2
                stack1.pop();
                stack2.pop();
                return "Stack 1 and Stack 2";
            } else if (roundNum % 3 == 0 & roundNum % 8 == 0) { // condition to pop stack 1 and 3
                stack1.pop();
                stack3.pop();
                return "Stack 1 and Stack 3";
            } else if (roundNum % 5 == 0 & roundNum % 8 == 0) { // condition to pop stack 2 and 3
                stack2.pop();
                stack3.pop();
                return "Stack 2 and Stack 3";
            } else if (roundNum % 3 == 0) { // condition to pop stack 1
                stack1.pop();
                return "Stack 1";
            } else if (roundNum % 5 == 0) { // condition to pop stack 2
                stack2.pop();
                return "Stack 2";
            } else if (roundNum % 8 == 0) { // condition to pop stack 3
                stack3.pop();
                return "Stack 3";
            } else { // if no conditions are met then return none
                return "None";
            }
        } catch (EmptyStackException e) {
            return "None"; // none if the stack is empty
        }
    }

    /**
     * RandomStack() - helper method selects random stack 1-3 for player to push disk to
     * @return - the stack that was selected randomly
     */
    private StackInterface<Disk> RandomStack() {
        Random rand = new Random();
        int stackNum = rand.nextInt(3);
        if (stackNum == 0) {
            return stack1;
        } else if (stackNum == 1) {
            return stack2;
        } else {
            return stack3;
        }
    }

    /**
     * StringStack() - helper method to format the print string in Round Method
     * @param player_stack - the stack that was called during the round
     * @return - a string version of the stack called during the round
     */
    private String StringStack(StackInterface<Disk> player_stack){
        //if the input stack is equal to object the return it as a string
        if(player_stack == stack1){
            return "Stack 1";
        }else if(player_stack == stack2){
            return "Stack 2";
        }else{ //there are only three stacks so the if the other two stmts fail then it must by this
            return "Stack 3";
        }
    }

    /**
     * Round() - similates a round of the game comprised of one turn for each player
     */
    public void Round() {
        // check game over
        if (CheckGameOver() == false) {
            // each player gets a random stack value saved so it can be passed later
            StackInterface<Disk> player1_stack = RandomStack();
            StackInterface<Disk> player2_stack = RandomStack();

            // turn for each player
            if(roundNum%2 != 0){    //odd rounds player 1 goes first
                Turn(player1, player1_stack);
                Turn(player2, player2_stack);
            }else{                          //even rounds player 2 goes first
                Turn(player2, player2_stack);
                Turn(player1, player1_stack);
            }

            roundNum++;// turns are done increment the round

            // check if roundNum is one of conditions then pop off stack
            String poppedStack = PopTimer();

            System.out.println("Turn " + roundNum
                    + ": Player: " + player1 + " pushes disk onto " + StringStack(player1_stack) +
                    "\n\tPlayer: " + player2 + " pushes disk onto " + StringStack(player2_stack) +
                    "\n\tA disk was popped from Stack: " + poppedStack+"\n");
        }

    }


    /**
     * CountDisks() - helper method to track the score of the players
     * @param stack - the stack to be counted
     */
    private void CountDisks(StackInterface<Disk> stack) {
        // while the stack is not empty
        while (!stack.isEmpty()) {
            // using a disk obj and casted disk object to the item popped off the stack
            Disk disk = (KingofStacks.Disk) stack.pop();

            // depending on the name that the disk holds increment the score of that player
            if (disk.GetName() == player1) {
                player1_score++;
            } else if (disk.GetName() == player2) {
                player2_score++;
            }
        }
    }

    /**
     * GameOver() - count the stacks and see who is the winner
     */
    public void GameOver() {
        if (CheckGameOver() == true) { // if the game is over count disks on stacks
            CountDisks(stack1);
            CountDisks(stack2);
            CountDisks(stack3);

            // print the scores of each player
            System.out.println(player1 + " Score: " + player1_score +
                    "\n" + player2 + " Score: " + player2_score);

            // decide winner by seeing who has higher score if score is even then its a tie
            if (player1_score == player2_score) {
                System.out.println("ITS A TIE");
            } else if (player1_score > player2_score) {
                System.out.println(player1 + " Wins");
            } else {
                System.out.println(player2 + " Wins");
            }
        } else {
            System.out.println("The game continues");
        }
    }

    // private class for Disk
    private class Disk {
        // property
        private String name;

        // default constructor
        public Disk(String playerName) {
            name = playerName;
        }

        /**
         * GetName() - method used to return the name contained in the disk
         * @return - string of the name
         */
        public String GetName() {
            return name;
        }
    }
}