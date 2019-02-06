package com;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "com.AddressBook")
public class AddressBook {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BuddyInfo> buddyInfoList;

    public AddressBook(Integer id, List<BuddyInfo> bi) {
        this.id = id;
        this.buddyInfoList = bi;
    }

    public List<BuddyInfo> getBuddyInfos() {
        return this.buddyInfoList;
    }

    public AddressBook(Integer id) {
        this(id, new ArrayList<>());
    }

    public AddressBook() {
        this(0, new ArrayList<>());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // @OneToMany(cascade = CascadeType.PERSIST)PERSIST
    public List<BuddyInfo> getBuddyInfoList() {
        return buddyInfoList;
    }

    public void setBuddyInfoList(List<BuddyInfo> buddyInfos) {
        this.buddyInfoList = buddyInfos;
    }

    public void addBuddyInfo(BuddyInfo bi) {
        this.buddyInfoList.add(bi);
    }

    @Override
    public String toString() {
        String str = "";
        for(BuddyInfo b : this.buddyInfoList) {
            str += b.toString() + "\n";
        }
        return str;
    }

    public static void main(String []args) {
        System.out.println("Creating Buddies");
        BuddyInfo b1 = new BuddyInfo("Buddy1", "0001");
        BuddyInfo b2 = new BuddyInfo("Buddy2", "0002");
        BuddyInfo b3 = new BuddyInfo("Buddy3", "0003");

        List<BuddyInfo> buddies = new ArrayList<BuddyInfo>();
        buddies.add(b1);
        buddies.add(b2);
        buddies.add(b3);

        System.out.println("Creating com.AddressBook");
        AddressBook addressBook = new AddressBook(1, buddies);
        System.out.println(addressBook.toString());
    }

}
