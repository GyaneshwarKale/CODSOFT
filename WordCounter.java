import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class WordCounter {
    public static void main(String[] args) {
        WordCounter w = new WordCounter();
        JFrame frame = new JFrame();
        frame.setSize(400, 200);

        Object[] options = { "File", "Text" };
        int choice = JOptionPane.showOptionDialog(frame, "       "+"Choose input type", "Word Counter",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            w.file();
        }
        else if (choice == JOptionPane.NO_OPTION) {
            w.text();
        }
    }

    public void file() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                int words = 1;
                FileInputStream fin = new FileInputStream(selectedFile);
                int data = 0;

                while ((data = fin.read()) != -1) {
                    if ((char) data == ' ' || (char)data=='\n') {
                        words += 1;
                    }
                }
                JOptionPane.showMessageDialog(null, "Number of words: " + words, "Word Counter", JOptionPane.INFORMATION_MESSAGE);
                fin.close();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void text() {
        JTextArea textArea = new JTextArea(10, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);

        int option = JOptionPane.showOptionDialog(null,scrollPane,"Enter Text",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,
                null,
                null);

        if (option == JOptionPane.OK_OPTION) {
            String input = textArea.getText();
            int words = 1;
            char[] a = new char[input.length()];
            for (int i = 0; i < input.length(); i++) {
                a[i] = input.charAt(i);
                if (a[i] == ' ' || a[i] == '\n') {
                    words += 1;
                }
            }
            JOptionPane.showMessageDialog(null, "Number of words: " + words, "Word Counter", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}