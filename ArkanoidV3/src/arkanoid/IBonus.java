package arkanoid;

public interface IBonus
{

    int DEFAULT_BONUS_SIZE = 15;

    /**
     *
     */
    void UseAbility();

    void move();

    int getID();

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    default int getSize()
    {
        return DEFAULT_BONUS_SIZE;
    }

    boolean getActive();

    void setActive(boolean a);

}
