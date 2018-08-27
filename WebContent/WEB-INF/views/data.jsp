<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data</title>
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.css" />
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid-theme.min.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.js"></script>

<link type="text/css" rel="stylesheet" href="resources/assets/jsgrid/jsgrid.min.css" />
<script type="text/javascript" src="resources/assets/jsgrid/jsgrid.min.js"></script>

</head>
<body>
<a href="/Assignment_Login_Rest/dashboard">Go To Dashboard</a>
<div id="jsGrid"></div>
<script>

   /*  var clients = [
        { "Name": "Otto Clay", "Age": 25, "Country": 1, "Address": "Ap #897-1459 Quam Avenue", "Married": false },
        { "Name": "Connor Johnston", "Age": 45, "Country": 2, "Address": "Ap #370-4647 Dis Av.", "Married": true },
        { "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false },
        { "Name": "Timothy Henson", "Age": 56, "Country": 1, "Address": "911-5143 Luctus Ave", "Married": true },
        { "Name": "Ramona Benton", "Age": 32, "Country": 3, "Address": "Ap #614-689 Vehicula Street", "Married": false }
    ]; */
 
    //var clients="";
    /* var countries = [
        { Name: "", Id: 0 },
        { Name: "United States", Id: 1 },
        { Name: "Canada", Id: 2 },
        { Name: "United Kingdom", Id: 3 }
    ];
     */
    
    
     
     
 	var RepeatingField = function(config) {
         jsGrid.TextAreaField.call(this, config);
       };

       RepeatingField.prototype = new jsGrid.TextAreaField({
         orignalArrayLength : 0,
         itemTemplate : function(value) {
           if (!value) {
             return "";
           }
           return value.filter(function(val) {
             return val !== null;
           }).join(", ")
         },

         insertTemplate : function() {
           if (!this.inserting)
             return "";
           return this.insertControl = this._createTextArea();
         },

         editTemplate : function(value) {
           if (!this.editing)
             return this.itemTemplate.apply(this, arguments);
           var $result = this.editControl = this._createTextArea();
           // save length of array before editing
           if(value){
             this.orignalArrayLength = value.length;		
             $result.val(value.join("\n"));
           }
           return $result;
         },

         _createTextArea : function() {
           return $("<textarea>").prop("readonly", !!this.readOnly);
         },

         insertValue : function() {
           return this.insertControl.val().split("\n");
         },

         editValue : function() {
           var updatedArray = this.editControl.val().split("\n");
           // the updateditem is later merged with old values and if null values
           // aren't present in place of old values they remain.
          // while (updatedArray.length < this.orignalArrayLength) {
           //  updatedArray.push(null);
          // }
           return updatedArray;
         }
       });
     
     
     
    jsGrid.fields.repeating = RepeatingField;
 
    $("#jsGrid").jsGrid({
        width: "100%",
        height: "700px",
 
        inserting: true,
        editing: true,
        sorting: true,
        paging: true,
/*         rowClick: function(args) { alert("row clicked"); }, */
        rowDoubleClick: function(args) { alert("row clicked");  },
        noDataContent: "Not found",
        confirmDeleting: true,
        deleteConfirm: "Are you sure?",
        pageIndex: 1,
        pageSize: 20,
        pageButtonCount: 15,
        pagerFormat: "Pages: {first} {prev} {pages} {next} {last}    {pageIndex} of {pageCount}",
        pagePrevText: "Prev",
        pageNextText: "Next",
        pageFirstText: "First",
        pageLastText: "Last",
        invalidMessage: "Invalid data entered!",
        loadIndication: true,
        loadMessage: "Please, wait Data is loading...",
        updateOnResize: true,
        //data: clients,
        autoload: true,
    	controller: {
    		loadData: function(item) {
    			//alert("load data called");
    			 var d = $.Deferred();
    			 $.ajax({
    	            type: "GET",
    	            url: "http://localhost:8080/Assignment_Login_Rest/getCountries",
    	            data: JSON.stringify(item),
    	            dataType: "json",
    	            contentType: "application/json; charset=utf-8"
    	         /*    success: function(data){
    	            	console.log(data);
    	            
    	            	
                    } */
    	        }).done(function (response, textStatus, errorThrown) {
                    d.resolve(response);
                });
    			 return d.promise();
    	    },
        insertItem: $.noop,
        updateItem: $.noop,
        deleteItem: $.noop
    },
    
        fields: [
            { name:"name", type: "text" ,width: 150},
             { name: "topLevelDomain", type:"text" ,width: 150},
             { name: "alpha2Code", type: "text",width: 150 },
             { name: "callingCodes", type: "text", width: 150},
             { name: "capital", type: "text", width: 150},
             { name: "region", type: "text", width: 150},
             { name: "subregion", type: "text", width: 150},
             { name: "population", type: "text", width: 150},
             { name: "demonym", type: "text", width: 150},
             { name: "area", type: "text", width: 150},
             { name: "gini", type: "text", width: 150},
             { name: "timezones", type: "repeating", width: 300},
             { name: "nativeName", type: "text", width: 250},
             { name: "numericCode", type: "text", width: 150},
             { name: "currencies", type: "text", width: 150},
             { name: "relevance", type: "text", width: 150},
             { name: "translations.de", type: "text", width: 150},
             { name: "translations.es", type: "text", width: 150},
             { name: "translations.fr", type: "text", width: 150},
             { name: "translations.ja", type: "text", width: 150},
             { name: "translations.it", type: "text", width: 150},
             { name: "borders",type: "repeating", width: 300},
             { name: "languages",type: "repeating", width: 300},
             { name: "latlng",type: "repeating", width: 300},
             { name: "altSpellings",type: "repeating", width: 300},
             
/*             { name: "Country", type: "select", items: countries, valueField: "Id", textField: "Name" }, */
            /* { name: "Married", type: "checkbox", title: "Is Married", sorting: false }, */
            { type: "control" }
        ]
    });
</script>

</body>
</html>