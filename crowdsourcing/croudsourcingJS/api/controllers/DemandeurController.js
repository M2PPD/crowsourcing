/**
 * utilisateurController.js
 *
 * @description :: Server-side logic for managing subscriptions
 * @help        :: See http://links.sailsjs.org/docs/controllers
 */
 //var bcrypt= require ('bcrypt');
 module.exports = {


   
	  index:function(req,res,next){
		
		theme.find(function foundthemes(err,themes) {
		 
		 	 
		
            res.view({
                themes:themes
            });
			
			
        });
      },
	  
	  
	  newTheme:function (req,res){


       console.log("je suis là");
        res.view();

    },
	
	
	 imports:function(req,res,next){
		
		Import.find(function foundimports(err,listeImport) {
		 
		 	 //console.log(imports);
		
            res.view({
                listeImport:listeImport
            });
			
			
        });
      },
	  
	  traitementImport:function(req,res,next){
		  
		  
		var id=req.param('id');  
		
		 const Client = require('sync-rest-client');
		 
		 var a =Client.get("http://localhost:8080/attributByImport/"+id);
		 
		 var data=a.body;
		 
		  res.view({
          	data:data
            });
		  
		  
	  },
	
	createTheme:function(req,res,next){
		
		theme.create(req.params.all(),function themeCreated(err,theme){
			
			if (err) {
				
				console.log(err);
				return res.redirect('/demandeur/newTheme');
			
			
		}
	
			
			res.redirect('/demandeur/index');
		});
			
		
	},
	

    };