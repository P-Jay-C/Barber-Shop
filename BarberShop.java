import java.util.LinkedList;

public class BarberShop {
    private static final int MAX_CHAIRS = 6; //Main chair + 5 waiting chairs
    private LinkedList<String> chairs;
    private int ordCount;
    private int vipCount;

    public BarberShop() {
        chairs = new LinkedList<>();

        // Setting all chairs as empty.
        for(int i = 0; i < MAX_CHAIRS; i++){
            chairs.add(null);
        }
        ordCount = 0;
        vipCount = 0;
    }

    public   String clientArrives(String type){
        String clientName = type.equals("VIP") ? "VIP"+ ++vipCount : "ORD" + ++ordCount;
        String event = "";

        if(chairs.getFirst()== null){ // If main chair is empty,
            chairs.set(0, clientName);
            event="++ "+clientName;
        }else {
            if(type.equals("VIP")){
                event = handleVIPArrival(clientName);
            }else {
                event  = handleORDArrival(clientName);
            }
        }
        return  event;
    }

    public String displayShopState() {
        StringBuilder state = new StringBuilder();

        for( String chair: chairs){
            state.append(chair == null ? "----" : chair).append(" : ");
        }
        return state.toString();
    }

    private String handleORDArrival(String ordClientName) {

        int firstEmptyChair = findFirstEmptyChair();

        if(firstEmptyChair != -1){
            chairs.set(firstEmptyChair,ordClientName);
            return  "++ "+ ordClientName;
        }else {
            return  "+- "+ ordClientName;
        }

    }


    private String handleVIPArrival(String vipClientName) {

        int lastVIPIndex = findLastVIPIndex();
        int firstEmptyChair = findFirstEmptyChair();

        if(firstEmptyChair != -1 && (lastVIPIndex == -1 || lastVIPIndex < firstEmptyChair)){
            // If there is no VIP in queue or the last VIP is before the first empty chair
            if(lastVIPIndex == -1 || lastVIPIndex == 0){
                shiftClientsRight(1);
                chairs.set(1,vipClientName);
            }else{
                shiftClientsRight(lastVIPIndex+1);
                chairs.set(lastVIPIndex+1, vipClientName);
            }
            return "++ " + vipClientName;
        }else{
            return "+- " + vipClientName;
        }
    }

    private void shiftClientsRight(int fromIndex) {
        for(int i = MAX_CHAIRS - 1; i > fromIndex; i--){
            chairs.set(i, chairs.get(i-1));
        }
    }


    private int findFirstEmptyChair() {
        for(int i=0; i < MAX_CHAIRS; i++){
            if(chairs.get(i) == null)
                return i;
        }
        return -1;
    }

    private int findLastVIPIndex(){
        // Starting from the waiting seat
        int lastIndex = -1;

        for(int i = 1; i < MAX_CHAIRS; i++){
            if(chairs.get(i)!= null && chairs.get(i).startsWith("VIP")) {
                lastIndex = i;
            }
        }
            return lastIndex;
    }


    public String clientLeaves(){
        String event = "";

        if(chairs.getFirst() != null){
            event = "-- "+ chairs.getFirst();
            chairs.removeFirst();
            chairs.addLast(null);
        }
        return event;
    }
}
