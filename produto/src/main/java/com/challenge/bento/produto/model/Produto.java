package com.challenge.bento.produto.model;

import com.challenge.bento.produto.dto.ProdutoDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;


@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Produto(ProdutoDto produtoDto) {
        this.nome = produtoDto.getNome();

        if(produtoDto.getDataCadastro() == null){
            this.dataCadastro = LocalDate.now();
        }else{
            this.dataCadastro = produtoDto.getDataCadastro();
        }
    }

    public Produto() {
    }
}
