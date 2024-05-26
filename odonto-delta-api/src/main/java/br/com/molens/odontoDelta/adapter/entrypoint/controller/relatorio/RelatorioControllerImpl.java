package br.com.molens.odontoDelta.adapter.entrypoint.controller.relatorio;

import br.com.molens.odontoDelta.domain.usecase.relatorios.relatorioFichaPaciente.RelatorioFichaPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.relatorios.relatorioFichaPaciente.RelatorioFichaPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import br.com.molens.odontoDelta.utils.FileDownloadUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
public class RelatorioControllerImpl implements RelatorioController {


    private RelatorioFichaPacienteUsecase relatorioFichaPacienteUsecase;


    private UserDataUsecase userDataUsecase;


    @Override
    public void gerarFichaPaciente(Long pacienteId, HttpServletResponse httpServletResponse) {

        byte[] arquivo = relatorioFichaPacienteUsecase.executar(RelatorioFichaPacienteInput.builder()
                        .pacienteId(pacienteId)
                        .empresaId(userDataUsecase.executar().getEmpresa().getId())
                .build());
        FileDownloadUtil.download("FichaPaciente", "pdf", arquivo, httpServletResponse);
    }

}