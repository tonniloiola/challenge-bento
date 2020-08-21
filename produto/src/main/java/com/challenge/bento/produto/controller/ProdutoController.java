package com.challenge.bento.produto.controller;

import com.challenge.bento.produto.dto.ProdutoDto;
import com.challenge.bento.produto.model.Produto;
import com.challenge.bento.produto.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    @ApiOperation(value = "Retorna uma lista de produtos", response = Produto[].class)
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produtos = produtoService.findAll();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping(path = "/produtos/getByNome/{nome}")
    @ApiOperation(value = "Retorna usuário pelo nome", response = Produto[].class)
    public ResponseEntity<?> getByDescricao(@PathVariable("nome") String nome) {
        List<Produto> produtos = produtoService.findByNome(nome);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
    
    @GetMapping(path = "produtos/{id}")
    @ApiOperation(value = "Retorna o produto pelo id", response = Produto[].class)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Produto produto = produtoService.findById(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping("produtos")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "Cria um novo produto", response = ProdutoDto[].class)
    public ResponseEntity<?> salvar(@Valid @RequestBody ProdutoDto produto) {
        return new ResponseEntity<>(produtoService.salvar(produto),HttpStatus.CREATED);
    }

    @DeleteMapping("produtos/{id}")
    @ApiOperation(value = "Remove um produto", response = Produto[].class)
    public ResponseEntity<?>  deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "produtos")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "Atualiza informações do produto", response = Produto[].class)
    public ResponseEntity<?> atualizar(@RequestBody Produto produto) {
        produtoService.atualizar(produto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
