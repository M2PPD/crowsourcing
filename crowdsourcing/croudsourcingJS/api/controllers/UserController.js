/**
 * UserController.js
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
		
		User.create(req.params.all(),function userCreated(err,user){
			
			if (err) {
				
				console.log(err);
				return res.redirect('/user/new');
			
			
		}
			
			res.redirect('/user/show/'+user.id);
		});
			
		
	},
	
	   show:function(req,res,next){
		   
		   var Client = require('node-rest-client').Client;

var client = new Client();

// direct way
client.get("http://localhost:8080/cars", function (data, response) {
	// parsed response body as js object
	console.log(data);
	// raw response
	console.log(response);
});
		   
	
        User.findOne(req.param('id'),function foundUser(err,user){
            res.view({
                user: user
            });
        }); 
       
    },
	
	   edit: function (req,res,next){
        console.log("Le formulaire de modification de profil est affiché");
        User.findOne(req.param('id'),function foundUser(err,user){

            if(!user) return next('L\'utilisateur n\'existe pas');  
            res.view({
                user:user
            });
        });
    },
	
	  index:function(req,res,next){
        User.find(function foundusers(err,users) {
            res.view({
                users:users
            });
        });
        console.log("Affichage de la liste de tous les utilisateurs");
      },
	   update:function (req,res,next){
        User.update(req.param('id'), req.params.all() ,function userUpdate(err)
        {
            if(err){
                return res.redirect('/user/edit/'+req.param('id'));
            }
            res.redirect('/user/show/'+req.param('id'));
            console.log("Modification enregistrée avec succès");
        });
     },
	     destroy: function (req,res,next){
            User.findOne(req.param('id'),function foundUser(err,user){
                if(err) return next(err);
                if(!user) return next('L\'utilisateur n\'existe pas');

                User.destroy(req.param('id'),function userDestroyed(err){
                    if(err) return next(err);
                });

                res.redirect('/user');
                console.log("Etudiant supprimé");
            });
        }



    };