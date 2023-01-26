package flyget;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.firstName + ' ' + this.lastName;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }
}
