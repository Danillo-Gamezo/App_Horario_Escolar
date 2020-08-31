package br.com.santarosadigital.app_horario_aula;

import com.orm.SugarRecord;

public class Horario extends SugarRecord {

    public static String filtro_dia_da_semana;

    Long id;
    String dia, bloco, disciplina, laboratorio;

    public Horario() {
    }

    public Horario(String dia, String bloco, String disciplina, String laboratorio) {
        this.dia = dia;
        this.bloco = bloco;
        this.disciplina = disciplina;
        this.laboratorio = laboratorio;
    }

    @Override
    public String toString() {
        return bloco + "Bloco \n" + disciplina + "\n" + laboratorio;
    }

    public static String getFiltro_dia_da_semana() {
        return filtro_dia_da_semana;
    }

    public static void setFiltro_dia_da_semana(String filtro_dia_da_semana) {
        Horario.filtro_dia_da_semana = filtro_dia_da_semana;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }
}
