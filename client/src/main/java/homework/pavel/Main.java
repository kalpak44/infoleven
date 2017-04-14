package homework.pavel;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created on 14.04.17.
 */
public class Main {
    public static void main(String[] args) {
        Client client = Client.create();

        // Display menu graphics
        System.out.println("============================");
        System.out.println("|   MENU SELECTION         |");
        System.out.println("============================");
        System.out.println("| Options:                 |");
        System.out.println("|  1. show seats        1  |");
        System.out.println("|  2. reserve seat      2  |");
        System.out.println("|  3. Exit              3  |");
        System.out.println("============================");

        // Local variable
        int swValue;

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                swValue = scanner.nextInt();
                switch (swValue) {
                    case 1:
                        String getBusUrl = "http://localhost:8080/get-bus/coolBus/";
                        WebResource busWebResource = client.resource(getBusUrl);
                        ClientResponse getBusResponse = busWebResource.accept("application/json").get(ClientResponse.class);
                        if (getBusResponse.getStatus() == 200) {
                            String entity = getBusResponse.getEntity(String.class);
                            showSeats(new JSONObject(entity));
                        }
                        break;
                    case 2:
                        String getReservation = "http://localhost:8080/get-bus/coolBus/reserve";
                        WebResource reservationWebResource = client.resource(getReservation);
                        ClientResponse reservationResponse = reservationWebResource.accept("application/json").get(ClientResponse.class);
                        if (reservationResponse.getStatus() != 406 && reservationResponse.getStatus() == 200) {
                            System.out.println("reserved");
                        } else {
                            System.out.println("This bus is full");
                        }

                        break;
                    case 3:
                        System.out.println("Exit selected");
                        return;
                    default:
                        System.out.println("Invalid selection");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid selection");
            } catch (JSONException e) {
                System.out.println("Invalid server response");
            }
        }
    }

    private static void showSeats(JSONObject bus) throws JSONException {
        JSONArray seats = bus.getJSONArray("seats");
        for (int i = 0; i < seats.length(); i++) {
            JSONObject seat = (JSONObject) seats.get(i);
            System.out.println(seat.getInt("seatId"));
            System.out.println(seat.getBoolean("isFree"));
            System.out.println("============================");
        }
    }
}