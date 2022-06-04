package sg.edu.np.mad.p02.activity1;

public class User {
    String name;
    String description;
    int id;
    boolean followed;

    //Name
    public void setName(String n)
    {
        name = n;
    }

    public String getName(){
        return name;
    }

    //Description
    public void setDescription(String d){
        description = d;
    }

    public String getDescription(){
        return description;
    }

    //ID
    public void setId(int identity)
    {
        id = identity;
    }

    public int getId(){
        return id;
    }

    //Followed
    public void setFollowed(boolean f){
        followed = f;
    }

    public boolean getFollowed(){
        return followed;
    }

    public User(String n, String d, int identity, boolean f) {
        name = n;
        description = d;
        id = identity;
        followed = f;
    }
}
