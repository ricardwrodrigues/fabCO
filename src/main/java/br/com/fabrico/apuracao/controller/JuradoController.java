package br.com.fabrico.apuracao.controller;

import br.com.fabrico.apuracao.domain.Jurado;
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
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * REST controller para o gerenciamento Jurado.
 */
@RestController
@RequestMapping("/api")
public class JuradoController {

    private final Logger log = LoggerFactory.getLogger(JuradoController.class);

    private List<Jurado> jurados = new ArrayList<Jurado>();

    private Long idToJurado = 1L;
        
    /**
     * POST  /jurado Cria jurado
     *
     * @return status 201 (Created)
     * @param jurado o para salvar
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/jurado",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jurado> createJurado(@Valid @RequestBody Jurado jurado) throws URISyntaxException {
        log.debug("REST request to save Jurado : {}", jurado);
        jurado.cpf(jurado.getCpf().replace(".","").replace("-",""));

        jurado.setId(idToJurado++);
        jurados.add(jurado);
       return ResponseEntity.created(new URI("/api/jurado/" + jurado.getId()))
                .body(jurado);
    }

    /**
     * PUT  /jurado atualiza um jurado existente
     *
     * @param jurado o jurado para ser atualizado
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/jurado",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jurado> updateJurado(@Valid @RequestBody Jurado jurado) throws URISyntaxException {
        log.debug("API_REST atulizar um Jurado : {}", jurado);
        if (jurado.getId() == null) {
            return createJurado(jurado);
        }
        Iterator<Jurado> juradoIn = jurados.iterator();
        while (juradoIn.hasNext()) {
            Jurado jura = juradoIn.next();
            if (jura.getId() == jurado.getId()) {
                jura.setNome(jurado.getNome());
                jura.setCpf(jurado.getCpf());
            }
        }
        return ResponseEntity.ok()
            .body(jurado);
    }

    /**
     * GET  /jurados lista dos os jurados
     *
     * @return jurados
     */
    @RequestMapping(value = "/jurados",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Jurado> getAllJurados() {
        log.debug("API_REST lista todos Jurados");
        return jurados;
    }

    /**
     * GET  /jurado/isCpfValid :cpf .
     *
     * @param cpf do jurado
     * @return um jurado se existir ou um status 404
     */
    @RequestMapping(value = "/jurado/isCpfValid/{cpf}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jurado> getJuradoIsValid(@PathVariable String cpf) {
        log.debug("API_REST valida se existe jurado pelo cpf : {}", cpf);
        Jurado jurado = null;
        for (Jurado juratoGet : jurados) {
            if(juratoGet.getCpf().equals(cpf)) {
                jurado = juratoGet;
            }
        }

        return Optional.ofNullable(jurado)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /jurado/:id retorna um jurado pelo id.
     *
     * @param id o id do jurado
     * @return um jurado ou um status 404 se não existir
     */
    @RequestMapping(value = "/jurado/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jurado> getJurado(@PathVariable Long id) {
        log.debug("API_REST retorna o Jurado pelo id : {}", id);
        Jurado jurado = new Jurado();
        for (Jurado juratoGet : jurados) {
            if(juratoGet.getId() == id) {
                jurado = juratoGet;
            }
        }
        return Optional.ofNullable(jurado)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /jurado/:id  remover jurado pelo id.
     *
     * @param id do jurado para remover
     * @return status 200 (OK)
     */
    @RequestMapping(value = "/jurado/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteJurado(@PathVariable Long id) {
        log.debug("API_RES remove o Jurado pelo id : {}", id);
        Iterator<Jurado> juradoIn = jurados.iterator();
        while (juradoIn.hasNext()) {
            Jurado jura = juradoIn.next();
            if (jura.getId() == id) {
                juradoIn.remove();
            }
        }
        return ResponseEntity.ok().build();
    }

}
