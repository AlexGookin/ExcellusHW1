package tests;

import animals.petstore.pet.types.Cat;
import org.junit.jupiter.api.*;
import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SnakeTests {

    private static Snake actualSnake;
    @BeforeAll
    public static void createAnimals()
    {
        actualSnake = new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.UNKNOWN, Breed.UNKNOWN);
    }


    @Test
    @Order(1)
    @DisplayName("Animal Test Type Tests Domestic")
    public void animalTypeTests()
    {
        assertEquals(AnimalType.DOMESTIC, actualSnake.getAnimalType(), "Animal Type Expected[" + AnimalType.DOMESTIC
                + "] Actual[" + actualSnake.getAnimalType() + "]");
    }

    @Test
    @Order(1)
    @DisplayName("Snake goes ssss Tests")
    public void snakeGoesSsssTest()
    {
        assertEquals("The snake goes ssss ssss ssss ssss ssss", actualSnake.speak(), "I was expecting ssss ssss ssss ssss ssss");
    }

    @Test
    @Order(1)
    @DisplayName("Snake Scales is it Hyperallergetic")
    public void snakeHyperAllergeticTests()
    {
        assertEquals("The snake is hyperallergetic!", actualSnake.snakeHypoallergenic(),
                "The snake is hyperallergetic!");
    }

    @Test
    @Order(1)
    @DisplayName("Snake has legs Test")
    public void legTests()
    {
        Assertions.assertNotNull(actualSnake.getNumberOfLegs());
    }

    @Test
    @Order(2)
    @DisplayName("Snake Gender Test Male")
    public void genderTestMale()
    {
        actualSnake = new Snake(AnimalType.WILD, Skin.UNKNOWN,Gender.MALE, Breed.UNKNOWN);
        assertEquals(Gender.MALE, actualSnake.getGender(), "Expecting Male Gender!");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Breed Test Maltese")
    public void genderSnakeBreed() {
        actualSnake = new Snake(AnimalType.WILD, Skin.UNKNOWN,Gender.FEMALE, Breed.MILK);
        assertEquals(Breed.MILK, actualSnake.getBreed(), "Expecting Breed Milk!");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Speak rattle Tests")
    public void snakeGoesRattleTest()
    {
        actualSnake = new Snake(AnimalType.WILD, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The snake goes rattle rattle rattle rattle", actualSnake.speak(), "I was expecting rattle");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Speak Psss Tests 1")
    public void snakeGoesPsssTest()
    {
        actualSnake = new Snake(AnimalType.UNKNOWN, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The snake goes Psss! Psss!", actualSnake.speak(), "I was expecting Psss");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Legs test")
    public void snakeLegs()
    {
        Snake snakeLegs = new Snake(AnimalType.UNKNOWN, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("Snakes have 0 legs!", snakeLegs.numberOfLegs(), "Expecting 0 legs!");
    }

    @Test
    @Order(2)
    @DisplayName("Set Snakes legs test")
    public void setCatLegs()
    {
        Snake snakeLegs = new Snake(AnimalType.UNKNOWN, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
        snakeLegs.setNumberOfLegs(1);
    }
}
