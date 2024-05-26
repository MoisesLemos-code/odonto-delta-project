package br.com.molens.odontoDelta.adapter.entrypoint.controller.relatorio;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(value = "relatorios.html", tags = "Relatorios", description = "Manutenção de Relatorios")
@RequestMapping("/relatorios")
public interface RelatorioController {

    @ApiOperation(value = "Gera o relatório de ficha de paciente")
    @GetMapping(value = "/fichaPaciente/{pacienteId}")
    void gerarFichaPaciente(@PathVariable @Valid Long pacienteId, HttpServletResponse httpServletResponse);



}