package domain;

public interface CustomerNames {
    String getFirstName();
    String getLastName();

    default String getFullName(){
        return "Firstname: " + getFirstName() + " Lastname: " + getLastName();
    }
}
