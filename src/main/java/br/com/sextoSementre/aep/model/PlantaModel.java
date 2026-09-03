package br.com.sextoSementre.aep.model;

import br.com.sextoSementre.aep.enums.EstadoConservacao;
import br.com.sextoSementre.aep.enums.Luminosidade;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "plantas")
public class PlantaModel {

    @Id
    private String id;

    @Indexed(unique = true)
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public List<String> getNomesPopulares() {
        return nomesPopulares;
    }

    public void setNomesPopulares(List<String> nomesPopulares) {
        this.nomesPopulares = nomesPopulares;
    }

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public Boolean getVenenosa() {
        return venenosa;
    }

    public void setVenenosa(Boolean venenosa) {
        this.venenosa = venenosa;
    }

    public Boolean getSeguraConsumoHumano() {
        return seguraConsumoHumano;
    }

    public void setSeguraConsumoHumano(Boolean seguraConsumoHumano) {
        this.seguraConsumoHumano = seguraConsumoHumano;
    }

    public double getAlturaAproximada() {
        return alturaAproximada;
    }

    public void setAlturaAproximada(double alturaAproximada) {
        this.alturaAproximada = alturaAproximada;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public int getLongevidade() {
        return longevidade;
    }

    public void setLongevidade(int longevidade) {
        this.longevidade = longevidade;
    }

    public String getTipoSolo() {
        return tipoSolo;
    }

    public void setTipoSolo(String tipoSolo) {
        this.tipoSolo = tipoSolo;
    }

    public Luminosidade getLuminosidade() {
        return luminosidade;
    }

    public void setLuminosidade(Luminosidade luminosidade) {
        this.luminosidade = luminosidade;
    }

    public String getPeriodoFloracao() {
        return periodoFloracao;
    }

    public void setPeriodoFloracao(String periodoFloracao) {
        this.periodoFloracao = periodoFloracao;
    }

    public List<String> getCorDasFlores() {
        return corDasFlores;
    }

    public void setCorDasFlores(List<String> corDasFlores) {
        this.corDasFlores = corDasFlores;
    }
}
