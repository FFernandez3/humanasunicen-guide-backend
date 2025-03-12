package org.humanas.guia.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.humanas.guia.enums.FileMonth;
import org.humanas.guia.enums.FileType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileNameTemplate {
    private String catedra;
    private FileType tipoArchivo;
    private int anioArchivo;
    private FileMonth llamado;

    public String getFileTemplateName(){
        return this.tipoArchivo + " " + this.catedra + " " + this.llamado + " " + anioArchivo;
    }
}
