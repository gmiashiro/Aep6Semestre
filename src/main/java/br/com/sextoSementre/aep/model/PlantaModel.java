package br.com.sextoSementre.aep.model;

import br.com.sextoSementre.aep.enums.EstadoConservacao;
import br.com.sextoSementre.aep.enums.Luminosidade;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "plantas")
public class PlantaModel {

    @Id
    private String id;

    private String nomeCientifico;
    private List<String> nomesPopulares;

    private EstadoConservacao estadoConservacao;

    private Boolean venenosa;
    private Boolean seguraConsumoHumano;

    private double alturaAproximada;
    private String porte;
    private int longevidade;
    private String tipoSolo;
    private Luminosidade luminosidade;

    private String periodoFloracao;
    private List<String> corDasFlores;


    public PlantaModel(
            String porte,
            String id,
            String nomeCientifico,
            List<String> nomesPopulares,
            EstadoConservacao estadoConservacao,
            Boolean venenosa,
            Boolean seguraConsumoHumano,
            double alturaAproximada,
            int longevidade,
            String tipoSolo,
            Luminosidade luminosidade,
            String periodoFloracao,
            List<String> corDasFlores
    ) {

        this.porte = porte;
        this.id = id;
        this.nomeCientifico = nomeCientifico;
        this.nomesPopulares = new ArrayList<>(nomesPopulares);
        this.estadoConservacao = estadoConservacao;
        this.venenosa = venenosa;
        this.seguraConsumoHumano = seguraConsumoHumano;
        this.alturaAproximada = alturaAproximada;
        this.longevidade = longevidade;
        this.tipoSolo = tipoSolo;
        this.luminosidade = luminosidade;
        this.periodoFloracao = periodoFloracao;
        this.corDasFlores = new ArrayList<>(corDasFlores);
    }
}
