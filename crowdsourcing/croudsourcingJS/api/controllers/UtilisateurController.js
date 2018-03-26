/**
 * utilisateurController.js
 *
 * @description :: Server-side logic for managing subscriptions
 * @help        :: See http://links.sailsjs.org/docs/controllers
 */
 //var bcrypt= require ('bcrypt');
 module.exports = {


    //Affihage du formulaire de création de compte
    'new':function (req,res){


       
        res.view();

    },
	
	'create':function(req,res,next){
		
		utilisateur.create(req.params.all(),function utilisateurCreated(err,utilisateur){
			
			if (err) {
				console.log(utilisateur.id);
				console.log(err);
				return res.redirect('/utilisateur/new');
			
			
		}
		  console.log(utilisateur.id);
			
			res.redirect('/utilisateur/show/'+utilisateur.id);
		});
			
		
	},
	
	   show:function(req,res,next){
		   
		   var Client = require('node-rest-client').Client;

var client = new Client();

// direct way
/*client.get("http://localhost:8080/cars", function (data, response) {
	// parsed response body as js object
	console.log(data);
	// raw response
	console.log(response);
});*/
		 
	
        utilisateur.findOne(req.param('id'),function foundutilisateur(err,utilisateur){
            res.view({
                utilisateur: utilisateur
            });
        }); 
       
    },
	
	   edit: function (req,res,next){
        console.log("Le formulaire de modification de profil est affiché");
        utilisateur.findOne(req.param('id'),function foundutilisateur(err,utilisateur){

            if(!utilisateur) return next('L\'utilisateur n\'existe pas');  
            res.view({
                utilisateur:utilisateur
            });
        });
    },
	
	  index:function(req,res,next){
        utilisateur.find(function foundutilisateurs(err,utilisateurs) {
            res.view({
                utilisateurs:utilisateurs
            });
        });
        console.log("Affichage de la liste de tous les utilisateurs");
      },
	   update:function (req,res,next){
        utilisateur.update(req.param('id'), req.params.all() ,function utilisateurUpdate(err)
        {
            if(err){
                return res.redirect('/utilisateur/edit/'+req.param('id'));
            }
            res.redirect('/utilisateur/show/'+req.param('id'));
            console.log("Modification enregistrée avec succès");
        });
     },
	     destroy: function (req,res,next){
            utilisateur.findOne(req.param('id'),function foundutilisateur(err,utilisateur){
                if(err) return next(err);
                if(!utilisateur) return next('L\'utilisateur n\'existe pas');

                utilisateur.destroy(req.param('id'),function utilisateurDestroyed(err){
                    if(err) return next(err);
                });

                res.redirect('/utilisateur');
                console.log("utilisateur supprimé");
            });
        }



    };