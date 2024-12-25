package ApplicationObject;

public class User {
    private String username;
    private Float height;
    private Float weight;
    private String dob;
    private String gender;
    //constructor for User class
    //takes in a username, height, weight, age and gender
    public User(String username, Float height, Float weight, String dob, String gender){
        this.username = username;
        this.height = height;
        this.weight = weight;
        this.dob = dob;
        this.gender = gender;
    }

    // returns username
    public String getUsername() {
        return username;
    }

    //returns height
    public Float getHeight(){
        return height;
    }

    //returns weight
    public Float getWeight(){
        return weight;
    }

    //returns age
    public String getDob(){
        return dob;
    }

    // returns gender
    public String getGender() {
        return gender;
    }

    //sets inputted username value as username
    public void setUsername(String username) {
        this.username = username;
    }

    //sets inputted height value as height
    public void setHeight(Float newHeight){
        height = newHeight;
    }

    //sets inputted weight value as weight
    public void setWeight(Float newWeight){
        weight = newWeight;
    }

    //sets inputted age value as age
    public void setDob(String newDob){ dob = newDob;}

    //sets inputted gender value as gender
    public void setGender(String gender) {
        this.gender = gender;
    }
}
