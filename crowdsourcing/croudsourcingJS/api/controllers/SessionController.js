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

     	if(!req.param('email') || !req.param('password')){
     		
     		var usernamePasswordRequiredError=[{name:'usernamePasswordRequired',message:"vous devez entrer une adresse email et un mot de passe"}]

     		req.session.flas={
     			err:usernamePasswordRequiredError
     		}

     		res.redirect('/session/new');
     		return;
     }

     User.findOneByEmail(req.param('email'),function foundUser(err,user){
     	 if (err) return next(err);

     	 if(!user){
     	 	var noAccountError=[{name:'noAccount',message:'L\'adresse email n\'a pas été trouver'}]

     	 	req.session.flash={
     	 		err: noAccountError
     	 	}
     	 	res.redirect('/session/new');
     	 	return;
     	 }

     bcrypt.compare(req.param('password'),user.encryptedPassword,function(err,valid){
     	if(err) return next(err);

     	if(!valid){
     		var usernamePasswordMismatchError =[{name:'usernamePasswordMismatch',message:'Mot de passe non valide'}]
             req.session.flash={
             	err:usernamePasswordMismatchError
             }  
console.log(user.encryptedPassword);			 
             res.redirect('/session/new');
			 
             return;
     	}

     	req.session.authenticated=true;
     	req.session.User=user;
  
		 console.log("bon mdp");
     	res.redirect('/user/show/' + user.id);
     

     });
     });
},

destroy: function(req,res,next){

	req.session.destroy();
	res.redirect('/');
}
};

