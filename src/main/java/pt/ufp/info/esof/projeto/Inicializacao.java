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

    Logger logger = LoggerFactory.getLogger(this.getClass());

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

        Projeto p1 = criarProjeto("ESOF");
        Projeto p2 = criarProjeto("Projeto2");
        Projeto p3 = criarProjeto("Projeto3");

        Cliente c1 = criarCliente("ZÉ Nando", "cliente1@teste.com");
        Cliente c2 = criarCliente("Cliente2", "cliente2@teste.com");
        Cliente c3 = criarCliente("Cliente3", "cliente3@teste.com");


        associarProjetoCliente(p1,c1);
        associarProjetoCliente(p2,c2);
        associarProjetoCliente(p3,c3);


        Empregado e1 = criarEmpregado("Pedro", "36763@ufp.edu.pt", Cargo.desenvolvedorJunior);
        Empregado e2 = criarEmpregado("Samuel", "36726@ufp.edu.pt", Cargo.desenvolvedorSenior);
        Empregado e3 = criarEmpregado("João", "165156531651546@ufp.edu.pt", Cargo.desenvolvedorSenior);

        TarefaPrevista tp1 = criarTarefaPrevista("Tarefa1");
        TarefaPrevista tp2 = criarTarefaPrevista("Tarefa2");
        TarefaPrevista tp3 = criarTarefaPrevista("Tarefa3");
        TarefaPrevista tp4 = criarTarefaPrevista("Tarefa4");
        TarefaPrevista tp5 = criarTarefaPrevista("Tarefa5");



        associarTarefaProjeto(tp1,p1);
        associarTarefaProjeto(tp2,p1);
        associarTarefaProjeto(tp3,p2);
        associarTarefaProjeto(tp3,p3);
        associarTarefaProjeto(tp5,p3);

        associarTarefaEmpregado(tp1,e1);
        associarTarefaEmpregado(tp2,e2);









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


    Empregado criarEmpregado(String nome, String email, Cargo cargo) {
        Empregado e1 = new Empregado();
        e1.setNome(nome);
        e1.setEmail(email);
        e1.setCargo(cargo);
        this.empregadoRepository.save(e1);
        return e1;
    }

    Cliente criarCliente(String nome, String email) {
        Cliente c1 = new Cliente();
        c1.setNome(nome);
        c1.setEmail(email);
        this.clienteRepository.save(c1);
        return c1;

    }

    Projeto criarProjeto(String nome) {
        Projeto p1 = new Projeto();
        p1.setNome(nome);
        this.projetoRepository.save(p1);
        return p1;
    }

    TarefaPrevista criarTarefaPrevista(String nome) {
        TarefaPrevista tp1 = new TarefaPrevista();
        tp1.setNome(nome);
        tp1.setTempoPrevistoHoras(8);
        tarefaPrevistaRepository.save(tp1); // como as tarefas sao mapeadas pelo projeto, tem de ser salvas depois do projeto, enquanto o projeto nao estiver na bd nao posso salvar as tarefas
        return tp1;
    }


    void associarTarefaProjeto(TarefaPrevista tarefaPrevista,Projeto projeto){
        projeto.adicionarTarefas(tarefaPrevista); // associa o projeto à tarefa e vice-versa
        tarefaPrevistaRepository.save(tarefaPrevista); // como as tarefas sao mapeadas pelo projeto, tem de ser salvas depois do projeto, enquanto o projeto nao estiver na bd nao posso salvar as tarefas

    }

    void associarProjetoCliente(Projeto projeto,Cliente cliente){
        projeto.setCliente(cliente); // associa projeto ao cliente
        cliente.getProjetos().add(projeto); // associa cliente ao projeto
        projetoRepository.save(projeto);
        clienteRepository.save(cliente);
    }

    void associarTarefaEmpregado(TarefaPrevista tarefaPrevista,Empregado empregado){
        tarefaPrevista.atribuirTarefaEfetiva();
        tarefaPrevista.getTarefaEfetiva().setEmpregado(empregado);
        empregado.getTarefaEfetivas().add(tarefaPrevista.getTarefaEfetiva());
       tarefaEfetivaRepository.save(tarefaPrevista.getTarefaEfetiva());
       tarefaPrevistaRepository.save(tarefaPrevista);
       empregadoRepository.save(empregado);
    }

}
