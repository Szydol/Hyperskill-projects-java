package contacts;

public class Person extends Contact {
    private String name;
    private String surname;
    private String birthDate;
    private String gender;

    public Person(){super();}

    static boolean validGender(String gender) {
        String pattern = "[MF]";
        return gender.matches(pattern) ? true : false;
    }

    static boolean validBirthDate(String birthDate) {
        String pattern = "\\d{4}-\\d{2}-\\d{2}";
        return birthDate.matches(pattern) ? true : false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if (!Person.validBirthDate(birthDate)) {
            birthDate = "[no data]";
            System.out.println("Bad birth date!");
        }
        this.birthDate = birthDate;
    }

    @Override
    public void getInfo() {
        System.out.println("Name: " + this.getName());
        System.out.println("Surname: " + this.getSurname());
        System.out.println("Birth date: " + this.getBirthDate());
        System.out.println("Gender: " + this.getGender());
        System.out.println("Number: " + this.getPhoneNumber());
        System.out.println("Time created: " + this.getCreationTime());
        System.out.println("Time last edit: " + this.getLastEditTime());
    }

    @Override
    public String getFields() {
        return "name, surname, birth, gender, number";
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name" :
                setName(value);
                break;
            case "surname" :
                setSurname(value);
                break;
            case "birth" :
                setBirthDate(value);
                break;
            case "gender" :
                setGender(value);
                break;
            case "number" :
                setPhoneNumber(value);
                break;
            default:
                System.out.println("There is no such field!");
                break;
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (!Person.validGender(gender)) {
            gender = "[no data]";
            System.out.println("Bad gender!");
        }
        this.gender = gender;
    }

    @Override
    public String getFullName() {
        return name + " " + surname;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + this.getPhoneNumber() + " " +birthDate + " " + gender;
    }
}