/**
 * SessionController
 *
 * @description :: Server-side logic for managing sessions
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */
var bcrypt= require ('bcrypt');
module.exports = {
	
	'new':function(req,res){
		res.view('session/new');
	},
	
	create: function(req,res,next){

     	if(!req.param('username') || !req.param('password')){
     		
     		var utilisateurnamePasswordRequiredError=[{name:'utilisateurnamePasswordRequired',message:"vous devez entrer un identifiant et un mot de passe"}]

     		req.session.flas={
     			err:utilisateurnamePasswordRequiredError
     		}

     		res.redirect('/session/new');
     		return;
     }

     utilisateur.findOneByUsername(req.param('username'),function foundutilisateur(err,utilisateur){
     	 if (err) return next(err);

     	 if(!utilisateur){
     	 	var noAccountError=[{name:'noAccount',message:'L\'username n\'a pas été trouvé'}]

     	 	req.session.flash={
     	 		err: noAccountError
     	 	}
     	 	res.redirect('/session/new');
     	 	return;
     	 }

     bcrypt.compare(req.param('password'),utilisateur.encryptedPassword,function(err,valid){
     	if(err) return next(err);

     	if(!valid){
     		var utilisateurnamePasswordMismatchError =[{name:'utilisateurnamePasswordMismatch',message:'Mot de passe non valide'}]
             req.session.flash={
             	err:utilisateurnamePasswordMismatchError
             }  
console.log(utilisateur.encryptedPassword);			 
             res.redirect('/session/new');
			 
             return;
     	}

     	req.session.authenticated=true;
     	req.session.utilisateur=utilisateur;
  
		 console.log("bon mdp"+ req.session.authenticated);
		 
		if(utilisateur.usertype=="Demandeur"){
			
     	res.redirect('/demandeur/index/');
		}
		
		else{
			res.redirect('/crowder/index/');
			
		}

     });
     });
},

destroy: function(req,res,next){

	req.session.destroy();
	res.redirect('/');
}
};


