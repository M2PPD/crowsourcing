module.exports = {

  'consulter':function (req,res){
    res.view();
  },

  'sucess':function (req,res){
    res.view();
  },


  'fichier':function (req,res) {
    var Client = require('node-rest-client').Client;
    var client = new Client();


    req.file('inputFile').upload({
      maxBytes: 100000000
    }, function whenDone(err,uploadedFiles){


      if (err) {
        return res.negotiate(err);
      }

      if (uploadedFiles.length === 0){
        return res.badRequest('No file was uploaded');
      }
      var args = {
        data:{test:"hello"},
        parameters:{fileAccess: uploadedFiles[0].fd},
        header:{"Content-Type": "application/json"}
      };
      // var baseUrl = sails.config.custom.baseUrl;
      client.post("http://localhost:8080/import",args, function(data,response){
        res.redirect('/import/sucess');

      });

    });



  }

}
