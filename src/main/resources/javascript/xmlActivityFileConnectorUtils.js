/**
 * Created by Juan Carlos Rodas on 29/08/2016.
 */
/**
 * This function check the live properties and clear the interval if the live is up to date
 */
function waitForLive() {
    var onLive = checkLive();
    if (onLive) {
        clearInterval(intervalValue);
    }
}

/**
 * This function makes an api call to check if the properties in live mode are updated
 */
function checkLive() {
    $.ajax({
        type: "GET",
        url: readUrl,
        async: false,
        success: function (result) {
            return ($('#xmlFilePath').val() == result.properties.xmlFilePath.value);
        }
    });
    return false;
}

/**
 * This function create or update the twitter settings JCR nodes with the access settings from the form
 * @param intervalValue
 */
function createUpdateXmlActivityFileParameters(intervalValue) {
    //getting the good Json form
    var jsonData;
    if (mode == 'create') {
        jsonData = "{\"children\":{\"xmlActivityFileSettings\":{\"name\":\"xmlActivityFileSettings\",\"type\":\"jnt:xmlActivityFileConnector\",\"properties\":{\"xmlFilePath\":{\"value\":\"" + $('#xmlFilePath').val() + "\"}}}}}";
    }
    else {
        jsonData = "{\"properties\":{\"xmlFilePath\":{\"value\":\"" + $('#xmlFilePath').val() + "\"}}}";
    }
    //Calling API to update JCR
    $.ajax({
        contentType: 'application/json',
        data: jsonData,
        dataType: 'json',
        processData: false,
        async: false,
        type: 'PUT',
        url: writeUrl,
        success: function (result) {
            //check live values every 0.5 sec untill they are up to date
            intervalValue = setInterval(waitForLive(), 500);
            window.location.reload();
        }
    });
}
/**
 * Reset form fields
 */
function resetXmlActivityFileSettings() {
    $('#xmlFilePath').val(xmlFilePath);
    $('#cancelXmlActivityFileSettings').attr("disabled", "disabled");
}
/**
 * Validates to enable the save/cancel button
 */
function validate(jqField,jcrKey){
    if ((  ($('#xmlFilePath').val() != "")  )
        && ($("#"+jqField).val() != jcrKey))
    {
        $('#saveXmlActivityFileSettings').removeAttr("disabled");
        $('#cancelXmlActivityFileSettings').removeAttr("disabled");
    } else {
        $('#saveXmlActivityFileSettings').attr("disabled", "disabled");
        $('#cancelXmlActivityFileSettings').attr("disabled", "disabled");
    }
}
/**
 * Add the keyup event listener to the form fields for validation purposes
 * */
$(document).ready(function () {
    $('#xmlFilePath').keyup(function() {
        validate(this.id,xmlFilePath);
    });
});

