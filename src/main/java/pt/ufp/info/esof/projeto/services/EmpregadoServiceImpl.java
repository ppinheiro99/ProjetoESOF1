package pt.ufp.info.esof.projeto.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpregadoServiceImpl implements EmpregadoService {
    private final EmpregadoRepository empregadoRepository;

    public EmpregadoServiceImpl(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }


    @Override
    public List<Empregado> findAll() {
        List<Empregado> e =new ArrayList<>();
        empregadoRepository.findAll().forEach(e::add);
        return e;
    }

    @Override
    public Optional<Empregado> findById(Long id) {
        return empregadoRepository.findById(id);
    }

    @Override
    public Optional<Empregado> createEmpregado(Empregado empregado) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findByEmail(empregado.getEmail());
        if(!optionalEmpregado.isPresent()){
            return Optional.of( empregadoRepository.save(empregado));
        }
        return Optional.empty();
    }
}
