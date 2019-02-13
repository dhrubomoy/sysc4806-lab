

$(document).ready(function() {

    var addressBookLiId = 'addressbook-li-',
    addBuddyInfoBtnClassName = 'add-buddyinfo-btn',
    addressBookIdAttr = 'addressBookId',
    buddyInfoInputDivId = 'buddyinfo-input-div-',
    buddyName = 'buddy-name-',
    buddyPhoneNumber = 'buddy-phn-',
    addBuddyBtnId = 'add-buddy-btn-',
    cancelBuddyBtnId = 'cancel-buddy-btn-';
    addressBookList = [];

    getAddressBooks();

    $("#createAddressBook").click(function(){
        createAddressBook();
    });

    function addBuddyInfoClickListener() {
        $('.'+addBuddyInfoBtnClassName).click(function() {
            var id = $(this).attr('id'),
            buddyInfoInputDiv = $('#'+buddyInfoInputDivId+id);
            buddyInfoInputDiv.show();
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
                    addressBookList = result;
                    var abList = '';
                    $.each(result, function(index, addressBook) {
                        console.log(addressBook);
                        abList += getAddressBookLi(addressBook);
                    });
                    $('#addressBookList').append(abList);
                    addBuddyInfoClickListener();
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
                if(addressBookList.length === 0) {
                    addBuddyInfoClickListener();
                }
                addressBookList.push(result);
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
                    console.log(result);
                },
                error : function(e) {
                    console.log(e);
                    $('#error').html('<p>An error has occurred: ' + e +'</p>');
                }
            });
        }

    function getBuddyInfoInputs(id) {
        var result = '<div id="' + buddyInfoInputDivId + id + '" hidden>';
        result += 'Name: <input type="text" name="'+ buddyName + id +'"><br>';
        result +=  'Phone number: <input type="text" name="'+ buddyPhoneNumber+id +'"><br>';
        result += '<button id="' + addBuddyBtnId + id + '">Add</button>';
        result += '<button id="' + cancelBuddyBtnId + id + '">Cancel</button>'
        result += '</div>';
        return result;
    }

    function getAddressBookLi(addressBook) {
        var abList = '<li id="' + addressBookLiId+addressBook.id + '"> AddressBook-' + addressBook.id;
        abList += '    <button id="' + addressBook.id + '" class="'
                    + addBuddyInfoBtnClassName +'"> Add buddy </button> <br><br>';
        abList += getBuddyInfoInputs(addressBook.id);
        abList += '</li> <br><br><br>';
        return abList;
    }

});