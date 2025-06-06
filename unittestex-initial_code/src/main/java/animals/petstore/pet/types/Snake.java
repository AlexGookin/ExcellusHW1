package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;


public class Snake extends Pet implements PetImpl {

    /* Properties */
    private int numberOfLegs;
    private Breed breed;

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild dog
     * @param skinType The {@link Skin} of the dog
     * @param gender The {@link Gender} of the dog
     * @param breed The type of dog {@link Breed}
     */
    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed)
    {
        this(animalType, skinType, gender, breed, new BigDecimal(0));
    }

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild dog
     * @param skinType The {@link Skin} of the dog
     * @param gender The {@link Gender} of the dog
     * @param breed The type of dog {@link Breed}
     * @param cost The cost of the dog
     */
    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost)
    {
        this(animalType, skinType, gender, breed, cost, 0);
    }

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild dog
     * @param skinType The {@link Skin} of the dog
     * @param gender The {@link Gender} of the dog
     * @param breed The type of dog {@link Breed}
     * @param cost The cost of the dog
     * @param petStoreId  The pet store id
     */
    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost, int petStoreId)
    {
        super(PetType.SNAKE, cost, gender, petStoreId);
        super.skinType = skinType;
        super.animalType = animalType;
        this.numberOfLegs = 0;
        this.breed = breed;
    }

    /**
     * Is the dog allergy friendly determined by skin type
     * @return A message that tells if the cat is hypoallergenic
     */
    public String snakeHypoallergenic()
    {
        return super.petHypoallergenic(this.skinType).replaceAll("pet", "snake");
    }

    /**
     * What does the dog say depends on {@link AnimalType} - Domestic, Wild, Farm, or Unknown
     * @return what dogs would speak
     */
    public String speak()
    {
        String language;
        switch(animalType){
            case DOMESTIC:
                language = "The snake goes ssss ssss ssss ssss ssss";
                break;
            case WILD:
                language = "The snake goes rattle rattle rattle rattle";
                break;
            default:
                language = "The snake goes " + super.getPetType().speak + "! " + super.getPetType().speak + "!";
        }
        return language;
    }

    public String numberOfLegs()
    {
        return "Snakes have " + numberOfLegs + " legs!";
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Breed getBreed() {
        return this.breed;
    }

    public AnimalType getAnimalType() {
        return this.animalType;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "The snake is " +  super.animalType + "!\n" +
                "The snakes breed is " + this.getBreed() + "!\n" +
                this.snakeHypoallergenic() + "!\n" +
                this.speak() + "\n" +
                this.numberOfLegs();
    }


}
