package model;

import java.time.LocalDate;
import java.util.Date;

public class Team {
    private String nome;
    private String gritoDeGuerra;
    private int anoFundacao;
    private int blots;
    private int plifs;
    private int advrunghs;
    private LocalDate data;

    public Team(String nome, String gritoDeGuerra, int anoFundacao) {
        data = LocalDate.now();
        if(anoFundacao <= 0) {
            throw new IllegalArgumentException("Ano de fundação não pode ser negativo");
        }
        if(anoFundacao > data.getYear()) {
            throw new IllegalArgumentException("Ano de fundação não pode ser maior que o ano atual");
        }
        if(nome == null || gritoDeGuerra == null){
            throw new IllegalArgumentException("O nome e o grito de guerra não podem ser nulos");
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
        return (blots * 5) + (plifs * 1) + (advrunghs * -10);
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
