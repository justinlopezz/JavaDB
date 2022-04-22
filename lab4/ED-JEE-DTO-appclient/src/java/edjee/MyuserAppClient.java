/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edjee;

import entity.MyuserDTO;
import java.util.ArrayList;
import session.MyuserFacadeRemote;

/**
 *
 * @author Justin
 */
public class MyuserAppClient {

    @javax.ejb.EJB
    private static MyuserFacadeRemote myuserFacade;

    public MyuserAppClient() {
    }

    public static void main(String[] args) {
        MyuserAppClient client = new MyuserAppClient();
        // assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO = new MyuserDTO("000001", "Peter Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        boolean result = client.createRecord(myuserDTO);
        client.showCreateResult(result, myuserDTO);
        // assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO2 = new MyuserDTO("000007", "David Lee", "654321",
                "dlee@swin.edu.au", "0123456789", "Swinburne EN510g",
                "What is my name?", "David");
        result = client.createRecord(myuserDTO2);
        client.showCreateResult(result, myuserDTO2);

        //Test1
        MyuserDTO test1 = myuserFacade.getRecord(myuserDTO.getUserid());
        showGetResult(myuserDTO.getUserid(), test1);

        //Test2
        MyuserDTO test2 = new MyuserDTO("000001", "John Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        myuserFacade.updateRecord(test2);
        MyuserDTO test2con = myuserFacade.getRecord(test2.getUserid());
        showUpdatedResult(test2, test2con);
        
        //Test3
        String addressLookup = "Swinburne EN510f";
        ArrayList<MyuserDTO> address = myuserFacade.getRecordsByAddress(addressLookup);
        showAddressLookup(addressLookup, address);
        
        //Test4
        String delete = test2.getUserid();
        boolean deleteResult = myuserFacade.deleteRecord(delete);
        showDeletedResult(delete, deleteResult);
    }

    public static void showCreateResult(boolean result, MyuserDTO myuserDTO) {
        if (result) {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " has been created in the database table.");
        } else {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " could not be created in the database table!");
        }
    }

    public static void showGetResult(String userId, MyuserDTO getResult) {
        if (getResult == null) {
            System.out.println("Record with primary key " + userId
                    + " has not been found in the database table.");
        } else {
            if (getResult.getUserid().equals(userId)) {
                System.out.println("Record with primary key " + userId
                        + " has been found in the database table.");
            } else {
                System.out.println("Record with primary key " + userId
                        + " has been found " + getResult.getUserid() + " has been found instead.");
            }
        }
    }

    public static void showUpdatedResult(MyuserDTO updatedObject, MyuserDTO updatedObject2) {
        if (updatedObject.getName().equals(updatedObject2.getName())) {
            System.out.println("Record with primary key " + updatedObject.getUserid()
                    + " has not been found in the database table.");
        } else {
            System.out.println("Record with primary key " + updatedObject.getUserid()
                    + " has been found in the database table.");
        }
    }

    public static void showDeletedResult(String userId, boolean deletedResult) {
        if (deletedResult == true) {
            System.out.println("Record with primary key " + userId
                    + " has not been found in the database table.");
        } else {
            System.out.println("Record with primary key " + userId
                    + " has been found in the database table.");
        }
    }

    public static void showAddressLookup(String address, ArrayList<MyuserDTO> result) {
        System.out.println("Found " + result.size() + " Records" + " at " + address);
    }

    public Boolean createRecord(MyuserDTO myuserDTO) {
        return myuserFacade.createRecord(myuserDTO);
    }

}
