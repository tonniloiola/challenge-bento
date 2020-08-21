package com.challenge.bento.produto.service;

import com.challenge.bento.produto.dto.ProdutoDto;
import com.challenge.bento.produto.error.ResourceNotFoundException;
import com.challenge.bento.produto.model.Produto;
import com.challenge.bento.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }
    public List<Produto> findByNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Produto findById(Long id){
        verificaSeExisteProdutoPeloId(id);
        return produtoRepository.findById(id).get();
    }

    public Produto salvar(ProdutoDto produto){
        Produto produtoSalvar = new Produto(produto);
        return produtoRepository.save(produtoSalvar);
    }

    public Produto atualizar(Produto produto){
        verificaSeExisteProdutoPeloId(produto.getId());
        return produtoRepository.save(produto);
    }

    public void deletar(Long id){
        verificaSeExisteProdutoPeloId(id);
        Produto produto = produtoRepository.findById(id).get();
        produtoRepository.delete(produto);
    }

    private void verificaSeExisteProdutoPeloId(Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (!produtoOptional.isPresent())
            throw new ResourceNotFoundException("Produto não encontrado para o ID: "+id);
    }

}

