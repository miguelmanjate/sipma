function initDataTable() {

    $('#dynamic-table').dataTable( {
	    dom: 'T<"clear">lfrtip',
	    tableTools: {
	        "sSwfPath": "js/copy_csv_xls_pdf.swf"
	    }
    } );
}