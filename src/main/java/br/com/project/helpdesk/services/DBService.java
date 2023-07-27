package br.com.project.helpdesk.services;

import br.com.project.helpdesk.domain.Chamado;
import br.com.project.helpdesk.domain.Cliente;
import br.com.project.helpdesk.domain.Tecnico;
import br.com.project.helpdesk.domain.enums.Perfil;
import br.com.project.helpdesk.domain.enums.Prioridade;
import br.com.project.helpdesk.domain.enums.Status;
import br.com.project.helpdesk.repositories.ChamadoRepository;
import br.com.project.helpdesk.repositories.ClienteRepository;
import br.com.project.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB(){
        Tecnico tec0 = new Tecnico(null, "Adm", "22117333010", "adm@mail.com", encoder.encode("123"));
        tec0.addPerfil(Perfil.ADMIN);
        Tecnico tec1 = new Tecnico(null, "Bruno", "45111889809", "bruno@gmail.com", encoder.encode("123"));
        tec1.addPerfil(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null, "Dauane", "31158526067", "dauane@gmail.com", encoder.encode("123"));
        tec2.addPerfil(Perfil.ADMIN);
        Tecnico tec3 = new Tecnico(null, "Paulina", "14565829096", "paulina@gmail.com", encoder.encode("123"));

        Cliente cli0 = new Cliente(null, "Osmar", "76142080050", "osmar@gmail.com", encoder.encode("123"));
        Cliente cli1 = new Cliente(null, "Julia", "09622413005", "julia@gmail.com", encoder.encode("123"));
        Cliente cli2 = new Cliente(null, "Shell", "09980483059", "shell@gmail.com", encoder.encode("123"));
        Cliente cli3 = new Cliente(null, "Lucas", "33021071023", "lukito@gmail.com", encoder.encode("123"));

        Chamado c0 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 00", "Primeiro chamado", tec1, cli3 );
        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1 );
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 02", "Primeiro chamado", tec2, cli2 );

        tecnicoRepository.saveAll(Arrays.asList(tec0,tec1,tec2,tec3));
        clienteRepository.saveAll(Arrays.asList(cli0,cli1,cli2,cli3));
        chamadoRepository.saveAll(Arrays.asList(c0,c1,c2));
    }
}
