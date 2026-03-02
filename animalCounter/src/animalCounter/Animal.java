//Jordan Brown, CIS 407, Animal Counter
package animalCounter;
public abstract class Animal {
    protected int count;

    public void resetCount() {
        count = 0;
    }

    public int getCount() {
        return count;
    }
}
