package com.andrielelange.ballitgame.model;

import java.time.LocalDate;
import java.util.Date;

public class Team {
    private String nome;
    private String gritoDeGuerra;
    private int anoFundacao;
    private int blots;
    private int plifs;
    private int advrunghs;
    private int grusht;
    private LocalDate data;

    public Team(String nome, String gritoDeGuerra, int anoFundacao) {
        data = LocalDate.now();
        if(anoFundacao <= 0 || anoFundacao > data.getYear()) {
            throw new IllegalArgumentException("O time não pode ser cadastrado. Ano de fundação inválido.\n");
        }
        if(nome == null || gritoDeGuerra == null || nome == "" || gritoDeGuerra == "") {
            throw new IllegalArgumentException("O time não pode ser cadastrado por o nome e o grito de guerra serem nulos.\n");
        }
        this.nome = nome;
        this.gritoDeGuerra = gritoDeGuerra;
        this.anoFundacao = anoFundacao;
        this.blots = 0;
        this.plifs = 0;
        this.advrunghs = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getGritoDeGuerra() {
        return gritoDeGuerra;
    }

    public int getPontos() {
        return 50 + (blots * 5) + (plifs * 1) + (advrunghs * -10) + (grusht * 3);
    }

    public int getBlots() {
        return blots;
    }

    public int getPlifs() {
        return plifs;
    }

    public int getAdvrunghs() {
        return advrunghs;
    }

    private int getAnoFundacao() {
        return anoFundacao;
    }

    public void addBlot() {
        blots++;
    }

    public void addPlif() {
        plifs++;
    }

    public void addAdvrungh() {
        advrunghs++;
    }

    public void addGrusht() {
        grusht++;
    }

    public int getGrusht() {
        return grusht;
    }

    public String toString() {
        return "Time: " + nome + " - Fundado em: " + anoFundacao + " - Pontos: " + getPontos();
    }

    /*
     *     @Override
    public String toString() {
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = data.format(anoDeFundação);

        return String.format(
            "Time %s \n Grito de guerra: %s\n  Ano de Fundação: %s", nome, gritoDeGuerra, dataFormatada
        );
    }
     */
}
