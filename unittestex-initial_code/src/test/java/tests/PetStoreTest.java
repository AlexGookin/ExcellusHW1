package tests;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Cat;
import animals.petstore.pet.types.Dog;
import animals.petstore.pet.types.Snake;
import animals.petstore.store.DuplicatePetStoreRecordException;
import animals.petstore.store.PetNotFoundSaleException;
import animals.petstore.store.PetStore;
import number.Numbers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class PetStoreTest
{
    private static PetStore petStore;

    @BeforeEach
    public void loadThePetStoreInventory()
    {
        petStore = new PetStore();
        petStore.init();
    }

    @Test
    @DisplayName("Inventory Count Test")
    public void validateInventory()
    {
        assertEquals(7, petStore.getPetsForSale().size(),"Inventory counts are off!");
    }

    @Test
    @DisplayName("Print Inventory Test")
    public void printInventoryTest()
    {
        petStore.printInventory();
    }

    @Test
    @DisplayName("Sale of Poodle Remove Item Test")
    public void poodleSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;
        Dog poodle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1);

        // Validation
        petStore.soldPetItem(poodle);
        assertEquals(inventorySize, petStore.getPetsForSale().size(), "Expected inventory does not match actual");
    }

    @Test
    @DisplayName("Poodle Duplicate Record Exception Test")
    public void poodleDupRecordExceptionTest() {
        petStore.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1));
        Dog poodle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1);

        // Validation
        String expectedMessage = "Duplicate Dog record store id [1]";
        Exception exception = assertThrows(DuplicatePetStoreRecordException.class, () ->{
            petStore.soldPetItem(poodle);});
        assertEquals(expectedMessage, exception.getMessage(), "DuplicateRecordExceptionTest was NOT encountered!");

    }

    @Test
    @DisplayName("Sale of Sphynx Remove Item Test")
    public void sphynxSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Cat sphynx = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"),2);
        Cat removedItem = (Cat) petStore.soldPetItem(sphynx);

        // Validation
        assertEquals(inventorySize, petStore.getPetsForSale().size(), "Expected inventory does not match actual");
        assertEquals(sphynx.getPetStoreId(), removedItem.getPetStoreId(), "The cat items are identical");
    }

    @Test
    @DisplayName("Sale of Milk Remove Item Test")
    public void milkSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Snake milk = new Snake(AnimalType.WILD, Skin.SCALES, Gender.FEMALE, Breed.MILK,
                new BigDecimal("1000.00"),4);
        Snake removedItem = (Snake) petStore.soldPetItem(milk);

        // Validation
        assertEquals(inventorySize, petStore.getPetsForSale().size(), "Expected inventory does not match actual");
        assertEquals(milk.getPetStoreId(), removedItem.getPetStoreId(), "The snake items are identical");
    }


    @Test
    @DisplayName("Add Duplicate Item")
    public void addDuplicateItem() throws PetNotFoundSaleException, DuplicatePetStoreRecordException {
        Cat cat = new Cat(AnimalType.DOMESTIC, Skin.HAIR, Gender.MALE, Breed.BURMESE, new BigDecimal("65.00"),1);
        petStore.initAddDuplicateItem(cat);
    }

    @Test
    @DisplayName("Throw PetNotFoundSaleException for storeId 0")
    public void shouldThrowPetNotFoundException() {
        Dog dog = new Dog(AnimalType.DOMESTIC, Skin.HAIR, Gender.MALE, Breed.GERMAN_SHEPARD,
                new BigDecimal("50.00"), 0);

        assertThrows(PetNotFoundSaleException.class, () -> {
            petStore.soldPetItem(dog);
        });
    }

    @Test
    @DisplayName("Throw DuplicatePetStoreRecordException for duplicate Cat")
    public void shouldThrowDuplicatePetStoreRecordExceptionForCat() {
        Cat cat = new Cat(AnimalType.DOMESTIC, Skin.HAIR, Gender.MALE, Breed.BURMESE,
                new BigDecimal("65.00"), 1);

        petStore.init();  // adds cat with ID 1
        petStore.addPetInventoryItem(cat);  // add duplicate

        assertThrows(DuplicatePetStoreRecordException.class, () -> {
            petStore.soldPetItem(cat);
        });
    }

    @Test
    @DisplayName("Throw DuplicatePetStoreRecordException for duplicate Cat")
    public void shouldThrowDuplicatePetStoreRecordExceptionForSnake() {
        Snake snake = new Snake(AnimalType.WILD, Skin.SCALES, Gender.FEMALE, Breed.MILK,
                new BigDecimal("1000.00"), 4);

        petStore.init();  // adds cat with ID 1
        petStore.addPetInventoryItem(snake);  // add duplicate

        assertThrows(DuplicatePetStoreRecordException.class, () -> {
            petStore.soldPetItem(snake);
        });
    }


    /**
     * Limitations to test factory as it does not instantiate before all
     * @return list of {@link DynamicNode} that contains the test results
     * @throws DuplicatePetStoreRecordException if duplicate pet record is found
     * @throws PetNotFoundSaleException if pet is not found
     */
    @TestFactory
    @DisplayName("Sale of Sphynx Remove Item Test2")
    public Stream<DynamicNode> sphynxSoldTest2() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Cat sphynx = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"),2);
        Cat removedItem = (Cat) petStore.soldPetItem(sphynx);

        // Validation
        List<DynamicNode> nodes = new ArrayList<>();
        List<DynamicTest> dynamicTests = Arrays.asList(
                dynamicTest("Inventory Check Size Test ", () -> assertEquals(inventorySize,
                        petStore.getPetsForSale().size())),
                dynamicTest("The cat objects match ", () -> assertEquals(sphynx.toString(),
                        removedItem.toString()))
                );
        nodes.add(dynamicContainer("Cat Item 2 Test", dynamicTests));//dynamicNode("", dynamicContainers);

        return nodes.stream();
    }

    /**
     * Example of parameterized test
     * @param number to be tested
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, -10, 128, Integer.MIN_VALUE}) // six numbers
    void isNumberEven(int number)
    {
        assertTrue(Numbers.isEven(number));
    }

}
