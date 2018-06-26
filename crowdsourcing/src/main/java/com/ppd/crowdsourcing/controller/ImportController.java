package com.ppd.crowdsourcing.controller;

import com.ppd.crowdsourcing.dao.ImportDao;
import com.ppd.crowdsourcing.entity.Fichier;
import com.ppd.crowdsourcing.entity.Theme;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@Controller
public class ImportController {

    private static final int maxColumn = 5;

    @PostMapping(value = "import/{uploadedFiles}")
    @ResponseBody
    public String importFichier(@PathVariable("uploadedFiles")File file) throws IOException {
        System.out.println(file);
        String csvFile = "./croudsourcingJS/.tmp/uploads/test.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
            String[] lineCompare = line.split(cvsSplitBy);
            Fichier fichier = new Fichier();
            Theme theme = new Theme();


            System.out.println("Line [name= " + lineCompare[1] + " , description=" + lineCompare[2] + " , price=" + lineCompare[3] +"]");
        }

        return "import";
    }

    @PostMapping(value = "import")
    @ResponseBody
    public String importFichierTest(@RequestBody ImportDao importFichier) throws IOException {
        System.out.println(importFichier.getImportFile());

        Fichier fichier = new Fichier();
        fichier.setNomFichier(importFichier.getImportFile().getName());
        Theme theme = new Theme();
        theme.setNomTheme(importFichier.getNomTheme());

        String csvFile = "./croudsourcingJS/.tmp/uploads/test.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        br = new BufferedReader(new FileReader(csvFile));
        boolean firstLine = true;
        while ((line = br.readLine()) != null) {
            String[] lineCompare = line.split(cvsSplitBy);
            if(firstLine){
                int nombreColumn = lineCompare.length;
                if(nombreColumn > maxColumn){
                    return "Import impossible depasssement de format";
                }else{
                    for(Attribut attribut : lineCompare){

                    }
                }


            }






            System.out.println("Line [name= " + lineCompare[1] + " , description=" + lineCompare[2] + " , price=" + lineCompare[3] +"]");
        }

        return "import";
    }
}
