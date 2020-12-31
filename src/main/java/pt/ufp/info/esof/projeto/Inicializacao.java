package pt.ufp.info.esof.projeto;

import lombok.SneakyThrows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.*;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    private final ClienteRepository clienteRepository;
    private final EmpregadoRepository empregadoRepository;
    private final ProjetoRepository projetoRepository;
    private final TarefaRepository tarefaRepository;
    private final TempoPrevistoRepository tempoPrevistoRepository;

    @Autowired
    public Inicializacao(ClienteRepository clienteRepository, EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository, TarefaRepository tarefaRepository, TempoPrevistoRepository tempoPrevistoRepository) {
        this.clienteRepository = clienteRepository;
        this.empregadoRepository = empregadoRepository;
        this.projetoRepository = projetoRepository;
        this.tarefaRepository = tarefaRepository;
        this.tempoPrevistoRepository = tempoPrevistoRepository;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("\n\n\nInicializou\n\n\n");

        Projeto p1 = new Projeto();
        p1.setNome("ESOF");

        Cliente c1 = new Cliente();
        c1.setNome("Armando");
        this.clienteRepository.save(c1);

        Empregado e1 = new Empregado();
        e1.setNome("Pedro");
        e1.setCargo(Cargo.desenvolvedorSenior);
        this.empregadoRepository.save(e1);

        TarefaEfetiva t1 = new TarefaEfetiva();
        t1.setNome("Tarefa1");
       // tarefaRepository.save(t1);

//        TarefaPrevista tprevisto = new TarefaPrevista(); // esta classe nao está a fazer nada (discutir se vamos ou nao tirá-la)
//        tprevisto.setTempoPrevistoHoras(8);
//        tprevisto.setTarefaEfetiva(t1);
//        //tempoPrevistoRepository.save(tprevisto);
//
//        t1.setTempoPrevisto(tprevisto);

        //this.projetoRepository.save(p1);
        p1.setCliente(c1); // associa projeto ao cliente
        c1.getProjetos().add(p1); // associa cliente ao projeto
        //p1.adicionarTarefas(t1); // associa o projeto à tarefa e vice-versa
    //    this.tarefaRepository.save(t1);
        //this.projetoRepository.save(p1);

        t1.setEmpregado(e1); // associa tarefa ao empregado
        e1.getTarefaEfetivas().add(t1); // associa empregado à tarefa
      //  this.tarefaRepository.save(t1);
        this.projetoRepository.save(p1);

        System.out.println("\n" + "valorHora: "+ e1.valorHora()+"\n");
        /*
            Projeto p = new Projeto();
            Tarefa t = new Tarefa();
            t.setNome("tarefa1");
            p.getTarefas().add(t);
            System.out.println( p.estadoDoProjeto());

            System.out.println(p);
       */

        //c1.getProjetos().get(0).mostrarProgresso();
    }
}