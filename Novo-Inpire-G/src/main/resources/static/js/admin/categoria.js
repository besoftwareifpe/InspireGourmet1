$(document).ready(function(){

	$('#myModal').on('shown.bs.modal', function () {
	  $('#myInput').trigger('focus')
	})


    
    toastr.options = {
        	"closeButton": true,
       		"debug": false,
       		"newestOnTop": false,
       		"progressBar": true,
       		"positionClass": "toast-bottom-left",
       		"preventDuplicates": true,
       		"onclick": null,
       		"showDuration": "200",
       		"hideDuration": "1000",
       		"timeOut": "7000",
       		"extendedTimeOut": "10000",
       		"showEasing": "swing",
       		"hideEasing": "linear",
       		"showMethod": "fadeIn",
       		"hideMethod": "fadeOut"
    	}
        
        	
        var t = $("#mensagemErro").text();
        
        switch(t){
        case "1":
        	toastr.info("Categoria já cadastrada!");
        	break;
        
        }
});
