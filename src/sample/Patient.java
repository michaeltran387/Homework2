package sample;

import java.util.UUID;

public class Patient {
    private String name;
    private UUID id;
    private int age;
    private char gender;
    private String bloodType;
    private float weight;
    private String height;

    public Patient(){

    }

    public Patient (String a, UUID b, int c, char d, String f, float g, String h){
        name = a;
        id = b;
        age = c;
        gender = d;
        bloodType = f;
        weight = g;
        height = h;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public float getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    @Override
    public String toString() {
        String dummy = "Name: " + name + "\nAge: " + age + "\nGender: " + gender + "\nBlood Type: " + bloodType + "\nWeight: " + weight + "\nHeight: " + height;
        return dummy;
    }
}
