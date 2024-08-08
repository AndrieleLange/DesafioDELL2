package com.andrielelange.ballitgame.model;

import java.time.LocalDate;

public class Team {
    // informações básicas sobre o time
    private String nome;
    private String gritoDeGuerra;
    private int anoFundacao;

    // informações sobre a pontuação do time no jogo
    private int blots;
    private int plifs;
    private int advrunghs;
    private int grusht;

    public Team(String nome, String gritoDeGuerra, int anoFundacao) {
        LocalDate data = LocalDate.now(); // pra usar de comparação com o ano de fundação

        // validações
        if(anoFundacao <= 0 || anoFundacao > data.getYear()) {
            throw new IllegalArgumentException("O time não pode ser cadastrado. Ano de fundação inválido.\n");
        }
        if(nome == null || gritoDeGuerra == null || nome == "" || gritoDeGuerra == "") {
            throw new IllegalArgumentException("O time não pode ser cadastrado por o nome e ou grito de guerra serem nulos.\n");
        }

        this.nome = nome;
        this.gritoDeGuerra = gritoDeGuerra;
        this.anoFundacao = anoFundacao;
        this.blots = 0;
        this.plifs = 0;
        this.advrunghs = 0;
    }

    // getters
    public String getNome() { return nome;}
    public String getGritoDeGuerra() { return gritoDeGuerra;}
    public int getAnoFundacao() { return anoFundacao;}

    public int getPontos() {
        return 50 + (blots * 5) + (plifs * 1) + (advrunghs * -10) + (grusht * 3);
    }

    public int getBlots() { return blots; }
    public int getPlifs() {return plifs;   }
    public int getAdvrunghs() { return advrunghs;}
    public int getGrusht() { return grusht; }

    // setters ou adições
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

    // não achei necessário fazer um tostring
}
