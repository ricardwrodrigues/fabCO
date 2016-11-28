package br.com.fabrico.apuracao.controller;

import br.com.fabrico.apuracao.domain.EscolaSamba;
import br.com.fabrico.apuracao.domain.Nota;
import br.com.fabrico.apuracao.domain.Quesito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller para o gerenciamento da Nota.
 */
@RestController
@RequestMapping("/api")
public class NotaController {

    private final Logger log = LoggerFactory.getLogger(NotaController.class);

    private List<Nota> notas;
        
    /**
     * POST  /nota Salva uma nota
     *
     * @param nota para salvar
     * @return status 201 (Created)
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/nota",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Nota> createNota(@Valid @RequestBody Nota nota) throws URISyntaxException {
        log.debug("API_REST criar Nota : {}", nota);
        Nota result = new Nota();
        return ResponseEntity.created(new URI("/api/nota/" + result.getId())).body(result);
    }

    /**
     * POST  /notas Salva uma lista de nota
     *
     * @param notas {@link List<Nota>} para salvar
     * @return status 201 (Created)
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/notas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Nota> createNotas(@RequestBody List<Nota> notas) throws URISyntaxException {
        log.debug("API_REST criar Notas : {}", notas);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT  /nota atualiza nota.
     *
     * @param nota para ser atualizado
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/nota",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Nota> updateNota(@Valid @RequestBody Nota nota) throws URISyntaxException {
        log.debug("API_REST atualiza Nota : {}", nota);
        if (nota.getId() == null) {
            return createNota(nota);
        }
        Nota result = new Nota();
        return ResponseEntity.ok().body(result);
    }

    /**
     * GET  /notas lista dos
     *
     * @return status 200 (OK)
     */
    @RequestMapping(value = "/notas",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Nota> getAllNotas() {
        log.debug("API_REST lista todas as Notas");
        List<Nota> notas = new ArrayList<Nota>();
        return notas;
    }

    /**
     * GET  /nota/:id retorna nota por id
     *
     * @param id  da nota a ser retornada
     * @return status 200 (OK) ou status 404 (NOT FOUND) se não existir
     */
    @RequestMapping(value = "/nota/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Nota> getNota(@PathVariable Long id) {
        log.debug("API_REST retorna Nota por id : {}", id);
        Nota nota = new Nota();
        return Optional.ofNullable(nota)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /nota/jurado/:juradoId retorna notas pora o jurado
     *
     * @param juradoId  jurado para retornar as notas a serem aplicadas
     * @return status 200 (OK) ou status 404 (NOT FOUND) se não existir
     */
    @RequestMapping(value = "/nota/jurado/{juradoId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Nota>> getNotaJurados(@PathVariable Long juradoId) {
        log.debug("API_REST retorna notas para o  juradoId : {}", juradoId);
        criaListaNotas();
        return Optional.ofNullable(notas)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    private void criaListaNotas(){
        notas = new ArrayList<Nota>();
        for(Long i=1L; i<4; i++){
            Nota nota = new Nota();
            nota.setId(i);
            EscolaSamba escola = new EscolaSamba();
            escola.setId(i);
            escola.setNome("Escola "+i);
            Quesito quesito = new Quesito();
            quesito.setId(i);
            quesito.setNome("Quesito"+1);
            nota.escolaSamba(escola);
            nota.setQuesito(quesito);
            notas.add(nota);
        }

    }

    /**
     * DELETE  /nota/:id  remove a nota por id
     *
     * @param id da nota a ser removida
     * @return status 200 (OK)
     */
    @RequestMapping(value = "/nota/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        log.debug("API_REST remove a Nota por id : {}", id);
        return ResponseEntity.ok().build();
    }


}
