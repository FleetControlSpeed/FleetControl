package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/*
    @Author: Pedro Henrique Vieira
    Date: 07/05/2023
    Last att: 27/05/2023
 */

@Service
public class EventosService {

    @Autowired
    private EventosRepository repository;

    @Autowired
    private VeiculoService veiculoService;

    public Eventos buscarPorId(Long id) {

        if (id == 0) {
            throw new RuntimeException(", por favor, informe um valor valido!");

        } else if (repository.findById(id).isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar o evento informado!");

        } else {
            return repository.findById(id).orElse(null);
        }
    }

    public List<Eventos> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar nenhum evento cadastrado!");

        } else {
            return repository.findAll();
        }
    }

    public List<Eventos> listarPorAtivo() {
        if (repository.buscarPorAtivo().isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar nenhum evento ativo cadastrado!");

        } else {
            return repository.buscarPorAtivo();
        }
    }

    @Transactional
    public Eventos salvar(Eventos eventos) {
            return this.repository.save(eventos);
    }

    @Transactional
    public void editar(Long id, Eventos eventosNovo){
        final Eventos eventosBanco = this.buscarPorId(id);

        if(eventosBanco == null || !eventosBanco.getId().equals(eventosNovo.getId())){
            throw new RuntimeException(", não foi possivel identificar o evento informado!");

        } else {
            salvar(eventosNovo);
        }
    }

    @Transactional
    public void desativar(Long id) {
        Eventos eventos = buscarPorId(id);

        if (!eventos.isAtivo()) {
            throw new RuntimeException(", evento informado já esta desativado!");

        } else {
            repository.desativar(id);
        }
    }

    @Transactional
    public void ativar(Long id) {
        Eventos eventos = buscarPorId(id);

        if (eventos.isAtivo()) {
            throw new RuntimeException(", evento informado já esta ativado!");
        } else {
            repository.ativar(id);
        }
    }

    @Transactional
    public void fechar(Long id, Eventos evento){

        Veiculo veiculo = veiculoService.buscarPorId(evento.getVeiculo().getId());

        evento.setDataRetorno(LocalDateTime.now());
        evento.setKmTotal(evento.getKmFinal() - veiculo.getKm());
        salvar(evento);

        veiculo.setKm(evento.getKmFinal());

    }

    @Transactional
    public String finalizar(Long id){

        Eventos evento = buscarPorId(id);


        int diaEntrada = evento.getDataInicio().getDayOfMonth();         int horaEntrada = evento.getDataInicio().getHour();
        int mesEntrada = evento.getDataInicio().getMonthValue();         int minutoEntrada = evento.getDataInicio().getMinute();
        int anoEntrada = evento.getDataInicio().getYear();               int segundoEntrada = evento.getDataInicio().getSecond();

        int diaSaida = evento.getDataRetorno().getDayOfMonth();          int horaSaida = evento.getDataRetorno().getHour();
        int mesSaida = evento.getDataRetorno().getMonthValue();          int minutoSaida = evento.getDataRetorno().getMinute();
        int anoSaida = evento.getDataRetorno().getYear();                int segundoSaida = evento.getDataRetorno().getSecond();

        int horasTotal = 0;
        int minutosTotal = 0;
        int segundosTotal = 0;

        while (diaEntrada != diaSaida && mesEntrada != mesSaida && anoEntrada != anoSaida) {

            segundoEntrada++;
            segundosTotal++;
            if(segundoEntrada >= 60){
                minutoEntrada++;
                minutosTotal++;
                segundoEntrada = 0;
            }

            minutoEntrada++;
            minutosTotal++;
            if(minutoEntrada >= 60){
                horaEntrada++;
                horasTotal++;
                minutoEntrada=0;
            }

            horaEntrada++;
            horasTotal++;
            if (horaEntrada >= 24) {
                horaEntrada = 0;
                diaEntrada++;

                if (mesEntrada == 1 || mesEntrada == 3 || mesEntrada == 5 || mesEntrada == 7 || mesEntrada == 8 || mesEntrada == 10 || mesEntrada == 12) {
                    if (diaEntrada > 31) {      // --> Verifica se o mês possui 31 dias
                        diaEntrada = 1;
                        mesEntrada++;
                        if (mesEntrada >= 12) {
                            anoEntrada++;
                            mesEntrada = 1;
                        }
                    }
                } else if (mesEntrada == 2){    // --> Verifica se o mês é fevereiro
                    if(anoEntrada % 4 == 0) {   // --> Verifica se ano é bissexto
                        if (diaEntrada > 29) {
                            diaEntrada = 1;
                            mesEntrada++;
                        }
                    } else {
                        if (diaEntrada > 28) {
                            diaEntrada = 1;
                            mesEntrada++;
                        }
                    }
                } else {
                    if (diaEntrada > 30) {      // --> Verifica se o mês já atingiu a data limite
                        diaEntrada = 1;
                        mesEntrada++;
                    }
                }
            }
        }

        while (segundoEntrada != segundoSaida) {
            segundoEntrada++;
            segundosTotal++;
            if(segundoEntrada >= 60){
                segundoEntrada =0;
                minutoEntrada++;
                minutosTotal++;
            }
        }

        while (minutoEntrada != minutoSaida) {
            minutoEntrada++;
            minutosTotal++;
            if(minutoEntrada >= 60){
                minutoEntrada =0;
                horaEntrada++;
            }
        }

        while (horaEntrada != horaSaida) {
            horaEntrada++;
            horasTotal++;
        }

        if(segundosTotal >= 60){
            minutosTotal++;
            segundosTotal=0;
        }

        if(minutosTotal >= 60){
            horasTotal++;
            minutosTotal=0;
        }

        evento.setPeriodo((horasTotal*60L*60L) + (minutosTotal*60L) + segundosTotal);


        return evento.toString();
    }
}
