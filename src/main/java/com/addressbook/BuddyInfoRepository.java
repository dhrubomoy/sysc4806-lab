package com.addressbook;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BuddyInfoRepository extends JpaRepository<BuddyInfo, Integer> {

}
