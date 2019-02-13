package com.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.validation.Valid;

@RestController
public class BuddyInfoController {

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @Autowired
    private AddressBookRepository addressBookRepository;

    @GetMapping("/addressBooks/{addressBookId}/buddyInfoList")
    public List<BuddyInfo> getAllBuddyInfoByAddressBookId(@PathVariable (value = "addressBookId") Integer addressBookId) {
        return addressBookRepository.findById(addressBookId).map(addressBook -> {
            return addressBook.getBuddyInfoList();
        }).orElseThrow(() -> new RuntimeException("AddressBookId " + addressBookId + " not found"));
    }

    @PostMapping("/addressBooks/{addressBookId}/buddyInfo")
    public BuddyInfo createBuddyInfo(
            @PathVariable (value = "addressBookId") Integer addressBookId,
            @Valid @RequestBody BuddyInfo buddyInfo) {
        return addressBookRepository.findById(addressBookId).map(addressBook -> {
           buddyInfo.setAddressBook(addressBook);
           return buddyInfoRepository.save(buddyInfo);
        }).orElseThrow(() -> new RuntimeException("AddressBookId " + addressBookId + " not found"));

    }
//
//    @PutMapping("/posts/{postId}/comments/{commentId}")
//    public Comment updateComment(@PathVariable (value = "postId") Long postId,
//                                 @PathVariable (value = "commentId") Long commentId,
//                                 @Valid @RequestBody Comment commentRequest) {
//        if(!postRepository.existsById(postId)) {
//            throw new ResourceNotFoundException("PostId " + postId + " not found");
//        }
//
//        return commentRepository.findById(commentId).map(comment -> {
//            comment.setText(commentRequest.getText());
//            return commentRepository.save(comment);
//        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
//    }
//
//    @DeleteMapping("/posts/{postId}/comments/{commentId}")
//    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
//                                           @PathVariable (value = "commentId") Long commentId) {
//        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
//            commentRepository.delete(comment);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
//    }
}
