import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    
    //REFACTOR ALL THE REPEATED LINES OF CODE
    
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");

    //Used Spying as for Restuarant closed status to avoid creating multiple localtime variables
    @Spy Restaurant spyRestaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

    @BeforeEach
    public void before_each_test(){
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        assertEquals(true,restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime mockedTime=LocalTime.parse("22:30:00");
        //Used Spy here as we are changing the value returned by a method and then calling a different method
        when(spyRestaurant.getCurrentTime()).thenReturn(mockedTime);

        assertEquals(false,spyRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());

    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() throws itemNotFoundException {
        String ItemToRemove="French Fries1";
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu(ItemToRemove));

    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void return_order_value_when_menu_items_selected() throws itemNotFoundException{
        List<String> itemsAdded = new ArrayList<String>();
        //This add in list is just for string that will be passed to the method/function
        //The returned value is an int of total
        //Items will always be found with price
        itemsAdded.add(0,"Sweet corn soup");
        assertEquals(119, restaurant.orderTotalValue(itemsAdded));
    }
    
    @Test
    public void validate_amount_returned_is_not_null() throws itemNotFoundException{
        List<String> itemsAdded = new ArrayList<String>();
        //This add in list is just for string that will be passed to the method/function
        //The returned value is an int of total
        //Items will always be found with price
        itemsAdded.add(0,"Sweet corn soup");
        assertNotNull(restaurant.orderTotalValue(itemsAdded));      
    }

    @Test
    public void validate_total_amount_is_not_zero() throws itemNotFoundException{
        List<String> itemsAdded = new ArrayList<String>();
        //This add in list is just for string that will be passed to the method/function
        //The returned value is an int of total
        //Items will always be found with price
        itemsAdded.add(0,"Sweet corn soup");
        assertNotEquals(0, restaurant.orderTotalValue(itemsAdded));  

    }

}