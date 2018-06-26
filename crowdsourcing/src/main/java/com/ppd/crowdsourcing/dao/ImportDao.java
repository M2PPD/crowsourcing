package com.ppd.crowdsourcing.dao;

import java.io.File;

public class ImportDao {
    private String nomTheme;
    private File importFile;
    private String accessFile;

    public String getNomTheme() {
        return nomTheme;
    }

    public void setNomTheme(String nomTheme) {
        this.nomTheme = nomTheme;
    }

    public File getImportFile() {
        return importFile;
    }

    public void setImportFile(File importFile) {
        this.importFile = importFile;
    }

}
