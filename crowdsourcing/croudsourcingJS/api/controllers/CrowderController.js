/**
 * utilisateurController.js
 *
 * @description :: Server-side logic for managing subscriptions
 * @help        :: See http://links.sailsjs.org/docs/controllers
 */
 //var bcrypt= require ('bcrypt');
 module.exports = {


   
	  index:function(req,res,next){
		  
	
		 const Client = require('sync-rest-client');


		  
var data=[];
	
	 theme.find(function foundthemes(err,themes) {
		 
		 
		 for(var i=1;i<=themes.length;i++)
		 {
			var a =Client.get("http://localhost:8080/comparaisonByTheme/"+i);
		
		
		
		if(a.body[0]){
			
			data[i]=a.body;
		
		
				console.log("i:"+i+"data:"+data[i][0]['idLigne1']['champs2']);
				
		}
			  
			 
		 }
		 	 
		 
		setTimeout(function() {  
		 //console.log(data);
            res.view({
                themes:themes,
				data:data
            });
			
			}, 1000);
        });
			  
		 
        
      },
	
	  createComparaison:function (req,res,next){

	  var counter=req.param('counter');
	  var crowderId=req.param('crowderId');
	  var comparaisonId=req.param('comparaisonId').split(",");
	  var similaire=req.param('similaire').split(",");
	  var champ1=req.param('champ1').split(",");
	 var champ2=req.param('champ2').split(",");
	 var champ3=req.param('champ3').split(",");
	 var champ4=req.param('champ4').split(",");
	 
	 for(var i=0;i<=counter;i++)
		  
		  {
	  ComparaisonCrowder.create({
	 					
	 					id_Crowder_ID:crowderId,
	 					id_Comparaison_ID:comparaisonId[i],
						isSimilaire:similaire[i],
						isChamp1_Left:champ1[i],
						isChamp2_Left:champ2[i],
						isChamp3_Left:champ3[i],
						isChamp4_Left:champ4[i]
	 				},function ComparaisonCrowderCreated(err,comparaisonCrowder){
						
						//console.log("comparaisonCrowder Id:"+comparaisonCrowder.Id);
	 					if (err) {                                                                    
	 						console.log(err); 
	 						req.session.flash ={
	 							err :err
	 						}
	 					}
	 				})
	  
	
		  }
		
		  res.redirect('/crowder/index/');
	  }
    };