package utility;

import lombok.Data;

import java.util.Random;

@Data
public class PersonGenerator {


    public String firstNameGenerator() {
        String[] firstNameArr = {"Adam", "Bartosz", "Czesław", "Dominik", "Edmund", "Filip", "Grzegorz"};
        int firstNameArrIndex = new Random().nextInt(firstNameArr.length);
        String finalFirstName = (firstNameArr[firstNameArrIndex]);
        return finalFirstName;
    }

    public String lastNameGenerator() {
        String[] vovelArr = {"a", "e", "i", "u", "y"};

        String[] consonantArr = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n",
                "p", "r", "s", "t", "w", "z", "sz", "cz", "ch", "ż"};
        String[] endingArr = {"wski", "cki", "dzki", "rski", "niec", "wiec", "szczak",};

        String finalName = "";
        int charNumber = new Random().nextInt(1) + 2;

        for (int i = 0; i < charNumber; i++) {
            int vovelArrIndex = new Random().nextInt(vovelArr.length);
            int consonantArrIndex = new Random().nextInt(consonantArr.length);
            int endingArrIndex = new Random().nextInt(endingArr.length);

            String consonant = (consonantArr[consonantArrIndex]);
            String vovel = (vovelArr[vovelArrIndex]);
            String ending = (endingArr[endingArrIndex]);

            String syllabe = consonant + vovel;
            String firstLeter = syllabe.substring(0, 1).toUpperCase() + syllabe.substring(1);

            if (i == 0) {
                finalName = firstLeter;
            } else if (i == charNumber - 1) {
                finalName += syllabe + ending;
            } else
                finalName += syllabe;
        }

        return finalName;
    }

    public String positionGenerator() {
        String[] positionArr = {"Junior", "Regular", "Senior"};
        int positionArrIndex = new Random().nextInt(positionArr.length);
        String finalPosition = (positionArr[positionArrIndex]);

        return finalPosition;
    }

    public int salaryGenerator(String position) {

        int finalSalary = 0;

        switch (position) {
            case "Junior":
                finalSalary = new Random().nextInt(1000) + 1800;
                break;
            case "Regular":
                finalSalary = new Random().nextInt(1000) + 3000;
                break;
            case "Senior":
                finalSalary = new Random().nextInt(1000) + 4500;
                break;
        }
        return finalSalary;
    }
}
