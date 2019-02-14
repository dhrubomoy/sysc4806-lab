

$(document).ready(function() {

    var addressBookLiId = 'addressbook-li-',
    addBuddyInfoBtnClassName = 'add-buddyinfo-btn',
    addressBookIdAttr = 'addressBookId',
    buddyInfoInputDivId = 'buddyinfo-input-div-',
    buddyName = 'buddy-name-',
    buddyPhoneNumber = 'buddy-phn-',
    addBuddyBtnId = 'add-buddy-btn-',
    cancelBuddyBtnId = 'cancel-buddy-btn-',
    addBuddyInfoBtnId = 'add-buddy-info-btn-',
    buddyInfoUlId = 'buddyinfo-ul-';

    getAddressBooks();

    $("#createAddressBook").click(function(){
        createAddressBook();
    });

    function addBuddyInfoClickListener(id) {
        $('#'+buddyInfoInputDivId + id).hide();
        $('#'+addBuddyInfoBtnId+id).click(function() {
            buddyInfoInputDiv = $('#'+buddyInfoInputDivId+id);
            buddyInfoInputDiv.show();
            $('#'+cancelBuddyBtnId+id).click(function() {
                buddyInfoInputDiv.hide();
            });
            $('#'+addBuddyBtnId+id).off("click");
            $('#'+addBuddyBtnId+id).click(function() {
                var name = $('input[name='+ buddyName + id +']').val(),
                phoneNumber = $('input[name='+ buddyPhoneNumber + id +']').val();
                addBuddyInfo(id, name, phoneNumber);
                buddyInfoInputDiv.hide();
            });
        });
    }

    function getAddressBooks() {
        $.ajax({
            type: "GET",
            url: "addressBooks",
            data: {
                format: 'json'
            },
            success: function(result) {
                if(result && result.length > 0) {
                    var abList = '';
                    $.each(result, function(index, addressBook) {
                        console.log(addressBook);
                        abList += getAddressBookLi(addressBook);
                    });
                    $('#addressBookList').append(abList);
                    $.each(result, function(index, addressBook) {
                        addBuddyInfoClickListener(addressBook.id);
                    });
                }
            },
            error: function(e) {
                $('#error').html('<p>An error has occurred: ' + e +'</p>');
            },
        });
    }

    function createAddressBook() {
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "addressBook",
            dataType : 'json',
            data : '{}',
            success : function(result) {
                $('#addressBookList').append(getAddressBookLi(result));
                addBuddyInfoClickListener(result.id);
            },
            error : function(e) {
                $('#error').html('<p>An error has occurred: ' + e +'</p>');
            }
        });
    }

    function addBuddyInfo(addressBookId, name, phoneNumber) {
        var buddy = {
            name: name,
            phoneNumber: phoneNumber
        }

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "addressBooks/" + addressBookId + "/buddyInfo",
            dataType : 'json',
            data : JSON.stringify(buddy),
            success : function(result) {
                var list = '<li>Name:' + buddy.name + ', Phone: ' + buddy.phoneNumber + '</li>';
                $('#'+buddyInfoUlId+addressBookId).append(list);
            },
            error : function(e) {
                console.log(e);
                $('#error').html('<p>An error has occurred: ' + e +'</p>');
            }
        });
    }

    function getBuddyInfo(addressBookId) {
        $.ajax({
            type: "GET",
            url: "addressBooks/" + addressBookId + "/buddyInfoList",
            data: {
                format: 'json'
            },
            success: function(result) {
                console.log(result);
            },
            error: function(e) {
                $('#error').html('<p>An error has occurred: ' + e +'</p>');
            },
        });
    }

    function getBuddyInfoInputs(id) {
        var result = '<div id="' + buddyInfoInputDivId + id + '">';
        result += 'Name: <input type="text" name="'+ buddyName + id +'"><br>';
        result +=  'Phone number: <input type="text" name="'+ buddyPhoneNumber+id +'"><br>';
        result += '<button id="' + addBuddyBtnId + id + '">Add</button>';
        result += '<button id="' + cancelBuddyBtnId + id + '">Cancel</button>'
        result += '</div>';
        return result;
    }

    function getBuddyInfoList(addressBook) {
        var result = '<ul id="' + buddyInfoUlId+addressBook.id +'">';
        $.each(addressBook.buddyInfoList, function(index, buddy) {
            result += '<li><b>Name:</b> ' + buddy.name + ', <b>Phone:</b> ' + buddy.phoneNumber + '</li>';
        });
        result += '</ul>';
        return result;
    }

    function getAddressBookLi(addressBook) {
        var abList = '<li id="' + addressBookLiId+addressBook.id + '"> AddressBook-' + addressBook.id;
        abList += '    <button id="' + addBuddyInfoBtnId + addressBook.id + '"> Add buddy </button> <br><br>';
        abList += getBuddyInfoInputs(addressBook.id);
        abList += getBuddyInfoList(addressBook);
        abList += '</li> <br><br><br>';
        return abList;
    }

});