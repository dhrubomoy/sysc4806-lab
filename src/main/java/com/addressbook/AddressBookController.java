package com.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.validation.Valid;

@RestController
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @GetMapping("/addressBooks")
    public List<AddressBook> getAllAddressBooks() {
        return addressBookRepository.findAll();
    }

    @PostMapping("/addressBook")
    public AddressBook createAddressBook(@Valid @RequestBody AddressBook ab) {
        return addressBookRepository.save(ab);
    }

//    @PutMapping("/posts/{postId}")
//    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
//        return postRepository.findById(postId).map(post -> {
//            post.setTitle(postRequest.getTitle());
//            post.setDescription(postRequest.getDescription());
//            post.setContent(postRequest.getContent());
//            return postRepository.save(post);
//        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
//    }
//
//
//    @DeleteMapping("/posts/{postId}")
//    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
//        return postRepository.findById(postId).map(post -> {
//            postRepository.delete(post);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
//    }

}
