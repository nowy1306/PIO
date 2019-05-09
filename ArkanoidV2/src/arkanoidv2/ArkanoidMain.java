package arkanoidv2;

public class ArkanoidMain
{

    public static void main(String[] args)
    {
        ArkanoidFrame frame = new ArkanoidFrame("Arkanoid");
        Menu menu = new Menu();
        frame.add(menu);
        frame.pack();
        frame.setVisible(true);
    }
}
