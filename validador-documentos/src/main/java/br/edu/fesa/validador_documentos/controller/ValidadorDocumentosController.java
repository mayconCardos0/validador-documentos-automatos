/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.edu.fesa.validador_documentos.controller;

import br.edu.fesa.validador_documentos.service.CpfService;
import br.edu.fesa.validador_documentos.service.RgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/validador-documentos")
public class ValidadorDocumentosController {

    @Autowired
    CpfService cpfService;
    @Autowired
    RgService rgService;
    
    @GetMapping("")
    public String index(){
        return "validador/index";
    }
    
    @PostMapping("")
    @ResponseBody
    public boolean validarDoc(String documento, String tipoDocumento){
        
        switch (tipoDocumento){
            case "cpf":
                return cpfService.validarCpf(documento);
            case "rg":
                return rgService.validarRg(documento);
            default:
                return false;
        }
    }
}
