package utility;

import lombok.Data;

import java.util.Random;

@Data
public class PersonGenerator {

    String randFirstName;
    String randLastName;
    String randPosition;
    int randSalary;

    String [] firstName= {"Adam", "Bartosz", "Czesław","Dominik", "Edmund", "Filip", "Grzegorz",};



    String lastNameGenerator(){
        String[] vovelArr = {"a", "e", "i", "u", "y"};

        String[] consonantArr = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n",
                "p", "r", "s", "t", "w", "z", "sz", "cz", "ch", "ż"};
        String[] endingArr = {"wski","cki","dzki","rski","niec","wiec","szczak",};

        String finalName="";
        int charNumber = new Random().nextInt(1)+2;

        for (int i = 0; i <charNumber ; i++) {
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

    public static void main(String[] args) {
        PersonGenerator per = new PersonGenerator();
        System.out.println(per.lastNameGenerator());
    }
}
