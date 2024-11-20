/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Mauro Gaspar
 */

import java.util.Date;
public class pagamentos {
    
   
    private double valor;
    private Date data;
    private MetodoPagamento metodo;

    void exibirDetalhes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    public enum MetodoPagamento {
        CARTAO_CREDITO, TRANSFERENCIA_BANCARIA, DINHEIRO
    }

    // Construtor
    public pagamentos(double valor, Date data, MetodoPagamento metodo) {
        this.valor = valor;
        this.data = data;
        this.metodo = metodo;
    }

    // Getters e Setters
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public MetodoPagamento getMetodo() {
        return metodo;
    }

    public void setMetodo(MetodoPagamento metodo) {
        this.metodo = metodo;
    }
}
