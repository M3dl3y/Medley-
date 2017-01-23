/**
 * Created by Nick on 1/21/17.
 */
$('input').on('click', function(){
    var valeur = 0;
    $('input:checked').each(function(){
        if ( $(this).attr('value') > valeur )
        {
            valeur =  $(this).attr('value');
        }
    });
    $('#progressBar').css('width', valeur+'%').attr('aria-valuenow', valeur);
});