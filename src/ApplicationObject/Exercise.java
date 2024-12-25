package ApplicationObject;

public class Exercise {
    private String name;

    // constructor for the class to create an instance of exercise
    // using an Image for the image and String for name
    public Exercise(String n)
    {
        this.name = n;
    }

    // returns the current value of name
    public String getName(){
        return this.name;
    }
}
