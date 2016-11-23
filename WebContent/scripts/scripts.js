$(document).ready(function() {
		$('#dataTableExp').DataTable();
});

$(document).ready(function() {
	jQuery('#datetimepicker').datetimepicker();
});

$(document).ready(function () {
    $("#textbox").val('');
    $("#textbox").keypress(function () {
        var textLength = $("#textbox").val().length;
        if (textLength % 50 == 0) {
            var height = textLength / 50;
            $("#textbox").css('height', 20 + (height * 20));
        }
    });
    $('#textbox').keydown(function (e) {
        if (e.keyCode == 13) {
            var msg = $(this).val();
            if (msg == "") {
                alert('full the message');
            }
            else {
                $(".commentsblock").append(
                		"<div class='comments'><div class='imageicons divfloat'><img src='https://cdn1.iconfinder.com/data/icons/black-easy/32/538647-user_32x32.png' /></div><div class='commentinfo divfloat'>" + msg + "</div></div>"
                    ).fadeIn('slow');
                $("#textbox").val("");
            }
        }
    })
});
