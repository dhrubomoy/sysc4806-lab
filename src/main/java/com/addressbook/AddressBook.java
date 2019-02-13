package com.addressbook;

import javax.persistence.*;
import java.util.*;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToMany(cascade = CascadeType.ALL)
    private List<BuddyInfo> buddyInfoList;

    public AddressBook(List<BuddyInfo> buddyInfoList) {
        this.buddyInfoList = buddyInfoList;
    }

    public AddressBook() {
        this(new ArrayList<>());
    }

    public List<BuddyInfo> getBuddyInfoList() {
        return buddyInfoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addBuddyInfo(BuddyInfo buddyInfo) {
        this.buddyInfoList.add(buddyInfo);
    }
}
