/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routerrental_package;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
/** this is an interface that include customer options in services or routers that used in the main class
 */
interface main_methods{
    /**this a method which show the customer routers he can rent and its numbers
     * and we made it static because we will use it in the main class
     */
public static void ShowRouters(){
        System.out.println("choose your favorite router model by typing its number");
        System.out.println("1- D-LINK Wireless N300 with 4 ports");
        System.out.println("2- Huawei Wireless EchoLife with 3 ports");
        System.out.println("3- TP-Link Wireless TL-MR6400 with 6 ports");
        System.out.println("4- Linksys Wireless XAC1900 with 5 ports");
        System.out.println("5- Asus RT-AC3200 with 5 ports");
        System.out.println("6- TP-Link Tl-MR3420 with 2 ports");
    }
    /**this a method which show the customer services he can use and its numbers
     *  and we made it static because we will use it in the main class
     */
    public static void ShowService(){
        System.out.println("please, now choose what service do you want:");
        System.out.println("1- Rent Router");
        System.out.println("2- Extend Router(s) renting Duration");
        System.out.println("3- Cancel Router(s) Resevation");
        System.out.println("4- Change your Router(s) model(s)");
        System.out.println("5- Report a technical issue or a feedback");
        System.out.println("6- Show your Invoices");
        System.out.println("7- Show our services again");
        System.out.println("8- Quit");
    }
}
/**this is our main class where we will test our code and it implements from class main_methods
 * and in this program we want to mention that we used one of SOLID object-oriented design principles
 * which is Single-Responsibility principle (SRP)
*/
public class RouterRental implements main_methods{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Router [] R1 = new Router [6];
        R1[0]=new Router(1478,"D-LINK Wireless N300",4,6);
        R1[1]=new Router(2589,"Huawei Wireless EchoLife",3,4);
        R1[2]=new Router(3698,"TP-Link Wireless TL-MR6400",6,10);  
        R1[3]=new Router(7412,"Linksys Wireless XAC1900",5,8);
        R1[4]=new Router(3578,"Asus RT-AC3200",5,12);
        R1[5]=new Router(9514,"TP-Link Tl-MR3420",2,2);
        int number_of_invoices=0;
        int number_of_customers=1;
        int number_of_Reservation=1;
        invoice user_invoice=null;
        Scanner user_input = new Scanner(System.in);
        ArrayList<Customer>Customers=new ArrayList<>();
        Customer customer1=new Customer("michael");
        Customer customer2=new Customer("omar");
        Customer customer3=new Customer("ava");
        Customers.add(customer1);
        System.out.println("Welcome to Intenet Router Rental Mini Project");
        System.out.println("Are you old Customer or New one?");
        String Type;
        String response;
        Customer Current = null;
        Type= user_input.next().toLowerCase();
        if("old".equals(Type))
        {System.out.println("Enter your name: ");
        
        response= user_input.next();
        
        for(int i=0;i<Customers.size();i++){
        if(response.equals(Customers.get(i).getCustomer_name()))
        {
          Current=Customers.get(i);
        }
        }
        }
        else if("new".equals(Type))
        {
        System.out.println("Enter your name: ");
        response= user_input.next();
        Customer new_customer=new Customer(response);
        Customers.add(new_customer);
        Current=new_customer;
        }
        System.out.println("Are you resident or foreigner");
        response= user_input.next().toLowerCase();
        Current.setCustomer_Type(response);
        main_methods.ShowService();
        int ChooseService=0;
        try{
        while(ChooseService !=8){
         System.out.print("insert the number of the service you want: ");
         ChooseService= user_input.nextInt();
        switch(ChooseService){
            case 1: 
                 main_methods.ShowRouters();
                 int ChooseRouter;
                 ChooseRouter= user_input.nextInt();
                 System.out.println("Write your reservation type: ");
                 System.out.println("days");
                 System.out.println("weeks");
                 System.out.println("months");
                 String res_type;
                 res_type=user_input.next();
                 System.out.println("how many "+res_type+" do you want ?");
                 int duration;
                 duration= user_input.nextInt();
                 System.out.println("enter today's date in format of yyyy-mm-dd");
                 String today;
                 today = user_input.next();
                 LocalDate today_date = LocalDate.parse(today);
                 System.out.println("enter the date when you start your rent in format of yyyy-mm-dd");
                 String start;
                 start = user_input.next();
                 LocalDate start_date = LocalDate.parse(start);
                 int reserve_id=0001;
                 Reservation Reserve = null;
                 if("days".equals(res_type)){
                 Reserve= new DailyReservation(reserve_id, duration,today_date,start_date, R1[ChooseRouter-1]);
                 }
                 else if ("weeks".equals(res_type)){
                  Reserve= new WeeklyReservation(reserve_id, duration,today_date,start_date, R1[ChooseRouter-1]);
                 }
                 else if ("months".equals(res_type)){
                  Reserve= new MonthlyReservation(reserve_id, duration,today_date,start_date, R1[ChooseRouter-1]);
                 }
                 
                 user_invoice=new invoice(Reserve);
                 Current.addinvoice(user_invoice);
                 reserve_id++;
                 break;
                 
            case 2:
                if (Current.Invoices.size()==0){
                 System.out.println("you cann't do any service without having at least one invoice");
                 }
                else{
                System.out.println("for how many days do you want?");
                 duration= user_input.nextInt();
                 System.out.println("which Rent do you want to extend?(enter the number of your rent)");
                    int Extened_Rent;
                Extened_Rent = user_input.nextInt();
                Current.Extend_Reservation(Extened_Rent-1, duration);
                }
                break;
            case 3: 
                if (Current.Invoices.size()==0){
                 System.out.println("you cann't do any service without having at least one invoice");
                 }
                else{
                System.out.println("which Rent do you want to delete?(enter the number of your rent)");
                int Removed_Rent;
                Removed_Rent = user_input.nextInt();
                Current.cancel_reservation(Removed_Rent-1);
                }
                break;
            case 4:
                if (Current.Invoices.size()==0){
                 System.out.println("you cann't do any service without having at least one invoice.");
                 }
                else{
                 System.out.println("which Router Rent do you want to change?");
                int Change_Router;
                System.out.print("the number of wanted Router is :");
                Change_Router = user_input.nextInt();
                int Change_invoice;
                System.out.print("the number of the invoice that you want to change is :");
                Change_invoice = user_input.nextInt();
                Current.Change_Router(Current.Invoices.get(Change_invoice-1), R1[Change_Router-1]);
                }
                break;
            case 5:
                if (Current.Invoices.size()==0){
                 System.out.println("you cann't do any service without having at least one invoice");
                 }
                else{
                 System.out.print("What is your problem? :");
                String problem;
                problem = user_input.next();
                Current.setReport(problem);
                }
                break;
            case 6:
                if (Current.Invoices.size()==0){
                 System.out.println("you cann't do any service without having at least one invoice");
                 }
                else{
                for(int i=0;i<Current.Invoices.size();i++){
                Current.Invoices.get(i).calculate_fees();
                Current.Invoices.get(i).display_info();
                Current.Apply_Discount(Current.Invoices.get(i));
                }
                }
                break;
            case 7:
                main_methods.ShowService();
            default:
                break;
                 }
        if(ChooseService>8 || ChooseService<0){
        throw new wrong_number("This number Don't refer to any services");
        }
        }
        
        
        }
        catch(wrong_number ex){
         System.out.println("This number Don't refer to any services");
        }
        }
 
    }
 /**
  * this is a class where Routers data are installed and we make all its variables final
  * because we want change its values during running the project 
  */   
class Router
{
private final int serial_number;
private final int prize, number_of_ports;
private final String model;

/**
 *this is the constructor of Router Class
 *@param  serial_number which take the Serial Number of the Router
 * @param model which take the model of the Router
 * @param number_of_ports which take the number of ports of the Router
 * @param prize which take the prize of the Router
 */
    public Router(int serial_number, String model, int number_of_ports, int prize) {
        this.serial_number = serial_number;
        this.number_of_ports = number_of_ports;
        this.prize = prize;
        this.model = model;
    }
/**
 *this is a method where we call the prize of a specific router
 * @return the prize of a specific router
 */
    public int getPrize() {
        return prize;
    }
    /**
 *this is a method where we call the serial number of a specific router
 * @return the serial number of a specific router
 */
    public int getSerial_number() {
        return serial_number;
    }
/**this is a method where we call the Model of a specific router
 * @return the Model of a specific router
 */
    public String getModel() {
        return model;
    }
/**
 *this is a method where we call the Number of Ports in a specific router
 * @return Number of Ports in a specific router
 */
    public int getNumber_of_ports() {
        return number_of_ports;
    }
}
/**
 * an abstract class that deal with different
 * types of Reservations that customer can do
 */
abstract class Reservation {
   private int id_number, Duration;
   private LocalDate today_date;
   private LocalDate start_date;
   Router Router_reservation;
    protected LocalDate due_date;
/**this is a method where we call an object from router class which contain specific router data to be reserved 
 * @return the Model of a specific router
 */
        public Router getRouter_reservation() {
            return Router_reservation;
        }
/**this is the constructor of the Reservation class
 * @param id_number which contain the id of the Reservation
 * @param Duration which contain the Duration of the Reservation
 * @param today_date which contain the today's date of the Reservation
 * @param start_date which contain the start date of the Reservation
 * @param Router_reservation which contain the Router that the customer want to reserve
 */
    public Reservation(int id_number, int Duration, LocalDate today_date, LocalDate start_date, Router Router_reservation) {
        this.id_number = id_number;
        this.Duration = Duration;
        this.today_date = today_date;
        this.start_date = start_date;
        this.Router_reservation = Router_reservation;
    }
/**this is a method where we call the duration of the reservation
 * @return the duration of the reservation
 */   
    public final int getDuration() {
        return Duration;
    }
/**this is a method where we set the duration of the reservation
 * @param Duration take the duration that the customer wants depend on which type of reservation he wants
 */   
    public final void setDuration(int Duration) {
        this.Duration = Duration;
    }
/**this is a method where we call the date when the customer reserve his router
 * @return the date when the customer reserve his router
 */   
    public LocalDate getToday_date() {
        return today_date;
    }
/**this is a method where the customer set the date when the customer reserve his router
 * @param today_date the date that customer set to start his reservation
 */   
    public void setToday_date(LocalDate today_date) {
        this.today_date = today_date;
    }
/**this is a method where we call the ID Number of the reservation
 * @return the ID number of the reservation
 */   
    public int getId_number() {
        return id_number;
    }
/**this is a method where we set the ID number of the reservation
 * @param id_number set the id number of the reservation
 */   
    public void setId_number(int id_number) {
        this.id_number = id_number;
    }
/**this is a method where we call the date when reservation starts
 * @return the date when reservation starts
 */   
    public LocalDate getStart_date() {
        return start_date;
    }
/**this is a method where the customer set the date when the customer starts his reservation
 * @param start_date  set the date when reservation starts
 */   
    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }
/**this is a method where we call the date when reservation finishes
 * @return the date when reservation finishes
 */   
    public LocalDate CalculateDue_date(){
    return due_date;
    }
/**this is a method where we can change the deadline when the customer want to extend
     * @param due_date set the date when reservation finishes
 */  
    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }
    
/**this is a class inherited from class Reservation which is used when user choose his reservation to be daily
 * and it used to calculate the expiry date of the user daily Reservation
 */
}
class DailyReservation extends Reservation{
/** this is the constructor of the DailyReservation class which is inherited from class Reservation
 * @param id_number which contain the id of the Reservation
 * @param Duration which contain the Duration of the Reservation
 * @param today_date which contain the today's date of the Reservation
 * @param start_date which contain the start date of the Reservation
 * @param Router_reservation which contain the Router that the customer want to reserve
 */
    public DailyReservation(int id_number, int Duration, LocalDate today_date, LocalDate start_date, Router Router_reservation) {
        super(id_number, Duration, today_date, start_date, Router_reservation);
    }
    /** this is a method that overrides from a method of reservation class 
     * which calculate the expiry date of the user daily Reservation
     */
    @Override
    public LocalDate CalculateDue_date() {
       due_date = getStart_date().plusDays(getDuration());
       return due_date;
    }
}
/**this is a class inherited from class Reservation which is used when user choose his reservation to be weekly
 * and it used to calculate the expiry date of the user weekly Reservation
 */
class WeeklyReservation extends Reservation{
    
/** this is the constructor of the WeeklyReservation class which is inherited from class Reservation
 * @param id_number which contain the id of the Reservation
 * @param Duration which contain the Duration of the Reservation
 * @param today_date which contain the today's date of the Reservation
 * @param start_date which contain the start date of the Reservation
 * @param Router_reservation which contain the Router that the customer want to reserve
 */
    public WeeklyReservation(int id_number, int Duration, LocalDate today_date, LocalDate start_date, Router Router_reservation) {
        super(id_number, Duration, today_date, start_date, Router_reservation);
    }
    /** this is a method that overrides from a method of reservation class 
     * which calculate the expiry date of the user weekly Reservation
     */
    @Override
    public LocalDate CalculateDue_date() {
       due_date = getStart_date().plusWeeks(getDuration());
       return due_date;
    }
}
/**this is a class inherited from class Reservation which is used when user choose his reservation to be monthly
 * and it used to calculate the expiry date of the user monthly Reservation
 */
class MonthlyReservation extends Reservation{
/** this is the constructor of the MonthlyReservation class which is inherited from class Reservation
 * @param id_number which contain the id of the Reservation
 * @param Duration which contain the Duration of the Reservation
 * @param today_date which contain the today's date of the Reservation
 * @param start_date which contain the start date of the Reservation
 * @param Router_reservation which contain the Router that the customer want to reserve
 */
    public MonthlyReservation(int id_number, int Duration, LocalDate today_date, LocalDate start_date, Router Router_reservation) {
        super(id_number, Duration, today_date, start_date, Router_reservation);
    }
    /** this is a method that overrides from a method of reservation class 
     * which calculate the expiry date of the user monthly Reservation
     */
    @Override
    public LocalDate CalculateDue_date() {
       due_date = getStart_date().plusMonths(getDuration());
       return due_date;
    }
}
/**this is the class where we calculate the total fees and display the invoice details
 * and here we used Bill as an static variable
 */
class invoice {
Reservation final_reservation;
static int Bill=0;
/** this is the constructor of the invoice class which take the reservation 
 * which we would calculate its fees and display its information 
 * @param final_reservation which deal with the reservation that customer made 
 */
    public invoice(Reservation final_reservation) {
        this.final_reservation = final_reservation;
    }
 /**this is a method where we calculate the fees of the reservation that the user want
  * we used arithmetic exception in case that insert a huge duration
  * @return the total fees of the reservation
  */
    public double calculate_fees(){
    if(final_reservation instanceof DailyReservation){
             try{
                 Bill= final_reservation.Router_reservation.getPrize()*final_reservation.getDuration();
                 }
                 catch(ArithmeticException ex){
                     System.out.println(ex.getMessage());
                 }
    }
    else if(final_reservation instanceof WeeklyReservation){
              try{
                 Bill= final_reservation.Router_reservation.getPrize()*final_reservation.getDuration()*7;
                 }
                 catch(ArithmeticException ex){
                     System.out.println(ex.getMessage());
                 }
    }
    else if(final_reservation instanceof MonthlyReservation){
             try{
                  Bill= final_reservation.Router_reservation.getPrize()*final_reservation.getDuration()*30;
                 }
                 catch(ArithmeticException ex){
                     System.out.println(ex.getMessage());
                 }
    }
   return Bill;
} 
    /**in this method we display the info of the reservation
     */
public void display_info(){
    System.out.println("Your Router Serial Number is: "+final_reservation.Router_reservation.getSerial_number());
    System.out.println("Your Router Model is: "+final_reservation.Router_reservation.getModel());
    System.out.println("Your Reservation Number is: "+final_reservation.getId_number());
    System.out.println("Your Reservation will end in the date of: "+final_reservation.CalculateDue_date());
    System.out.println("Your Total Fees are: "+calculate_fees());
}
}
/** this class where customer can perform all the services he wants
 * like reserve and cancel router(s) reservation
 * change router
 * extend reservation 
 * apply discount for a specific type of customers
 * and report for problems
 * and our customer name variable would be final
 */
class Customer{
int number_of_customers;
ArrayList<invoice> Invoices;
private String report;
private String Customer_Type;
private final String Customer_name;
/**this is a method where we call customer name
 * @return it returns customer name
 */
    public String getCustomer_name() {
        return Customer_name;
    }
/**this is the constructor of the customer class where we take only his name 
 * to start perform the services he want
 * @param Customer_name where we take only customer's name 
 */
    public Customer(String Customer_name) {
        this.number_of_customers = 1;
        this.Customer_name = Customer_name;
        Invoices=new ArrayList<>();
    }
    /**this is an overloaded constructor of the customer class where we take his name and other details hard coded
     * without any interaction with the user 
     * @param Customer_Type which takes if the customer is Resident of Foreigner
     * @param Customer_name which takes customer name
     * @param Invoices which takes the list of invoices for customer
     * @param report which takes report of customers problems
     */
    public Customer(ArrayList<invoice> Invoices, String report, String Customer_Type, String Customer_name) {
        this.number_of_customers = 1;
        this.Invoices = Invoices;
        this.report = report;
        this.Customer_Type = Customer_Type;
        this.Customer_name = Customer_name;
    }
/**this is a method where we call customer's type if he is a Resident or Foreigner
 * @return it returns customer's type if he is a Resident or Foreigner
 */ 
    public String getCustomer_Type() {
        return Customer_Type;
    }
    /**in this method we add an invoice to other user invoices
     * @param newinvoice takes the invoice that customer created to be added to his list of invoices
     */
    public void addinvoice(invoice newinvoice){
    Invoices.add(newinvoice);
    }
    /**this is a method where customer set his type if he is a Resident or Foreigner
     * @param Customer_Type where we set customer type
 */ 
    public void setCustomer_Type(String Customer_Type) {
        this.Customer_Type = Customer_Type;
    }
    /**in this class user extend the reservation of one of his routers
     * with using java defined exception if the user insert an invoice he don't has
     * @param extend_rent the number of the invoice he want to extend
     * @param duration the duration of his extend
     */
   public void Extend_Reservation (int extend_rent, int duration){
         try{
               LocalDate Extened_Date=Invoices.get(extend_rent).final_reservation.CalculateDue_date();
               Extened_Date=Extened_Date.plusDays(duration);
               System.out.println("Your new final date is: "+Extened_Date);
               Invoices.get(extend_rent).final_reservation.setDuration(Invoices.get(extend_rent).final_reservation.getDuration()+duration);
               Invoices.get(extend_rent).final_reservation.setDue_date(Extened_Date);
                 }
                 catch(IndexOutOfBoundsException Er){
                     System.out.println("you only have"+Invoices.size()+1+"Rent(s)");
                 }
   }
   /** this method is applied for Resident customers to give them a 25% discount for each invoice
    * @param Invoice this is the invoice which we will apply our discount on
    */
   public void Apply_Discount(invoice Invoice){
   if("resident".equals(Customer_Type)){
       Invoice.Bill=Invoice.Bill-((Invoice.Bill*25)/100);
       System.out.println("As a Resident you have 25% discount on your invoice so your total fees of your invoice is "+Invoice.Bill);
   }
   }
   /**this is a method where user can cancel one of his rents
    * with using java defined exception if the user insert an invoice he don't has
    * @param removed_rent the number of the rent which would be canceled
    */
   public void cancel_reservation(int removed_rent){
       try{
        long daysBetween = ChronoUnit.DAYS.between(Invoices.get(removed_rent).final_reservation.getToday_date(), Invoices.get(removed_rent).final_reservation.getStart_date());
        if(daysBetween>=2){
       System.out.println("Your reservation has been canceled");
        Invoices.remove(removed_rent);
       }
       else{
       System.out.println("You can only cancel your reservation at least 2 days before the Start date");
       }
          }
       catch(IndexOutOfBoundsException Er){
         System.out.println("you only have"+Invoices.size()+1+"Rent(s)");
             }
   }
   /**this a method which customer can change his router he rented with another one
    * @param Invoices this is the reservation which user wants to change its router
    * @param R this is the router that customer want change his router with
    */
    public void Change_Router(invoice Invoices, Router R){
       Invoices.final_reservation.Router_reservation=R;
       System.out.println("your router type is:" +Invoices.final_reservation.Router_reservation.getModel());
   }
/**this method used for getting the report that customer wrote
 * @return returns the report that customer wrote
*/
    public String getReport() {
        return report;
    }
    /**this is the method that client call when he want to inform a problem
     * @param report the report that client wrote
     */
    public void setReport(String report) {
        this.report = report;
        System.out.println("your Report has been received, Thank you for informing us");
    }
}
/** this is an exception class that works when user enter the number of an service that doesn't exist
 */
class wrong_number extends Exception{
/**this is the constructor of the own defined exception class 
 * @param string take the string that allow the method to be executed
 */
    public wrong_number(String string) {
        super(string);
    }
}