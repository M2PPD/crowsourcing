/**
 * User.js
 *
 * @description :: TODO: You might write a short summary of how this model works and what it represents here.
 * @docs        :: http://sailsjs.org/documentation/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    schema:true,
	
  	usertype:{
  		type:'string',
  		required:false
  	},
  
    username:{
  		type:'string',
  		required:true
  	},
  
	
	email:{
		type:'string',
		email:'true',
		required:true,
		unique:true
	},
	 
	encryptedPassword:{
		type:'string'
	}
	

  },
  beforeCreate: function (values,next){

  
  
  
    //var randomstring=Math.random().toString(36).slice(-8);
   if(!values.password || values.password != values.confirmation){
	   
	   return next({err:["Les mots de passe ne correspondent pas"]});
	   
	   
   }
     
     

  	require('bcrypt').hash(values.password,10,function passwordEncrypted(err,encryptedPassword){
  		if(err) return next(err);
  		values.encryptedPassword = encryptedPassword;

  		next();
  	});
  }
};

