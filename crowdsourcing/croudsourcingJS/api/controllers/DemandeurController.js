/**
 * utilisateurController.js
 *
 * @description :: Dans ce controller est défini les fonctions nécessaires au page crowder
 * @help        :: See http://links.sailsjs.org/docs/controllers
 */
//var bcrypt= require ('bcrypt');
module.exports = {


  //Page d'accueil du contoller,lister tous les thèmes
  index:function(req,res,next){

    theme.find(function foundthemes(err,themes) {



      res.view({
        themes:themes
      });


    });
  },


  //Affihage du formulaire d'ajout d'un thème
  newTheme:function (req,res){


    console.log("je suis là");
    res.view();

  },

  //affichage de la liste des fichiers importés par l'utlisateur
  imports:function(req,res,next){

    Import.find(function foundimports(err,listeImport) {

      //console.log(imports);

      res.view({
        listeImport:listeImport
      });


    });
  },


  //affichage du résultat suite au traitement des imports
  analyseImport:function(req,res,next){



    var id=req.param('id');

    const Client = require('sync-rest-client');

    var a =Client.get("http://localhost:8080/countPosNeg/"+id);

    var data=a.body;

    console.log("je suis là 1");
    if (data) {

      var b =Client.get("http://localhost:8080/matriceSimilitude/"+id);

      var data2=b.body;

      console.log("je suis là 2");

      if(data2){


        var c =Client.get("http://localhost:8080/modelDependency/"+id);

        var data3=b.body;

        console.log("je suis là 3");
        res.redirect('/imports/index/');


      }

      else{

        res.redirect('/imports/index/');

      }
    }


    else{

      res.redirect('/imports/index/');
    }




  },

  //affichage du résultat suite au traitement des imports
  traitementImport:function(req,res,next){


    var id=req.param('id');

    const Client = require('sync-rest-client');

    var a =Client.get("http://localhost:8080/attributByImport/"+id);

    var data=a.body;

    res.view({
      data:data
    });


  },

  //enregistrer dans la base les infos rentrées par l'utilisateur
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
