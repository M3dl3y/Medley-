/**
 * Created by Nick on 1/22/17.
 */
(function(){
    setTimeout(function() {
        $(document).on("click", "#toggle-conversation", function(){
            if(!window.ConversationalForm){
                window.ConversationalForm = new cf.ConversationalForm({
                    formEl: document.getElementById("registerForm"),
                    context: document.getElementById("form-outer"),
                    userImage: "img/human.png"
                });
            }
            $(this).addClass("disabled");
            var form = $(".conversational-form");
            if (form.hasClass("conversational-form--show")) {
                $(this).removeClass("active");
                $(this).text("Turn on conversation");
                $(".conversational-form").removeClass("conversational-form--show");
            } else {
                $(this).addClass("active");
                $(this).text("Turn off conversation");
                $(".conversational-form").addClass("conversational-form--show");
            }
            $(this).removeClass("disabled");
        });
    }, 200);
})();