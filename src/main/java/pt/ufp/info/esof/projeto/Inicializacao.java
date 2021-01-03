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
    private final TarefaEfetivaRepository tarefaEfetivaRepository;
    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    public Inicializacao(ClienteRepository clienteRepository, EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository, TarefaEfetivaRepository tarefaEfetivaRepository, TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.clienteRepository = clienteRepository;
        this.empregadoRepository = empregadoRepository;
        this.projetoRepository = projetoRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
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

        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setNome("Tarefa1");
        tp1.setTempoPrevistoHoras(8);
        p1.adicionarTarefas(tp1); // associa o projeto à tarefa e vice-versa


        p1.setCliente(c1); // associa projeto ao cliente
        c1.getProjetos().add(p1); // associa cliente ao projeto
        //p1.adicionarTarefas(tp1); // associa o projeto à tarefa e vice-versa

        this.projetoRepository.save(p1);
        tarefaPrevistaRepository.save(tp1); // como as tarefas sao mapeadas pelo projeto, tem de ser salvas depois do projeto, enquanto o projeto nao estiver na bd nao posso salvar as tarefas

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
