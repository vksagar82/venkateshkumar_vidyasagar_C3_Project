import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;


public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        //Calling the getCurrentTime method for local time
        String currentLocalTime= getCurrentTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String restuarantClosingTime= closingTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        int timeCompare = currentLocalTime.compareTo(restuarantClosingTime);

        //This will return 0 if currentTime > Restuarant Closing Time
        if(timeCompare>0){
            displayDetails();
            return false;
        }
        //This will return -1 if currentTime < Resturant Closing Time & 0 if its equal to
        else{
            return true;
        }

    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName) throws itemNotFoundException{
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        throw new itemNotFoundException(itemName);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {
        //the findItemByName function takes care of itemNotFound and will result in error
        //thus can remove the item after this is called.
        Item itemToBeRemoved = findItemByName(itemName);
        menu.remove(itemToBeRemoved);
        
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }
    
    public Integer orderTotalValue(List<String> itemsAdded) throws itemNotFoundException {
        int totalPrice=0;
        for (String items: itemsAdded){
            //findItemByName is taking care of finding the item with name and returning the full Item Object
            Item itemVal=findItemByName(items);
            totalPrice=itemVal.getPrice()+totalPrice;
        }
        return totalPrice;
    }
}
