import java.util.Random;
import javax.swing.JOptionPane;

class NumberGame {
    int random_no = 0;
    int guess_no = 0;
    int rounds = 1;
    int wins = 0;

    public static void main(String[] args)
    {
        NumberGame n = new NumberGame();
        n.random();
        n.operate();
        n.end();
    }

    public void random()
    {
        Random d = new Random();
        random_no = d.nextInt(10) + 1;
        JOptionPane.showMessageDialog(null, "                               [NUMBER GAME]\n                  Number is being auto-generated.\nYou have 3 attempts to guess the auto-generated number.");
    }

    public void guess()
    {
        guess_no = Integer.parseInt(JOptionPane.showInputDialog("Guess the number between 1 to 10:"));
    }

    public void check()
    {
        if (guess_no == random_no)
        {
            JOptionPane.showMessageDialog(null, "YEHH! you guessed the auto-generated number:)");
            wins +=1;
        } 
        else
        {
            JOptionPane.showMessageDialog(null, "OOPS! you guessed the incorrect number:(");
        }
    }

    public void operate()
    {
        for (int i = 1; i <= 3; i++)
        {
            guess();
            check();
            if(guess_no==random_no){
                break;
            }
            if (i == 3 && guess_no != random_no)
            {
                JOptionPane.showMessageDialog(null, "Auto-generated number was: " + random_no);
                break;
            }
        }

        int option = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION)
        {
            random();
            operate();
            rounds += 1;
        }
    }

    public void end()
    {
        JOptionPane.showMessageDialog(null, "_______________________\n\nTotal rounds played: " + rounds +
                "\n\nTotal wins: " + wins + "\n\n             Thank You!\n\n_______________________\n");
    }
}