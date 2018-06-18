module.exports = {

  attributes: {
    schema:true,

	
  	id_Crowder_ID:{
  		type:'integer',
  		required:true
  	},
  
    id_Comparaison_ID:{
  		type:'integer',
  		required:true
  	},
  
	isChamp1_Left:{
  		type:'boolean',
  		required:false
  	},
	
	isChamp2_Left:{
  		type:'boolean',
  		required:false
  	},
	
	isChamp3_Left:{
  		type:'boolean',
  		required:false
  	},
	
	isChamp4_Left:{
  		type:'boolean',
  		required:false
  	},
	
	isChamp5_Left:{
  		type:'boolean',
  		required:false
  	},
	
	isSimilaire:{
  		type:'boolean',
  		required:false
  	}

  }
  
  
  /*beforeCreate: function(myModel, next) {
    if(!myModel.Id) {
        ComparaisonCrowder.count(function(err,num) {
            if(err){
                next(err);
            } else {
                myModel.Id = num + 1;
				
				console.log("id:"+myModel.Id);
                next();
            }
        });
    } else {
        next();
    }
  }*/

  
};